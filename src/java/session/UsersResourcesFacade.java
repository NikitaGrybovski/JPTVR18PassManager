/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Resource;
import entity.UsersResources;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class UsersResourcesFacade extends AbstractFacade<UsersResources> {

    @PersistenceContext(unitName = "JPTVR18PassManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersResourcesFacade() {
        super(UsersResources.class);
    }

    public void removeByResource(Resource deleteResource) {
        try {
            em.createQuery("DELETE FROM UsersResources ur WHERE ur.resource = :resource")
                    .setParameter("resource", deleteResource)
                    .executeUpdate();
            em.flush();
        } catch (Exception e) {
            
        }
    }
    
}
