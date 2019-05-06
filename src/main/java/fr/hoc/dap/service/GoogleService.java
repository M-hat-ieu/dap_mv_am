package fr.hoc.dap.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.people.v1.PeopleServiceScopes;

import fr.hoc.dap.config.Config;

/**
 * La classe Access sert aux autorisations d'accés pour notre application.
 * @author house Mathieu et Antoine.
 */
public class GoogleService {
    /**
     * @Autowired Marks a constructor, field, setter method or config method as to be autowired by Spring's
     * dependency injection facilities.
     */
    @Autowired
    private Config maConf;

    /**log.*/
    private static final Logger LOG = LogManager.getLogger();

    /** Implementation is thread-safe, and sub-classes must be thread-safe. */
    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static List<String> scopes;

    /** scopes View and manage your data across Google Cloud Platform services. */
    public GoogleService() {
        scopes = new ArrayList<String>();
        scopes.add(GmailScopes.GMAIL_READONLY);
        scopes.add(GmailScopes.GMAIL_LABELS);
        scopes.add(CalendarScopes.CALENDAR_READONLY);
        scopes.add(PeopleServiceScopes.CONTACTS_READONLY);
        scopes.add(PeopleServiceScopes.USER_EMAILS_READ);
        scopes.add(PeopleServiceScopes.USERINFO_EMAIL);
        scopes.add(PeopleServiceScopes.USERINFO_PROFILE);
        scopes.add(PeopleServiceScopes.PLUS_LOGIN);
        scopes.add(PeopleServiceScopes.CONTACTS);
    }

    /**
     * Creates an authorized Credential object.
     * @param userKey = le nom du compte.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException class is a generic security exception class.
     */
    protected Credential getCredentials(final String userKey) throws GeneralSecurityException, IOException {

        AuthorizationCodeFlow flow = getFlow();

        return flow.loadCredential(userKey);
    }

    /**
     * Build flow and trigger user authorization request.
     * @return variable flow, Load client secrets.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException class is a generic security exception class.
     */
    public GoogleAuthorizationCodeFlow getFlow() throws GeneralSecurityException, IOException {

        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        File file = new java.io.File(maConf.getCredentialsFilePath());

        LOG.info("le client secret chargé" + file);

        if (!file.exists()) {

            LOG.error(" Credential file " + maConf.getCredentialsFilePath() + " doesn't exist !");
        }

        Reader targetReader = new FileReader(file);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, targetReader);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, scopes)
                        .setDataStoreFactory(
                                new FileDataStoreFactory(new java.io.File(maConf.getTokensDirectoryPath())))
                        .setAccessType("offline").build();
        return flow;
    }

    /** @param maCnf definie le setter de MaConf.*/
    public void setMaConf(final Config maCnf) {
        this.maConf = maCnf;
    }

    /** @return the maConf. */
    protected Config getMaConf() {
        return maConf;
    }
}
