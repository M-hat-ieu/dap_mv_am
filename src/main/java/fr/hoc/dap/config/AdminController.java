package fr.hoc.dap.config;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
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
* Classe controller afin de recevoir des demandes des utilisateurs
 * et proposer une réponse (en collectant les données via des Services,
 * et en collectant les données dans un Model).
 * @author house Mathieu et Antoine.
*/
@Controller
public class AdminController extends GoogleService {

    /** log. */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * declare flow and trigger user authorization request.
     * @param model "Le model MVC".
     * @return "Nom de la vue admin à afifcher".
     * @throws GeneralSecurityException If the credentials.json file cannot be found.
     * @throws IOException class is a generic security exception class.
     */
    @RequestMapping("/admin")
    public String retrieveUser(final ModelMap model) throws IOException, GeneralSecurityException {

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
