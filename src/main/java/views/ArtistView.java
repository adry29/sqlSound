
package views;

import com.mycompany.sqlsound.models.Artist;
import dao.ArtistDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.ArtistUtils;
import utils.viewUtils;

/**
 *
 * @author adryc
 */
public class ArtistView {
    viewUtils u = new viewUtils();
    ArtistUtils au = new ArtistUtils();
    int option = -1;
    Artist a;
    ArrayList<Artist> aList;
    
    

    public void ArtistView() throws SQLException {
        while (option != 0) {
            System.out.println("-----------------------");
            System.out.println("Menú Artistas");
            System.out.println("-----------------------");
            System.out.println("Seleccione una opción");
            System.out.println("1.[Ver artistas]");
            System.out.println("2.[Buscar artista por nombre]");
            System.out.println("3.[Crear artista]");
            System.out.println("4.[Editar artista]");
            System.out.println("5.[Borrar artista]");
            System.out.println("6.[Ver artista concreto (discos o canciones)]");
            System.out.println("0.[Volver]");
            option = u.validateGeneral(6);
            switch (option) {
                case 1:
                    System.out.println("-----------------------");
                    System.out.println("Lista de artistas: ");
                    for(Artist aux : au.getAll()){
                        System.out.println(aux.toString());
                    }
                    System.out.println("-----------------------");
                    break;

                case 2:
                    System.out.println("-----------------------");
                    aList = au.search();
                    System.out.println("Lista de coincidencias: ");
                    for(Artist aux : aList){
                        System.out.println(aux.toString());
                    }
                    System.out.println("-----------------------");
                    break;
                    

                case 3:
                    au.create();
                    System.out.println("-----------------------");
                    break;

                case 4:
                    au.edit();
                    System.out.println("-----------------------");
                    break;

                case 5:
                    au.remove();
                    System.out.println("-----------------------");
                    break;
                    
                case 6:
                    au.seeArtist();
                    System.out.println("-----------------------");
                    break;

                case 0:
                    
                    
                    break;
            }
        }

    }
    
    
}
