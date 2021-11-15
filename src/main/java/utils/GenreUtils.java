
package utils;

import com.mycompany.sqlsound.models.Genre;
import com.mycompany.sqlsound.models.Song;
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
public class GenreUtils {
    
    Genre g;
    ArrayList<Genre> gList;
    ArrayList<Song> songs;
    GenreDAO dao;
    SongDAO sdao;

    public GenreUtils() {
        try{
            dao = new GenreDAO();
            sdao = new SongDAO();
        }catch(SQLException e){
            System.out.println("Error");
        }
    }
    
    public ArrayList<Genre> getAll(){
        return gList = dao.getAllGenres();
    }
    
    public void seeGenre(){
        System.out.println("Escriba el ID del género:");
        Scanner sc;
        int id=-1;
        while(id == -1){
            try{
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if(id <= 0 || dao.getGenrebyId(id) == null){
                    System.out.println("Género no encontrado");
                }else{
                    System.out.println(dao.getGenrebyId(id));
                    System.out.println("Canciones del género:");
                    for(Song s : sdao.getSongsbyGenre(id)){
                        System.out.println(s);
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Introduce un número válido");
                id = -1;
            }
        }
        
    }
    
    public void create(){
        g = new Genre();
        Scanner sc = new Scanner(System.in);
        System.out.println("Creando género");
        System.out.println("Introduce el nombre:");
        g.setName(sc.nextLine());
        dao.insertGenre(g);
        System.out.println("Género "+g.getName()+" creado");
    }
    
    public void remove(){
        System.out.println("Seleccione la id del género que quiere eliminar:");
        int id=-1;
        Scanner sc;
        while(id==-1){
            try{
               sc = new Scanner(System.in);
               id=sc.nextInt();
               if(id<=0 || dao.getGenrebyId(id)==null){
                   System.out.println("Género no encontrado");
                   id=0;
               }else{
                   String auxname = dao.getGenrebyId(id).getName();
                   dao.deleteGenre(id);
                   System.out.println("Género "+auxname+" eliminado");
               }
            }catch(InputMismatchException e){
                System.out.println("Introduce un número válido");
                id = -1;
            }
        }
        
    }
    
    public void edit(){
        g = new Genre();
        int id=-1;
        Scanner sc;
        System.out.println("Seleccione la id del género que quiere editar:");
        while(id==-1){
            try{
               sc = new Scanner(System.in);
               id=sc.nextInt();
               if(id<=0 || dao.getGenrebyId(id)==null){
                   System.out.println("Género no encontrado");
                   id=0;
               }else{
                   sc = new Scanner(System.in);
                   System.out.println("Inserte el nuevo nombre:");
                   g.setName(sc.nextLine());
                   dao.updateGenre(g, id);
                   System.out.println("Género "+g.getName()+" actualizado");
               }
            }catch(InputMismatchException e){
                System.out.println("Introduce un número válido");
                id = -1;
            }
        }
        
    }
    
    
}
