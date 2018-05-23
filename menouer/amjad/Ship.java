package menouer.amjad;
import java.util.*;

/**
 * Class Ship contain all the informations of the ship
 * 
 * A Ship is caracterized by :
 * A start position, and an end position
 * A list defined by the start and the end position
 * A size and a name
 * 
 * For more informations, check the doc below
 * 
 * @see Main
 * @see Player
 * 
 * @author menouer
 */
public class Ship{
    // Attributes
    private String startCoord; //Coordonnée de début
    private String endCoord; //Coordonnée de fin
    private ArrayList <String> coordShip; //Contient la liste des coordonnées du bateau
    private int size; //taille du bateau
    private String name;//nom du bateau

    // Constructors
    public Ship (String startCoord, String endCoord){
        this.startCoord=startCoord;
        this.endCoord=endCoord;
    }
    public Ship (int size){
        this.size=size;
        this.coordShip = new ArrayList ();
    }
    public Ship(int size,String name){
        this.size=size;
        this.name=name;
        this.coordShip = new ArrayList ();
    }

    // Getters
    public ArrayList<String> getCoordShip() {
        return this.coordShip;
    }
    public String getStartCoord() {
        return startCoord;
    }
    public String getEndCoord() {
        return endCoord;
    }
    public int getSize() {
        return size;
    }
    public String getName() {
        return name;
    }
    
    // Setters
    public void setStartCoord(String startCoord) {
        this.startCoord = startCoord;
    }
    public void setEndCoord(String endCoord) {
        this.endCoord = endCoord;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setCoordShip(ArrayList<String> coordShip) {
        this.coordShip = coordShip;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    //Methods

    /**
     * Eject the error when a ship is placed
     * 
     * @return true if there is an error, false if there isn't
     */
    public boolean errorFinder(){
        String []sC=this.startCoord.split("");
        String []eC=this.endCoord.split("");
        int sLine = Integer.valueOf(sC[1]);
        int eLine = Integer.valueOf(eC[1]);
        int min = Math.min(sLine,eLine);
        int max = Math.max(sLine,eLine);
        int ecartLin = max-min+1;
        char sCol = sC[0].charAt(0);
        char eCol = eC[0].charAt(0);
        int asciiSC = (int) sCol;
        int asciiEC = (int) eCol;
        int asciiMin = Math.min(asciiSC,asciiEC);
        int asciiMax = Math.max(asciiSC,asciiEC);
        int ecartCol = asciiMax-asciiMin+1;
        if(this.startCoord.equals(this.endCoord)){
            return true;
        }else if(this.startCoord.length()!=2||this.endCoord.length()!=2){
            System.out.println("You just enter a bad position...");
            return true;
        }else if(sC[0].equals(eC[0]) && sC[1].equals(eC[1])){
            System.out.println("You can't write the same position for the start and the end...");
            return true;
        }else if(!(sC[0].equals(eC[0])) && !(sC[1].equals(eC[1]))){
            System.out.println("The ship cant be in diagonal...");
            return true;
        }else if(ecartLin!=this.size && ecartCol!=this.size){
            System.out.println("Your ship has the wrong size...");
            return true;
        }else if(asciiSC<65 || asciiSC>74 || asciiEC<65 || asciiEC>74){
            System.out.println("You should enter positions which are available (look at the Grid)...");
            return true;
        }else if(sLine<0 || sLine>9 || eLine<0 || eLine>9){
            return true;
        }return false;
    }

    /**
     * Tell us if the missile hit a ship or not
     * 
     * @param missileCoord is a String (uppercase and a number)
     * @return true if the  missile hit a ship, false if not
     */
    public boolean isHit(String missileCoord){ 
        return this.coordShip.contains(missileCoord);          
    }
    
    /**
     * Tell us if the ship has been sinked or not
     * 
     * @return true if the ship has been sinked, false if t hasn't
     */
    public boolean hasSink(){
        return(this.coordShip.isEmpty());
    }
    
    /**
     * Set the position of the ship in each case (horizontal) where it shoud be placed
     * 
     * @param startCoord is a String (uppercase and a number) which can be seen as a position
     * @param endCoord is a String (uppercase and a number) which can be seen as a position
     */
    public void horizontalSet(String startCoord,String endCoord){
        String coordString;
        String []sC=startCoord.split("");
        String []eC=endCoord.split("");
        char charSC = sC[0].charAt(0);
        char charEC = eC[0].charAt(0);
        int asciiSC = (int) charSC;
        int asciiEC = (int) charEC;
        int min = Math.min(asciiSC,asciiEC);
        int max = Math.max(asciiSC,asciiEC);
        for(int i=min;i<=max;i++){
            coordString=Character.toString((char)i);
            coordString+=sC[1];
            this.coordShip.add(coordString);
        }
    }

    /**
     * Set the position of the ship in each case (vertical) where it shoud be placed
     * 
     * @param startCoord is a String (uppercase and a number) which can be seen as a position
     * @param endCoord is a String (uppercase and a number) which can be seen as a position
     */
    public void verticalSet(String startCoord,String endCoord){
        String coordString;
        String []sC=startCoord.split("");
        String []eC=endCoord.split("");
        int lineSC = Integer.parseInt(sC[1]);
        int lineEC = Integer.parseInt(eC[1]);
        int min = Math.min(lineSC,lineEC);
        int max = Math.max(lineSC,lineEC);
        for(int i=min;i<=max;i++){
            coordString=Integer.toString(i);
            coordString=sC[0]+coordString;
            this.coordShip.add(coordString);
        }
    }
    
    /**
     * Allows to input the start/end position of a ship
     * 
     * @see Ship#setStartCoord(java.lang.String) 
     * @see Ship#setEndCoord(java.lang.String)
     * @see Ship#setCoordShip(java.lang.String, java.lang.String, int)
     * @see Ship#errorFinder()
     */
    public void inputCoord(){
        ArrayList coordShipArray;
        coordShipArray = new <String>ArrayList();
        this.setCoordShip(coordShipArray);
        Scanner entryKeyboard = new Scanner(System.in);
        System.out.println("Start position : ");
        String sC = entryKeyboard.nextLine();
        System.out.println("End position : ");
        String eC = entryKeyboard.nextLine();
        this.setStartCoord(sC);
        this.setEndCoord(eC);
        if(!this.errorFinder()){
            this.setCoordShip(sC,eC,this.size);
        }
        else{
            System.out.println("Error... try again.");
            this.inputCoord();
        }
    }
    
    /**
     * It is a Setter different from the basic one
     * 
     * It places the ship not from an ArrayList, but from a start/end position and the size of the ship
     * 
     * @param startCoord is a String (means the start position)
     * @param endCoord is a String (means the end position)
     * @param size of the ship
     */
    public void setCoordShip(String startCoord, String endCoord, int size){
        String sCArray[]; sCArray = startCoord.split("");
        String eCArray[]; eCArray = endCoord.split("");
        if(sCArray[0].equals(eCArray[0])){ // Vertical
            this.verticalSet(startCoord,endCoord);
        }else if(sCArray[1].equals(eCArray[1])){ // Horizontal
            this.horizontalSet(startCoord, endCoord);
        }
    }
    
    /**
     * Place the ship randomly
     * 
     * It's used for the AI (easy/beginner, medium, hard, it doesn't matter)
     */
    public void placingAleaShip(){
        Random rand= new Random();
        String startCoord, endCoord;
        int position = rand.nextInt(2); //Vertical or Horizontal? 0:vertical, 1:horizontal
        if(position==0){ //Vertical
            int col = rand.nextInt(10)+65;
            String column = Character.toString((char)col);
            int startLine = rand.nextInt(10-this.getSize());
            startCoord = column+String.valueOf(startLine);
            this.setStartCoord(startCoord); //We create the start position...
            startLine+=this.getSize()-1;
            endCoord = column+String.valueOf(startLine);
            this.setEndCoord(endCoord); //We create the end position...
            this.setCoordShip(this.getStartCoord(),this.getEndCoord(),this.getSize()); //We set each spots to the Ship ArrayList...
        }else if(position==1){ //Horizontal
            int line = rand.nextInt(10);
            String lineS = String.valueOf(line);
            int startCol = rand.nextInt(10)+65-this.getSize();
            String startColS = Character.toString((char)startCol);
            startCoord = startColS+lineS;
            this.setStartCoord(startCoord);
            startCol+=this.getSize()-1;
            String endColS = Character.toString((char)startCol);
            endCoord=endColS+lineS;
            this.setEndCoord(endCoord);
            this.setCoordShip(this.getStartCoord(),this.getEndCoord(),this.getSize());
        }
    } //Place un bateau de manière aléatoire
}