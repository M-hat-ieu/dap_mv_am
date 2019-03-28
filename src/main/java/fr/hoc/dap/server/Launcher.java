//package fr.hoc.dap.server;
//
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
///**
// * Porte d'entrée de l'application.
// */
//public abstract class Launcher {
//    /**
//     * .
//     */
//    private static final Logger LOG = LogManager.getLogger();
//
//    /**
//     * @throws IOException If the credentials.json file cannot be found.
//     * @throws GeneralSecurityException IF the connection cannot be found.
//     * @param args parameter of your program.
//     */
//    public static void main(final String[] args) throws IOException, GeneralSecurityException {
//
//        LOG.info("pensez aux générations futures le monde");
//
//        //1- gere la conf
//
//        Config configuration = new Config();
//
//        //2- changer app name "mon appli"
//        configuration.setApplicationName("Bob");
//        configuration.setTokensDirectoryPath("Tokens");
//        configuration.setCredentialsFilepath("/credentials.json");
//
//        //3- passer la conf a calendrier et a messagerie
//        CalendarService calendarSvc = CalendarService.getInstance();
//
//        if (calendarSvc == null) {
//            System.out.println("errorr");
//            System.exit(-1);
//        }
//
//        calendarSvc.setMaConf(configuration);
//        calendarSvc.calendar();
//
//        GmailService mesmails = GmailService.getInstance();
//        mesmails.setMaConf(configuration);
//        mesmails.mail();
//
//        LOG.info("Donne le nom de l'application actuelle", configuration.getApplicationName());
//        System.out.println(configuration.getApplicationName());
//
//        LOG.info("Donne le nom du dossier de destination des fichiers d'autorisation d'accés actuelle",
//                configuration.getCredentialsFilePath());
//        System.out.println(configuration.getCredentialsFilePath());
//
//        LOG.info("Donne le nom actuel sous lequel les autorisations sont stockées",
//                configuration.getTokensDirectoryPath());
//        System.out.println(configuration.getTokensDirectoryPath());
//    }
//}
