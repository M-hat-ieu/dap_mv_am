package fr.hoc.dap.server;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

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

/**
 * LA classe Access sert aux autorisations d'accés pour notre application.
 */
public class GoogleService {
    /**
     *  .
     */
    @Autowired
    private Config maConf;

    /**
     * Le nom affiché de l'application de la popin (popup mais à l'intérieur d'une appli) Google.
     */
    //protected static final String APPLICATION_NAME = "Data Access Project";
    /**
     *Implementation is thread-safe, and sub-classes must be thread-safe.
     */
    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Dossier dans lequel les autorisations accordées par l'utiliateur seront sauvegardées.
     */
    //private static final String TOKENS_DIRECTORY_PATH = "tokens";
    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.ssss
     */
    private static List<String> scopes;
    /**
     * emplacement du fichier d'authentification de l'application.
     */
    //private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    /**
     * Le port local pour la reponse au consentement de l'utilisateur.
     *
     */
    //private static final Integer PORT = 8888;

    /**
     * .
     * @throws IOException .
     * @throws GeneralSecurityException .
     */
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
     * @return the config
     */
    /**
     * Creates an authorized Credential object.
     * @param userKey .
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException .
     */
    protected Credential getCredentials(final String userKey) throws IOException, GeneralSecurityException {

        AuthorizationCodeFlow flow = getFlow();

        //        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(PORT).build();
        //        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return flow.loadCredential(userKey);
    }

    /**
     *
     * @param maCnf .
     */
    public void setMaConf(final Config maCnf) {
        this.maConf = maCnf;
    }

    /**
     * @return the maConf
     */
    protected Config getMaConf() {
        return maConf;
    }

    /**
     *
     * @return .
     * @throws IOException .
     * @throws GeneralSecurityException .
     */
    public GoogleAuthorizationCodeFlow getFlow() throws GeneralSecurityException, IOException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        // Load client secrets.
        File file = new java.io.File(maConf.getCredentialsFilePath());
        Reader targetReader = new FileReader(file);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, targetReader);

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, scopes)
                        .setDataStoreFactory(
                                new FileDataStoreFactory(new java.io.File(maConf.getTokensDirectoryPath())))
                        .setAccessType("offline").build();
        return flow;
    }
}
