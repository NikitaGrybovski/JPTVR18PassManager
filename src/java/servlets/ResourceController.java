/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Resource;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ResourceFacade;
import session.UsersFacade;

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
    "/showUpdateResource",
     "/showFormAddUsers",
     "/createUsers"
    

})
public class ResourceController extends HttpServlet {
    @EJB
    private ResourceFacade resourceFacade;
    private UsersFacade usersFacade;
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
        String path = request.getServletPath();
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
                request.setAttribute("info", "Ресурс создан : "+resource.getName()+" / "+resource.getUrl());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/listResources":
                resourceFacade.findAll();
                
                break;
            case "/deleteResource":
                
                break;
            case "/showEditResource":
                
                break;
            case "/showUpdateResource":
                
                break;
            case "/showFormAddUsers":
                request.getRequestDispatcher("/showFormAddUsers.jsp").forward(request, response);
                break;
            case "/createUsers":
                String userlogin = request.getParameter("userlogin");
                String userpassword = request.getParameter("userpassword");
                String phone = request.getParameter("phone");
                String mail = request.getParameter("mail");
                
                
                Users users = new Users(userlogin, userpassword, phone, mail);
                usersFacade.create(users);
                request.setAttribute("info", "Пользователь : "+users.getUserlogin());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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
