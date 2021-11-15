
package views;

import com.mycompany.sqlsound.models.Song;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.SongUtils;
import utils.viewUtils;

/**
 *
 * @author adryc
 */
public class SongView {
    
    viewUtils vu = new viewUtils();
    SongUtils su = new SongUtils();
    int option = -1;
    Song s;
    ArrayList<Song> sList = new ArrayList();
    
    public void SongView() throws SQLException{
        while (option != 0) {
            System.out.println("-----------------------");
            System.out.println("Menú Canciones");
            System.out.println("-----------------------");
            System.out.println("Seleccione una opción");
            System.out.println("1.[Ver canciones]");
            System.out.println("2.[Buscar canciones por nombre]");
            System.out.println("3.[Crear canción]");
            System.out.println("4.[Editar canción]");
            System.out.println("5.[Borrar canción]");
            System.out.println("6.[Buscar canción por ID]");
            System.out.println("0.[Volver]");
            option = vu.validateGeneral(6);
            switch (option) {
                case 1:
                    for(Song s : su.getAll()){
                        System.out.println(s);
                    }
                    System.out.println("-----------------------");
                    break;

                case 2:
                    su.search();
                    System.out.println("-----------------------");
                    break;
                    

                case 3:
                    su.create();
                    System.out.println("-----------------------");
                    break;

                case 4:
                    su.edit();
                    System.out.println("-----------------------");
                    break;

                case 5:
                    su.remove();
                    System.out.println("-----------------------");
                    break;
                    
                    
                case 6:
                    su.seeSong();
                    System.out.println("-----------------------");
                    break;

                case 0:
                    
                    
                    break;
            }
        }
    }
    
    
}
