
package views;

import com.mycompany.sqlsound.models.User;
import java.sql.SQLException;
import utils.viewUtils;

/**
 *
 * @author adryc
 */
public class GeneralView {

    viewUtils u = new viewUtils();
    boolean keep = true;
    int option = -1;
    ArtistView av;
    DiskView dv;
    SongView sv;
    GenreView gv;
    ListView lv;

    public void generalView(User user) throws SQLException {
        do {
            
            System.out.println("¡¡¡Bienvenido a SQLSound!!!");
            System.out.println("-----------------------");
            System.out.println("Seleccione un menú");
            System.out.println("1.[Menú de artistas]");
            System.out.println("2.[Menú de discos]");
            System.out.println("3.[Menú de canciones]");
            System.out.println("4.[Menú de géneros musicales]");
            System.out.println("5.[Menú de playlists]");
            System.out.println("0.[Salir]");
            
            switch (u.validateGeneral(5)) {
                case 1:
                    av = new ArtistView();
                    av.ArtistView();
                    break;

                case 2:
                    dv = new DiskView();
                    dv.DiskView();
                    break;

                case 3:
                    sv = new SongView();
                    sv.SongView();
                    break;

                case 4:
                    gv = new GenreView();
                    gv.generalView();
                    break;

                case 5:
                    lv = new ListView();
                    lv.ListView(user);
                    break;

                case 0:
                    System.out.println("¡¡¡Adios!!!");
                    option = 0;
                    break;
            }
        }while (option != 0);

    }
    
    
   
    
    
}
