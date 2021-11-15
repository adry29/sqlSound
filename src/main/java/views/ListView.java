
package views;

import com.mycompany.sqlsound.models.Disk;
import com.mycompany.sqlsound.models.List;
import com.mycompany.sqlsound.models.User;
import dao.ListDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DiskUtils;
import utils.ListUtils;
import utils.SongUtils;
import utils.viewUtils;

/**
 *
 * @author adryc
 */
public class ListView {
    
    viewUtils u = new viewUtils();
    ListUtils lu = new ListUtils();
    int option = -1;
    List l;
    ArrayList<List> lList;
    

    
    
    
    

        public void ListView(User user) throws SQLException {
        ListDAO dao = new ListDAO();
        while (option != 0) {
            System.out.println("-----------------------");
            System.out.println("Menú Playlists");
            System.out.println("-----------------------");
            System.out.println("Seleccione una opción");
            System.out.println("1.[Ver playlists]");
            System.out.println("2.[Buscar playlists por nombre]");
            System.out.println("3.[Crear playlist]");
            System.out.println("4.[Editar playlist]");
            System.out.println("5.[Borrar playlist]");
            System.out.println("6.[Ver playlist concreta y canciones]");
            System.out.println("7.[Ver mis playlists]");
            System.out.println("8.[Ver playlists a las que estoy suscrito]");
            System.out.println("9.[Suscribirme a playlist]");
            System.out.println("10.[Borrar suscripción]");
            System.out.println("11.[Añadir canción]");
            System.out.println("12. [Borrar canción de lista]");
            System.out.println("0.[Volver]");
            option = u.validateGeneral(12);
            switch (option) {
                case 1:
                    System.out.println("-----------------------");
                    for(List l : lu.getAll()){
                        System.out.println(l);
                    }
                    System.out.println("-----------------------");
                    break;

                case 2:
                    for(List l : lu.search()){
                        System.out.println(l);
                    }
                    System.out.println("-----------------------");
                    break;
                    

                case 3:
                    lu.create(user);
                    System.out.println("-----------------------");
                    break;

                case 4:
                    lu.edit(user);
                    System.out.println("-----------------------");
                    break;

                case 5:
                    lu.delete(user);
                    System.out.println("-----------------------");
                    break;
                    
                    
                case 6:
                    lu.seeList();
                    System.out.println("-----------------------");
                    break;
                    
                case 7:
                    for(List l : lu.getMyLists(user)){
                        System.out.println(l);
                    }
                    System.out.println("-----------------------");
                    break;
                
                case 8:
                    for(List l : lu.getSuscribedLists(user)){
                        System.out.println(l);
                    }
                    System.out.println("-----------------------");
                    break;
                 
                case 9:
                    lu.suscribe(user);
                    System.out.println("-----------------------");
                    break;
                    
                case 10:
                    lu.unsuscribe(user);
                    System.out.println("-----------------------");
                    break;
                 
                case 11:
                    lu.addSong(user);
                    System.out.println("-----------------------");
                    break;
                    
                case 12:
                    lu.deleteSong(user);
                    System.out.println("-----------------------");
                    break;

                case 0:
                    
                    
                    break;
            }
        }

    }
}
