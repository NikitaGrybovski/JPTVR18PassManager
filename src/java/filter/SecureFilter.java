/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import entity.Users;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import session.RoleFacade;
import session.UserRolesFacade;

/**
 *
 * @author pupil
 */
@WebFilter(filterName = "SecureFilter",dispatcherTypes = {DispatcherType.FORWARD}, urlPatterns = {"/*"})
public class SecureFilter implements Filter {
    @EJB 
    private RoleFacade roleFacade;
    @EJB
    private UserRolesFacade userRolesFacade;
    @EJB
    private UserRolesFacade usersFacade;   
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public SecureFilter() {
    }    
    
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest hsr = (HttpServletRequest)request;
        String topRoleCurrentUsers = null;
        HttpSession session = hsr.getSession(false);
        if(session == null){
            hsr.setAttribute("topRoleCurrentUsers", topRoleCurrentUsers);
                    chain.doFilter(request, response);
                    return;

        }
        Users users = (Users) session.getAttribute("users");
        if(users == null){
            hsr.setAttribute("topRoleCurrentUsers", topRoleCurrentUsers);
                    chain.doFilter(request, response);
                    return;

        }
        topRoleCurrentUsers = userRolesFacade.getTopRoleName(users);
        if(topRoleCurrentUsers == null){
            hsr.setAttribute("topRoleCurrentUsers", topRoleCurrentUsers);
                    chain.doFilter(request, response);
                    return;

        }
        request.setAttribute("topRoleCurrentUsers", topRoleCurrentUsers);
        chain.doFilter(request, response);
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Return a String representation of this object.
     */
    
    
}
