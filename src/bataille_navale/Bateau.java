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
import java.util.Random;
public class Bateau {
    private int length;
    private int state;
    //l'etat 1 designe indemne, 2 touce, 3 coule;
    private int xCoordinate;
    private int yCoordinate;
    private int nrTouches;
    //nrTouches le nombre de fois q'un bateau a ete touche
    private boolean dHorizontal;
    // si le bateau horizontal alors true
    Scanner kbd=new Scanner(System.in);
    Random rnd = new Random();
    
    public Bateau(int ligne,int colonne, int longeur,boolean horizontal){
        length=longeur;
        dHorizontal=horizontal;
        yCoordinate=ligne;
        xCoordinate=colonne;
        //initialement pas touche, et indemne
        nrTouches=0;
        state=1;
    }
    
    public Bateau(int longeur){
        length=longeur;
        //initialement pas touche, et indemne
        nrTouches=0;
        state=1;
    }
    
    public Bateau(int longeur,boolean horizontal){
        length=longeur;
        dHorizontal=horizontal;
        //initialement pas touche, et indemne
        nrTouches=0;
        state=1;
    }
    
    public boolean getHorizontal(){
        return(dHorizontal);
    }
    public int getXCoordinate(){
        return(xCoordinate);
    }
    public int getYCoordinate(){
        return(yCoordinate);
    }
    public int getLength(){
        return(length);
    }
    
    public void setCoordinatesSimple(String S){
        int l=0;
        if ((S.charAt(0)>='A')&&(S.charAt(0)<='Z'))
            l=(int)(S.charAt(0))-(int)('A');
        if ((S.charAt(0)>='a')&&(S.charAt(0)<='z'))
            l=(int)(S.charAt(0))-(int)('a');
        String temp=S.substring(1,S.length());
        int c=Integer.valueOf(temp)-1;
        yCoordinate=l;
        xCoordinate=c;
    } //pour mettre un bateau a partir des coordones comme A4 ou c10
    
    public void setCoordinatesComplexes(int l, int c){
        yCoordinate=l-1;
        xCoordinate=c-1;
    }//pour mettre un bateau a partir des coordones sans lettres;
    
    public boolean setPosition(String S){
        boolean corect=false;
        if((S.charAt(0)=='h')||(S.charAt(0)=='H')){
            dHorizontal=true;
            corect=true;
        }
        else if((S.charAt(0)=='v')||(S.charAt(0)=='V')) {
            dHorizontal=false;
            corect=true;
        }
        else System.out.println("vous n'avez pas introduit corectement");
        return(corect);
    }//pose l'horientation du bateau
    
    public void renseignementsSimples(){
        boolean bol=false;
            while(bol==false){
                System.out.println("Bateau de taille "+length+". Donner l'orientation, H pour horizontale et V pour verticale.");
                String S=kbd.next();
                bol=setPosition(S);
                System.out.println("Bateau de taille "+length+". Donner les coordones du bateau.(de forme A4)");
                String SC=kbd.next();
                setCoordinatesSimple(SC);
            }
    }//se renseigne pour un bateau en coordonees avec lettres
    
    public void setPositionOrdi(int o){
        if(o==1) dHorizontal=true;
        if(o==0) dHorizontal=false;
    }
    
    public void setCoordinatesOrdi(int yC,int xC){
        yCoordinate=yC;
        xCoordinate=xC;
    }
    
    public void renseignementsComplexes(){
        boolean bol=false;
            while(bol==false){
                System.out.println("Bateau de taille "+length+". Donner l'orientation, H pour horizontale et V pour verticale.");
                String S=kbd.next();
                bol=setPosition(S);
                System.out.println("Bateau de taille "+length+". Donner les coordones du bateau.");
                System.out.println("Premierement la ligne.");
                int lin=kbd.nextInt();
                System.out.println("Maintenant la colonne.");
                int col=kbd.nextInt();
                setCoordinatesComplexes(lin,col);
            }
    }//se renseigne pour un bateau en coordonees sans letres
    
    public void renseignementsOrdinateur(int lines, int columns){
        int orient=rnd.nextInt(1);
        setPositionOrdi(orient);
        int xC=rnd.nextInt(columns-1);
        int xL=rnd.nextInt(lines-1);
        setCoordinatesOrdi(xL,xC);
    }
    
    public boolean tir(int x, int y){
        boolean touche=false;
        if ((dHorizontal)&&(y==yCoordinate)&&(x>=xCoordinate)&&(x<=xCoordinate+length)){
            touche=true;
            nrTouches=nrTouches+1;
        }
        else if((x==xCoordinate)&&(y>=yCoordinate)&&(y<=yCoordinate+length)){
            touche=true;
            nrTouches=nrTouches+1;
        }
        
        if(touche){
            if (nrTouches==length) state=3;
            else state=2;
        }
        return(touche);
    }
    
}



