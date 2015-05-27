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
    private String nom1;
    private String nom2;
    private int type;
    private Plateau plateau1;
    private Plateau plateau2;
    private int ship;
    
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
        if((sp_mp.equals("S"))||(sp_mp.equals("s"))){
            System.out.println("Introduce the name of the first player");
            nom1=kbd.next();
            nom2="ordinateur";
        }
        if((sp_mp.equals("M"))||(sp_mp.equals("m"))){
            System.out.println("Introduce the name of the player");
            nom1=kbd.next();
            System.out.println("Introduce the name of the second player");
            nom2=kbd.next();
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
            System.out.println("Le premier joueur positionne sa flotille");
            plateau1=new Plateau(nom1,line,column,b);
            plateau1.fillPlateau();
            System.out.println("Le second joueur positionne sa flotille");
            plateau2=new Plateau(nom2,line,column,b);
            plateau2.fillPlateau();
        }
        if(type==3){
            
            System.out.println("introduce the number of lines");
            int line=kbd.nextInt();
            System.out.println("introduce the number of columns");
            int column=kbd.nextInt();
              double caseDispo=line*column;
            double size=line*column;
            double ratioSize6=size*0.20689; //Ratio des bateaux
            double ratioSize4=size*0.27586;
            double ratioSize3=size*0.31034;
            double ratioSize2=size*0.20689;
            int n6=0;
            int n4=0;
            int n3=0;
            int n2=0;
           while(caseDispo>0){
            while(((ratioSize6))>6){
                ratioSize6 = ratioSize6 - 6;
                caseDispo = caseDispo-6;
                n6++;
            }
             while(((ratioSize4))>4){
                ratioSize4 = ratioSize4 - 4;
                caseDispo = caseDispo-4;
                n4++;
            }
             while(((ratioSize3))>3){
                ratioSize3 = ratioSize3 - 3;
                caseDispo = caseDispo-4;
                n3++;
            }
             while(((ratioSize2))>0){
                ratioSize2 = ratioSize2 - 2;
                caseDispo = caseDispo-2;
                n2++;
            }
           }
            Bateau bateauRandom[] =new Bateau[n2+n3+n4+n6];
            for(int i=0; i<n6; i++){
                bateauRandom[i]=new Bateau(6);
            //Il faut penser combien des bateaux il nous faut
            }
            for(int i=n6; i<n4+n6; i++){
                bateauRandom[i]=new Bateau(4);
            }
            for(int i=n4; i<n3+n4+n6; i++){
                bateauRandom[i]=new Bateau(3);
            }
            for(int i=n3; i<n2+n3+n4+n6; i++){
                bateauRandom[i]=new Bateau(2);
            } 
            plateau1=new Plateau(nom1,line,column,bateauRandom);
            plateau1.fillPlateau();
            plateau2=new Plateau(nom2,line,column,bateauRandom);
            plateau2.fillPlateau();
            //Il faut penser combien des bateaux il nous faut
        }
        //juste pour verifier l'affichage
        //plateau1.affichageRudimentaire();
    }
        
}
