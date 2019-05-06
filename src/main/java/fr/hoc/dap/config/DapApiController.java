package fr.hoc.dap.config;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.calendar.model.Event;

import fr.hoc.dap.service.CalendarService;
import fr.hoc.dap.service.GmailService;

/**
 * Classe controller afin de recevoir des demandes des utilisateurs
 * (ici des requêtes URLs) et proposer une réponse (en collectant les données via des Services,
 * et en collectant les données dans un Model).
 * @author house Mathieu et Antoine.
 */

@RestController
public class DapApiController {

    /** log.*/
    private static final Logger LOG = LogManager.getLogger();

    /** Calendar svc. */
    @Autowired
    private CalendarService cldService;

    /** Gmail svc. */
    @Autowired
    private GmailService gmService;

    /**
     * configure publish the next event for the user.
     * @param nb d'event à afficher.
     * @param userKey "nom du comte".
     * @return "List representation of next(s) event(s) (form most explain)".
     * @throws GeneralSecurityException class is a generic security exception class.
     * @throws IOException If the credentials.json file cannot be found.
     */
    @RequestMapping("/event/next")
    public List<Event> nextEvent(@RequestParam(value = "nb", defaultValue = "1") final Integer nb,
            @RequestParam(value = "userKey") final String userKey) throws IOException, GeneralSecurityException {
        LOG.info("searching for Next Event for users most explain" + userKey);
        return cldService.configItemNextEvent(nb, userKey);
    }

    /**
      * configure publish the next event for the user.
      * @param nb d'event à afficher.
      * @param userKey "nom du comte".
      * @return "Textual representation of next(s) event(s)".
      * @throws GeneralSecurityException class is a generic security exception class.
      * @throws IOException If the credentials.json file cannot be found.
     */
    @RequestMapping("/event/nextString")
    public String nextEventString(@RequestParam(value = "nb", defaultValue = "1") final Integer nb,
            @RequestParam(value = "userKey") final String userKey) throws IOException, GeneralSecurityException {
        LOG.info("searching for Next Event for users " + userKey);
        return cldService.retrieveNextEvent(userKey);
    }

    /**
     * configure publish the email nb unread for the user.
     * @param userKey "nom du comte".
     * @return ce qui doit être afficher sur la page web ici le nbr de message non lu de l'utilisateur.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException class is a generic security exception class.
     */
    @RequestMapping("/email/nbunread")
    public Integer unreadEmail(@RequestParam(value = "userKey") final String userKey)
            throws IOException, GeneralSecurityException {
        LOG.info("searching for unread emails for users " + userKey);
        return gmService.mail(userKey);
    }
}
