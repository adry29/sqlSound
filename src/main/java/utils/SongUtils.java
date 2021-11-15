
package utils;


import com.mycompany.sqlsound.models.Disk;
import com.mycompany.sqlsound.models.Genre;
import com.mycompany.sqlsound.models.Song;
import dao.DiskDAO;
import dao.GenreDAO;
import dao.SongDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author adryc
 */
public class SongUtils {
    
    SongDAO dao;
    DiskDAO ddao;
    GenreDAO gdao;
    Song s = new Song();
    ArrayList<Song> sList = new ArrayList();
    viewUtils vu = new viewUtils();

    public SongUtils() {
        try{
            dao = new SongDAO();
            ddao = new DiskDAO();
            gdao = new GenreDAO();
        }catch(SQLException e){
            System.out.println("Error");
        }
    }
    
    public ArrayList<Song> getAll(){
        return sList = dao.getAllSongs();
    }

    public ArrayList<Song> search() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca nombre: ");
        String parameter = sc.nextLine();
        return sList = dao.getSongsbyName(parameter);
    }
    
    public void seeSong(){
        int id = -1;
        int option = -1;
        viewUtils vu = new viewUtils();
        Scanner sc;
        System.out.println("Introduzca ID de la canción");
        while (id == -1) {
            try {
                
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if (id <= 0 || dao.getSongbyId(id) == null || dao.getSongbyId(id).getName() == null) {
                    System.out.println("Canción no encontrada");
                    id = 0;
                } else{
                    System.out.println(dao.getSongbyId(id).toString());
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero positivo");
                id = -1;
            }
        }
    }
    
    public void create() throws SQLException{
        System.out.println("Creando canción:");
        System.out.println("Introduzca nombre:");
        Scanner sc = new Scanner(System.in);
        s.setName(sc.nextLine());
        System.out.println("Introduzca la duración (en segundos)");
        s.setDuration(vu.validateGeneral(3600));
        System.out.println("Introduzca la ID del disco al que pertenece:");
        System.out.println("+++Mostrando discos+++");
        for(Disk d : ddao.getAllDisks()){
            System.out.println(d);
        }
        int id = -1;
        while (id == -1) {
            try {
                
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if (id <= 0 || ddao.getDiskbyId(id).getName() == null) {
                    System.out.println("Disco no encontrado");
                    id = 0;
                } else {
                    System.out.println(ddao.getDiskbyId(id));
                    s.setDiskid(id);
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero positivo");
                id = -1;
            }
        }
        
        System.out.println("Introduzca la ID del género al que pertenece (0 para saltar):");
        System.out.println("+++Mostrando géneros+++");
        for(Genre g : gdao.getAllGenres()){
            System.out.println(g);
        }
        id = -1;
        while (id == -1) {
            try {

                sc = new Scanner(System.in);
                id = sc.nextInt();
                if (id < 0 || gdao.getGenrebyId(id).getName() == null) {
                    System.out.println("Disco no encontrado");
                    id = 0;
                } else if (id != 0) {
                    System.out.println(gdao.getGenrebyId(id));
                    s.setGenreId(id);
                } else {
                    System.out.println("La canción no tendrá un género");
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero positivo");
                id = -1;
            }
        }
        dao.insertSong(s);
        System.out.println("Canción " + "'" + s.getName() + "'" + " creada");
    }

    public void edit() throws SQLException {
        int ids = -1;
        Scanner sc;
        while (ids == -1) {
            System.out.println("Introduzca id de la canción a editar:");
            try {
                sc = new Scanner(System.in);
                ids = sc.nextInt();
                if (dao.getSongbyId(ids) == null || dao.getSongbyId(ids).getName() == null) {
                    System.out.println("Canción no encontrada");
                    ids = 0;
                } else{
                    System.out.println("Editando canción " + dao.getSongbyId(ids).getName() + ":");
                    System.out.println("Introduzca nombre:");
                    sc = new Scanner(System.in);
                    s.setName(sc.nextLine());
                    System.out.println("Introduzca la duración (en segundos)");
                    s.setDuration(vu.validateGeneral(3600));
                    System.out.println("Introduzca la ID del disco al que pertenece:");
                    System.out.println("+++Mostrando discos+++");
                    for (Disk d : ddao.getAllDisks()) {
                        System.out.println(d);
                    }
                    int id2 = -1;
                    while (id2 == -1) {
                        try {

                            sc = new Scanner(System.in);
                            id2 = sc.nextInt();
                            if (id2 <= 0 || ddao.getDiskbyId(id2).getName() == null) {
                                System.out.println("Disco no encontrado");
                                id2 = -1;
                            } else {
                                System.out.println(ddao.getDiskbyId(id2));
                                s.setDiskid(id2);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Introduzca un número entero positivo");
                            id2 = -1;
                        }
                    }

                    System.out.println("Introduzca la ID del género al que pertenece (0 para saltar):");
                    System.out.println("+++Mostrando géneros+++");
                    for (Genre g : gdao.getAllGenres()) {
                        System.out.println(g);
                    }
                    int id3 = -1;
                    while (id3 == -1) {
                        try {

                            sc = new Scanner(System.in);
                            id3 = sc.nextInt();
                            if (id3 < 0 || gdao.getGenrebyId(id3).getName() == null) {
                                System.out.println("Género no encontrado");
                                id3 = -1;
                            } else if (id3 != 0) {
                                System.out.println(gdao.getGenrebyId(id3));
                                s.setGenreId(id3);
                            } else {
                                s.setGenreId(1);
                                System.out.println("La canción no tendrá un género");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Introduzca un número entero positivo");
                            id3 = -1;
                        }
                    }
                    dao.updateSong(s, ids);
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero positivo");
                ids = -1;
            }
        }

    }
    
    public void remove(){
        int id = -1;
        Scanner sc2;
        while(id==-1){
            try{
                Song s = new Song();
                System.out.println("Introduzca el ID de la canción:");
                sc2 = new Scanner(System.in);
                id = sc2.nextInt();
                if(id <= 0 || dao.getSongbyId(id) == null || dao.getSongbyId(id).getName() == null){
                    System.out.println("Canción no encontrada");
                }else{
                    String name = dao.getSongbyId(id).getName();
                    dao.deleteSong(id);
                    System.out.println("Canción "+name+" eliminada.");
                }
            }catch(InputMismatchException e){
                System.out.println("Introduzca un número válido");
                id = -1;
            }
        }
    }

}
