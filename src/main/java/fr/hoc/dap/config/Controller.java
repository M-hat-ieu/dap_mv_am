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
//TODO mv&am by Djer |POO| Nom de classe un peu (trop) générique "DapController" ou "DapApiController" serait mieux
@RestController
public class Controller {

    /**log.*/
    private static final Logger LOG = LogManager.getLogger();

    /**Calendar svc. */
    @Autowired
    private CalendarService cldService;

    /**
     * TODO mv&am by Djer |JavaDoc| Il manque la première ligne de la JavaDoc (la "description")
     */
    /**
     * @param nb d'event à afficher..
     * @param userKey fait appel au nom de compte actif. //TODO mv&am by Djer |JavaDoc| Pas d'apel ici, "nom du comte" est suffisant
     * @return String. //TODO mv&am by Djer |JavaDoc| Ca n'est pas VRAI !!
     * @throws GeneralSecurityException class is a generic security exception class.
     * @throws IOException If the credentials.json file cannot be found.
     */
    @RequestMapping("/event/next")
    public List<Event> nextEvent(@RequestParam(value = "nb", defaultValue = "1") final Integer nb,
            @RequestParam(value = "userKey") final String userKey) throws IOException, GeneralSecurityException {
        //TODO mv&am by Djer |Log4J| Cette méthode pourrait ête appeler sans passer par cette URL (dans "Application.java" par exemple). Cette log risque de donner une FAUSSE information dans ce cas (certes assez spécial)
        LOG.info("URL : /event/next?userkey=" + userKey + " call");
        return cldService.calendar(nb, userKey);
    }

    /**
     *@param nb d'event à afficher.
     * @param userKey fait appel au nom de compte actif.
      * @return String. //TODO mv&am by Djer |JavaDoc| "Textual representation of next(s) event(s)" serait mieux
      * @throws GeneralSecurityException class is a generic security exception class.
      * @throws IOException If the credentials.json file cannot be found.
     */
    @RequestMapping("/event/nextString")
    public String nextEventString(@RequestParam(value = "nb", defaultValue = "1") final Integer nb,
            @RequestParam(value = "userKey") final String userKey) throws IOException, GeneralSecurityException {
        LOG.info("URL : /event/nextString?userkey=" + userKey + " call");
        return cldService.retrieveNextEvent(userKey);
    }

    //TODO mv&am by Djer |Java| Les attributs se trouvent normalement au debut de la classe.
    /**
     * constructor. //TODO mv&am by Djer |JavaDoc| NON ! c'est un attribut qui contient un service.
     */
    @Autowired
    private GmailService gmService;

    /**
     * //TODO mv&am by Djer |JavaDoc| Il manque la "description"
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
