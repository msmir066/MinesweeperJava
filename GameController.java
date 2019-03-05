import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;

import java.io.*;
import java.util.*;
import java.util.ArrayList;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE
 private GameModel gameModel;
 private GameView gameView;
 
 private int h;
 private int w;
 private int numberOfMines;
 

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    public GameController(int width, int height, int numberOfMines) {
    // ADD YOU CODE HERE
      w = width;
      h = height ;
      this.numberOfMines = numberOfMines;
      
      gameModel = new GameModel(width,height,numberOfMines);
      gameView = new GameView(gameModel,this);
    }

    
    

    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
    // ADD YOU CODE HERE
      if (e.getActionCommand().equals("quit")){
        System.exit(0);
      }
      if (e.getActionCommand().equals("reset")){
        gameModel.reset();
      }
    }

    
    
    /**
     * resets the game
     */
    private void reset(){
    // ADD YOU CODE HERE
      GameModel gameModel = new GameModel(w,h, numberOfMines);
      GameView gameView = new GameView(gameModel, this);
      gameView.update();
      
    }

    
    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){
    // ADD YOU CODE HERE
      
      if (!gameModel.hasBeenClicked(width,heigth)){
         gameModel.click(width,heigth);
        
        if (gameModel.isBlank(width,heigth)){
          clearZone(gameModel.get(width,heigth));
        }
            
        gameView.update();
        gameModel.step();
      
      }
       
        
      if (gameModel.isMined(width,heigth) == true){
          gameView.update();
          reset();
        }
        if (gameModel.isFinished() == true){
          gameView.update();
          System.out.println("Congratulations you have won the game!");
        } 
        
        }
      
   
    
   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
   private void clearZone(DotInfo initialDot) {


    // ADD YOU CODE HERE
    Stack<DotInfo> S = new GenericArrayStack<DotInfo>(6);
    
    if(initialDot.isCovered()==true && initialDot.getNeighbooringMines() == 0){
      S.push(initialDot);
    }
    else {
      initialDot.uncover();
    }
    
    while(S.isEmpty() == false){
      DotInfo v = S.pop();
      int x = v.getX();
      int y = v.getY();

      if(v.isCovered()==true && v.getNeighbooringMines() == 0){
        v.uncover();
        
        if(x+1 < w - 1){
          DotInfo N1 = gameModel.get(x+1,y);
          
          if(N1.isCovered()==true && N1.getNeighbooringMines() == 0){
           S.push(N1); 
          }
          
        }
        
        if(x-1 > 0){
          DotInfo N2 = gameModel.get(x-1,y);
        }
        
        if(y + 1 < h - 1){
           DotInfo N3 = gameModel.get(x,y+1);
        }
        
        if(y - 1 > 0){
          DotInfo N4 = gameModel.get(x,y-1);
        }
        
      }
      
      }
    }
  }

