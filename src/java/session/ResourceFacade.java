/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Resource;
import entity.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class ResourceFacade extends AbstractFacade<Resource> {

    @PersistenceContext(unitName = "JPTVR18PassManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResourceFacade() {
        super(Resource.class);
    }

    

    public List<Resource> findByUser(Users users) {
        try{
            return em.createQuery("SELECT ur.resource FROM UsersResources ur WHERE ur.users = :users")
                    .setParameter("users", users)
                    .getResultList();
        } catch(Exception e){
            return null;
        }
    }
    
}
