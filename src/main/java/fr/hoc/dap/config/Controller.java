/**
*
 */
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
 * @author house Mathieu et Antoine.
 *
 */
@RestController
public class Controller {

    /**log.*/
    private static final Logger LOG = LogManager.getLogger();

    /**Calendar svc. */
    @Autowired
    private CalendarService cldService;

    /**
    *@param nb d'event à afficher..
    * @param userKey fait appel au nom de compte actif.
     * @return String.
     * @throws GeneralSecurityException class is a generic security exception class.
     * @throws IOException If the credentials.json file cannot be found.
    */
    @RequestMapping("/event/next")
    public List<Event> nextEvent(@RequestParam(value = "nb", defaultValue = "1") final Integer nb,
            @RequestParam(value = "userKey") final String userKey) throws IOException, GeneralSecurityException {
        LOG.info("URL : /event/next?userkey=" + userKey + " call");
        return cldService.calendar(nb, userKey);
    }

    /**
     *@param nb d'event à afficher.
     * @param userKey fait appel au nom de compte actif.
      * @return String.
      * @throws GeneralSecurityException class is a generic security exception class.
      * @throws IOException If the credentials.json file cannot be found.
     */
    @RequestMapping("/event/nextString")
    public String nextEventString(@RequestParam(value = "nb", defaultValue = "1") final Integer nb,
            @RequestParam(value = "userKey") final String userKey) throws IOException, GeneralSecurityException {
        LOG.info("URL : /event/nextString?userkey=" + userKey + " call");
        return cldService.retrieveNextEvent(userKey);
    }

    /**
     * constructor.
     */
    @Autowired
    private GmailService gmService;

    /**
     * @param userKey fait appel au nom de compte actif.
     * @return ce qui doit être afficher sur la page web ici le nbr de message non lu de l'utilisateur.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException class is a generic security exception class.
     */
    @RequestMapping("/email/nbunread")
    public Integer unreadEmail(@RequestParam(value = "userKey") final String userKey)
            throws IOException, GeneralSecurityException {
        LOG.info("URL : /email/nbunread?userkey=" + userKey + " call");
        return gmService.mail(userKey);

    }
}
