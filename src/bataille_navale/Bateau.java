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
 //COmmit try 
import java.util.Scanner;
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
        int l=(int)(S.charAt(0))-(int)('A');
        String temp=S.substring(1,S.length());
        int c=Integer.valueOf(temp)-1;
        yCoordinate=l;
        xCoordinate=c;
    }
    
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
    }
    
    public void renseignementsSimples(){
        boolean bol=false;
            while(bol==false){
                System.out.println("donner l'orientation bateau, H pour horizontale et V pour verticale");
                String S=kbd.next();
                bol=setPosition(S);
                System.out.println("bateau de taille "+length+" donner les coordones du bateau");
                String SC=kbd.next();
                setCoordinatesSimple(SC);
            }
        
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



