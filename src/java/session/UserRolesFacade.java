/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Role;
import entity.UserRoles;
import entity.Users;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> {

    @EJB
    private RoleFacade roleFacade;
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
           em.createQuery("SELECT ur FROM UserRoles ur WHERE ur.role.name = :roleName AND ur.users = :users")
                   .setParameter("roleName", roleName)
                   .setParameter("users", users)
                   .getSingleResult();
                   return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTopRoleName(Users users) {
        List<UserRoles> listUserRoles = em.createQuery("SELECT r FROM UserRoles r WHERE r.users = :users")
                .setParameter("users",users)
                .getResultList();
        for (UserRoles userRoles : listUserRoles) {
            if("ADMIN".equals(userRoles.getRole().getName())){
                return "ADMIN";
            }else if("USER".equals(userRoles.getRole().getName())){
                return "USER";
            }
            
        }
        return null;
    }

    public void deleteAllUserRoles(Users updateUser) {
        em.createQuery("DELETE FROM UserRoles ur WHERE ur.users = :updateUser")
                .setParameter("updateUser", updateUser)
                .executeUpdate();
    }

    public void setNewRoleToUser(Role newRole, Users updateUser) {
        UserRoles ur = new UserRoles();
        if(newRole.getName().equals("ADMIN")){
            ur.setUsers(updateUser);
            ur.setRole(newRole);
            this.create(ur);
            Role role = roleFacade.getRole("USER");
            ur = new UserRoles();
            ur.setUsers(updateUser);
            this.create(ur);
            
        }
        if(newRole.getName().equals("USER")){
            ur.setUsers(updateUser);
            ur.setRole(newRole);
            this.create(ur);
            
        }
    }
    
    
    
    
}
