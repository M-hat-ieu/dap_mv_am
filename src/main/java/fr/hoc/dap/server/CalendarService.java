package fr.hoc.dap.server;

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
 * .
 * @author house
 *
 */
/**
 * Cette classe regroupe le moyen d'extraire les infos des contacts (API people Google).
 */
@Service
public final class CalendarService extends GoogleService {
    /**
     * insatncie calendar service en singleton.
     */
    //    private static CalendarService instance = null;

    /**
     * CalendarService ici représente le constructeur de sa propre classe.
     */
    //    private CalendarService() {
    //
    //    }
    //
    //    /**
    //     * .
    //     * @return .
    //     */
    //    public static synchronized CalendarService getInstance() {
    //        if (instance == null) {
    //            instance = new CalendarService();
    //        }
    //        return instance;
    //
    //    }

    /**
     * @param userKey .
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException class is a generic security exception class.
     * @return service.
     */
    public Calendar getService(final String userKey) throws GeneralSecurityException, IOException {
        Calendar service = null;
        NetHttpTransport httpTransport;
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(userKey))
                .setApplicationName(getMaConf().getApplicationName()).build();
        return service;
    }

    /**
     * @param nb .
     * @param userKey .
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException class is a generic security exception class.
     * @return le prochain event de l'utilisateur.
     */
    public List<Event> calendar(final Integer nb, final String userKey) throws IOException, GeneralSecurityException {

        // Build a new authorized API client service.

        // List the next 10 events from the primary calendar.event
        DateTime now = new DateTime(System.currentTimeMillis());

        Events events = getService(userKey).events().list("primary").setMaxResults(nb).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();

        List<Event> items = events.getItems();

        return items;
    }

    /**
     * Text representation of the NEXT event.
     * @param userKey .
     * @return  le prochain event de l'utilisateur.
     * @throws IOException IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException GeneralSecurityException class is a generic security exception class.
     */
    public String retrieveNextEvent(final String userKey) throws IOException, GeneralSecurityException {

        String resultat = null;
        List<Event> items = calendar(1, userKey);

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
