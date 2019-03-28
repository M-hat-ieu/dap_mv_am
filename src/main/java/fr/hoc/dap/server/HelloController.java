/**
*
 */
package fr.hoc.dap.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author house
 *
 */
@RestController
public class HelloController {
    /**
     * @return message d'entré sur le serveur local de Spring.
     */
    @RequestMapping("/hello")
    public String index() {

        return "Greetings from Spring Boot!";

    }

    /**
     *
     * @param theName méthode d'appel du paramétre.
     * @return l'affichage de la page web en localhost ici.
     */
    @RequestMapping("/bonjour")
    public String direBonjour(@RequestParam("name") final String theName) {

        return "Salut, pensez aux générations futures !!   " + theName;

    }
}
