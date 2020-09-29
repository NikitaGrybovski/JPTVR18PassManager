package servlets;

import entity.Role;
import entity.UserRoles;
import util.MakeHash;
import entity.Users;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.RoleFacade;
import session.UserRolesFacade;
import session.UsersFacade;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "UserController", urlPatterns = {
    "/createUsers",
     "/showFormLogin",
     "/login",
     "/logout",
     
})
public class UserController extends HttpServlet {
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private RoleFacade roleFacade;
    @EJB
    private UserRolesFacade userRolesFacade;

    @Override
    public void init() throws ServletException {
        int countRoles = roleFacade.count();
        if(countRoles>0) return;
        
        MakeHash mh = new MakeHash();
        String salts = mh.CreateSalts();
        String userpassword = mh.createHash("123123", salts);
        Users admin = new Users("ADMIN", userpassword, salts);
        usersFacade.create(admin);
        UserRoles userRoles = new UserRoles();
        userRoles.setUsers(admin);
        Role roleUser = new Role("ADMIN");
        roleFacade.create(roleUser);
        userRoles.setRole(roleUser);
        roleUser = new Role("USER");
        roleFacade.create(roleUser);
        userRoles.setRole(roleUser);
    }
    
    
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
            case "/index":
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
            case "/showFormLogin":
                request.getRequestDispatcher("/systemPages/showFormLogin.jsp")
                        .forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                String users = usersFacade.fingByLogin(login);
                
                MakeHash mh = new MakeHash();
                String encriptPassword = mh.createHash(password,users.getSalts());
                
                if(!encriptPassword.equals(users.getUserpassword())){
                    request.setAttribute("info", "Нет такого пользователя");
                    request.getRequestDispatcher("/showFormLogin.jsp").forward(request, response);
                }
                session = request.getSession(true);
                session.setAttribute("users", users);
                request.setAttribute("info", "Привет, "+users.getUserlogin());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/logout":
                session = request.getSession(false);
                if(session != null) session.invalidate();
                request.setAttribute("info", "Вы вышли");
                response.sendRedirect("index.jsp");
                break;
            case "/showFormAddUsers":
                request.getRequestDispatcher("/showFormAddUsers.jsp").forward(request, response);
                break;
            case "/createUsers":
                String userlogin = request.getParameter("userlogin");
                String userpassword = request.getParameter("userpassword");
                MakeHash makeHash = new MakeHash();
                String salts = makeHash.CreateSalts();
                String encodingPassword = makeHash.createHash(userpassword, salts);
                String phone = request.getParameter("phone");
                String mail = request.getParameter("mail");
                
                
                Users users = new Users(userlogin, encodingPassword,salts);
                
                usersFacade.create(users);
                Role role = roleFacade.getRole("USER");
                UserRoles userRoles = new UserRoles(users,role);
                userRolesFacade.create(userRoles);
                request.setAttribute("info", "Пользователь : "+users.getUserlogin()+"создан");
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