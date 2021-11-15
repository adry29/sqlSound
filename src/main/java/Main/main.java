
package Main;


import java.sql.SQLException;
import views.LoginView;

/**
 *
 * @author adryc
 */
public class main {

    public static void main(String[] args) throws SQLException{

        LoginView lv = new LoginView();
        lv.Login();
        
        
    }
}
