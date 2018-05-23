package menouer.amjad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *Class Test, used to create a csv file and write into
 * 
 * @author menouer
 */
public class TestAI {
    
    /**
     * Mode Beginner versus Medium
     * 
     * @return 1 if Medium win the Game else 0 if Easy win
     */
    public static int EvM(){
        /* Medium VS HardMode */
        /* Initialisation nouvelle partie */
        boolean turn = false;
        Player player1,player2;
        player1 = new Player("Easy");
        player2 = new Player("Medium");
        /* Placement des bateaux */
        player1.placingIAShips();
        player2.placingIAShips();
        do{
            turn=!(turn);
            if(turn){ //player1
                player1.shootEasy(player2);
            }else if(!turn){ //player2
                player2.shootMedium(player1);
            }
        }while(!(player1.endGame())&&!(player2.endGame())); //A vérifier au cas ou
        /* Fin du jeu */
        if(player1.endGame()){
            System.out.println("Medium win!");
            return 1;
        }else{
            System.out.println("Easy win!");
            return 0;
        }
    }

    /**
     *Mode Medium versus Hard
     * 
     * @return 1 if Hard win the Game else 0 if Medium win
     */
    public static int MvH(){
        /* Medium VS HardMode */
        /* Initialisation nouvelle partie */
        boolean turn = false;
        Player player1,player2;
        boolean []stateArray=new boolean[3];
        stateArray[0]=false;
        stateArray[1]=false;
        stateArray[2]=false;
        player1 = new Player("Medium");
        player2 = new Player("Hard");
        /* Placement des bateaux */
        player1.placingIAShips();
        player2.placingIAShips();
        do{
            turn=!(turn);
            if(turn){ //player1
                player1.shootMedium(player2);
            }else if(!turn){ //player2
                player2.shootHard(player1, stateArray);
            }
        }while(!(player1.endGame())&&!(player2.endGame())); //A vérifier au cas ou
        /* Fin du jeu */
        if(player1.endGame()){
            System.out.println("Hard win!");
            return 1;
        }else{
            System.out.println("Medium win!");
            return 0;
        }
    }

    /**
     * Mode Beginner versus Hard
     * 
     * @return 1 if Hard win the Game or 0 if Easy win
     */
    public static int EvH(){
        /* Medium VS HardMode */
        /* Initialisation nouvelle partie */
        boolean turn = false;
        Player player1,player2;
        boolean []stateArray=new boolean[3];
        stateArray[0]=false;
        stateArray[1]=false;
        stateArray[2]=false;
        player1 = new Player("Easy");
        player2 = new Player("Hard");
        /* Placement des bateaux */
        player1.placingIAShips();
        player2.placingIAShips();
        do{
            turn=!(turn);
            if(turn){ //player1
                player1.shootEasy(player2);
            }else if(!turn){ //player2
                player2.shootHard(player1, stateArray);
            }
        }while(!(player1.endGame())&&!(player2.endGame())); //A vérifier au cas ou
        /* Fin du jeu */
        if(player1.endGame()){
            System.out.println("Hard win!");
            return 1;
        }else{
            System.out.println("Easy win!");
            return 0;
        }
    }
    
    /**
     * Main method used to count and write into the csv file
     * 
     * @param args
     * @throws IOException if can't create a file, or it will sen an error message
     */
    public static void main(String[]args)throws IOException{
        int evm=0;
        int evh=0;
        int mvh=0;
        
        for(int i=0;i<100;i++){
            evm+=EvM();
            evh+=EvH();
            mvh+=MvH();
        }
        
        StringBuffer buffer = new StringBuffer();
        String line_1 = "AI Name; score; AI Name2; score2";
        String line_2 = "AI Level Beginner; "+evm+"; Level Medium; "+(100-evm);
        String line_3 = "AI Level Beginner; "+evh+"; Level Hard; "+(100-evh);
        String line_4 = "AI Level Medium; "+mvh+"; Level Hard;"+(100-mvh);

        
        try (FileWriter fileWriter = new FileWriter(new File("ai_proof.csv"))) {
            fileWriter.write(buffer.toString());
            fileWriter.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
