
package utils;

import com.mycompany.sqlsound.models.User;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author adryc
 */
public class LoginUtils {

    UserDAO dao;
    User u;
    Scanner sc;

    public LoginUtils() {
        try {
            this.dao = new UserDAO();
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public String passwordValidation() {
        boolean valid = false;
        String password = "";
        while (!valid) {
            System.out.println("Escriba su contraseña:");
            sc = new Scanner(System.in);
            password = sc.nextLine();
            if (password.length() < 5 ) {
                System.out.println("La contraseña debe tener al menos 5 carácteres");
            } else {
                valid = true;
            }
        }
        return password;
    }

    public String nickValidation() {
        int isUsed;
        String nick = "";
        do {
            isUsed = 0;
            System.out.println("Escriba su nickname:");
            sc = new Scanner(System.in);
            nick = sc.nextLine();
            for (User u : dao.getAllUsers()) {
                if (u.getNickname().equals(nick)) {
                    System.out.println("Nombre de usuario no disponible");
                    isUsed++;
                }
            }
        } while (isUsed > 0);
        return nick;
    }

    public String nickLogIn() {
        boolean found = false;
        String nick = "";
        do {
            System.out.println("Escriba su nickname:");
            sc = new Scanner(System.in);
            nick = sc.nextLine();
            for (User u : dao.getAllUsers()) {
                if (u.getNickname().equals(nick)) {
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Usuario no encontrado");
            }
        } while (!found);
        return nick;
    }

    public User signUp() {
        System.out.println("Registro de usuario");
        String nick = nickValidation();
        String password = passwordValidation();
        u = new User(nick, password);
        dao.insertUser(u);
        System.out.println("Usuario creado");
        for (User u2 : dao.getAllUsers()) {
            if (u2.getNickname().equals(u.getNickname())) {
                u = new User(u2.getId(), u2.getNickname(), u2.getPassword());
            }
        }
        return u;
    }

    public User logIn() {
        System.out.println("Inicio de sesión");
        boolean logged = false;
        User result = new User();
        while (!logged) {
            String nick = nickLogIn();
            String password = passwordValidation();
            for (User u : dao.getAllUsers()) {
                if (u.getNickname().equals(nick) && u.getPassword().equals(password)) {
                    result = new User(u.getNickname(), u.getPassword());
                    result.setId(u.getId());
                    logged = true;
                }
            }
            if (!logged) {
                System.out.println("Nickname o contraseña equivocados");
            }
        }
        return result;
    }

}
