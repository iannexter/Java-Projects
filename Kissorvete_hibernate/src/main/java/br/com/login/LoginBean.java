package br.com.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;
    private boolean loggedIn;
    private String role;

    private UserDAO userDAO = new UserDAO();

    public String login() {
        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null && PasswordUtils.checkPassword(password, user.getPassword())) {
                loggedIn = true;
                role = user.getRole();
                return "home?faces-redirect=true";
            } else {
                loggedIn = false;
                return "login";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String logout() {
        loggedIn = false;
        role = null;
        return "login?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isAdmin() {
        return "ADMIN".equals(role);
    }

    public boolean isFuncionario() {
        return "FUNCIONARIO".equals(role);
    }

    public boolean isUser() {
        return "USER".equals(role);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
}