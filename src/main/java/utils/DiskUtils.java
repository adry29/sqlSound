
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
public class DiskUtils {
    
    DiskDAO dao;
    SongDAO sdao;
    ArtistDAO adao;
    ArrayList<Song> sList;
    ArrayList<Disk> dList;
    Disk d;
    viewUtils vu = new viewUtils();
    ArtistUtils au = new ArtistUtils();

    public DiskUtils() {
        try{
            dao = new DiskDAO();
            sdao = new SongDAO();
            adao = new ArtistDAO();
        }catch(SQLException e){
            System.out.println("Error");
        }
    }
    
    
    
    public ArrayList<Disk> getAll(){
        return dList = dao.getAllDisks();
    } 
    
    public ArrayList<Disk> search(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca nombre: ");
        String parameter = sc.nextLine();
        return dList = dao.getDisksbyName(parameter);
    }
    
    public void seeDisk(){
        int id = -1;
        int option = -1;
        viewUtils vu = new viewUtils();
        Scanner sc;
        System.out.println("Introduzca ID del disco");
        while (id == -1) {
            try {
                
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if (id <= 0 || dao.getDiskbyId(id).getName() == null) {
                    System.out.println("Disco no encontrado");
                    id = 0;
                } else {
                    while (option != 0) {
                    System.out.println(dao.getDiskbyId(id));
                    System.out.println("¿Qué desea hacer?");
                    System.out.println("1.[Ver sus canciones]");
                    System.out.println("0.[Atrás]");
                    
                        option = vu.validateGeneral(1);
                        if (option == 1) {
                            for (Song s : sdao.getSongsbyDisk(id)) {
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
    
    public void create(){
        d = new Disk();
        System.out.println("Creando disco:");
        System.out.println("Introduzca nombre:");
        Scanner sc = new Scanner(System.in);
        d.setName(sc.nextLine());
        System.out.println("Introduzca URL de la foto:");
        d.setPhoto(sc.nextLine());
        System.out.println("Introduzca la fecha de lanzamiento:");
        d.setDate(vu.createDate());
        System.out.println("Introduzca la id de su autor:");
        System.out.println("+++Mostrando artistas+++");
        for(Artist a : adao.getAllArtists()){
            System.out.println(a);
        }
        d.setArtist_id(au.getID());
        dao.insertDisk(d);
        System.out.println("Disco " + "'" +d.getName()+ "'" +  " creado");
    }
    
    
    
    public void remove(){
        int id = -1;
        System.out.println("Eliminando disco:");
        Scanner sc;
        System.out.println("Introduzca la ID del disco a eliminar:");
        while(id==-1){
            try{
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if(id <= 0 || dao.getDiskbyId(id).getName()==null){
                    System.out.println("Disco no encontrado");
                    id = 0;
                } else {
                    String nameaux = dao.getDiskbyId(id).getName();
                    dao.deleteDisk(id);
                    System.out.println("Disco " + nameaux + " eliminado.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero positivo");
                id = -1;
            }
        }
    }
    
    public void edit(){
        d = new Disk();
        int id = -1;
        Scanner sc;
        System.out.println("Introduzca la ID del disco a editar:");
        while(id==-1){
            try{
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if(id <= 0 || dao.getDiskbyId(id).getName()==null){
                    System.out.println("Disco no encontrado");
                    id = 0;
                } else {
                    System.out.println("Editando disco "+ dao.getDiskbyId(id).getName()+":");
                    System.out.println("Introduzca nombre:");
                    sc = new Scanner(System.in);
                    d.setName(sc.nextLine());
                    System.out.println("Introduzca URL de la foto:");
                    d.setPhoto(sc.nextLine());
                    System.out.println("Introduzca la fecha de lanzamiento:");
                    d.setDate(vu.createDate());
                    System.out.println("Introduzca la id de su autor:");
                    System.out.println("+++Mostrando artistas+++");
                    for (Artist a : adao.getAllArtists()) {
                        System.out.println(a);
                    }
                    d.setArtist_id(au.getID());
                    dao.updateDisk(d, id);
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero positivo");
                id = -1;
            }
        }
    }
}
