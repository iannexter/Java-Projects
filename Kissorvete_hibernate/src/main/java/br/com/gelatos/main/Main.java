package br.com.gelatos.main;

import br.com.login.PasswordUtils;
import br.com.login.UserDAO;

public class Main {
    
    public static void main(String[] args) {
    	//descomente esse metodo abaixo para criar o usuario admin
     //   criarUsuariosDeTeste();
    }

    public static void criarUsuariosDeTeste() {
        UserDAO userDAO = new UserDAO();
        
        // Senhas em texto puro que serão hasheadas
        String senhaAdmin = "admin1";
        String senhaFuncionario = "funcionario2";
        String senhaUsuario = "usuario3";
        
        try {
            //usuário Admin
            userDAO.saveUser("admin", senhaAdmin, "ADMIN");
            System.out.println("Usuário admin criado com sucesso!");
            System.out.println("Login: admin | Senha: " + senhaAdmin);
            
            //usuário Funcionário
            userDAO.saveUser("funcionario", senhaFuncionario, "FUNCIONARIO");
            System.out.println("Usuário funcionario criado com sucesso!");
            System.out.println("Login: funcionario | Senha: " + senhaFuncionario);
            
            //usuário normal
            userDAO.saveUser("usuario", senhaUsuario, "USER");
            System.out.println("Usuário usuario criado com sucesso!");
            System.out.println("Login: usuario | Senha: " + senhaUsuario);
            
            System.out.println("\nUsuários criados com sucesso no banco de dados!");
            
        } catch (Exception e) {
            System.err.println("Erro ao criar usuários:");
            e.printStackTrace();
        }
    }
}