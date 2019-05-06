package fr.hoc.dap.config;

/**
 * Cette classe regroupe les différentes configurations de notre dap, utilise le principe zeroconf.
 * @author house Mathieu et Antoine.
 */
public class Config {

    /*
     * --- DEFINITION DE CONSTANTES ---.
     */

    /** Emplacement du dossier d'authentification de l'application. */
    protected static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "/dap/tokens";

    /** Emplacement du fichier. */
    protected static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + "/dap/credentials_web.json";

    /** Le nom affiché de l'application de la popin (popup mais à l'intérieur d'une appli) Google. */
    protected static final String APPLICATION_NAME = "Data Access Project";

    /** Gestion multi utilisateur, URL de réception des tokens. */
    protected static final String AUTHCALLBACKURL = "/oAuth2Callback";

    /*
     * --- DEFINITION DE VARIABLES ---.
     */

    /** String applicationNAme. */
    private String applicationName;
    /** String credentialsFilePath. */
    private String credentialsFilePath;
    /** String tokensDirectoryPath. */
    private String tokensDirectoryPath;
    /** String oAuth2CallbackUrl. */
    private String oAuth2CallbackUrl;

    /** constructeur par défaut préremplie les variables Config. */
    public Config() {
        init();
    }

    /** "Fill with default values". */
    private void init() {
        applicationName = APPLICATION_NAME;
        credentialsFilePath = CREDENTIALS_FILE_PATH;
        tokensDirectoryPath = TOKENS_DIRECTORY_PATH;
        oAuth2CallbackUrl = AUTHCALLBACKURL;
    }

    /** @return the applicationName qui correspond au nom du projet, défini dans le programme. */
    public String getApplicationName() {
        return applicationName;
    }

    /** @param newApplicationName the applicationName to set. */
    public void setApplicationName(final String newApplicationName) {
        this.applicationName = newApplicationName;
    }

    /** @return the credentialsFileName */
    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }

    /** @param newCredentialsFilePath the credentialsFilePath to set */
    public void setCredentialsFilepath(final String newCredentialsFilePath) {
        this.credentialsFilePath = newCredentialsFilePath;
    }

    /** @return the tokensDirectoryPath */
    public String getTokensDirectoryPath() {
        return tokensDirectoryPath;
    }

    /** @param newTokensDirectoryPath the tokensDirectoryPath to set */
    public void setTokensDirectoryPath(final String newTokensDirectoryPath) {
        this.tokensDirectoryPath = newTokensDirectoryPath;
    }

    /** @return the auth2CallbackUrl */
    public String getoAuth2CallbackUrl() {
        return oAuth2CallbackUrl;
    }

    /** @param newoAuth2CallbackUrl the auth2CallbackUrl to set */
    public void setoAuth2CallbackUrl(final String newoAuth2CallbackUrl) {
        oAuth2CallbackUrl = newoAuth2CallbackUrl;
    }
}
