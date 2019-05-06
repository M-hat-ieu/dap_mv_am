package fr.hoc.dap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.data.DapUser;
import fr.hoc.dap.data.DapUserRepository;

/**
 * Permet de faire des tests des entités JPA.
 * @author house Mathieu et Antoine.
 */
@RestController
public class TestJpaController {
    /**  */
    @Autowired
    private DapUserRepository dapUserRepo;

    /**
     * Créer un nouvelle utilisateur DaP avec un userKey.
     * Exemple d'apel : /test/createDapUser?loginName=Djer&useKey=DjerPerso.
     * @param loginName DaP User Key.
     * @param userKey DaP User Key.
     * @return le nom de l'utilisateur.
     */
    @RequestMapping("/test/createDapUser")
    public DapUser createDapUser(final String loginName, final String userKey) {
        DapUser nomUser = new DapUser();
        nomUser.setLoginName(loginName);
        nomUser.setUserKey(userKey);

        //TODO save user !
        DapUser savedUser = dapUserRepo.save(nomUser);

        return savedUser;
    }

    /**
     * Créer un nouvelle utilisateur DaP avec un userKey.
     * Exemple d'apel : /test/createDapUser?loginName=Djer&useKey=DjerPerso.
     * @param userKey DaP User Key.
     * @return le nom de l'utilisateur.
     */
    @RequestMapping("/test/loadDapUser")
    public DapUser loadDapUser(@RequestParam final String userKey) {

        //TODO save user !
        DapUser loadUser = dapUserRepo.findByUserKey(userKey);

        return loadUser;
    }
}
