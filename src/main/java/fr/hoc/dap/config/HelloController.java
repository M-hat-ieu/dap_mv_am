package fr.hoc.dap.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author house
*/
@Controller
public class HelloController {
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
}
//    /**
//     * .
//     * @param model est le nom du parametre ModelMap.
//     * @return "admin".
//     */
//    @RequestMapping("/admin")
//    public String admin(final ModelMap model) {
//
//        DataStore<StoredCredential> users = GoogleService.getFlow();
//
//        Map<String, StoredCredential> usersMap = new HashMap<>();
//
//        Set<String> allKeys = users.keySet();
//
//        for (String akey : allKeys) {
//            StoredCredential value = users.get(akey);
//            usersMap.put(akey, value);
//        }
//
//        model.addAttribute("allUsers", usersMap);
//
//        return "admin";
//

//flow.getCredentialDataStore();

//    }
//}
