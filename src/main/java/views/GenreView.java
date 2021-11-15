
package views;

import com.mycompany.sqlsound.models.Genre;
import java.sql.SQLException;
import utils.GenreUtils;
import utils.viewUtils;

/**
 *
 * @author adryc
 */
public class GenreView {
    
    int option = -1;
    viewUtils vu = new viewUtils();
    GenreUtils gu = new GenreUtils();
    
    public void generalView() throws SQLException {
        do {
            
            System.out.println("-----------------------");
            System.out.println("Menu Géneros");
            System.out.println("-----------------------");
            System.out.println("Seleccione una opción");
            System.out.println("1.[Ver géneros]");
            System.out.println("2.[Crear género]");
            System.out.println("3.[Editar género]");
            System.out.println("4.[Borrar género]");
            System.out.println("5.[Ver género]");
            System.out.println("0.[Volver]");
            
            switch (vu.validateGeneral(5)) {
                case 1:
                    gu = new GenreUtils();
                    for(Genre g : gu.getAll()){
                        System.out.println(g);
                    }
                    break;

                case 2:
                    gu = new GenreUtils();
                    gu.create();
                    break;

                case 3:
                    gu = new GenreUtils();
                    gu.edit();
                    break;

                case 4:
                    gu = new GenreUtils();
                    gu.remove();
                    break;

                case 5:
                    gu = new GenreUtils();
                    gu.seeGenre();
                    break;
                    
                

                case 0:
                    option = 0;
                    break;
            }
        }while (option != 0);

    }
}
