package fr.hoc.dap.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.hoc.dap.service.GoogleService;

/**
* @author house
*/
@Controller
public class HelloController {

    /**
     * @return "hello".
     */
    @RequestMapping("/hello")

    /**
     * .
     * @param model nom du ModelMap.
     * @return "hello".
     */
    public String hello(final ModelMap model) {
        model.addAttribute("maVar", "toto");

        List<String> bestioles = new ArrayList<>();
        bestioles.add("chien");
        bestioles.add("zebre");
        bestioles.add("tamanoir");
        bestioles.add("cacatoes");
        bestioles.add("cacatoes2");

        model.addAttribute("bebetes", bestioles);

        return "hello";
    }

    /**
     * .
     * @param model est le nom du parametre ModelMap.
     * @return "admin".
     */
    @RequestMapping("/admin")
    public String admin(final ModelMap model) {

        DataStore<StoredCredential> users = GoogleService.getFlow();

        Map<String, StoredCredential> usersMap = new HashMap<>();

        Set<String> allKeys = users.keySet();

        for (String akey : allKeys) {
            StoredCredential value = users.get(akey);
            usersMap.put(akey, value);
        }

        model.addAttribute("allUsers", usersMap);

        return "admin";

    }
}
