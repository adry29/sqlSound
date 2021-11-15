
package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author adryc
 */
public class viewUtils {
    
    Scanner sc;
    String read;
    int option;
    boolean valid = false;
    
    public int validateGeneral(int num){
        
        do{
            
            try{
               sc = new Scanner(System.in);
               option = sc.nextInt();
               if(option <= num && option >= 0){
                   valid = true;
               }else{
                   System.out.println("Por favor, introduzca un número válido de 0 a "+num);
               }
            }catch(InputMismatchException e){
                
                System.out.println("Por favor, introduzca un número válido de 0 a "+num);
                valid = false;
                
            }
        }while(!valid);
        return option;
    }
    
    public int validateDate(int num){
        
        do{
            
            try{
               sc = new Scanner(System.in);
               option = sc.nextInt();
               if(option <= num && option >= 1){
                   valid = true;
               }else{
                   System.out.println("Por favor, introduzca un número válido de 1 a "+num);
                   valid = false;
               }
            }catch(InputMismatchException e){
                
                System.out.println("Por favor, introduzca un número válido de 1 a "+num);
                valid = false;
                
            }
        }while(!valid);
        return option;
    }
    
    public String createDate(){
        String result;
        int year, month, day;
        System.out.println("Introduzca el año:");
        year = validateDate(2021);
        System.out.println("Introduzca el mes:");
        month = validateDate(12);
        System.out.println("Introduzca el dia:");
        if(month == 2){
            day = validateDate(28);
        }else if (month == 1 || month == 3 || month == 5 
                || month == 7 || month == 8 || month == 10 || month == 12){
            day = validateDate(31);
        }else{
            day = validateDate(30);
        }
        return result = (""+year+"-"+month+"-"+day);
    }
}
