/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json.Servlets;

import Json.Builders.ResourceJsonBuilder;
import entity.Resource;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import session.ResourceFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "JsonResourceController", urlPatterns = {
    "/createResourceJson"
})
public class JsonResourceController extends HttpServlet {

    @EJB
    ResourceFacade resourceFacade;
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
            case "/createResourceJson":
                JsonObject jsonObject = jsonReader.readObject();
                String name = jsonObject.getString("inputName");
                String url = jsonObject.getString("inputUrl");
                String login = jsonObject.getString("inputLogin");
                String password = jsonObject.getString("inputPassword");
                if(name == null || name.isEmpty() || url == null || url.isEmpty() || login == null || login.isEmpty() || password == null || password.isEmpty()){
                    job.add("info", "Заполните все поля");
                    json = job.build().toString();
                    break;
                }
                Resource resource = new Resource(name, url, login, password);
                resourceFacade.create(resource);
                job.add("info", "Ресурс Успешно добавлен");
                ResourceJsonBuilder resourceJsonBuilder = new ResourceJsonBuilder();
                job.add("data","");
                json = job.build().toString();
                break;
            
        }
        if("".equals(json)){
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
