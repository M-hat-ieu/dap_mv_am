package fr.hoc.dap.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/**
 * Cette classe regroupe plussieurs éléments de l'API GMAIL.
 * @author house Mathieu et Antoine.
 */
@Service
public final class GmailService extends GoogleService {

    /**
     * Build a new authorized API client service.
     * service An accessor for creating requests from the Users collection.
     * @param userKey = nom du compte actif.
     * @return variable de Gmail.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException class is a generic security exception class.
     */

    private Gmail buildService(final String userKey) throws GeneralSecurityException, IOException {
        Gmail service = null;
        NetHttpTransport httpTransport;
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(userKey))
                .setApplicationName(getMaConf().getApplicationName()).build();
        return service;
    }

    /**
     * Print the labels in the user's account.
     * @param userKey = nom du compte actif.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException class is a generic security exception class.
     * @return le nombre de messages non lu de l'utilisateurs.
     */
    public Integer mail(final String userKey) throws IOException, GeneralSecurityException {

        String userId = "me";
        String query = "is:unread in:inbox";

        List<Message> messageNonLu = listMessagesMatchingQuery(userId, query, userKey);

        return messageNonLu.size();
    }

    /**
     * Supports the same query format as theGmail search box,
     * For example, "from:someuser@example.com rfc822msgid: is:unread",
     * Parameter cannot be used when accessing the api using the gmail.metadata scope.
     * @throws IOException If the credentials.json file cannot be found.
     * @param userId  The user's email address. The special value me can be used to indicate the authenticated user.
     * [default: me].
     * @param query Only return messages matching the specified query,
     * @param userKey fait appel au nom de compte actif.
     * @return An authorized.
     * @throws GeneralSecurityException class is a generic security exception class.
     */
    public List<Message> listMessagesMatchingQuery(final String userId, final String query, final String userKey)
            throws IOException, GeneralSecurityException {
        ListMessagesResponse response = buildService(userKey).users().messages().list(userId).setQ(query).execute();

        List<Message> messages = new ArrayList<Message>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = buildService(userKey).users().messages().list(userId).setQ(query).setPageToken(pageToken)
                        .execute();
            } else {
                break;
            }
        }

        System.out.println("vous avez  " + messages.size() + " nouveau(x) message(s)");

        return messages;
    }

}
