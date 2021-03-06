/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "JPTVR18PassManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users fingByLogin(String login){
        try {
            return (Users) em.createQuery("SELECT u FROM Users u WHERE u.userlogin = :login")
                .setParameter("login", login)
                .getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }
}
