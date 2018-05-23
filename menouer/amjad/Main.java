package menouer.amjad;
import java.util.*;

/**
 * Main class 
 * 
 * It contain methods for each mode
 * It contain a csv file generator (for the ai proof)
 * 
 * @see Player
 * @see Ship
 * 
 * @author menouer
 */
public class Main {  

    /**
     * Mode Player versus Player
     * 
     * @see Player#placingShips() 
     * @see Player#printGrid()
     * @see Player#shoot(ship.Player)
     * @see Player#endGame() 
     */
    public static void PvP(){
        /* Player VS Player */
        /* Initialisation nouvelle partie */
        Scanner name = new Scanner(System.in);
        String name1, name2;
        boolean turn = false;
        Player player1,player2;
        System.out.print("Name of the first player : ");
        name1 = name.nextLine();
        System.out.print("Name of the second player : ");
        name2 = name.nextLine();
        player1 = new Player(name1);
        player2 = new Player(name2);
        player1.printGrid();player2.printGrid();
        /* Placement des bateaux */
        player1.placingShips();
        player1.printGrid();
        player2.placingShips();
        player2.printGrid();
        System.out.println("The game can begin !");
        do{
            turn=!(turn);
            if(turn){ //player1
                System.out.println(player1.getName()+", it's your turn");
                player1.shoot(player2);
            }else if(!turn){ //player2
                System.out.println(player2.getName()+", it's your turn");
                player2.shoot(player1);
            }
        }while(!(player1.endGame())&&!(player2.endGame())); //A vérifier au cas ou
        /* Fin du jeu */
        if(player1.endGame()){
            System.out.println("Congratulations, "+player2.getName()+", you just win against "+player1.getName());
        } else if(player2.endGame()){
            System.out.println("Congratulations, "+player1.getName()+", you just win against "+player2.getName());
        }

        //Joueur entre une coordonnée -> Missile
        //Joueur entre score -> Score actuel (nombre de coups + bateau touchés/coulés)        
    } //Finish

    /**
     * Mode Player versus Easy
     * 
     * @see Player#shootEasy(ship.Player)
     * @see Player#shoot(ship.Player) 
     */
    public static void PvE(){
        /* Player VS EasyMode */
        /* Initialisation nouvelle partie */
        Scanner name = new Scanner(System.in);
        String name1;
        boolean turn = false;
        Player player1,player2;
        System.out.print("What's your name? ");
        name1 = name.nextLine();
        player1 = new Player(name1);
        player2 = new Player("Easy");
        player1.printGrid();
        /* Placement des bateaux */
        player1.placingShips();
        player1.printGrid();
        player2.placingIAShips(); //A creer
        System.out.println("The game can begin !");
        do{
            turn=!(turn);
            if(turn){ //player1
                System.out.println(player1.getName()+", it's your turn");
                player1.printOpponentGrid();
                player1.printGrid();
                player1.shoot(player2);
            }else if(!turn){ //player2
                System.out.println(player2.getName()+", it's your turn");
                player2.shootEasy(player1); //A creer
            }
        }while(!(player1.endGame())&&!(player2.endGame())); //A vérifier au cas ou
        /* Fin du jeu */
        if(player1.endGame()){
            System.out.println("Congratulations, "+player2.getName()+", you just win against "+player1.getName());
        } else if(player2.endGame()){
            System.out.println("Congratulations, "+player1.getName()+", you just win against "+player2.getName());
        }

        //Joueur entre une coordonnée -> Missile
        //Joueur entre score -> Score actuel (nombre de coups + bateau touchés/coulés) 
    } //Finish

    /**
     * Mode Player versus Medium
     * 
     * @see Player.shootMedium(Player)
     * @see Player.shoot(Player)
     */
    public static void PvM(){
        /* Player VS MediumMode */
        /* Initialisation nouvelle partie */
        Scanner name = new Scanner(System.in);
        String name1;
        boolean turn = false;
        Player player1,player2;
        System.out.print("What's your name? ");
        name1 = name.nextLine();
        player1 = new Player(name1);
        player2 = new Player("Medium");
        player1.printGrid();
        /* Placement des bateaux */
        player1.placingShips();
        player1.printGrid();
        player2.placingIAShips();
        System.out.println("The game can begin !");
        do{
            turn=!(turn);
            if(turn){ //player1
                System.out.println(player1.getName()+", it's your turn");
                player1.shoot(player2);
            }else if(!turn){ //player2
                System.out.println(player2.getName()+", it's your turn");
                player2.shootMedium(player1); //A creer
            }
        }while(!(player1.endGame())&&!(player2.endGame())); //A vérifier au cas ou
        /* Fin du jeu */
        if(player1.endGame()){
            System.out.println("Congratulations, "+player2.getName()+", you just win against "+player1.getName());
        } else if(player2.endGame()){
            System.out.println("Congratulations, "+player1.getName()+", you just win against "+player2.getName());
        }

        //Joueur entre une coordonnée -> Missile
        //Joueur entre score -> Score actuel (nombre de coups + bateau touchés/coulés) 
    } //Finish

    /**
     * Mode Player versus Hard
     * 
     * @see Player.shootHard(Player)
     * @see Player.shoot(Player)
     */
    public static void PvH(){
        /* Player VS HardMode */
        /* Initialisation nouvelle partie */
        Scanner name = new Scanner(System.in);
        String name1;
        boolean []stateArray=new boolean[3];
        stateArray[0]=false;
        stateArray[1]=false;
        stateArray[2]=false;
        
        boolean turn = false;
        Player player1,player2;
        System.out.print("What's your name? ");
        name1 = name.nextLine();
        player1 = new Player(name1);
        player2 = new Player("Hard");
        player1.printGrid();
        /* Placement des bateaux */
        player1.placingShips();
        player1.printGrid();
        player2.placingIAShips(); //A creer
        System.out.println("The game can begin !");
        do{
            turn=!(turn);
            if(turn){ //player1
                System.out.println(player1.getName()+", it's your turn");
                player1.shoot(player2);
            }else if(!turn){ //player2
                System.out.println(player2.getName()+", it's your turn");
                player2.shootHard(player1, stateArray);
            }
        }while(!(player1.endGame())&&!(player2.endGame())); //A vérifier au cas ou
        /* Fin du jeu */
        if(player1.endGame()){
            System.out.println("Congratulations, "+player2.getName()+", you just win against "+player1.getName());
        } else if(player2.endGame()){
            System.out.println("Congratulations, "+player1.getName()+", you just win against "+player2.getName());
        }

        //Joueur entre une coordonnée -> Missile
        //Joueur entre score -> Score actuel (nombre de coups + bateau touchés/coulés) 
    } //Finish

    /**
     * Contain the game
     * 
     * @see PvP()
     * @see PvE()
     * @see PvM()
     * @see PvH()
     * @param args
     */
    public static void main(String[] args) {
        Scanner choice = new Scanner(System.in);
        System.out.println("Hello there, welcome to Battleship !!");
        System.out.println("Please, choose one of the four mode below...");
        System.out.println("");
        System.out.println("1:Player vs Player");
        System.out.println("2:Player vs IA (easy)");
        System.out.println("3:Player vs IA (medium)");
        System.out.println("4:Player vs IA (hard)");
        System.out.println("(Please write 1,2,3 or 4 in the keyboard)");
        int response = choice.nextInt();
        switch(response){
            case(1):
                PvP();
                break;
            case(2):
                PvE();
                break;
            case(3):
                PvM();
                break;
            case(4):
                PvH();
                break;
            default:
                System.out.println("S-S-Sorry master... I don't understand your response... I will sleep a bit... See you next time");
                break;
        }
    }
}
