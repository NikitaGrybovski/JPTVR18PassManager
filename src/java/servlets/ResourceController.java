/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import util.UserManager;
import util.MakeHash;
import entity.Resource;
import entity.Role;
import entity.UserRoles;
import entity.Users;
import entity.UsersResources;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.ResourceFacade;
import session.RoleFacade;
import session.UserRolesFacade;
import session.UsersFacade;
import session.UsersResourcesFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "ResourceController", urlPatterns = {
    "/showFormAddResource",
    "/createResource",
    "/listResources",
    "/deleteResource",
    "/showEditResource",
    "/updateResource",
     "/showResource",
    

})
public class ResourceController extends HttpServlet {
    @EJB
    private ResourceFacade resourceFacade;
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private UsersResourcesFacade usersResourcesFacade;
    @EJB 
    private RoleFacade roleFacade;
    @EJB
    private UserRolesFacade userRolesFacade;
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
        String path = request.getServletPath();
        HttpSession session = request.getSession(false);
        
        
        
        Users users = (Users) session.getAttribute("users");
        //UserManager userManager = new UserManager();
        if(!userRolesFacade.checkRole(users,"USER")){
            request.setAttribute("info", "У вас нет права");
            request.getRequestDispatcher("/showFormLogin").forward(request, response);
        }
        
       
        switch (path) {
            case "/showFormAddResource":
            request.getRequestDispatcher("/showFormAddResource.jsp").forward(request, response);
            break;
            
            case "/createResource":
                String name = request.getParameter("name");
                String url = request.getParameter("url");
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                Resource resource = new Resource(name, url, login, password);
                
                resourceFacade.create(resource);
                
                Calendar c = new GregorianCalendar();
                UsersResources usersResources = new UsersResources(users, resource, c.getTime());
                usersResourcesFacade.create(usersResources);
                
                request.setAttribute("info", "Ресурс создан : "+resource.getName()+" / "+resource.getUrl());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listResources":
            List<Resource> listResources = resourceFacade.findByUser(users);
            request.setAttribute("listResources",listResources);
            request.getRequestDispatcher("/listResources.jsp") .forward(request, response);
                
                
                
                break;
            case "/showResource":
                String id = request.getParameter("idRecource");
                resource = resourceFacade.find(Long.parseLong(id));
                request.setAttribute("resource", resource);
                request.getRequestDispatcher("/showResource.jsp")
                        .forward(request, response);
                break;
            case "/deleteResource":
                id = request.getParameter("id");
                if(id == null || "".equals(id)){
                    request.setAttribute("info","Нет такого ресурса");
                    request.getRequestDispatcher("/listResources.jsp") .forward(request, response);
                
                    break;
                
                }
                Resource deleteResource = resourceFacade.find(Long.parseLong(id));
                listResources = resourceFacade.findByUser(users);
                if(!listResources.contains(deleteResource)){
                    request.setAttribute("info","Нет такого ресурса");
                    request.getRequestDispatcher("/listResources.jsp") .forward(request, response);
                
                    break;
                }
                usersResourcesFacade.removeByResource(deleteResource);
                resourceFacade.remove(deleteResource);
                request.setAttribute("info", "ресурс удален");
                request.getRequestDispatcher("/listResources.jsp") .forward(request, response);
                
                
                break;
            case "/showEditResource":
                request.getRequestDispatcher("/showFormEditResource.jsp").forward(request, response);
                break;
            case "/updateResource":
                id = request.getParameter("idResource");
                resource = resourceFacade.find(Long.parseLong(id));
                name = request.getParameter("name");
                url = request.getParameter("url");
                login = request.getParameter("login");
                password = request.getParameter("password");
                resource.setLogin(name);
                resource.setLogin(url);
                resource.setLogin(login);
                resource.setLogin(password);
                resourceFacade.edit(resource);
                request.setAttribute("resource", resource);
                request.getRequestDispatcher("/pages/listResources.jsp").forward(request,response);
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
