import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

     // ADD YOUR INSTANCE VARIABLES HERE
  
  private DotButton[][] board;
  private GameModel gameModel;
  GameController gameController;
  private JPanel myPanel;
  private JPanel myPanelS;
  private JPanel myPanelC;
  private JPanel myPanelN;
  
  private Label nbreOfStepsLabel;
  


    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {
    // ADD YOU CODE HERE
      
      super("MineSweeper - The ITI 1521 version");
      
      gameModel = new GameModel(20,12,26);
      gameController = new GameController(20,12,26);
      board = new DotButton[gameModel.getWidth()][gameModel.getHeigth()];
     
      myPanel = new JPanel();
      myPanelS=new JPanel();
      myPanelC = new JPanel();
      myPanelN = new JPanel();

      nbreOfStepsLabel = new Label("Number of steps" + gameModel.getNumberOfSteps());
      myPanelS.add(nbreOfStepsLabel);
      
      
      for(int x=0; x< gameModel.getWidth();x++) {
             for(int y=0; y < gameModel.getHeigth();y++) {
           board[x][y] = new DotButton(x,y, DotButton.COVERED);
           board[x][y] .setVisible(true);
           board[x][y] .addActionListener(gameController);
                 myPanelC.add(board[x][y]);
             }
         }
      
      myPanel.add(myPanelS, BorderLayout.SOUTH);
      myPanel.add(myPanelC, BorderLayout.CENTER);
      myPanel.add(myPanelN, BorderLayout.NORTH);
    
      JButton myButtonR = new JButton("Reset");
      myButtonR.setBackground(Color.white);
      myButtonR.addActionListener(gameController);
      myPanelS.add(myButtonR);
      
      
      JButton myButtonQ = new JButton("Quit");
      myButtonQ.setBackground(Color.white);
      myButtonQ.addActionListener(gameController);
      myPanelS.add(myButtonQ);
     
      myPanel.setVisible(true);
     

    }

    
    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
    // ADD YOU CODE HERE
      
      for(int x=0; x< gameModel.getWidth();x++) {
            for(int y=0; y < gameModel.getHeigth();y++) {
          if(gameModel.hasBeenClicked(x, y)==true || gameModel.isCovered(x, y)){
              board[x][y].setIconNumber(getIcon(x, y));
              board[x][y].setIcon(board[x][y].getImageIcon());
              board[x][y].setVisible(true);
          }
          
            }
        }
     nbreOfStepsLabel.setText("Number of steps: " + gameModel.getNumberOfSteps());
     nbreOfStepsLabel.setVisible(true); 
    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){
        
    // ADD YOU CODE HERE
      
      
      if (gameModel.hasBeenClicked(i,j) && gameModel.isMined(i,j) == true){
        return DotButton.CLICKED_MINE;
      }
      
      if (gameModel.isMined(i,j) == true){
        return DotButton.MINED;
      }

      if (gameModel.getNeighbooringMines(i,j) == 0){
        return DotButton.ZERO_NEIGHBOURS;
      }
      if (gameModel.getNeighbooringMines(i,j) == 1){
        return DotButton.TWO_NEIGHBOURS;

      }
      if (gameModel.getNeighbooringMines(i,j) == 2){
        return DotButton.ONE_NEIGHBOURS;

      }
      if (gameModel.getNeighbooringMines(i,j) == 3){
       return DotButton.THREE_NEIGHBOURS;

      }
      if (gameModel.getNeighbooringMines(i,j) == 4){
       return DotButton.FOUR_NEIGHBOURS;

      }
      if (gameModel.getNeighbooringMines(i,j) == 5){
        return DotButton.FIVE_NEIGHBOURS;

      }
      if (gameModel.getNeighbooringMines(i,j) == 6){
        return DotButton.SIX_NEIGHBOURS;

      }
      if (gameModel.getNeighbooringMines(i,j) == 7){
        return DotButton.SEVEN_NEIGHBOURS;

      }
      if (gameModel.getNeighbooringMines(i,j) == 8){
        return DotButton.EIGHT_NEIGHBOURS;

      }
  return DotButton.COVERED;

    }

}
