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
    public class Plateau {
        private String name;
        private int ships;
        private int type;
        private int lines;
        private int columns;
        private boolean vivant;
        //Le tableau du plateau
        private int[][] tabF;
        //Le tableau des bateaux
        private Bateau[] tabB;
        Scanner kbd=new Scanner(System.in);

    public Plateau(String nom){
       name=nom;
       //System.out.println(name);
       type=1;
       lines=10;
       columns=10;
       tabF=new int[10][10];
       fill0();
       tabB=new Bateau[10];
       int j=-1;
       //filling a table of boats of a specific length 4x2 2x3 2x4 1x6
            j++;
                Bateau ba=new Bateau(6);
                tabB[j]=ba;
            for(int i=1;i<3;i++){
                j++;
                Bateau b=new Bateau(4);
                tabB[j]=b;
            }
            for(int i=1;i<4;i++){
                j++;
                Bateau b=new Bateau(3);
                tabB[j]=b;
            }
            for(int i=1;i<5;i++){
                j++;
                Bateau b=new Bateau(2);
                tabB[j]=b;
            }
       affichageRudimentaire();
    }//constructeur pour le type de jeu 1
    public Plateau(String nom,int line,int column, Bateau[] b){
       name=nom;
       type=2;
       ships=b.length;
       lines=line;
       columns=column;
       tabF=new int[line][column];
       fill0();
       tabB=b;
       
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
    
    public int getLines(){
        return lines;
    }
    public int getColumns(){
        return columns;
    }
    
    public void fill0(){
        for(int i=0;i<tabF.length;i++)
            for(int j=0;j<tabF[0].length;j++)
                tabF[i][j]=0;
    }//remplie tout le plateau avec des 0

    public void affichageRudimentaire(){
        for(int i=0;i<tabF.length;i++){
            for(int j=0;j<tabF[0].length;j++)
                System.out.print(" "+tabF[i][j]+" ");
            System.out.println();
        }
        System.out.println();
    }//affiche le plateau de maniere rudimentaire
    
    public boolean emplacementPossible(Bateau b){
        boolean bol=true;
        if(b.getHorizontal()){
            if (b.getXCoordinate()+b.getLength()>tabF[0].length){bol=false;} 
            else for(int i=0;i<b.getLength();i++)
                if (tabF[b.getYCoordinate()][b.getXCoordinate()+i]!=0) bol=false;
        }
        
        if(!b.getHorizontal()){
            if(b.getYCoordinate()+b.getLength()>tabF.length){bol=false;}
            else for(int j=0;j<b.getLength();j++)
                if (tabF[b.getYCoordinate()+j][b.getXCoordinate()]!=0) bol=false;
        }
        
        return(bol);
    }//renvoie true si l'emplacement du bateau est possible
            
    public boolean paintField(Bateau b,int nr){
        boolean bol=false;
        if (emplacementPossible(b)){
            bol=true;
            if(b.getHorizontal()) 
                for(int i=0;i<b.getLength();i++) 
                    tabF[b.getYCoordinate()][b.getXCoordinate()+i]=nr;
            if(!b.getHorizontal()) 
                for(int i=0;i<b.getLength();i++) 
                    tabF[b.getYCoordinate()+i][b.getXCoordinate()]=nr;
        }
        affichageRudimentaire();
        //System.out.println(emplacementPossible(b));
        return(bol);
    }//positionne le bateau sur le plateau
    
    public void fillPlateau(){
        //pour le joueur reel
        if (!(name.equals("ordinateur"))){
                for(int i=0;i<tabB.length;i++){
                        boolean bol=false;
                        while(bol==false){
                            if(lines<=26)
                            tabB[i].renseignementsSimples();
                            else tabB[i].renseignementsComplexes();
                            bol=paintField(tabB[i],i+1);
                        }
                }
        }
        if (name.equals("ordinateur")) {
            for(int i=0;i<tabB.length;i++){
                        boolean bol=false;
                        while(bol==false){
                            tabB[i].renseignementsOrdinateur(lines,columns);
                            bol=paintField(tabB[i],i+1);
                        }
                }
        affichageRudimentaire();
        }
    }//remplie le plateau avec des bateaux
    
    public boolean isLiving(){
        boolean bol=false;
        for(int i=0;i<tabF.length;i++)
            for(int j=0;j<tabF[0].length;j++)
                if (tabF[i][j]>0) bol=true;
        vivant=bol;
        return(bol);
    }
    public void paintDead(Bateau b){
        if(b.getHorizontal()) 
            for(int i=0;i<b.getLength();i++) 
                tabF[b.getYCoordinate()][b.getXCoordinate()+i]=-3;
        if(!b.getHorizontal()) 
            for(int i=0;i<b.getLength();i++) 
                tabF[b.getYCoordinate()+i][b.getXCoordinate()]=-3;
    }
    public boolean tirerDessus(int l, int c){
        boolean bol=false;
        int ligne=l;
        int colonne=c;
        int nr=tabF[l][c]-1;
        if(tabF[l][c]>0) {
            bol=true;
            if(tabB[nr].tir(l,c)) {
                if (tabB[nr].getState()==2) tabF[ligne][colonne]=-2;
                else if (tabB[nr].getState()==3) paintDead(tabB[nr]);
                
                
            }
        }
        if (tabF[ligne][colonne]==0){
            bol=true;
            tabF[l][c]=-1;
        }
        //if (bol==true)
            //affichageRudimentaire();
        return(bol);
    }
    
}
