package br.com.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gelatos.JSFUtil;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user = new User();
    private List<User> users;
    private UserDAO userDAO = new UserDAO();

    public List<User> getUsers() {
        try {
            if (users == null) {
                users = userDAO.getAllUsers();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.adicionarMensagemErro("Falha ao carregar dados do usuário!");
        }
        return users;
    }

    public void save() {
        try {
            if (user.getId() == 0) {
                userDAO.saveUser(user.getUsername(), user.getPassword(), user.getRole());
            } else {
                userDAO.updateUser(user);
            }
            users = null;
            user = new User();
            JSFUtil.adicionarMensagemSucesso("Usuário salvo com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.adicionarMensagemErro("Falha ao salvar dados do usuário!");
        }
    }

    public void edit(User u) {
        this.user = u;
    }

    public void delete(User u) {
        try {
            userDAO.deleteUser(u.getId());
            users = null;
        } catch (SQLException e) {
            e.printStackTrace();
            JSFUtil.adicionarMensagemErro("Falha ao deletar dados do usuário!");
        }
    }

    public void prepareAdd() {
        this.user = new User();
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
