/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json.Servlets;

import Json.Builders.ResourceJsonBuilder;
import Json.Builders.UserJsonBuilder;
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
import javax.json.Json;
import javax.json.JsonArrayBuilder;
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
import session.UsersResourcesFacade;
import util.MakeHash;

/**
 *
 * @author pupil
 */
@WebServlet(name = "JsonResourceController", urlPatterns = {
    "/createResourceJson",
    "/createUserJson",
    "/getListResourcesJson"
})
public class JsonResourceController extends HttpServlet {

    @EJB
    ResourceFacade resourceFacade;
    @EJB
    UsersFacade usersFacade;
    @EJB
    UserRolesFacade userRolesFacade;
    @EJB
    RoleFacade roleFacade;
    @EJB
    UsersResourcesFacade usersResourcesFacade;
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
        JsonArrayBuilder jab = Json.createArrayBuilder();
        String json = "";
        String path = request.getServletPath();
        switch (path) {
            case "/createResourceJson":
                JsonObject jsonObject = jsonReader.readObject();
                String inputName = jsonObject.getString("inputName");
                String inputUrl = jsonObject.getString("inputUrl");
                String inputLogin = jsonObject.getString("inputLogin");
                String inputPassword = jsonObject.getString("inputPassword");
                if(inputName == null || inputName.isEmpty() || inputUrl == null || inputUrl.isEmpty() || inputLogin == null || inputLogin.isEmpty() || inputPassword == null || inputPassword.isEmpty()){
                    job.add("info", "Заполните все поля");
                    json = job.build().toString();
                    break;
                }
                Resource resource = new Resource(inputName,inputUrl,inputLogin,inputPassword);
                resourceFacade.create(resource);
                UsersResources usersResources = new UsersResources();
                usersResources.setResource(resource);
                HttpSession session = request.getSession(false);
                Users users = (Users) session.getAttribute("users");
                usersResources.setUsers(users);
                Calendar c = new GregorianCalendar();
                usersResources.setDate(c.getTime());
                usersResourcesFacade.create(usersResources);
                
                job.add("info", "Ресурс Успешно добавлен");
                ResourceJsonBuilder resourceJsonBuilder = new ResourceJsonBuilder();
                job.add("data",resourceJsonBuilder.createJsonResource(resource));
                json = job.build().toString();
                break;
            case "/createUserJson":
                 jsonObject = jsonReader.readObject();
                 inputLogin = jsonObject.getString("inputLogin");
                 inputPassword = jsonObject.getString("inputPassword");
                 if(inputLogin == null || inputLogin.isEmpty() || inputPassword == null || inputPassword.isEmpty()){
                    job.add("info", "Заполните все поля");
                    json = job.build().toString();
                    break;
                }
                
                MakeHash makeHash = new MakeHash();
                String salts = makeHash.CreateSalts();
                String encodingPassword = makeHash.createHash(inputPassword, salts);
                users = new Users(inputLogin, encodingPassword, salts);
                usersFacade.create(users);
                Role role = roleFacade.getRole("USER");
                UserRoles userRoles = new UserRoles(users,role);
                userRolesFacade.create(userRoles);
                
                job.add("info", "Пользователь Успешно добавлен");
                UserJsonBuilder ujb = new UserJsonBuilder();
                job.add("data",ujb.createJsonUser(users));
                json = job.build().toString();
                break;
            case "/getListResourcesJson":
                jsonObject = jsonReader.readObject();
                int id = jsonObject.getInt("id");
                String JSESSIONID = jsonObject.getString("JSESSIONID");
                session = request.getSession(false);
                if(!JSESSIONID.equals(session.getId())){
                    job.add("info", "Вам следует залогиниться");
                    json = job.build().toString();
                    //json = "{\"info\":\"Заполните все поля\"}";
                    break;
                }
                users = (Users) session.getAttribute("users");
                if(users == null){
                    job.add("info", "Вам следует залогиниться");
                    json = job.build().toString();
                    //json = "{\"info\":\"Заполните все поля\"}";
                    break;
                }
                
                if(id != users.getId()){
                    job.add("info", "Вам следует залогиниться");
                    json = job.build().toString();
                    //json = "{\"info\":\"Заполните все поля\"}";
                    break;
                }
                List<Resource> listResourcesForUser = resourceFacade.findByUser(users);
                ResourceJsonBuilder rjb = new ResourceJsonBuilder();
                
                for (int i = 0; i < listResourcesForUser.size(); i++) {
                    resource = listResourcesForUser.get(i);
                    jab.add(rjb.createJsonResource(resource));
                }
                json = job.add("listResources", jab.build()).build().toString();
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
