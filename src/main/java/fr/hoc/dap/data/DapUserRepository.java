package fr.hoc.dap.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Gérer les accés aux DaP users en BDD.
 * @author house Mathieu et Antoine.
 */
public interface DapUserRepository extends CrudRepository<DapUser, Long> {
    /**
     * @param userKey DaP User Key.
     * @return USer KEy.
     */
    DapUser findByUserKey(String userKey);

}
