import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
public class TwoPlayer extends JPanel implements ActionListener, KeyListener
{
    private boolean showTitleScreen = true;
    private boolean playing = false;
    private boolean gameOver = false;
    private boolean pointOver = true;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;

    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;

    private boolean spacePressed = false;
    private boolean canServe = true;
    private boolean player1Serving = true;
    private boolean player2Serving = false;
    private boolean servingRightSide = true;
    private boolean servingLeftSide = false;
    private boolean canPlay = true;
    private boolean canMove = true;

    private boolean pause = false;

    private int ballX = getWidth()/2 - 20;
    private int ballY = getHeight()/2 + 20;
    private int diameter = 15;//20
    private int ballDeltaX = -1;//-1
    private int ballDeltaY = 2;
    private int newBallX = getWidth()/2 + 20;
    private int newBallY = getHeight()/2 - 20;

    private int playerOneX = 0;
    private int playerOneY = 500;
    private int playerOneWidth = 35;
    private int playerOneHeight = 300;

    private int playerTwoX = 435;
    private int playerTwoY = 500; 
    private int playerTwoWidth = 10;
    private int playerTwoHeight = 50;

    private int shadowPlayerOneX;
    private int shadowPlayerOneY;
    private int shadowPlayerTwoX;
    private int shadowPlayerTwoY;

    private int racketSpeed = 5;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private int playerOneGames = 0;
    private int playerTwoGames = 0;

    private String userMessage = "";

    private Player PlayerOne;
    private Player PlayerTwo;
    private Player shadowPlayer1;
    private Player shadowPlayer2;

    private int difficulty;
    private Color gameColor = new Color(100,145,209);
    private BufferedImage gameImage;

    private boolean isColor = true;
    private boolean isImage = false;

    private JLabel backgroundImage;
    private JLabel p1Score;
    private JLabel p2Score;
    private JLabel p1Games;
    private JLabel p2Games;

    private String playerOneName;
    private String playerTwoName;

    public TwoPlayer(Color gameColor,String playerOneName,String playerTwoName) {
        //listen to key presses
        setFocusable(true);
        addKeyListener(this);
        //call step() 60 fps
        Timer timer = new Timer(1000/60, this);
        timer.start();
        this.gameColor = gameColor;
        updateScore();
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }

    public TwoPlayer(BufferedImage gameImage,String playerOneName,String playerTwoName){
        //listen to key presses
        setFocusable(true);
        addKeyListener(this);
        //call step() 60 fps
        Timer timer = new Timer(1000/60, this);
        timer.start();
        this.gameImage = gameImage;
        isColor = false;
        isImage = true;
        updateScore();
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }

    public void actionPerformed(ActionEvent e){
        step();
    }

    public void step(){
        boolean pointOver = false;
        if(player1Serving && canServe && canMove){
            ballDeltaX = 0;
            ballDeltaY = 0;
            //move the player to the correct spot
            int leftServingPos = 20;
            int rightServingPos = getWidth()/2 - 30;
            int side = findServingSide(); // 0Right 1Left
            if(side == 0){
                if(playerOneX > rightServingPos){playerOneX -= racketSpeed;}
                else if(playerOneX < rightServingPos){playerOneX += racketSpeed;}
                else{playerOneX = playerOneX;}
            }
            else if(side == 1){
                if(playerOneX > leftServingPos){playerOneX -= racketSpeed;}
                else if(playerOneX < leftServingPos){playerOneX += racketSpeed;}
                else{playerOneX = playerOneX;}
            }
            ballX = playerOneX + playerOneWidth/2;
            ballY = playerOneY + 10;
            if(spacePressed && side == 0){ballDeltaX = -1; ballDeltaY = -2; canServe = false;}
            if(spacePressed && side == 1){ballDeltaX = 1; ballDeltaY = -2; canServe = false;}

            if(playerOneY != 640-playerOneHeight/2){
                if(playerOneY >= 640-playerOneHeight/2){playerOneY -= racketSpeed;}
                else{playerOneY += racketSpeed;}
            }
        }
        else if(player2Serving && canServe && canMove){
            ballDeltaX = 0;
            ballDeltaY = 0;
            //move the player to the correct spot
            int leftServingPos = getWidth()/2 + 20;
            int rightServingPos = getWidth() - 20;
            int side = findServingSide(); // 0Right 1Left
            if(side == 0){
                if(playerTwoX > rightServingPos){playerTwoX -= racketSpeed;}
                else if(playerTwoX < rightServingPos){playerTwoX += racketSpeed;}
                else{playerTwoX = playerTwoX;}
            }
            else if(side == 1){
                if(playerTwoX > leftServingPos){playerTwoX -= racketSpeed;}
                else if(playerTwoX < leftServingPos){playerTwoX += racketSpeed;}
                else{playerOneX = playerOneX;}
            }
            ballX = playerTwoX + playerTwoWidth/2;
            ballY = playerTwoY + 10;
            if(spacePressed && side == 0){ballDeltaX = -1; ballDeltaY = -2; canServe = false;}
            if(spacePressed && side == 1){ballDeltaX = 1; ballDeltaY = -2; canServe = false;}

            if(playerTwoY != 640-playerTwoHeight/2){
                if(playerTwoY >= 640-playerTwoHeight/2){playerTwoY -= racketSpeed;}
                else{playerTwoY += racketSpeed;}
            }
        }
        else if(playing && canMove){
            //Move Player One Left side of the Court
            if (wPressed) {
                if(playerOneY >= getHeight()/2){
                    playerOneY -= racketSpeed;
                }
            }
            if (aPressed) {
                if(playerOneX >= 0){
                    playerOneX -= racketSpeed;
                }
            }
            if(sPressed){
                if(playerOneY + playerTwoHeight <= getHeight()){
                    playerOneY += racketSpeed;
                }
            }
            if(dPressed){
                if(playerOneX + playerOneWidth <= getWidth()/2){
                    playerOneX += racketSpeed;
                }
            }
            //Move Player Two Right Side of The Court
            if (upPressed) {
                if(playerTwoY >= getHeight()/2){
                    playerTwoY -= racketSpeed; 
                }
            }
            if (downPressed) {
                if(playerTwoY + playerTwoHeight <= getHeight()){
                    playerTwoY += racketSpeed;
                }
            }
            if(rightPressed){
                if(playerTwoX + playerTwoWidth <= getWidth()){
                    playerTwoX += racketSpeed;
                }
            }
            if(leftPressed){
                if(playerTwoX >= getWidth()/2){
                    playerTwoX -= racketSpeed;
                }
            }

            //check if ball hits the player one's racket
            int[] player1RacketDims = PlayerOne.getRacketDimensions();
            int player1RacketX = player1RacketDims[0]-5;
            int player1RacketY = player1RacketDims[1]+5;
            int player1RacketWidth = player1RacketDims[2]+10;
            int player1RacketHeight = player1RacketDims[3];
            if(ballX + ballDeltaX > player1RacketX
            && ballX + ballDeltaX < player1RacketX+player1RacketWidth
            && ballY + diameter + ballDeltaY >= player1RacketY
            && ballY + diameter + ballDeltaY <= player1RacketY+player1RacketHeight){
                getNewBallSlope();
            }

            //check if ball hits player two's racket
            int[] player2RacketDims = shadowPlayer2.getRacketDimensions();
            int player2RacketX = player2RacketDims[0]-5;
            int player2RacketY = player2RacketDims[1]+5;
            int player2RacketWidth = player2RacketDims[2]+10;
            int player2RacketHeight = player2RacketDims[3];
            if(ballY == player2RacketY){
                getNewBallSlope();
            }

            //Check to see if a point has been won
            if(ballY + ballDeltaY + diameter >= getHeight()
            || (ballX + ballDeltaX + diameter <= 0) 
            || (ballX + ballDeltaX + diameter >= getWidth()/2)){
                playerTwoScore++;
                pointOver = true;
            }
            if(newBallY + ballDeltaY + diameter >= getHeight()
            || (newBallX + ballDeltaX + diameter >= getWidth())
            || (newBallX + ballDeltaX + diameter <= getWidth()/2)){
                playerOneScore++;
                pointOver = true;
            }

            //Check if a game has been won
            if(playerOneScore == 4){
                //Reset score as well as add users game score
                playerOneGames++;
                playerOneScore = 0;
                playerTwoScore = 0;
                if(player1Serving){player1Serving = false; player2Serving = true;}
                else{player1Serving = true; player2Serving = false;}
            }
            else if(playerTwoScore == 4){
                //Reset score as well as add one to comp game score
                playerTwoGames++;
                playerOneScore = 0;
                playerTwoScore = 0;
                if(player1Serving){player1Serving = false; player2Serving = true;}
                else{player1Serving = true; player2Serving = false;}
            }
            //If the point is over wait for a serve
            if(pointOver){
                ballDeltaX = 0;
                ballDeltaY = 0;
                canServe = true;
                removePreviousScore();
                updateScore();
            }
            else{
                ballX += ballDeltaX;
                ballY += ballDeltaY;
            }
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(isColor){
            g.setColor(this.gameColor);
            g.fillRect(0,0,getWidth(),getHeight());
        }
        else if(isImage){
            g.drawImage(gameImage,0,0,getWidth(),getHeight(),null);
        }

        drawCourt(g);

        PlayerOne = new Player("User1",0,300,playerOneX,playerOneY);
        PlayerTwo = new Player("User2",0,300,playerTwoX,playerTwoY);
        this.PlayerOne = PlayerOne;
        this.PlayerTwo = PlayerTwo;
        PlayerOne.drawPlayer(g);
        PlayerTwo.drawPlayer(g);

        //Add shadow players
        int p1distFromVert = (getWidth()/2) - playerOneX;
        int p1distFromHoriz = playerOneY - (getHeight()/2);
        int p2distFromVert = (getWidth()/2) - playerTwoX;
        int p2distFromHoriz = playerTwoY - (getHeight()/2);
        int newP1X = (getWidth()/2) + p1distFromVert;
        int newP1Y = (getHeight()/2) - p1distFromHoriz;
        int newP2X = (getWidth()/2) + p2distFromVert;
        int newP2Y = (getHeight()/2) - p2distFromHoriz;
        Player ShadowPlayer1 = new Player("ShadowPlayer1",0,300,newP1X,newP1Y-60);
        Player ShadowPlayer2 = new Player("ShadowPlayer2",0,300,newP2X-10,newP2Y-53);
        ShadowPlayer1.drawPlayer(g,Color.gray);
        ShadowPlayer2.drawPlayer(g,Color.gray);
        ShadowPlayer1.drawShorts(g,Color.gray);
        ShadowPlayer1.drawShirt(g,Color.gray);
        ShadowPlayer2.drawShorts(g,Color.gray);
        ShadowPlayer2.drawShorts(g,Color.gray);

        shadowPlayerOneX = newP1X;
        shadowPlayerOneY = newP1Y;
        shadowPlayerTwoX = newP2X;//Use to see if the ball was hit by P2
        shadowPlayerTwoY = newP2Y;//Use to see if the ball was hit by P2

        this.shadowPlayer1 = ShadowPlayer1;
        this.shadowPlayer2 = ShadowPlayer2;

        g.setColor(Color.WHITE); // draw the title screen
        if (showTitleScreen) {
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Tennis Tour Pro", 165, 100);
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            g.drawString("Press 'Space' to play.", 175, 400);
        }
        else if (playing) {
            //draw a ball
            //g.setColor(Color.yellow);
            //g.fillOval(ballX, ballY, diameter, diameter);
            drawBalls(g);
            //Set message, set player
            setPlayer(true);
            setPlayer(true);
            PlayerOne.drawPlayer(g);
            PlayerTwo.drawPlayer(g);
            //Draw the racket based on the balls position on the court
            if(ballX < playerOneX + (playerOneWidth/2)){
                PlayerOne.drawBackhandHit(g);
                ShadowPlayer1.drawForehandHit(g,Color.gray);
            }
            else if(ballX > playerOneX + (playerOneWidth/2)){
                PlayerOne.drawForehandHit(g);
                ShadowPlayer1.drawBackhandHit(g,Color.gray);
            }
            else{
                PlayerOne.drawForehandHit(g);
                ShadowPlayer1.drawForehandHit(g,Color.gray);
            }

            if(newBallX < playerTwoX + (playerTwoWidth/2)){
                PlayerTwo.drawBackhandHit(g);
                ShadowPlayer2.drawForehandHit(g,Color.gray);
            }
            else if(newBallX > playerTwoX + (playerTwoWidth/2)){
                PlayerTwo.drawForehandHit(g);
                ShadowPlayer2.drawBackhandHit(g,Color.gray);
            }
            else{
                PlayerTwo.drawForehandHit(g);
                ShadowPlayer2.drawForehandHit(g,Color.gray);
            }
        }
        else if(gameOver){
            g.setColor(Color.white);
            g.fillRect(0,0,getWidth(),getHeight());
            g.setColor(Color.black);
            g.drawString("GAME OVER!",(getWidth()/2)-25,getHeight()/2);
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (showTitleScreen) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                showTitleScreen = false;
                playing = true;
            }
        }
        else if(playing){
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_T) {
                ballX = 250;
                ballY = 250;
                ballDeltaX = 1;//-1
                ballDeltaY = 2;
            }
            else if(e.getKeyCode() == KeyEvent.VK_SPACE
            && canServe){
                spacePressed = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_W){
                wPressed = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_A){
                aPressed = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_S){
                sPressed = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_D){
                dPressed = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_P){
                canMove = false;
            }
        }
        else if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                gameOver = false;
                showTitleScreen = true;
                playerOneY = 250;
                playerTwoY = 250;
                ballX = 250;
                ballY = 250;
                playerOneScore = 0;
                playerTwoScore = 0;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (playing) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_SPACE){
                spacePressed = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_W){
                wPressed = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_A){
                aPressed = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_S){
                sPressed = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_D){
                dPressed = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_P){
                canMove = true;
            }
        }
    }

    public void drawCourt(Graphics g){
        drawCourt(g,0);
        drawCourt(g,420);
        //Draw the lines on the court
        g.setColor(Color.white);
        g.fillRect(0,0,3,800);//Left court left sideline
        g.fillRect(397,0,3,800);//Left court right sideline
        g.fillRect(0,0,400,3);//Left court top line
        g.fillRect(0,793,400,3);//left court bottom line
        g.fillRect(0,298,400,3);//Left court mid-horizontal line
        g.fillRect(198,0,3,800);//Left court mid-vertical line
        g.fillRect(420,0,3,800);//Right court left sideline
        g.fillRect(817,0,3,800);//Right court right sideline
        g.fillRect(420,0,400,3);//Right court top line
        g.fillRect(420,793,400,3);//Right court bottom line
        g.fillRect(420,298,400,3);//Right court mid-horizontal line
        g.fillRect(420+198,0,3,800);//Right court mid vertical line

        //Drawing the net
        g.setColor(Color.black);
        g.fillRoundRect(0,getHeight()/2 - 65,10,75,5,5);
        g.fillRoundRect(getWidth()-10,getHeight()/2 - 65,10,75,5,5);
        int startPosX = 0; 
        int startPosY = getHeight()/2 - 65;
        for(int i=0; i<getWidth()/5; i++){
            g.drawLine(startPosX,startPosY,startPosX,startPosY+75);
            startPosX += 5;
        }
        startPosX = 0; 
        startPosY = getHeight()/2 - 65;
        for(int a=0; a<16; a++){
            g.drawLine(startPosX,startPosY,startPosX+getWidth(),startPosY);
            startPosY += 5;
        }
        g.setColor(new Color(177,185,191));
        g.fillRect(160,150,380,10);
        //Drawing the net
        g.setColor(Color.black);
        g.fillRoundRect(145,150,15,100,5,5);
        g.fillRoundRect(540,150,15,100,5,5);
    }

    public void drawCourt(Graphics g, int startX){
        g.setColor(new Color(19,114,0));
        g.fillRect(startX,0,startX+400,600);
    }

    public void drawBalls(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(ballX,ballY,diameter,diameter);
        //Define a new set of coordinates to mirror the ball
        int distFromVert = (getWidth()/2) - ballX;
        int distFromHoriz = ballY - (getHeight()/2);
        this.newBallX = (getWidth()/2) + distFromVert;
        this.newBallY = (getHeight()/2) - distFromHoriz;
        g.fillOval(newBallX,newBallY,diameter,diameter);
    }

    public void setPlayer(boolean draw){
        if(draw){this.repaint();}
    }

    public int findServingSide(){
        if(playerOneScore+playerTwoScore % 2 == 1){return 1;}//Left Side
        else{return 0;}//Right Side
    }

    public void getNewBallSlope(){
        //Get Random new slope
        int newXSlope = (int)(Math.random()*2);
        int newYSlope = 1+(int)(Math.random()*4);
        //Find who is hitting the ball firstly based on the left half of the screem
        boolean isBottomPlayer = false;
        boolean isTopPlayer = false;
        if(ballY >= getHeight()/2){isBottomPlayer = true;}
        else{isTopPlayer = true;}
        //Now find which half of the court
        boolean isLeftHalf = false;
        boolean isRightHalf = false;
        if(ballX < getWidth()/4){ isLeftHalf = true;}
        else{isRightHalf = true;}
        //Lastly, create a new slope with correct signs
        if(isBottomPlayer){
            if(isLeftHalf){
                ballDeltaY = newYSlope*(-1);
                ballDeltaX = newXSlope;
            }
            else{
                ballDeltaY = newYSlope*(-1);
                ballDeltaX = newXSlope*(-1);
            }
        }
        if(isTopPlayer){
            if(isLeftHalf){
                ballDeltaY = newYSlope;
                ballDeltaX = newXSlope;
            }
            else{
                ballDeltaY = newYSlope;
                ballDeltaX = newXSlope*(-1);
            }
        }
    }

    public void updateScore(){
        if(playerOneName == null){playerOneName = "Plyr 1";}
        if(playerTwoName == null){playerTwoName = "Plyr 2";}
        if(playerOneName.length() > 6){playerOneName = playerOneName.substring(0,6);}
        if(playerTwoName.length() > 6){playerTwoName = playerTwoName.substring(0,6);}

        p1Score = new JLabel();
        p1Score.setBounds(getWidth() -155, 50, 100, 30);
        p1Score.setFont(new Font("SansSerif", Font.BOLD, 12));
        p1Score.setText(playerOneName+" Score: "+playerOneScore);
        this.add(p1Score);
        p1Score.setVisible(true);

        p2Score = new JLabel();
        p2Score.setBounds(getWidth() -155, 100, 100, 30);
        p2Score.setFont(new Font("SansSerif", Font.BOLD, 12));
        p2Score.setText(playerTwoName+" Score: "+playerTwoScore);
        this.add(p2Score);
        p2Score.setVisible(true);

        p1Games = new JLabel();
        p1Games.setBounds(getWidth() -155, 150, 100, 30);
        p1Games.setFont(new Font("SansSerif", Font.BOLD, 12));
        p1Games.setText(playerOneName+" Games: "+playerOneGames);
        this.add(p1Games);
        p1Games.setVisible(true);

        p2Games = new JLabel();
        p2Games.setBounds(getWidth() -155, 200, 100, 30);
        p2Games.setFont(new Font("SansSerif", Font.BOLD, 12));
        p2Games.setText(playerTwoName+" Games: "+playerTwoGames);
        this.add(p2Games);
        p2Games.setVisible(true);
    }

    public void removePreviousScore(){
        this.remove(p1Score);
        this.remove(p2Score);
        this.remove(p1Games);
        this.remove(p2Games);
    }
}