
package views;

import com.mycompany.sqlsound.models.User;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.Scanner;
import utils.LoginUtils;
import utils.viewUtils;

/**
 *
 * @author adryc
 */
public class LoginView {
    
    User u;
    UserDAO dao;
    boolean finish = false;
    viewUtils vu = new viewUtils();
    LoginUtils lu = new LoginUtils();
    GeneralView gv = new GeneralView();

    public LoginView() {
        try{
            this.dao = new UserDAO();
        }catch(SQLException e){
            System.out.println("Error");
        }
    }
    
    public void Login() throws SQLException{
        
        System.out.println("¡¡¡Bienvenido a SQLSound!!!");
        System.out.println("Inicie sesión o cree una cuenta:");
        System.out.println("1. [Iniciar sesión]");
        System.out.println("2. [Crear una cuenta]");
        System.out.println("0. [Salir]");
        while(!finish){
            switch(vu.validateGeneral(2)){
                case 1:
                    u = lu.logIn();
                    gv.generalView(u);
                    finish = true;
                    break;
                case 2:
                    u = lu.signUp();
                    gv.generalView(u);
                    finish = true;
                    break;
                case 3:
                    finish = true;
            }
        }
    }
    
    
}
