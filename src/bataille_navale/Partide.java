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
public class Partide {
    Scanner kbd=new Scanner(System.in);
    private int type;
    private int ship;
    private int nrPlayers;
    private int xCoordinate;
    private int yCoordinate;
    private int difficulte;
    private String[] tabPlayers;
    private Plateau[] tabPlateaux;
    Random rnd = new Random();
    
    
    public Partide(){}
    
    public boolean numberOfShipsPossible(Bateau[] b, int lignes, int colonnes){
        boolean bol=false;
        int Max=lignes*colonnes;
        int Cas=0;
        for (int i=0;i<b.length;i++)
            Cas=Cas+b[i].getLength();
        if (Cas<=Max) bol=true;
        return(bol);
    }//rends true si un tel nombre des bateaux est possible
    
    public void single_multi(){
        tabPlayers=new String[2];
        String sp_mp="";
        while((!(sp_mp.equals("S"))&&!sp_mp.equals("s")&&!sp_mp.equals("m")&&!sp_mp.equals("M"))){
            System.out.println("Introduce S for singleplayer or M for multiplayer");
            sp_mp=kbd.nextLine();  
        }
        if((sp_mp.equals("S"))||(sp_mp.equals("s"))){
            tabPlayers=new String[2];
            System.out.println("Introduce the name of the first player");
            tabPlayers[0]=kbd.next();
            tabPlayers[1]="ordinateur";
            tabPlateaux=new Plateau[2];
            nrPlayers=2;
        }
        if((sp_mp.equals("M"))||(sp_mp.equals("m"))){
            System.out.println("introduce the number of players");
            nrPlayers=kbd.nextInt();
            tabPlayers=new String[nrPlayers];
            for(int i=0;i<tabPlayers.length;i++){
                System.out.println("Introduce the name of the player"+(i+1));
                tabPlayers[i]=kbd.next();
            }
            tabPlateaux=new Plateau[nrPlayers];
        }
    }//demande si le jeu est singleplayer ou multiplayer
    
    public void difficulteAi(){
        System.out.println("Choose the inteligence of the AI by introducing its number");
        System.out.println("1.completely random");
        System.out.println("2.smart choices");
        difficulte=kbd.nextInt();
    }
    
    public void typeDeJeu(){
        System.out.println("Choose the type of game by introducing its number");
        System.out.println("1.rectangular field, 10x10, fixed number of ships");
        System.out.println("2.rectangular field, any dimensions, any number of ships ");
        System.out.println("3.rectangular field, any dimensions, fixed number of ships");
        System.out.println("4.triangular field, fixed number of ships");
        type=kbd.nextInt();
    }//demande le type de jeu
    
    public void quickSort(int left,int right,Bateau[] b){ 
        int l=left,r=right; 
        int pivot=b[(left+right)/2].getLength(); 
        /*int pivot=tab[left];*/ 
        do{ 
            while ((l<right)&&(b[l].getLength()>pivot)){
                l=l+1;
            } 
            while ((r>left)&&(b[r].getLength()<pivot)){
                r=r-1;
            } 
            if (l<=r){ 
                Bateau aux=b[l]; 
                b[l]=b[r]; 
                b[r]=aux; 
                l=l+1; 
                r=r-1; 
            } 
        } while(l<r); 
        if(left<r) 
            quickSort(left,r,b); 
        if(l<right) 
            quickSort(l,right,b); 
    }//sorte le tableau des bateaux
    
    public void initierPlateau(){
        if(type==1){
            for(int i=0;i<tabPlayers.length;i++){
                tabPlateaux[i]=new Plateau(tabPlayers[i]);
                System.out.println(tabPlayers[i]+" le joueur "+(i+1)+" positionne sa flotille");
                tabPlateaux[i].fillPlateau();
            }
            ship=10;
        }
        if(type==2){
            System.out.println("introduce the number of lines");
            int line=kbd.nextInt();
            System.out.println("introduce the number of columns");
            int column=kbd.nextInt();
            boolean bol=false;
            
            System.out.println("introduce the total number of ships");
            ship=kbd.nextInt();
            Bateau[] b=new Bateau[ship];
            while((bol==false)){
                int i=0;
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
                    quickSort(0,b.length-1,b);
                    bol=numberOfShipsPossible(b,line,column);
            }
            for(int j=0;j<tabPlayers.length;j++){
                tabPlateaux[j]=new Plateau(tabPlayers[j],line,column,b);
                System.out.println(tabPlayers[j]+" le joueur "+(j+1)+" positionne sa flotille");
                tabPlateaux[j].fillPlateau();
                
            }
        }
         if(type==3){
            System.out.println("introduce the number of lines");
            int line=kbd.nextInt();
            System.out.println("introduce the number of columns");
            int column=kbd.nextInt();
           
           
            double caseDispo=line*column*0.35;
            double size=caseDispo;
            double ratioSize6=size*0.30689; //Ratio des bateaux
            double ratioSize4=size*0.4;
            double ratioSize3=size*0.31034;
            double ratioSize2=size*0.20689;
            int n6=0;
            int n4=0;
            int n3=0;
            int n2=0;
           
            while(((ratioSize6))>5.98){
                ratioSize6 = ratioSize6 - 6;
                caseDispo = caseDispo-6;
                n6++;
            }
             while(((ratioSize4))>3.99){
                ratioSize4 = ratioSize4 - 4;
                caseDispo = caseDispo-4;
                n4++;
            }
             while(((ratioSize3))>2.99){
                ratioSize3 = ratioSize3 - 3;
                caseDispo = caseDispo-3;
                n3++;
            }
             while(((ratioSize2))>1.99){
                ratioSize2 = ratioSize2 - 2;
                caseDispo = caseDispo-2;
                n2++;
            }
           
            Bateau bateauRandom[] =new Bateau[n2+n3+n4+n6];
            for(int i=0; i<n6; i++){
                bateauRandom[i]=new Bateau(6);
            
            }
            for(int i=n6; i<n4+n6; i++){
                bateauRandom[i]=new Bateau(4);
            }
            for(int i=n4+n6; i<n3+n4+n6; i++){
                bateauRandom[i]=new Bateau(3);
            }
            for(int i=n3+n4+n6; i<n2+n3+n4+n6; i++){
                bateauRandom[i]=new Bateau(2);
            } 
            for(int j=0;j<tabPlayers.length;j++){
                tabPlateaux[j]=new Plateau(tabPlayers[j],line,column,bateauRandom);
                System.out.println(tabPlayers[j]+" le joueur "+(j+1)+" positionne sa flotille");
                tabPlateaux[j].fillPlateau();
            }
            
        }
    }//initie les plateaux
    
    public int calculeNrJoueursVivants(){
        int nr=0;
        for(int i=0;i<tabPlateaux.length;i++){
            if (tabPlateaux[i].isLiving()) nr++;                    
        }
        return nr;
    }
    
    public void donnerCoordonesTir(Plateau p){
        if (p.getLines()<26){
            System.out.println("Donnez les coordonnees de votre tir (format A4)");//faut changer
            String S=kbd.next();
            int l=0;
            if ((S.charAt(0)>='A')&&(S.charAt(0)<='Z'))
                l=(int)(S.charAt(0))-(int)('A');
            if ((S.charAt(0)>='a')&&(S.charAt(0)<='z'))
                l=(int)(S.charAt(0))-(int)('a');
            String temp=S.substring(1,S.length());
            int c=Integer.valueOf(temp)-1;
            yCoordinate=l;
            xCoordinate=c;
        }
        else {
            System.out.println("Donnez les coordonnees de votre tir (format 14 27)");
            System.out.println("Premierement la ligne.");
            yCoordinate=kbd.nextInt()-1;
            System.out.println("Maintenant la colonne.");
            xCoordinate=kbd.nextInt()-1;
        }
    }
    
    public void donnerCoordonesTirAleatoire(int line,int column){
        yCoordinate=rnd.nextInt(line);
        xCoordinate=rnd.nextInt(column);
    }
    
    public void tirOrdinateur(Plateau p){
        boolean bol = false;
        while (bol == false) {
            if (difficulte == 1) {
                donnerCoordonesTirAleatoire(p.getLines(), p.getColumns());
            }
            bol = p.tirerDessus(yCoordinate, xCoordinate);
        }
    }
    
    public void jouerADeux(){
        int nombreJoueursVivants=calculeNrJoueursVivants();
        int j;
        while(nombreJoueursVivants>1){
            j=-1;
            while(j<1){
                    j++;
                    if (!tabPlayers[j].equals("ordinateur")){
                        System.out.println("Le joueur "+tabPlayers[j]+" tire");
                        if (j==0){
                        donnerCoordonesTir(tabPlateaux[1]);
                        boolean bol=false;
                            while(bol==false){
                                bol=tabPlateaux[1].tirerDessus(yCoordinate,xCoordinate);
                            }
                        }
                        if(j==1){
                            donnerCoordonesTir(tabPlateaux[0]);
                            boolean bol=false;
                            while(bol==false){
                                bol=tabPlateaux[0].tirerDessus(yCoordinate,xCoordinate);
                            }
                        }
                        
                    }
                    //pour l'ordinateur
                    else {
                        if (j==0){
                            System.out.println("l'ordi1 tire");
                            tirOrdinateur(tabPlateaux[1]);
                        }
                        if (j==1) {
                            System.out.println("l'ordi2 tire");
                            tirOrdinateur(tabPlateaux[0]);
                        }
                    }
            }
        nombreJoueursVivants=calculeNrJoueursVivants();
        }
    }
    
    public void jouer(){
        int nombreJoueursVivants=calculeNrJoueursVivants();
        if (nrPlayers==2) jouerADeux();
            /*else{
                while(i<tabPlayers.length){
                    i++;
                    if (tabPlateaux[i].isLiving()){
                        if (!tabPlayers[i].equals("ordinateur")){
                            System.out.println("Le joueur "+tabPlayers[i]+" choisit sa cible. Introduisez le nombre de votre cible.");
                            int cible=kbd.nextInt();//faut changer
                            donnerCoordonesTir(tabPlateaux[cible-1]);
                            boolean bol=false;
                            while(bol==false){
                                bol=tabPlateaux[cible].tirerDessus(yCoordinate,xCoordinate);
                            }
                        }
                        else ;//pour un ordinateur
                    }
                    else i=i;//si le joueur n'est pas vivant on passe son tour;
                }
            nombreJoueursVivants=calculeNrJoueursVivants();    
            }//pour une partide plusieurs players
        }*/
    }
}
