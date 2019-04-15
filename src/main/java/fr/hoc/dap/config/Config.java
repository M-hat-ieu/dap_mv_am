package fr.hoc.dap.config;

/**
 *
 * @author house Mathieu et Antoine.
 *
 */
public class Config {

    //TODO mv&am by Djer |JavaDoc| Evite de préiciser "constante" dans la JavaDoc, on voit deja que c'est une cosntante (static final)
    /**
     * Emplacement du dossier d'authentification de l'application. CONSTANTE
     */
    protected static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "/dap/tokens";
    /**
     * Emplacement du fichier. CONSTANTE
     */
    protected static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + "/dap/credentials_web.json";
    /**
     * Le nom affiché de l'application de la popin (popup mais à l'intérieur d'une appli) Google. CONSTANTE
     */
    protected static final String APPLICATION_NAME = "Data Access Project";
    /**
     * CONSTANTE gestion multi utilisateur, URL de réception des tokens.
     */
    protected static final String AUTHCALLBACKURL = "/oAuth2Callback";

    //TODO mv&am by Djer |JavaDoc| Evite de préiciser "variable" dans la JavaDoc, on voit deja que c'est une variable
    /**
     * Variable applicationNAme.
     */
    private String applicationName;
    /**
     * Variable credentialsFilePath.
     */
    private String credentialsFilePath;
    /**
     * Variable tokensDirectoryPath.
     */
    private String tokensDirectoryPath;

    /**
     * Variable oAuth2CallbackUrl.
     */
    private String oAuth2CallbackUrl;

    /**
     *  intialisation du constrcteur Config.
     */
    public Config() {
        init();
    }

  //TODO mv&am by Djer |JavaDoc| "Fill with default values" serait mieux
    /**
     *  variable init.
     */
    private void init() {
        applicationName = APPLICATION_NAME;
        credentialsFilePath = CREDENTIALS_FILE_PATH;
        tokensDirectoryPath = TOKENS_DIRECTORY_PATH;
        oAuth2CallbackUrl = AUTHCALLBACKURL;
    }

    /**
     * @return the applicationName qui correspond au nom du projet, défini dans le programme.
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param newApplicationName the applicationName to set.
     */
    public void setApplicationName(final String newApplicationName) {
        this.applicationName = newApplicationName;
    }

    /**
     * @return the credentialsFileName
     */
    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }

    /**
     * @param newCredentialsFilePath the credentialsFilePath to set
     */
    public void setCredentialsFilepath(final String newCredentialsFilePath) {
        this.credentialsFilePath = newCredentialsFilePath;
    }

    /**
     * @return the tokensDirectoryPath
     */
    public String getTokensDirectoryPath() {
        return tokensDirectoryPath;
    }

    /**
     * @param newTokensDirectoryPath the tokensDirectoryPath to set
     */
    public void setTokensDirectoryPath(final String newTokensDirectoryPath) {
        this.tokensDirectoryPath = newTokensDirectoryPath;
    }

    /**
     * @return the auth2CallbackUrl
     */
    public String getoAuth2CallbackUrl() {
        return oAuth2CallbackUrl;
    }

    /**
     * @param newoAuth2CallbackUrl the auth2CallbackUrl to set
     */
    public void setoAuth2CallbackUrl(final String newoAuth2CallbackUrl) {
        oAuth2CallbackUrl = newoAuth2CallbackUrl;
    }
}
