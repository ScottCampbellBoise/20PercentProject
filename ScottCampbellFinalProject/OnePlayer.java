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

//Made for the background player images
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class OnePlayer3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OnePlayer extends JPanel implements ActionListener, KeyListener
{
    private boolean showTitleScreen = true;
    private boolean playing = false;
    private boolean gameOver = false;
    private boolean pointOver = true;

    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;

    private boolean spacePressed = false;
    private boolean canServe = false;
    private boolean player1Serving = true;
    private boolean player2Serving = false;
    private boolean servingRightSide = true;
    private boolean servingLeftSide = false;
    private boolean canPlay = true;

    private int ballX = 250;
    private int ballY = 250;
    private int diameter = 15;//20
    private int ballDeltaX = -1;//-1
    private int ballDeltaY = 2;

    private int playerOneX = 0;
    private int playerOneY = 500;
    private int playerOneWidth = 35;
    private int playerOneHeight = 300;

    private int playerTwoX = 465;
    private int playerTwoY = 0; 
    private int playerTwoWidth = 10;
    private int playerTwoHeight = 50;

    private int racketSpeed = 5;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private int playerOneGames = 0;
    private int playerTwoGames = 0;

    private String userMessage = "";

    private Player userPlayer;
    private Player compPlayer;

    private int difficulty;
    private Color gameColor = new Color(100,145,209);
    private BufferedImage gameImage;

    private boolean isColor = true;
    private boolean isImage = false;

    private JLabel backgroundImage;
    public OnePlayer(int difficulty, Color gameColor) {
        //listen to key presses
        setFocusable(true);
        addKeyListener(this);
        //call step() 60 fps
        Timer timer = new Timer(1000/60, this);
        timer.start();

        this.difficulty = difficulty;
        this.gameColor = gameColor;
    }

    public OnePlayer(int difficulty, BufferedImage gameImage){
        //listen to key presses
        setFocusable(true);
        addKeyListener(this);
        //call step() 60 fps
        Timer timer = new Timer(1000/60, this);
        timer.start();

        this.difficulty = difficulty;
        this.gameImage = gameImage;

        isColor = false;
        isImage = true;
    }

    public void actionPerformed(ActionEvent e){
        step();
    }

    public void step(){
        boolean pointOver = false;
        if(playing)
        {

            //Move the User Player
            if (upPressed) {
                if (playerOneY-racketSpeed > 200) {
                    playerOneY -= racketSpeed;
                }     
            }
            if (downPressed) {
                if (playerOneY + racketSpeed + playerOneHeight < getHeight()) {
                    playerOneY += racketSpeed;
                }
            }
            if(rightPressed)
            {
                if(playerOneX + racketSpeed + playerOneWidth < getWidth())
                {
                    playerOneX += racketSpeed;
                }
            }
            if(leftPressed)
            {
                if(playerOneX > 0)
                {
                    playerOneX -= racketSpeed;
                }
            }

            //Move the computer player
            int[] compRacketDims = compPlayer.getRacketDimensions();
            int compRacketX = compRacketDims[0];
            int compRacketWidth = compRacketDims[2];
            int compRacketHeight = compRacketDims[3];
            int compRacketY = compRacketDims[1];
            int compPlayerRacketMiddle = compRacketY + (compRacketWidth/2);
            if(compPlayerRacketMiddle != ballX+ballDeltaX+(diameter/2)
            && playerTwoX - racketSpeed >= 0
            && playerTwoX + racketSpeed + playerTwoWidth <= 700)
            {
                //Check to see whick way the player should move
                if(ballX >= compRacketX)
                {
                    playerTwoX += difficulty;
                }
                else
                {
                    playerTwoX -= difficulty;
                }
            }

            //check if ball hits the user players racket
            int[] player1RacketDims = userPlayer.getRacketDimensions();
            int player1RacketX = player1RacketDims[0]-5;
            int player1RacketY = player1RacketDims[1]+5;
            int player1RacketWidth = player1RacketDims[2]+10;
            if(ballX + ballDeltaX > player1RacketX
            && ballX + ballDeltaX < player1RacketX+player1RacketWidth
            && ballY + diameter + ballDeltaY == player1RacketY)
            {
                ballDeltaX *= -1;
                ballDeltaY *= -1;
            }

            //check if the ball hits the computer players racket
            if(ballY + ballDeltaY == compRacketY
            && ballX + ballDeltaX > compRacketX - 5)
            {
                ballDeltaX *= -1;
                ballDeltaY *= -1;
            }

            //Check to see if a point has been won
            if(ballY + ballDeltaY + diameter >= getHeight() - 100
            || (ballX + ballDeltaX <= 0 && ballY + ballDeltaY >= getHeight()/2) 
            || (ballX + ballDeltaX >= 700 && ballY + ballDeltaY >= getHeight()/2))//Ball passes the userPlayer
            {
                playerTwoScore++;
                pointOver = true;
            }
            if(ballY + ballDeltaY <= 0
            || (ballX + ballDeltaX <= 0 && ballY + ballDeltaY <= getHeight()/2)
            || (ballX + ballDeltaX >= 700 && ballY + ballDeltaY <= getHeight()/2))
            {
                playerOneScore++;
                pointOver = true;
            }

            //Check if a game has been won
            if(playerOneScore == 4)
            {
                //Reset score as well as add users game score
                playerOneGames++;
                playerOneScore = 0;
                playerTwoScore = 0;
            }
            else if(playerTwoScore == 4)
            {
                //Reset score as well as add one to comp game score
                playerTwoGames++;
                playerOneScore = 0;
                playerTwoScore = 0;
            }

            //If the point is over wait for a serve
            if(pointOver)
            {
                ballDeltaX = 0;
                ballDeltaY = 0;

                //Determine who is serving and where they should serve

                //if it is player ones turn to serve
                if(player1Serving)
                {
                    ballX = playerOneX + (playerOneWidth/2) - diameter;
                    ballY = playerOneY + 10 + diameter;
                }

                //if it is comp players turn to serve
                canServe = true;
            }
            else//Move the Ball
            {
                ballX += ballDeltaX;
                ballY += ballDeltaY;
            }
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(isColor)
        {
            g.setColor(this.gameColor);
            g.fillRect(0,0,getWidth(),getHeight());
        }
        else if(isImage)
        {
            g.drawImage(gameImage,0,0,getWidth(),getHeight(),null);
        }
        drawCourt(g);

        userPlayer = new Player("User",0,300,playerOneX,playerOneY);
        compPlayer = new Player("Comp",0,300,playerTwoX,playerTwoY);
        this.userPlayer = userPlayer;
        this.compPlayer = compPlayer;
        userPlayer.drawPlayer(g);
        compPlayer.drawPlayer(g);

        g.setColor(Color.WHITE); // draw the title screen
        if (showTitleScreen) {
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Tennis Tour Pro", 165, 100);
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            g.drawString("Press 'Space' to play.", 175, 400);
            g.drawString("Press '1' to change court settings.", 175, 420);
        }
        else if (playing) {

            //draw a ball
            g.setColor(Color.yellow);
            g.fillOval(ballX, ballY, diameter, diameter);
            //Set message, set player
            setPlayer(true);
            setPlayer(true);
            //Draw the Message at the end.
            userPlayer.drawPlayer(g);
            compPlayer.drawPlayer(g);
            compPlayer.drawBackhandHit(g);

            //Draw the racket based on the balls position on the court
            if(ballX < playerOneX + (playerOneWidth/2))
            {
                userPlayer.drawBackhandHit(g);
            }
            else if(ballX > playerOneX + (playerOneWidth/2))
            {
                userPlayer.drawForehandHit(g);
            }
            /** else if(ballX < playerTwoX)
            {
            compPlayer.drawForehandHit(g);
            }
            else
            {
            compPlayer.drawBackhandHit(g);
            }
             */
        }
        else if(gameOver)
        {
            g.setColor(Color.white);
            g.fillRect(0,0,getWidth(),getHeight());
            g.drawString("GAME OVER!",(getWidth()/2)-25,getHeight()/2);
        }
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
            && canServe)
            {
                if(player1Serving)
                {
                    //Then serve the ball
                    if(servingRightSide)
                    {
                        ballDeltaY = -2;
                        ballDeltaX = -1;
                    }
                    else
                    {
                        ballDeltaY = -2;
                        ballDeltaX = 1;
                    }
                    canServe = false;
                    canPlay = true;
                }
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
        }
    }

    public void drawCourt(Graphics g)
    {
        //Draw Court Background
        g.setColor(new Color(19,114,0));
        int[] courtXVals = new int[] {100,175,525,600};
        int[] courtYVals = new int[] {650,0,0,650};
        g.fillPolygon(courtXVals,courtYVals,4);
        //Draw the court Lines
        g.setColor(Color.white);
        int[] leftCourtLinesX = new int[] {100, 175, 180, 110};
        int[] leftCourtLinesY = new int[] {650, 0, 0, 650};
        g.fillPolygon(leftCourtLinesX, leftCourtLinesY, 4); // left side line
        int[] rightCourtLinesX = new int[] {600, 525, 520, 590}; // right side line
        int[] rightCourtLinesY = new int[] {650, 0, 0, 650};
        g.fillPolygon(rightCourtLinesX, rightCourtLinesY, 4);
        g.fillRect(103,640,496,10); //BackLine user Player
        g.fillRect(178,0,347,5); // Back Line computer Player
        int[] middleLineX = new int[] {345, 355, 352, 348 };
        int[] middleLineY = new int[] {650, 650, 0, 0};
        g.fillPolygon(middleLineX, middleLineY, 4);
        g.fillRect(148, 250, 400, 4);//Half Way Line in the court
        g.fillRect(133, 420,440, 6); // service line comp player
        g.fillRect(162, 115, 373, 4);//service line user player
        //Drawing the net
        g.setColor(Color.black);
        g.fillRoundRect(145,150,15,100,5,5);
        g.fillRoundRect(540,150,15,100,5,5);

        int startPosX = 145; 
        int startPosY = 150;
        for(int i=0; i<80; i++)
        {
            g.drawLine(startPosX,startPosY,startPosX,startPosY+100);
            startPosX += 5;
        }
        startPosX = 145; 
        startPosY = 150;
        for(int a=0; a<20; a++)
        {
            g.drawLine(startPosX,startPosY,startPosX+400,startPosY);
            startPosY += 5;
        }
        g.setColor(new Color(177,185,191));
        g.fillRect(160,150,380,10);
        //Drawing the net
        g.setColor(Color.black);
        g.fillRoundRect(145,150,15,100,5,5);
        g.fillRoundRect(540,150,15,100,5,5);

        //Draw The Blank Area that holds the Buttons and Game Information
        g.setColor(Color.white);
        g.fillRect(getWidth()-200,0,200,800);
    }

    public void setPlayer(boolean draw){
        if(draw)
        {
            this.repaint();
        }
    }
}
