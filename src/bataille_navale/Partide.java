/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataille_navale;

/**
 *
 * @author Dannemp
 */
import java.util.Scanner;
public class Partide {
    Scanner kbd=new Scanner(System.in);
    static String nom1;
    static String nom2;
    static int type;
    static Plateau plateau1;
    static Plateau plateau2;
    static int ship;
    
    public Partide(){}
    
    public String getNom1(){
    return nom1;}
    public String getNom2(){
    return nom2;}
    
    public void initierPlateau(){
        System.out.println("Introduce S for singleplayer or M for multiplayer");
        String sp_mp=kbd.next();
        if((sp_mp=="S")||(sp_mp=="s")){
            System.out.println("Introduce the name of the first player");
            nom1=kbd.next();
            System.out.println("Introduce the name of the second player");
            nom2=kbd.next();
        }
        if((sp_mp=="M")||(sp_mp=="m")){
            System.out.println("Introduce the name of the player");
            nom1=kbd.next();
            nom2="ordinateur";
        }
        System.out.println("Choose the type of game by introducing its number");
        System.out.println("1.rectangular field, 10x10, fixed number of ships");
        System.out.println("2.rectangular field, any dimensions, any number of ships ");
        System.out.println("3.rectangular field, any dimensions, fixed number of ships");
        System.out.println("4.triangular field, fixed number of ships");
        type=kbd.nextInt();
        if(type==1){
            plateau1=new Plateau(nom1);
            plateau2=new Plateau(nom2);
            ship=10;
        }
        if(type==2){
            System.out.println("introduce the number of lines");
            int line=kbd.nextInt();
            System.out.println("introduce the number of columns");
            int column=kbd.nextInt();
            System.out.println("introduce the number of ships");
            ship=kbd.nextInt();
            plateau1=new Plateau(nom1,line,column,ship);
            plateau2=new Plateau(nom2,line,column,ship);
        }
        if(type==3){
            System.out.println("introduce the number of lines");
            int line=kbd.nextInt();
            System.out.println("introduce the number of columns");
            int column=kbd.nextInt();
            plateau1=new Plateau(nom1,line,column);
            plateau2=new Plateau(nom2,line,column);
            //Il faut penser combien des bateaux il nous faut
        }
        //juste pour verifier l'affichage
        //plateau1.affichageRudimentaire();
    }
    
    
    
}
