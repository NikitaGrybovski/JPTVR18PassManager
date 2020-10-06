/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Role;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import session.ResourceFacade;
import session.RoleFacade;
import session.UserRolesFacade;
import session.UsersFacade;
import session.UsersResourcesFacade;
import util.MakeHash;

/**
 *
 * @author pupil
 */
@WebServlet(name = "AdminController", urlPatterns = {
    "/showListUsers",
    "/editUserRoles",
    "/updateUserRoles",
    
})
public class AdminController extends HttpServlet {
    
    @EJB
    private UsersFacade usersFacade;

    @EJB
    private UserRolesFacade userRolesFacade;
    
    @EJB
    private RoleFacade roleFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       request.setCharacterEncoding("UTF-8");
       
        HttpSession session = request.getSession(false);
        
        
        
        Users users = (Users) session.getAttribute("users");
        //UserManager userManager = new UserManager();
        if(!userRolesFacade.checkRole(users,"ADMIN")){
            request.setAttribute("info", "У вас нет права");
            request.getRequestDispatcher("/showFormLogin").forward(request, response);
        }
        String path = request.getServletPath();
        switch (path) {
            case "/showListUsers":
                Map<Users,String> usersMap = new HashMap<>();
                List<Users> listUsers = usersFacade.findAll() ;
                for (int i = 0; i < listUsers.size(); i++) {
                    Users  usersForMap = listUsers.get(i);
                    String topRoleUsers = userRolesFacade.getTopRoleName(usersForMap);
                    usersMap.put(usersForMap, topRoleUsers);
                }
                
               
                request.setAttribute("usersMap", usersMap);
                
                request.getRequestDispatcher("/admin/showListUsers.jsp").forward(request, response);
                break;
            case "/editUserRoles":
                String userId = request.getParameter("userId");
                Users editUsers = usersFacade.find(Long.parseLong(userId));
                if(editUsers == null){
                    request.setAttribute("info", "Не найден пользователь");
                    request.getRequestDispatcher("/admin/showListUsers.jsp").forward(request, response);
                }
                request.setAttribute("editUser", editUsers);
                List<Role> listRoles = roleFacade.findAll();
                request.setAttribute("listRoles",listRoles);
                String topRoleEditUser = userRolesFacade.getTopRoleName(editUsers);
                request.setAttribute("topRoleEditUser", topRoleEditUser);
                request.getRequestDispatcher("/admin/editUserRolesForm.jsp").forward(request, response);
                break;
            case "/updateUserRoles":
                userId = request.getParameter("userId");
                String userlogin = request.getParameter("userlogin");
                String userpassword = request.getParameter("userpassword");
                String newRole = request.getParameter("newRole");
                if(userId == null){
                    request.setAttribute("info", "Не найден пользователь");
                    request.getRequestDispatcher("/admin/showListUsers.jsp").forward(request, response);
                }
                Users updateUser = usersFacade.find(Long.parseLong(userId));
                userRolesFacade.deleteAllUserRoles(updateUser);
                userRolesFacade.setNewRoleToUser(newRole,updateUser);
                
                updateUser.setUserlogin(userlogin);
                if(userpassword != null){
                    MakeHash mh = new MakeHash();
                    String salts = mh.CreateSalts();
                    userpassword = mh.createHash(userpassword, salts);
                    updateUser.setUserpassword(userpassword);
                }
                usersFacade.edit(updateUser);
                request.setAttribute("info", "Данные пользователя - "+updateUser.getUserlogin()+" изменены");
                request.getRequestDispatcher("/admin/showListUsers.jsp").forward(request, response);
                break;
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
