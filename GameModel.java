import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameModel {
     // ADD YOUR INSTANCE VARIABLES HERE
  private int widthOfGame;
  private int heightOfGame;
  private int numberOfMines;
  private DotInfo[][] model;
  private int numberOfSteps;
  private int numberUncovered;
  private Random generator;
  private Stack <DotInfo> stack;
  


    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigh, int numberOfMines) {  
    // ADD YOU CODE HERE  
      widthOfGame = width;
      heightOfGame = heigh;
      numberOfMines = generator.nextInt();  
  
      
     model = new DotInfo[width][heigh];
    }

    
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){
    // ADD YOU CODE HERE
     stack = new GenericArrayStack<DotInfo>(widthOfGame*heightOfGame);
     for (int x = 0; x<=widthOfGame; x++){
       for (int y = 0; y<=heightOfGame; y++){
         DotInfo dot = new DotInfo(x,y);
         stack.push(dot);
        }
      }
      numberOfSteps = 0;
      numberUncovered = 0;
    }
      

    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth(){
    // ADD YOU CODE HERE
      return heightOfGame;
    }

    
    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){   
    // ADD YOU CODE HERE
      return widthOfGame;
    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){  
    // ADD YOU CODE HERE
      
      



 return model[i][j].isMined();
   }

    
    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
    // ADD YOU CODE HERE
  
      return model[i][j].hasBeenClicked();
    }

    
  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
    // ADD YOU CODE HERE

      return model[i][j].getNeighbooringMines() == 0;
    }
    
    
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){  
    // ADD YOU CODE HERE
     
      return model[i][j].isCovered();
    }

    
    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
    // ADD YOU CODE HERE
      return model[i][j].getNeighbooringMines();
    }

    

    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
    // ADD YOU CODE HERE
    
      model[i][j].uncover();
    }

    
    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
    // ADD YOU CODE HERE
  
      model[i][j].click();
    }
    
    
     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
    // ADD YOU CODE HERE
        for (int i=0; i<=widthOfGame; i++){
          for (int j = 0; j<=heightOfGame; j++){
          numberUncovered ++;
        }
      }
    }

 

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
    // ADD YOU CODE HERE
      return numberOfSteps;
    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
    // ADD YOU CODE HERE
      return model[i][j];
    }

    

   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
     public void step(){
    // ADD YOU CODE HERE
       numberOfSteps++;
    }
     
 
     
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
    // ADD YOU CODE HERE
      
      for (int i=0; i<=widthOfGame; i++){
        
        for (int j = 0; j<=heightOfGame; j++){ 
          
          if (model[i][j].isMined()){
        return false; 
        } 
      
      }
    }
      return false;
    }
      


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
      
      String s = new String();
     
      for (int x = 0; x<widthOfGame; x++){
        for (int y = 0; y<heightOfGame; y++){
          DotInfo d = get(x,y);
          s = (d.getX() + " and " + d.getY() +" are the coordinates of your current dot");
        }
      }
      return s;
    }
      
   
    
}
