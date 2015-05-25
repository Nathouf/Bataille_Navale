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
import static bataille_navale.Partide.nom2;
import java.util.Scanner;
    public class Plateau {
        static String name;
        static int ships;
        static int type;
        static int lines;
        static int columns;
        //Le tableau du plateau
        static int[][] tabF;
        //Le tableau des bateaux
        static Bateau[] tabB;
        Scanner kbd=new Scanner(System.in);

    public Plateau(String nom){
       name=nom;
       type=1;
       lines=10;
       columns=10;
       tabF=new int[10][10];
       fill0();
       tabB=new Bateau[10];
       int j=-1;
       //filling a table of boats of a specific length 4x1 3x2 2x3 1x4
            for(int i=1;i<5;i++){
                j++;
                Bateau b=new Bateau(2);
                tabB[j]=b;
            }
            for(int i=1;i<3;i++){
                j++;
                Bateau b=new Bateau(3);
                tabB[j]=b;
            }
            for(int i=1;i<3;i++){
                j++;
                Bateau b=new Bateau(3);
                tabB[j]=b;
            }
            j++;
                Bateau b=new Bateau(6);
                tabB[j]=b;
       
    }
    public Plateau(String nom,int line,int column, int ship){
       name=nom;
       type=2;
       ships=ship;
       lines=line;
       columns=column;
       tabF=new int[line][column];
       fill0();
       tabB=new Bateau[ships];
       //for(i=0;i<5;i++)
    }
    public Plateau(String nom,int line,int column){
       name=nom;
       type=3;
       lines=line;
       columns=column;
       tabF=new int[line][column];
       fill0();
       tabB=new Bateau[10];
       //for(i=0;i<5;i++)
    }

    public void fill0(){
        for(int i=0;i<tabF.length;i++)
            for(int j=0;j<tabF[0].length;j++)
                tabF[i][j]=0;
    }

    public void affichageRudimentaire(){
        for(int i=0;i<tabF.length;i++){
            for(int j=0;j<tabF[0].length;j++)
                System.out.print(tabF[i][j]);
            System.out.println();
        }
        System.out.println();
    }
    
    public boolean emplacementPossible(Bateau b){
        boolean bol=true;
        if((b.getHorizontal())&&(b.getXCoordinate()+b.getLength()<tabF[0].length)) 
            for(int i=0;i<b.getLength();i++)
                if (tabF[b.getYCoordinate()][b.getXCoordinate()+i]!=0) bol=false;
        if((b.getHorizontal())&&(b.getYCoordinate()+b.getLength()<tabF.length))
            for(int j=0;j<b.getLength();j++)
                if (tabF[b.getYCoordinate()+j][b.getXCoordinate()]!=0) bol=false;
        return(bol);
    }
    
    
    //positionne le bateau sur le plateau
    public boolean paintField(Bateau b,int nr){
        boolean bol=false;
        if (emplacementPossible(b)){
            bol=true;
            if(b.getHorizontal()) for(int i=0;i<b.getLength();i++) tabF[b.getYCoordinate()][b.getXCoordinate()+i]=nr;
            if(!b.getHorizontal()) for(int i=0;i<b.getLength();i++) tabF[b.getYCoordinate()+i][b.getXCoordinate()]=nr;
        }
        affichageRudimentaire();
        System.out.println(emplacementPossible(b));
        return(bol);
    }
    
    public void fillPlateau(){
        //pour le joueur reel
        if (name!="ordinateur"){
                for(int i=0;i<tabB.length;i++){
                    if (type==1) {
                        do{
                        tabB[i].renseignementsSimples();
                        paintField(tabB[i],i);
                        }while(!paintField(tabB[i],i+1));
                    }
                    if (type==2){}
                    if (type==3){}
                    if (type==4){}
                }
        }
    }
}
