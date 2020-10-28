/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json.Servlets;

import Json.Builders.UserJsonBuilder;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
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
import util.MakeHash;

/**
 *
 * @author pupil
 */
@WebServlet(name = "JsonLoginController", urlPatterns = {
    "/loginUserJson",
    "/logoutUserJson"
    
    
})
public class JsonLoginController extends HttpServlet {
@EJB
ResourceFacade resourceFacade;
@EJB
UsersFacade usersFacade;
@EJB
UserRolesFacade userRolesFacade;
@EJB
RoleFacade roleFacade;

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
        JsonReader jsonReader = Json.createReader(request.getReader());
        JsonObjectBuilder job = Json.createObjectBuilder();
        String json = "";
        String path = request.getServletPath();
        switch (path) {
            case "/loginUserJson":
                JsonObject jsonObject = jsonReader.readObject();
                String login = jsonObject.getString("inputLogin");
                String password = jsonObject.getString("inputPassword");
                if(login == null || login.isEmpty()
                       || password == null || password.isEmpty()
                                ){
                    job.add("info", "Заполните все поля");
                    json = job.build().toString();
                    //json = "{\"info\":\"Заполните все поля\"}";
                    break;
                }
                Users users = usersFacade.fingByLogin(login);
                if(users == null){
                    job.add("info", "Нет такого пользователя или неправильный пароль");
                    json = job.build().toString();
                    break;
                }
                MakeHash mh = new MakeHash();
                password = mh.createHash(password, users.getSalts());
                if(!password.equals(users.getUserpassword())){
                    job.add("info", "Нет такого пользователя или неправильный пароль");
                    json = job.build().toString();
                    break;
                }
                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("users", users);
                String JSESSIONID = httpSession.getId();
                String roleUser = userRolesFacade.getTopRoleName(users);
                UserJsonBuilder ujb = new UserJsonBuilder();
                job.add("data", ujb.createJsonUser(users, JSESSIONID, roleUser));
                json = job.build().toString();
                break;
            case "/logoutUserJson":
                httpSession = request.getSession(false);
                if(httpSession != null){
                    httpSession.invalidate();
                    job.add("info", "Вы вышли");
                    json = job.build().toString();
                }
                break;
        }
        if(!"".equals(json)){
            try(PrintWriter out = response.getWriter()){
                out.println(json);
                out.flush();
            }
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
