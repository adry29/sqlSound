
package views;

import com.mycompany.sqlsound.models.Artist;
import com.mycompany.sqlsound.models.Disk;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DiskUtils;
import utils.viewUtils;

/**
 *
 * @author adryc
 */
public class DiskView {
    viewUtils u = new viewUtils();
    DiskUtils du = new DiskUtils();
    int option = -1;
    Disk d;
    ArrayList<Disk> dList;
    
    

    public void DiskView() throws SQLException {
        while (option != 0) {
            System.out.println("-----------------------");
            System.out.println("Menú Discos");
            System.out.println("-----------------------");
            System.out.println("Seleccione una opción");
            System.out.println("1.[Ver discos]");
            System.out.println("2.[Buscar discos por nombre]");
            System.out.println("3.[Crear disco]");
            System.out.println("4.[Editar disco]");
            System.out.println("5.[Borrar disco]");
            System.out.println("6.[Ver disco concreto y canciones]");
            System.out.println("0.[Volver]");
            option = u.validateGeneral(6);
            switch (option) {
                case 1:
                    System.out.println("-----------------------");
                    System.out.println("Lista de discos: ");
                    for(Disk aux : du.getAll()){
                        System.out.println(aux.toString());
                    }
                    System.out.println("-----------------------");
                    break;

                case 2:
                    System.out.println("-----------------------");
                    dList = du.search();
                    System.out.println("Lista de coincidencias: ");
                    for(Disk aux : dList){
                        System.out.println(aux.toString());
                    }
                    System.out.println("-----------------------");
                    break;
                    

                case 3:
                    du.create();
                    System.out.println("-----------------------");
                    break;

                case 4:
                    du.edit();
                    System.out.println("-----------------------");
                    break;

                case 5:
                    du.remove();
                    System.out.println("-----------------------");
                    break;
                    
                    
                case 6:
                    du.seeDisk();
                    System.out.println("-----------------------");
                    break;

                case 0:
                    
                    
                    break;
            }
        }

    }
}
