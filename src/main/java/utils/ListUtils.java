
package utils;

import com.mycompany.sqlsound.models.List;
import com.mycompany.sqlsound.models.Song;
import com.mycompany.sqlsound.models.User;
import dao.ListDAO;
import dao.SongDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author adryc
 */
public class ListUtils {

    ListDAO dao;
    SongDAO sdao;
    List l;
    ArrayList<List> lList;

    public ListUtils() {
        try {
            this.dao = new ListDAO();
            this.sdao = new SongDAO();
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public ArrayList<List> getAll() {
        return lList = dao.getAllLists();
    }

    public ArrayList<List> search() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca nombre: ");
        String parameter = sc.nextLine();
        return lList = dao.getListsbyName(parameter);
    }

    public void create(User u) {
        l = new List();
        System.out.println("Creando nueva playlist");
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca nombre: ");
        l.setName(sc.nextLine());
        System.out.println("Introduzca una descripción: ");
        l.setDescription(sc.nextLine());
        l.setUser_id(u.getId());
        dao.insertList(l);
        System.out.println("Lista " + l.getName() + " creada");
    }

    public void edit(User u) {
        Scanner sc = new Scanner(System.in);
        int id = -1;
        while (id != 0) {
            
            try {
                System.out.println("Introduzca el ID de la playlist a editar:");
                
                l = new List();
                id = -1;
                sc = new Scanner(System.in);
                id = sc.nextInt();
                l = dao.getListbyId(id);
                if (l == null || l.getName()==null) {
                    System.out.println("Lista no encontrada");
                    id = 0;
                } else if (l != null && l.getId() != u.getId()) {
                    System.out.println("Solo puedes editar listas creadas por ti");
                    System.out.println(l);
                    id = -1;
                } else {
                    System.out.println("Introduzca nombre: ");
                    l.setName(sc.nextLine());
                    System.out.println("Introduzca una descripción: ");
                    l.setDescription(sc.nextLine());
                    l.setUser_id(u.getId());
                    dao.updateList(l, id);
                    System.out.println("Lista " + dao.getListbyId(id).getId() + " actualizada");
                    id = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero");
            }
        }
    }

    public void delete(User u) {
        Scanner sc;
        int id = -1;
        while (id != 0) {
            System.out.println("Introduzca el ID de la playlist a eliminar:");
            try {
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if (id <= 0 || dao.getListbyId(id) == null || dao.getListbyId(id).getName() == null) {
                    System.out.println("Lista no encontrada");
                    id = 0;
                } else if (dao.getListbyId(id).getUser_id() != u.getId()) {
                    System.out.println("Solo puedes eliminar listas creadas por ti");
                    id = -1;
                } else if (dao.getListbyId(id).getUser_id() == u.getId()) {
                    String auxname = dao.getListbyId(id).getName();
                    dao.deleteList(id);
                    System.out.println("Lista " + auxname + " eliminada");
                    id = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero");
            }
        }
    }

    public void seeList() {
        System.out.println("Introduzca el ID de la lista");
        int id = -1;
        Scanner sc;
        while (id != 0) {
            System.out.println("Introduzca el ID de la lista");
            try {
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if (id <= 0 || dao.getListbyId(id) == null || dao.getListbyId(id).getName()==null) {
                    System.out.println("Lista no encontrada");
                    id = 0;

                } else {
                    System.out.println(dao.getListbyId(id));
                    System.out.println("+++Canciones de la lista:+++");
                    for (Song s : dao.getSongsfromList(id)) {
                        System.out.println("-" + s);
                    }
                    id=0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero");
            }
        }
    }

    public ArrayList<List> getMyLists(User u) {
        return lList = dao.getListsbyUser(u);
    }

    public ArrayList<List> getSuscribedLists(User u) {
        return lList = dao.getsuscribedLists(u.getId());
    }

    public void suscribe(User u) {
        System.out.println("Suscribiendose a playlist");
        int idl = -1;
        Scanner sc;
        while (idl != 0) {
            System.out.println("Introduzca el ID de la playlist:");
            try {
                sc = new Scanner(System.in);
                idl = sc.nextInt();
                if (idl <= 0 || dao.getListbyId(idl) == null || dao.getListbyId(idl).getName()==null) {
                    System.out.println("Lista no encontrada");
                    idl = 0;

                } else {
                    System.out.println(dao.getListbyId(idl));
                    System.out.println("+++Canciones de la lista:+++");
                    for (Song s : dao.getSongsfromList(idl)) {
                        System.out.println("-" + s);
                    }
                    dao.suscribetoList(u.getId(), idl);
                    System.out.println("Suscrito a la lista " + dao.getListbyId(idl).getName());
                    idl = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero");
            }
        }
    }

    public void unsuscribe(User u) {
        System.out.println("Eliminando suscripción");
        Scanner sc;
        int idl = -1;
        while (idl != 0) {
            System.out.println("Introduzca el ID de la playlist:");
            try {
                sc = new Scanner(System.in);
                idl = sc.nextInt();
                if (idl <= 0 || dao.getListbyId(idl) == null || dao.getListbyId(idl).getName() == null) {
                    System.out.println("Lista no encontrada");
                    idl = 0;
                } else if (!isSuscribed(u, idl)) {
                    System.out.println("No está suscrito a esta playlist");
                    idl = 0;
                } else {
                    System.out.println(dao.getListbyId(idl));
                    System.out.println("+++Canciones de la lista:+++");
                    for (Song s : dao.getSongsfromList(idl)) {
                        System.out.println("-" + s);
                    }
                    dao.unsuscribetoList(u.getId(), idl);
                    System.out.println("Ya no está suscrito a la lista " + dao.getListbyId(idl).getName());
                    idl = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero");
            }
        }
    }

    public boolean isSuscribed(User u, int id_List) {
        boolean result = false;
        for (List l : dao.getAllLists()) {
            for (User u2 : dao.getSuscribers(id_List)) {
                if (u2.getId() == u.getId()) {
                    result = true;
                }
            }
        }
        return result;
    }

    public void addSong(User u) {
        System.out.println("Añadiendo canción a playlist");
        Scanner sc;
        int id = -1;
        while (id != 0) {
            System.out.println("Introduzca el ID de la playlist:");
            try {
                sc = new Scanner(System.in);
                id = sc.nextInt();
                if (id <= 0 || dao.getListbyId(id) == null || dao.getListbyId(id).getName() == null) {
                    System.out.println("Lista no encontrada");
                    id = 0;
                } else if (dao.getListbyId(id).getUser_id() != u.getId()) {
                    System.out.println("Solo puedes añadir canciones a listas creadas por ti");
                    id = -1;
                } else {
                    System.out.println("Introduzca ID de la canción");
                    int id2 = -1;
                    while (id2 == -1) {
                        
                        try {

                            Scanner sc2 = new Scanner(System.in);
                            id2 = sc2.nextInt();
                            if (id2 <= 0 || sdao.getSongbyId(id2) == null || sdao.getSongbyId(id2).getName() == null) {
                                System.out.println("Canción no encontrada");
                                id2 = 0;
                                id = 0;
                            } else if(sdao.getSongbyId(id2)!= null){
                                System.out.println(sdao.getSongbyId(id2).toString());
                                dao.addSongtoList(id2, id);
                                System.out.println("Cancion " + sdao.getSongbyId(id2).getName() + " añadida a playlist " + dao.getListbyId(id));
                                id2 = 0;
                                id = 0;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Introduzca un número entero positivo");
                            id = -1;
                        }
                    }

                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número entero");
            }
        }
    }
    
    public void deleteSong(User u) {
        int idsong = -1;
        int idlist = -1;
        Scanner scl = new Scanner(System.in);
        Scanner scs = new Scanner(System.in);
        List l;
        Song s;
        while (idlist == -1) {
            try {
                l = new List();
                System.out.println("Introduzca id de la lista");
                idlist = scl.nextInt();
                l = dao.getListbyId(idlist);
                if(l==null || idlist<=0 || l.getName() == null){
                    System.out.println("Lista no encontrada");
                    idlist=0;
                }else{
                    if(l.getUser_id()!=u.getId()){
                        System.out.println("Solo puedes modificar tus propias listas");
                        idlist=0;
                    }else{
                        System.out.println(l);
                        System.out.println("+++Canciones de la lista+++");
                        for(Song i : dao.getSongsfromList(idlist)){
                            System.out.println(i);
                        }
                        while(idsong == -1){
                            s = new Song();
                            try{
                                idsong = scs.nextInt();
                                s = sdao.getSongbyId(idsong);
                                if(s!=null && ListhasSong(idsong, idlist)){
                                    dao.deleteSongfromList(idsong, idlist);
                                    System.out.println("Canción "+s.getName()+" eliminada");
                                    idsong=0;
                                }else{
                                    System.out.println("La canción no se encuentra en la lista");
                                }
                            }catch(InputMismatchException e){
                                System.out.println("Introduce un número válido");
                                idsong=-1;
                            }
                        }
                        
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número positivo");

            }
        }

    }
    
    public boolean ListhasSong(int songid, int listid){
        boolean has = false;
        for(Song s : dao.getSongsfromList(listid)){
            if(s.getId() == songid){
                has = true;
            }
        }
        return has;
    }
}
