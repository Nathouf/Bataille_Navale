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
    
    public boolean numberOfShipsPossible(Bateau[] b, int lignes, int colonnes){
        boolean bol=false;
        int Max=lignes*colonnes;
        int Cas=0;
        for (int i=0;i<b.length;i++)
            Cas=Cas+b[i].getLength();
        if (Cas<=Max) bol=true;
        return(bol);
    }
    
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
            System.out.println("Le premier joueur positionne sa flotille");
            plateau1.fillPlateau();
            System.out.println("Le second joueur positionne sa flotille");
            plateau2.fillPlateau();
        }
        if(type==2){
            System.out.println("introduce the number of lines");
            int line=kbd.nextInt();
            System.out.println("introduce the number of columns");
            int column=kbd.nextInt();
            boolean bol=false;
            int test=0;
            int i=0;
            System.out.println("introduce the total number of ships");
            ship=kbd.nextInt();
            Bateau[] b=new Bateau[ship];
            while((bol==false)){
                while(i<ship){
                    System.out.println("introduce the length and then the number of ships of that length that you want to have");
                    System.out.println("introduce the length");
                    int givenLength=kbd.nextInt();
                    System.out.println("introduce the number of ships of this length, you can place "+(ship-i)+" more ships");
                    int nrLength=kbd.nextInt();
                    int k=i;
                    if (i+nrLength<=ship)i=i+nrLength;
                    else i=ship;
                    for(int j=k;j<i;j++)
                       b[j]=new Bateau(givenLength);
                }
                    bol=numberOfShipsPossible(b,line,column);
            }
            plateau1=new Plateau(nom1,line,column,b);
            plateau2=new Plateau(nom2,line,column,b);
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
