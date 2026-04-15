package br.com.login;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/faces/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String loginURI = req.getContextPath() + "/faces/login.xhtml";
        String requestedURI = req.getRequestURI();

        LoginBean loginBean = (LoginBean) req.getSession().getAttribute("loginBean");

        boolean loggedIn = loginBean != null && loginBean.isLoggedIn();
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean resourceRequest = req.getRequestURI().startsWith(req.getContextPath() + "/faces/javax.faces.resource/");

        if (loggedIn) {
            // Verificar se o usuário tem permissão para a página solicitada
            if (hasPermission(requestedURI, loginBean)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + "/faces/access-denied.xhtml");
            }
        } else if (loginRequest || resourceRequest) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURI);
        }
    }

    private boolean hasPermission(String requestedURI, LoginBean loginBean) {
        if (requestedURI.contains("gelatos.xhtml") && !loginBean.isAdmin()) {
            return false;
        }
        if (requestedURI.contains("users.xhtml") && !loginBean.isAdmin()) {
            return false;
        }
        if (requestedURI.contains("visualizarVendas.xhtml") && !loginBean.isAdmin()) {
            return false;
        }
        if (requestedURI.contains("realizarVenda.xhtml") && !(loginBean.isAdmin() || loginBean.isFuncionario())) {
            return false;
        }
        
        if (requestedURI.contains("usuarios.xhtml") && !(loginBean.isAdmin())) {
            return false;
        }
        
        return true;
    }

    @Override
    public void destroy() {
    }
}