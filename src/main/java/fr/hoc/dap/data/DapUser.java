package fr.hoc.dap.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Contient tous les attributs pour gérer un utilisateurs DaP.
 * @author house Mathieu et Antoine.
 */

@Entity
public class DapUser {

    /** Dap User ID. */
    @Id
    @GeneratedValue
    private Long id;
    /** Dap UserKey (to store in Google Credential. */
    @Column(nullable = false, unique = true)
    private String userKey;
    /** Dap User Login Name. */
    private String loginName;;

    /** @return the id */
    public synchronized Long getId() {
        return id;
    }

    /** @param newId the id to set*/
    public synchronized void setId(final Long newId) {
        this.id = newId;
    }

    /** @return the userKey */
    public synchronized String getUserKey() {
        return userKey;
    }

    /** @param newUserKey the userKey to set */
    public synchronized void setUserKey(final String newUserKey) {
        this.userKey = newUserKey;
    }

    /** @return the loginName */
    public synchronized String getLoginName() {
        return loginName;
    }

    /** @param newLoginName the loginName to set */
    public synchronized void setLoginName(final String newLoginName) {
        this.loginName = newLoginName;
    }

}
