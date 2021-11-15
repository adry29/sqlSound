
package utils;

import com.mycompany.sqlsound.models.Artist;
import com.mycompany.sqlsound.models.Disk;
import com.mycompany.sqlsound.models.Song;
import dao.ArtistDAO;
import dao.DiskDAO;
import dao.SongDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author adryc
 */
public class ArtistUtils {
    ArtistDAO dao;
    DiskDAO ddao;
    SongDAO sdao;
    Artist a;
    ArrayList<Artist> aList;

    public ArtistUtils() {
        
        try{
            dao = new ArtistDAO();
            ddao = new DiskDAO();
            sdao = new SongDAO();
        }catch(SQLException e){
            System.out.println("Error al acceder a la base de datos");
        }
    }
    
    
    
    public ArrayList<Artist> getAll(){
        aList = dao.getAllArtists();
        return aList;
    }
    
    public ArrayList<Artist> search(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca nombre: ");
        String parameter = sc.nextLine();
        return aList = dao.getArtistsbyName(parameter);
    }
    
    public void create(){
        System.out.println("Creando artista:");
        System.out.println("Introduzca nombre, nacionalidad y URL de la foto:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String nationality = sc.nextLine();
        String photo = sc.nextLine();
        a = new Artist(name, nationality, photo);
        dao.insertArtist(a);
        System.out.println("Artista " + "'" +name+ "'" +  " creado");
    }
    public void edit(){
        int id = -1;
        Scanner sc;
        System.out.println("Introduzca la id del artista a editar:");
        while(id==-1){
            try{
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if(id <= 0 || dao.getArtistbyId(id).getName()==null){
                    System.out.println("Artista no encontrado");
                    id=0;
                }else{
                    System.out.println("Editando Artista "+ dao.getArtistbyId(id).getName()+":");
                    System.out.println("Introduzca nuevo nombre, nueva nacionalidad y nueva URL de la foto de "+dao.getArtistbyId(id).getName()+":");
                    sc = new Scanner(System.in);
                    String name = sc.nextLine();
                    String nationality = sc.nextLine();
                    String photo = sc.nextLine();
                    a = new Artist(name, nationality, photo);
                    dao.updateArtist(a, id);
                }
            }catch(InputMismatchException e){
                System.out.println("Introduzca un número entero positivo");
                id = -1;
            }
        }
    }
    
    public void remove(){
        int id = -1;
        System.out.println("Eliminando artista:");
        Scanner sc;
        System.out.println("Introduzca la id del artista a eliminar:");
        while(id==-1){
            try{
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if(id <= 0 || dao.getArtistbyId(id).getName()==null){
                    System.out.println("Artista no encontrado");
                    id = 0;
                } else {
                    String nameaux = dao.getArtistbyId(id).getName();
                    dao.deleteArtist(id);
                    System.out.println("Artista " + nameaux + " eliminado.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero positivo");
                id = -1;
            }
        }
    }

    public void seeArtist() throws SQLException {

        int id = -1;
        int option = -1;
        viewUtils vu = new viewUtils();
        Scanner sc;
        System.out.println("Introduzca ID del artista");
        while (id == -1) {
            try {
                
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if (id <= 0 || dao.getArtistbyId(id).getName() == null) {
                    System.out.println("Artista no encontrado");
                    id = 0;
                } else {
                    while (option != 0) {
                    System.out.println(dao.getArtistbyId(id));
                    System.out.println("¿Qué desea hacer?");
                    System.out.println("1.[Ver sus discos]");
                    System.out.println("2.[Ver sus canciones]");
                    System.out.println("0.[Atrás]");
                    
                        option = vu.validateGeneral(2);
                        if (option == 1) {
                            for (Disk d : ddao.getDisksbyArtist(id)) {
                                System.out.println(d);
                            }
                        } else if (option == 2) {
                            for (Song s : sdao.getSongsbyArtist(id)) {
                                System.out.println(s);

                            }
                        }else{
                            option = 0;
                        }
                    }
                    
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero positivo");
                id = -1;
            }
        }
    }
    
    public int getID(){
        int result = 0;
        int id = -1;
        Scanner sc;
        while (id == -1) {
            try {
                
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if (id <= 0 || dao.getArtistbyId(id).getName() == null) {
                    System.out.println("Artista no encontrado");
                    id = -1;
                }else{
                    result = dao.getArtistbyId(id).getId();
                }
                    
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero positivo");
                id = -1;
            }
        }
        return result;
    }


}
