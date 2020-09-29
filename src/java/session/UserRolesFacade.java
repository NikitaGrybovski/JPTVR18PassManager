/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.UserRoles;
import entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> {

    @PersistenceContext(unitName = "JPTVR18PassManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRolesFacade() {
        super(UserRoles.class);
    }

    public boolean checkRole(Users users, String roleName) {
        try {
           em.createQuery("SELECT ur FROM UserRoles ur WHERE ur.role = :role AND ur.users = :users")
                   .setParameter("role", roleName)
                   .setParameter("users", users)
                   .getSingleResult();
                   return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
