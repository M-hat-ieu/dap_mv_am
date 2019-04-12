package fr.hoc.dap.config;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class HelloController extends GoogleService {

    /** log. */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * @param model .
     * @return String.
     */
    @RequestMapping("/hello2")
    public String hello(final ModelMap model) {
        String userName = "Leo";

        model.addAttribute("uName", userName);

        List<String> betes = new ArrayList<String>();

        betes.add("autruche");
        betes.add("très mechant chien");

        model.addAttribute("betes", betes);

        return "hello";
    }

    /**
     * @param model est le nom du parametre ModelMap.
     * @return "admin".
     * @throws GeneralSecurityException If the credentials.json file cannot be found.
     * @throws IOException class is a generic security exception class.
     */
    @RequestMapping("/admin")
    public String admin(final ModelMap model) throws IOException, GeneralSecurityException {

        DataStore<StoredCredential> users = getFlow().getCredentialDataStore();

        Map<String, StoredCredential> usersMap = new HashMap<>();

        Set<String> allKeys = users.keySet();

        for (String akey : allKeys) {
            StoredCredential value = users.get(akey);
            usersMap.put(akey, value);
        }

        model.addAttribute("allUsers", usersMap);

        LOG.info(usersMap);
        return "admin";

    }
}
