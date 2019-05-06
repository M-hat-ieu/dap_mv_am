package fr.hoc.dap.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

/**
 * Cette classe regroupe le moyen d'extraire les infos du calendrier (API calendar Google).
 * @author house Mathieu et Antoine.
 */
@Service
public final class CalendarService extends GoogleService {

    /**
     * Build a new authorized API client service.
     * @param userKey "nom du comte".
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException class is a generic security exception class.
     * @return service.
     */
    private Calendar buildService(final String userKey) throws GeneralSecurityException, IOException {
        Calendar service = null;
        NetHttpTransport httpTransport;
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(userKey))
                .setApplicationName(getMaConf().getApplicationName()).build();
        return service;
    }

    /**
     * "List build the Next Event".
     * @param nb = variable du nombre d'événement à affciher.
     * @param userKey "nom du comte".
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException class is a generic security exception class.
     * @return le prochain event de l'utilisateur.
     */
    public List<Event> configItemNextEvent(final Integer nb, final String userKey)
            throws IOException, GeneralSecurityException {

        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = buildService(userKey).events().list("primary").setMaxResults(nb).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();

        List<Event> items = events.getItems();

        return items;
    }

    /**
     * "Text representation of the Next Event".
     * @param userKey fait appel au nom de compte actif.
     * @return le prochain event de l'utilisateur.
     * @throws IOException IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException GeneralSecurityException class is a generic security exception class.
     */
    public String retrieveNextEvent(final String userKey) throws IOException, GeneralSecurityException {

        String resultat = null;
        List<Event> items = configItemNextEvent(1, userKey);

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Prochain événement :");

            Event event = items.get(0);

            DateTime start = event.getStart().getDateTime();
            if (start == null) {
                start = event.getStart().getDate();
            }

            resultat = event.getSummary() + "(" + start + ")";
        }
        return resultat;
    }
}
