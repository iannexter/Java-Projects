package br.com.login;

import org.mindrot.jbcrypt.BCrypt;

//classe para criptografia de senha
public class PasswordUtils {

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
