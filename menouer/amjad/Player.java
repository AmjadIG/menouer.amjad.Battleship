package menouer.amjad;
import java.util.*;

/**
 *Class Player manage the grids and the ships of the player
 * 
 * A Player is caracterized by:
 * A name
 * A shipArray (5 ships)
 * Its opponent grid (an arraylist of spot)
 * 
 * @see Main
 * @see Ship
 * 
 * @author menouer
 */
public class Player{
    
    private String name;
    private ArrayList<String> opponentGrid;
    private Ship[] shipArray;
    
    public Player(String name){
        this.name = name;
        this.opponentGrid = new ArrayList();
        this.shipArray = new Ship[5];
        this.shipArray[0]= new Ship(5,"Carrier");
        this.shipArray[1]= new Ship(4,"Battleship");
        this.shipArray[2]= new Ship(3,"Cruiser");
        this.shipArray[3]= new Ship(3,"Submarine");
        this.shipArray[4]= new Ship(2,"Destroyer");
    }
    
    public String getName() {
        return name;
    }
    public ArrayList<String> getOpponentGrid() {
        return opponentGrid;
    }
    public Ship[] getShipArray() {
        return shipArray;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    public void setOpponentGrid(ArrayList<String> grid) {
        this.opponentGrid = grid;
    }
    public void setShipArray(Ship[] shipArray) {
        this.shipArray = shipArray;
    }    
    
    // Methods

    /**
     * Allows the player to place his ships
     */
    public void placingShips(){
        System.out.println(this.name+", place the Carrier please (5):");
        this.shipArray[0].inputCoord();
        System.out.println("Place the Battleship please (4):");
        do{
            this.shipArray[1].inputCoord();
        }while(this.crossingShips(this.shipArray[0],this.shipArray[1]));
        System.out.println("Place the Cruiser please (3):");
        do{
            this.shipArray[2].inputCoord();
        }while((this.crossingShips(this.shipArray[0],this.shipArray[2]))||(this.crossingShips(this.shipArray[1],this.shipArray[2])));
        System.out.println("Place the Submarine please (3):");
        do{
            this.shipArray[3].inputCoord();
        }while((this.crossingShips(this.shipArray[0],this.shipArray[3]))||(this.crossingShips(this.shipArray[1],this.shipArray[3]))||(this.crossingShips(this.shipArray[2],this.shipArray[3])));
        System.out.println("Place the Destroyer please (2):");
        do{
            this.shipArray[4].inputCoord();
        }while((this.crossingShips(this.shipArray[0],this.shipArray[4]))||(this.crossingShips(this.shipArray[1],this.shipArray[4]))||(this.crossingShips(this.shipArray[2],this.shipArray[4]))||(this.crossingShips(this.shipArray[3],this.shipArray[4])));
    } //Placement des bateaux

    /**
     * Placing the AI Ships randomly in the grid
     * 
     * @see Ship#placingAleaShip() 
     * @see Player#crossingShips(ship.Ship, ship.Ship) 
     */
    public void placingIAShips(){
        this.shipArray[0].placingAleaShip();
        do{
            this.shipArray[1].placingAleaShip();
        }while(this.crossingShips(this.shipArray[0],this.shipArray[1]));
        do{
            this.shipArray[2].placingAleaShip();
        }while((this.crossingShips(this.shipArray[0],this.shipArray[2]))||(this.crossingShips(this.shipArray[1],this.shipArray[2])));
        do{
            this.shipArray[3].placingAleaShip();
        }while((this.crossingShips(this.shipArray[0],this.shipArray[3]))||(this.crossingShips(this.shipArray[1],this.shipArray[3]))||(this.crossingShips(this.shipArray[2],this.shipArray[3])));
        do{
            this.shipArray[4].placingAleaShip();
        }while((this.crossingShips(this.shipArray[0],this.shipArray[4]))||(this.crossingShips(this.shipArray[1],this.shipArray[4]))||(this.crossingShips(this.shipArray[2],this.shipArray[4]))||(this.crossingShips(this.shipArray[3],this.shipArray[4])));
    } //Utilisé pour toute les IA

    /**
     * Verify if the Ships are crossing themselves
     * 
     * The ships passed as parameter are analyze for each of their elements
     * 
     * @param s1 is an instance of a Ship
     * @param s2 is another Ship
     * @return true if two ships crossed ourselves
     */
    public boolean crossingShips(Ship s1,Ship s2){
        for(String object1 : s1.getCoordShip()){
            for(String object2 : s2.getCoordShip()){
                if(object1.equals(object2)){
                    System.out.println("The boat cross each other...");
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     *  Determine if the game reach its end
     * 
     * @see Ship#hasSink() 
     * 
     * @return true if all the player's ships are sinked, or it returns false
     */
    public boolean endGame(){
        return((this.shipArray[0].hasSink())&&(this.shipArray[1].hasSink())&&(this.shipArray[2].hasSink())&&(this.shipArray[3].hasSink())&&(this.shipArray[4].hasSink()));
    }
    
    /**
     * Print the player's Grid
     * 
     * @see Player#tabShip()
     */
    public void printGrid(){
        String tShip[][] = this.tabShip();
        System.out.println("");
        System.out.println("  A B C D E F G H I J");
        for(int i=0;i<10;i++){
            System.out.print(i);
            for(int j=0;j<10;j++){
                System.out.print(" "+tShip[j][i]);
            }
            System.out.println("");
        }
    }

    /**
     * TabShip is a premise of printGrid()
     * 
     * @return a matrice of String (used for the printing)
     */
    public String[][] tabShip(){
        String tShip[][] = new String[10][10];
        for (int a=0;a<10;a++){
            for(int b=0;b<10;b++){
                tShip[a][b]="~";
            }
        }
        String stringCut[];    
        int coordLine;
        ArrayList<String> ship[] = new ArrayList[this.shipArray.length];
        //On duplique les données pour ne pas modifier celle que l'on possède...
        for(int a=0;a<this.shipArray.length;a++){
            ship[a]=this.shipArray[a].getCoordShip();
        }
        //On met les éléments dans un tableau...
        for(int i=0;i<this.shipArray.length;i++){
            for(int j=0;j<ship[i].size();j++){
                stringCut = ship[i].get(j).split("");//Bateau n°i,coordonnées j
                coordLine = Integer.parseInt(stringCut[1]);
                switch(stringCut[0]){
                    case("A"):
                        tShip[0][coordLine] ="o";
                        break;
                    case("B"):
                        tShip[1][coordLine] = "o";
                        break;    
                    case("C"):
                        tShip[2][coordLine] = "o";
                        break;
                    case("D"):
                        tShip[3][coordLine] = "o";
                        break;
                    case("E"):
                        tShip[4][coordLine] = "o";
                        break;
                    case("F"):
                        tShip[5][coordLine] = "o";
                        break;
                    case("G"):
                        tShip[6][coordLine] = "o";
                        break;
                    case("H"):
                        tShip[7][coordLine] = "o";
                        break;
                    case("I"):
                        tShip[8][coordLine] = "o";
                        break;
                    case("J"):
                        tShip[9][coordLine] = "o";
                        break;
                }
            }
        }
        return tShip;
    }
    
    /**
     * Print the view of the opponent Grid
     * 
     * @see Player#tabOpponentGrid() 
     */
    public void printOpponentGrid(){
        String tOG[][] = this.tabOpponentGrid();
        System.out.println("");
        System.out.println("  A B C D E F G H I J");
        for(int i=0;i<10;i++){
            System.out.print(i);
            for(int j=0;j<10;j++){
                System.out.print(" "+tOG[j][i]);
            }
            System.out.println("");
        }
    }
    
    /**
     * TabOpponentGrid is a premise of printOpponentGrid()
     * 
     * @return a matrice of String (used for the printing)
     */
    public String[][] tabOpponentGrid(){
        String opponentGrid[][] = new String[10][10];
        for (int a=0;a<10;a++){
            for(int b=0;b<10;b++){
                opponentGrid[a][b]="~";
            }
        }
        String stringCut[];    
        int coordLine;
        ArrayList<String> oG = new ArrayList();
        //We duplicate the data to make sure we won't change our initial ArrayList
        oG = this.opponentGrid;
        for(int j=0;j<oG.size();j++){
                if(oG.get(j).length()==2){
                    stringCut = oG.get(j).split("");
                    coordLine = Integer.parseInt(stringCut[1]);
                    switch(stringCut[0]){
                        case("A"):
                            opponentGrid[0][coordLine] ="x";
                            break;
                        case("B"):
                            opponentGrid[1][coordLine] = "x";
                            break;    
                        case("C"):
                            opponentGrid[2][coordLine] = "x";
                            break;
                        case("D"):
                            opponentGrid[3][coordLine] = "x";
                            break;
                        case("E"):
                            opponentGrid[4][coordLine] = "x";
                            break;
                        case("F"):
                            opponentGrid[5][coordLine] = "x";
                            break;
                        case("G"):
                            opponentGrid[6][coordLine] = "x";
                            break;
                        case("H"):
                            opponentGrid[7][coordLine] = "x";
                            break;
                        case("I"):
                            opponentGrid[8][coordLine] = "x";
                            break;
                        case("J"):
                            opponentGrid[9][coordLine] = "x";
                            break;
                    }
                }else if(oG.get(j).length()==3){
                    stringCut = oG.get(j).split("");//Ship n°i,position j
                    coordLine = Integer.parseInt(stringCut[1]);
                    switch(stringCut[0]){
                        case("A"):
                            opponentGrid[0][coordLine] ="o";
                            break;
                        case("B"):
                            opponentGrid[1][coordLine] = "o";
                            break;    
                        case("C"):
                            opponentGrid[2][coordLine] = "o";
                            break;
                        case("D"):
                            opponentGrid[3][coordLine] = "o";
                            break;
                        case("E"):
                            opponentGrid[4][coordLine] = "o";
                            break;
                        case("F"):
                            opponentGrid[5][coordLine] = "o";
                            break;
                        case("G"):
                            opponentGrid[6][coordLine] = "o";
                            break;
                        case("H"):
                            opponentGrid[7][coordLine] = "o";
                            break;
                        case("I"):
                            opponentGrid[8][coordLine] = "o";
                            break;
                        case("J"):
                            opponentGrid[9][coordLine] = "o";
                            break;
                }
            }
        }return opponentGrid;
    }
    
    /**
     * A Method to shoot for the player mode
     * (It is used for IRL Player)
     * @param player which should be the opponent
     * 
     * @see Ship#isHit(java.lang.String) 
     * @see Ship#hasSink() 
     * @see Player#printOpponentGrid()
     * @see Player#shootErrorFinder(java.lang.String, ship.Player)
     */
    public void shoot(Player player){
        Scanner shoot = new Scanner(System.in);
        System.out.println("Where do you want to shoot?");
        this.printOpponentGrid();
        String coordMissile = shoot.nextLine();
        String cM = coordMissile; //Duplicat
        this.shootErrorFinder(coordMissile,player); //Error verification
        
        for(int i=0;i<player.shipArray.length;i++){
            if(player.shipArray[i].isHit(coordMissile)){
                coordMissile+="t";
                this.opponentGrid.add(coordMissile);
                System.out.println("Hit !!");
                for(int j = 0; j<player.shipArray[i].getCoordShip().size();j++){
                    if(player.shipArray[i].getCoordShip().get(j).equals(cM)){
                        player.shipArray[i].getCoordShip().remove(j);
                    }
                }
                if(player.shipArray[i].hasSink()){
                    System.out.println("Sink !!!");
                    System.out.println("You just destroy your opponent's "+player.shipArray[i].getName()+" !!!");
                }
            }else {this.opponentGrid.add(coordMissile);}
        }
        this.printOpponentGrid();
    }

    /**
     * A Method to shoot for the beginner mode
     * (Used for Easy AI)
     * 
     * Shoot Randomly
     * 
     * @param player which should be the opponent
     * 
     * @see Ship#isHit(java.lang.String)
     * @see Ship#hasSink()
     */
    public void shootEasy(Player player){
        Random rand=new Random();
        int randLine, randCol;
        randLine = rand.nextInt(10); randCol = rand.nextInt(10)+65;
        String line=String.valueOf(randLine);
        String col=Character.toString((char)randCol);
        String coordMissile=col+line;
        for(int i=0;i<player.shipArray.length;i++){
            if(player.shipArray[i].isHit(coordMissile)){
                System.out.println("Hit !!");
                int index = player.shipArray[i].getCoordShip().indexOf(coordMissile);
                player.shipArray[i].getCoordShip().remove(index);
                if(player.shipArray[i].hasSink()){
                    System.out.println("Sink !!!");
                    System.out.println("You just destroy your opponent's "+player.shipArray[i].getName()+" !!!");
                }
            }
        }
    }

    /**
     * A Method to shoot for the medium mode
     * (Used for MediumAI)
     * 
     * Shoot Randomly at first
     * Remember its own shoot so it won't repeat its own actions
     * 
     * @param player which should be the opponent
     * 
     * @see Ship#isHit(java.lang.String) 
     * @see Ship#hasSink()
     */
    public void shootMedium(Player player){
        Random rand=new Random();
        int randLine, randCol;
        String coordMissile;
        do{
            randLine = rand.nextInt(10); randCol = rand.nextInt(10)+65;
            String line=String.valueOf(randLine);
            String col=Character.toString((char)randCol);
            coordMissile=col+line;
        }while(this.opponentGrid.contains(coordMissile)); //On réitère le tir tant que l'on a déja tiré au même endroit
        for(int i=0;i<player.shipArray.length;i++){
            if(player.shipArray[i].isHit(coordMissile)){
                System.out.println("Hit !!");
                int index = player.shipArray[i].getCoordShip().indexOf(coordMissile);
                player.shipArray[i].getCoordShip().remove(index);
                if(player.shipArray[i].hasSink()){
                    System.out.println("Sink !!!");
                    System.out.println("You just destroy your opponent's "+player.shipArray[i].getName()+" !!!");
                }
            }
        }
        this.opponentGrid.add(coordMissile); //On garde en mémoire le tir que l'on vient d'effectuer
    }

    /**
     * A Method to shoot for the hard mode
     * (Used for Hard AI)
     * 
     * Shoot Randomly at first
     * Then It will try to search the ship around and then sink it
     * Then it re-shoot randomly
     * It remembers its own shoots
     * 
     * First step : shoot randomly
     * Second step : cross shoot (search of the second part)
     * Third step : determinize the linearity of the ship (vertical/horizontal)
     * Fourth step : shoot continuously in the same line
     * 
     * @param player which should be the opponent
     * @param stateRush which is an Array of three booleans (as indicators)
     * 
     * @see Player#intToStr(int, int) 
     * @see Player#crossShoot(java.lang.String) 
     * @see Player#positionMap(java.lang.String) 
     * @see Player#linearShoot(java.lang.String, int) 
     * @see Player#linearValue(java.lang.String, java.lang.String) 
     * 
     * @return an Array of three booleans (which are the previous indicators)
     */
    public boolean []shootHard(Player player,boolean []stateRush){
        Random rand=new Random();
        int randLine, randCol;
        String container, containerB;
        String coordMissile;
        if(!stateRush[0]){
            stateRush[0]=false;stateRush[1]=false;stateRush[2]=false;
            do{
                randLine = rand.nextInt(10); randCol = rand.nextInt(10)+65;
                coordMissile= this.intToStr(randCol, randLine);
            }while(this.opponentGrid.contains(coordMissile)); //On réitère le tir tant que l'on a déja tiré au même endroit
            for(int i=0;i<5;i++){
                if(player.shipArray[i].isHit(coordMissile)){
                    coordMissile+="$";//We add a particular flag
                    this.opponentGrid.add(coordMissile);
                    stateRush[0]=true;
                    stateRush[1]=false;
                    stateRush[2]=false;
                    return stateRush;
                }
            }
            this.opponentGrid.add(coordMissile);
            stateRush[0]=false;
            stateRush[1]=false;
            stateRush[2]=false;
            return stateRush;
        } else if(stateRush[0]&&!stateRush[1]){
            for(int j=this.opponentGrid.size()-1;j>0;j--){
                if(this.opponentGrid.get(j).length()==3 && this.opponentGrid.get(j).substring(2,3).equals("$")){
                    container = this.opponentGrid.get(j);
                    String []constr = container.split("");
                    coordMissile = this.crossShoot(constr[0]+constr[1]);
                    for(int k=0;k<5;k++){
                        if(player.shipArray[k].isHit(coordMissile)){
                            coordMissile+="@";
                            this.opponentGrid.add(coordMissile);
                            stateRush[0]=true;
                            stateRush[1]=true;
                            stateRush[2]=false;
                            return stateRush;
                        }
                    }
                    this.opponentGrid.add(coordMissile);
                    stateRush[0]=true;
                    stateRush[1]=false;
                    stateRush[2]=false;
                    return stateRush;
                }
            }
        } else if(stateRush[0]&&stateRush[1]){
            for(int l=this.opponentGrid.size()-1;l>0;l--){
                if(this.opponentGrid.get(l).substring(2, 3).equals("@")){
                    for(int m=l;m>0;m--){
                        if(this.opponentGrid.get(m).substring(2,3).equals("$")){
                            container = this.opponentGrid.get(l).substring(0, 2);
                            containerB = this.opponentGrid.get(m).substring(0, 2);
                            coordMissile = this.linearShoot(containerB,linearValue(container, containerB));
                            for(int n=0;n<5;n++){
                                if(player.shipArray[n].isHit(coordMissile)){
                                    if(player.shipArray[n].hasSink()){
                                        coordMissile+="t";
                                        this.opponentGrid.add(coordMissile);
                                        stateRush[0]=false;
                                        stateRush[1]=false;
                                        stateRush[2]=false;
                                        return stateRush;
                                    }else{
                                        coordMissile+="@";
                                        this.opponentGrid.add(coordMissile);
                                        stateRush[0]=true;
                                        stateRush[1]=true;
                                        stateRush[2]=true;
                                        return stateRush;
                                    }
                                }else{
                                    this.opponentGrid.add(coordMissile);
                                    stateRush[0]=true;
                                    stateRush[1]=true;
                                    stateRush[2]=false;
                                    return stateRush;
                                }
                            }
                        }
                    }
                }
            }
        }else {
            stateRush[0]=false;
            stateRush[1]=false;
            stateRush[2]=false;
            return stateRush;
        }
        stateRush[0]=false;
        stateRush[1]=false;
        stateRush[2]=false;
        return stateRush;
        //this.opponentGrid.add(coordMissile); HAS SINKKK?????
    }
    
    /**
     * Search around one of the spot previously hit (where a ship is placed)
     * 
     * @param position is the begin position
     * @return one of the position around the first missile launch
     */
    public String crossShoot(String position){
        int []coord=this.splitToInt(position);
        int pM=this.positionMap(position);
        switch(pM){
            case(0):
                if(!this.opponentGrid.contains(this.intToStr(coord[0]+1, coord[1])))return this.intToStr(coord[0]+1, coord[1]);
                else if(this.opponentGrid.contains(this.intToStr(coord[0]+1, coord[1])) && !this.opponentGrid.contains(this.intToStr(coord[0]-1, coord[1])))return this.intToStr(coord[0]-1, coord[1]);
                else if(this.opponentGrid.contains(this.intToStr(coord[0]+1, coord[1])) && this.opponentGrid.contains(this.intToStr(coord[0]-1, coord[1])) && !this.opponentGrid.contains(this.intToStr(coord[0], coord[1]+1)))return this.intToStr(coord[0], coord[1]+1);
                else return this.intToStr(coord[0], coord[1]-1);
            case(1):
                if(!this.opponentGrid.contains(this.intToStr(coord[0]+1, coord[1])))return this.intToStr(coord[0]+1, coord[1]);
                else if(this.opponentGrid.contains(this.intToStr(coord[0]+1, coord[1])) && !this.opponentGrid.contains(this.intToStr(coord[0]-1, coord[1])))return this.intToStr(coord[0]-1, coord[1]);
                else return this.intToStr(coord[0], coord[1]+1);
            case(2):
                if(!this.opponentGrid.contains(this.intToStr(coord[0]+1, coord[1])))return this.intToStr(coord[0]+1, coord[1]);
                else if(this.opponentGrid.contains(this.intToStr(coord[0]+1, coord[1])) && !this.opponentGrid.contains(this.intToStr(coord[0]-1, coord[1])))return this.intToStr(coord[0]-1, coord[1]);
                else return this.intToStr(coord[0], coord[1]-1);
            case(3):
                if(!this.opponentGrid.contains(this.intToStr(coord[0]+1, coord[1])))return this.intToStr(coord[0]+1, coord[1]);
                else if(this.opponentGrid.contains(this.intToStr(coord[0]+1, coord[1])) && !this.opponentGrid.contains(this.intToStr(coord[0], coord[1]+1)))return this.intToStr(coord[0], coord[1]+1);
                else return this.intToStr(coord[0], coord[1]-1);
            case(4):
                if(!this.opponentGrid.contains(this.intToStr(coord[0]-1, coord[1])))return this.intToStr(coord[0]-1, coord[1]);
                else if(this.opponentGrid.contains(this.intToStr(coord[0]-1, coord[1])) && !this.opponentGrid.contains(this.intToStr(coord[0], coord[1]+1)))return this.intToStr(coord[0], coord[1]+1);
                else return this.intToStr(coord[0], coord[1]-1);
            case(5):
                if(!this.opponentGrid.contains(this.intToStr(coord[0]+1, coord[1]))) return this.intToStr(coord[0]+1, coord[1]);
                else return this.intToStr(coord[0], coord[1]+1);
            case(6):
                if(!this.opponentGrid.contains(this.intToStr(coord[0]-1, coord[1]))) return this.intToStr(coord[0]-1, coord[1]);
                else return this.intToStr(coord[0], coord[1]+1);
            case(7)://
                if(!this.opponentGrid.contains(this.intToStr(coord[0]+1, coord[1]))) return this.intToStr(coord[0]+1, coord[1]);
                else return this.intToStr(coord[0], coord[1]-1);
            case(8)://
                if(!this.opponentGrid.contains(this.intToStr(coord[0]-1, coord[1]))) return this.intToStr(coord[0]-1, coord[1]);
                else return this.intToStr(coord[0], coord[1]-1);
            default:return "Error";
        }
    }

    /**
     * Used for the HardAI to target the line where the boat were once detected at first 
     * 
     * @param position (previous spot where we fired)
     * @param val determine the linearity (vertical/horizontal)
     * 
     * @see Player#splitToInt(java.lang.String) 
     * @see Player#intToStr(int, int) 
     * @see Player#incremente(int) 
     * @see Player#getOpponentGrid() 
     * 
     * @return a string in the same line that the ship the HardAI want to Sink
     */
    public String linearShoot(String position,int val){
        int []analyze=this.splitToInt(position);
        if (val==0 && this.incremente(analyze[0])){ //Horizontal +
            do{
                analyze[0]++;
            }while(this.getOpponentGrid().contains(this.intToStr(analyze[0], analyze[1])));
            return this.intToStr(analyze[0], analyze[1]);
        }else if(val==0 && !this.incremente(analyze[0])){ // Horizontal -
            do{
                analyze[0]--;
            }while(this.getOpponentGrid().contains(this.intToStr(analyze[0], analyze[1])));
            return this.intToStr(analyze[0], analyze[1]);
        }else if(val==1 && analyze[1]<9){ // Vertical +
            do{
                analyze[1]++;
            }while(this.getOpponentGrid().contains(this.intToStr(analyze[0], analyze[1])));
            return this.intToStr(analyze[0], analyze[1]);
        }else{
            do{
                analyze[1]--;
            }while(this.getOpponentGrid().contains(this.intToStr(analyze[0], analyze[1])));
            return this.intToStr(analyze[0], analyze[1]);
        }
    }

    /**
     * Let the Hard AI know if the line is vertical or horizontal
     * The line traced by the two positions
     * 
     * @param coordA is a spot int the grid
     * @param coordB is a spot int the grid
     * 
     * @return 0 if the line we observe is horizontal, or 1 if its vertical
     */
    public int linearValue(String coordA,String coordB){
        String []splitA=coordA.split("");
        String []splitB=coordB.split("");
        if(splitA[0].equals(splitB[0]))return 1; //vertical
        else return 0; //horizontal
    }

    /**
     * Determine if the ACSII value can be incremented
     * 
     * @param ASCII (value of a String that was previously cast into an integer)
     * @return true if we can incremente the value without overstep the board, else it will be false
     */
    public boolean incremente(int ASCII){
        return(ASCII!=74);
    }

    /**
     * Define if the position of a spot
     * 
     * @param coord which is a spot somewhere in the grid
     * @return an int, which is an indicator of its relative position
     */
    public int positionMap(String coord){
        String []splitC=coord.split("");
        int line = Integer.valueOf(splitC[1]);
        int col = (int)splitC[0].charAt(0);
        if(line>0 && line<9 && col>65 && col<74) return 0; //center
        if(line==0 && line<9 && col>65 && col<74) return 1; //upper side
        if(line>0 && line==9 && col>65 && col<74) return 2; //lower side
        if(line>0 && line<9 && col==65 && col<74) return 3; //left side
        if(line>0 && line<9 && col>65 && col==74) return 4; //right side
        if(line==0 && line<9 && col==65 && col<74) return 5; //upper left
        if(line==0 && line<9 && col>65 && col==74) return 6; //upper right
        if(line>0 && line==9 && col==65 && col<74) return 7; //lower left
        if(line>0 && line==9 && col>65 && col==74) return 8; //lower right
        else return -1; //error
    }
    
    /**
     * Split a string and convert each part to an integer
     * 
     * @param coord which is a String value
     * @return an Array of two integer
     * The first one is an ACSII value (column) and the second one is a basic number (line)
     */
    public int [] splitToInt(String coord){
        int []splitF;
        splitF = new int[2];
        String []splitC = coord.split("");
        int line = Integer.valueOf(splitC[1]);
        int col = (int) splitC[0].charAt(0);
        splitF[0]=col;
        splitF[1]=line;
        return splitF;
    }

    /**
     * Transform two integer into a string position
     * 
     * @param col (it is an ACSII value that will be cast into a char an then a String)
     * @param line is an integer between 0 and 9
     * 
     * @return a String, and a position more precisely
     */
    public String intToStr(int col,int line){
        String colS = Character.toString((char)col);
        String lineS = String.valueOf(line);
        String coord=colS+lineS;
        return coord;
    }
    
    /**
     * Manage the error
     * 
     * @param coordMissile
     * @param player
     */
    public void shootErrorFinder(String coordMissile,Player player){
        String []cM=coordMissile.split("");
        int cMCol = (int) cM[0].charAt(0); //String to ASCII
        int cMLine = Integer.valueOf(cM[1]);
        if(coordMissile.length()!=2){
            System.out.print("Error... ");
            this.shoot(player);
        }else if(cMCol<65 || cMCol>74){
            System.out.print("Error... ");
            this.shoot(player);
        }else if(cMLine<0 || cMLine>9){
            System.out.print("Error... ");
            this.shoot(player);
        }
    }
}