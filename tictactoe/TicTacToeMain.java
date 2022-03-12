package tictactoe;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TicTacToeMain extends JFrame implements ActionListener {

 JButton [][]buttons = new JButton[3][3];
 JButton playButton = new JButton("Play");
 JLabel statusLabel = new JLabel("");
 TicTacToeAI game = null;
 private int human = 0;
 private int computer = 0;
 boolean isPlay = false;
 private String []chars=new String[]{"","X","O"};

 void setStatus(String s) {
  statusLabel.setText(s);
 }

 void setButtonsEnabled(boolean enabled) {
  for(int i=0;i<3;i++)
   for(int j=0;j<3;j++) {
    buttons[i][j].setEnabled(enabled);
    if(enabled) buttons[i][j].setText(" ");
   }
 }

 public TicTacToeMain() {

  setTitle("Tic Tac Toe");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setResizable(false);

  JPanel centerPanel = new JPanel(new GridLayout(3,3));
  Font font = new Font("Arial",Font.BOLD, 32);
  for(int i=0;i<3;i++)
   for(int j=0;j<3;j++) {
    buttons[i][j] = new JButton(" ");
    buttons[i][j].setFont(font);
    buttons[i][j].addActionListener(this);
    buttons[i][j].setFocusable(false);
    centerPanel.add(buttons[i][j]);
   }

  playButton.addActionListener(this);

  JPanel northPanel = new JPanel();
  northPanel.add(statusLabel);

  JPanel southPanel = new JPanel();
  southPanel.add(playButton);

  setStatus("Click 'Play' To Start");
  setButtonsEnabled(false);

  add(northPanel,"North");
  add(centerPanel,"Center");
  add(southPanel,"South");

  setSize(300,300);

  // I'm lazy to implement the correct way
  setLocationRelativeTo(null);
 }

 public static void main(String []args) {
  new TicTacToeMain().setVisible(true);
 }

 private void computerTurn() {
  if(!isPlay) return;

  int []pos = game.nextMove(computer);
  if(pos!=null) {
   int i = pos[0];
   int j = pos[1];
   buttons[i][j].setText(chars[computer]);
   game.setBoardValue(i,j,computer);
  }

  checkState();
 }

 void gameOver(String s) {
  setStatus(s);
  setButtonsEnabled(false);
  isPlay = false;
 }

 private void checkState() {
  if(game.isWin(human)) {
   gameOver("Congratulations, You've Won!");
  }
  if(game.isWin(computer)) {
   gameOver("Sorry, You Lose!");
  }
  if(game.nextMove(human)==null && game.nextMove(computer)==null) {
   gameOver("Draw, Click 'Play' For Rematch!");
  }
 }

 void click(int i,int j) {
  if(game.getBoardValue(i,j)==TicTacToeAI.EMPTY) {
   buttons[i][j].setText(chars[human]);
   game.setBoardValue(i,j,human);
   checkState();
   computerTurn();
  }
 }

 public void actionPerformed(ActionEvent event) {
  if(event.getSource()==playButton) {
   play();
  }else {
   for(int i=0;i<3;i++)
    for(int j=0;j<3;j++)
     if(event.getSource()==buttons[i][j])
      click(i,j);
  }
 }

 private void play() {
  game = new TicTacToeAI();
  human = TicTacToeAI.ONE;
  computer = TicTacToeAI.TWO;
  setStatus("Your Turn");
  setButtonsEnabled(true);
  isPlay = true;
 }
}
