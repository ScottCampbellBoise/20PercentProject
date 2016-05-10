import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private String name;
    private int score;
    private int bodyHeight;
    private int startX;
    private int startY;
    private Color shirtColor;
    private int racketX = 0;
    private int racketY = 0;
    private int racketWidth = 0;
    private int racketHeight = 0;

    private boolean isForehand = false;
    private boolean isBackhand = false;
    public Player(String name, int score, int bodyHeight, int startX, int startY){
        this.name = name;
        this.score = score;
        this.bodyHeight = bodyHeight;
        this.startX = startX;
        this.startY = startY;
    }

    public String getName(){
        return name;
    }

    public int getRacketX(){
        return racketX;
    }

    public int getRacketY(){
        return racketY;
    }

    public void drawPlayer(Graphics g){
        //player Height must be 300 for correct shape
        int[] playerXVals = new int[] {10,10,0,0,5,0,0,0,5,10,10,15,20,25,25,30,
                35,35,35,30,35,35,25,25,10};
        int[] playerYVals = new int[] {0,10,15,25,50,70,90,110,150,150,110,90,90,
                110,150,150,110,90,70,50,25,15,10,0,0};
        //Moving the standard shape to the correct player position        
        for(int i=0; i<playerXVals.length; i++)
        {
            playerXVals[i] += startX;
        }
        for(int i=0; i<playerYVals.length; i++)
        {
            playerYVals[i] += startY;
        }
        Color skinColor = new Color(255,232,142);
        g.setColor(skinColor);
        g.fillPolygon(playerXVals,playerYVals,24);
        drawShorts(g, Color.red);
        drawShirt(g, Color.cyan);
    }

    public void drawPlayer(Graphics g, Color c){
        //For Shadow Players
        //player Height must be 300 for correct shape
        int[] playerXVals = new int[] {10,10,0,0,5,0,0,0,5,10,10,15,20,25,25,30,
                35,35,35,30,35,35,25,25,10};
        int[] playerYVals = new int[] {0,10,15,25,50,70,90,110,150,150,110,90,90,
                110,150,150,110,90,70,50,25,15,10,0,0};
        //Moving the standard shape to the correct player position        
        for(int i=0; i<playerXVals.length; i++)
        {
            playerXVals[i] += startX;
        }
        for(int i=0; i<playerYVals.length; i++)
        {
            playerYVals[i] += startY;
        }
        g.setColor(c);
        g.fillPolygon(playerXVals,playerYVals,24);
    }

    public void drawShorts(Graphics g,Color color){
        //Draw the players Shorts
        int[] playerShortXVals = new int[] {0,0,0,14,15,20,21,35,35,35,0};
        int[] playerShortYVals = new int[] {70,90,110,110,90,90,110,110,90,70,70};
        for(int i=0; i<playerShortXVals.length; i++)
        {
            playerShortXVals[i] += startX;
        }
        for(int i=0; i<playerShortYVals.length; i++)
        {
            playerShortYVals[i] += startY;
        }
        g.setColor(color);
        g.fillPolygon(playerShortXVals,playerShortYVals, 11);
    }

    public void drawShirt(Graphics g, Color color){
        shirtColor = color;
        int[] playerShirtXVals = new int[] {10,0,0,5,0,35,30,35,35,25};
        int[] playerShirtYVals = new int[] {5,15,25,50,70,70,50,25,15,5};
        for(int i=0; i<playerShirtXVals.length; i++)
        {
            playerShirtXVals[i] += startX;
        }
        for(int i=0; i<playerShirtYVals.length; i++)
        {
            playerShirtYVals[i] += startY;
        }
        g.setColor(color);
        g.fillPolygon(playerShirtXVals,playerShirtYVals, 10);
    }

    public void drawForehandHit(Graphics g){
        //Draw the first Arm that is hitting
        isForehand = true;
        isBackhand = false;
        int[] forehandX = new int[] {35,55,80,80,55,35,35};
        int[] forehandY = new int[] {15,30,30,35,40,25,15}; 
        for(int i=0; i<forehandX.length; i++)
        {
            forehandX[i] += startX;
        }
        for(int i=0; i<forehandY.length; i++)
        {
            forehandY[i] += startY;
        }
        Color skinColor = new Color(255,232,142);
        g.setColor(skinColor);
        g.fillPolygon(forehandX,forehandY, 6);
        g.setColor(shirtColor);
        int[] shirtX = new int[] {35,45,45,35,35};
        int[] shirtY = new int[] {15,20,32,27,17};
        for(int i=0; i<shirtX.length; i++)
        {
            shirtX[i] += startX;
        }
        for(int i=0; i<shirtY.length; i++)
        {
            shirtY[i] += startY;
        }
        g.fillPolygon(shirtX,shirtY,4);
        //Draw the racket
        g.setColor(Color.black);
        int[] racketX = new int[] {80,105,105,80,80};
        int[] racketY = new int[] {30,30,35,35,30};
        for(int i=0; i<racketX.length; i++)
        {
            racketX[i] += startX; 
        }
        for(int i=0; i<racketY.length; i++)
        {
            racketY[i] += startY;
        }
        g.fillPolygon(racketX,racketY,4);
        g.fillOval(105+startX,20+startY,40,25);
        g.setColor(Color.white);
        g.fillOval(108+startX,23+startY,34,19);
        this.racketX = startX+105;
        this.racketY = 20+startY;
        this.racketWidth = 40;
        this.racketHeight = 25;
        //Draw the second hand that is not hitting
        int[] leftHandX = new int[] {0,-20,-20,0,0};
        int[] leftHandY = new int[] {15,50,60,25,15};
        for(int i=0; i<leftHandX.length; i++)
        {
            leftHandX[i] += startX;
        }
        for(int i=0; i<leftHandY.length; i++)
        {
            leftHandY[i] += startY;
        }
        g.setColor(skinColor);
        g.fillPolygon(leftHandX,leftHandY,4);
        g.setColor(shirtColor);
        shirtX = new int[] {0,-6,-6,0,0};
        shirtY = new int[] {15,27,37,27,17};
        for(int i=0; i<shirtX.length; i++)
        {
            shirtX[i] += startX;
        }
        for(int i=0; i<shirtY.length; i++)
        {
            shirtY[i] += startY;
        }
        g.fillPolygon(shirtX,shirtY,4);
    }

    public void drawForehandHit(Graphics g, Color c){
        //Draw the first Arm that is hitting
        g.setColor(c);
        isForehand = true;
        isBackhand = false;
        int[] forehandX = new int[] {35,55,80,80,55,35,35};
        int[] forehandY = new int[] {15,30,30,35,40,25,15}; 
        for(int i=0; i<forehandX.length; i++)
        {
            forehandX[i] += startX;
        }
        for(int i=0; i<forehandY.length; i++)
        {
            forehandY[i] += startY;
        }
        Color skinColor = new Color(255,232,142);
        g.fillPolygon(forehandX,forehandY, 6);
        g.setColor(shirtColor);
        int[] shirtX = new int[] {35,45,45,35,35};
        int[] shirtY = new int[] {15,20,32,27,17};
        for(int i=0; i<shirtX.length; i++)
        {
            shirtX[i] += startX;
        }
        for(int i=0; i<shirtY.length; i++)
        {
            shirtY[i] += startY;
        }
        g.fillPolygon(shirtX,shirtY,4);
        //Draw the racket
        int[] drawRacketX = new int[] {80,105,105,80,80};
        int[] drawRacketY = new int[] {30,30,35,35,30};
        for(int i=0; i<drawRacketX.length; i++)
        {
            drawRacketX[i] += startX; //-100?
        }
        for(int i=0; i<drawRacketY.length; i++)
        {
            drawRacketY[i] += startY;
        }
        g.fillPolygon(drawRacketX,drawRacketY,4);
        g.fillOval(105+startX,20+startY,40,25);
        g.fillOval(108+startX,23+startY,34,19);
        this.racketX = 105+startX;
        this.racketY = 20+startY;
        this.racketWidth = 40;
        this.racketHeight = 25;
        //Draw the second hand that is not hitting
        int[] leftHandX = new int[] {0,-20,-20,0,0};
        int[] leftHandY = new int[] {15,50,60,25,15};
        for(int i=0; i<leftHandX.length; i++)
        {
            leftHandX[i] += startX;
        }
        for(int i=0; i<leftHandY.length; i++)
        {
            leftHandY[i] += startY;
        }
        g.fillPolygon(leftHandX,leftHandY,4);
        shirtX = new int[] {0,-6,-6,0,0};
        shirtY = new int[] {15,27,37,27,17};
        for(int i=0; i<shirtX.length; i++)
        {
            shirtX[i] += startX;
        }
        for(int i=0; i<shirtY.length; i++)
        {
            shirtY[i] += startY;
        }
        g.fillPolygon(shirtX,shirtY,4);
    }

    public void drawBackhandHit(Graphics g){
        isForehand = false;
        isBackhand = true;
        //Draw the hand that is hitting
        Color skinColor = new Color(255,232,142);
        g.setColor(skinColor);
        int[] backhandX = new int[] {0,-30,-55,-55,-30,0,0};
        int[] backhandY = new int[] {15,30,30,35,40,25,15};
        for(int i=0; i<backhandX.length; i++)
        {
            backhandX[i] += startX;
        }
        for(int i=0; i<backhandY.length; i++)
        {
            backhandY[i] += startY;
        }
        g.fillPolygon(backhandX, backhandY, 6);
        int[] shirtX = new int[] {0,-10,-10,0,0};
        int[] shirtY = new int[] {15,20,30,25,15};
        for(int i=0; i<shirtX.length; i++)
        {
            shirtX[i] += startX;
        }
        for(int i=0; i<shirtY.length; i++)
        {
            shirtY[i] += startY;
        }
        g.setColor(shirtColor);
        g.fillPolygon(shirtX,shirtY, 4);
        //Draw the Racket
        g.setColor(Color.black);
        int[] racketX = new int[] {80,105,105,80,80};
        int[] racketY = new int[] {30,30,35,35,30};
        for(int i=0; i<racketX.length; i++)
        {
            racketX[i] -= 160;
            racketX[i] += startX;
        }
        for(int i=0; i<racketY.length; i++)
        {
            racketY[i] += startY;
        }
        g.fillPolygon(racketX,racketY,4);
        g.fillOval(-120+startX,20+startY,40,25);
        g.setColor(Color.white);
        g.fillOval(-117+startX,23+startY,34,19);
        this.racketX = -120+startX;
        this.racketY = 20+startY;
        this.racketWidth = 40;
        this.racketHeight = 25;
    }
    
    public void drawBackhandHit(Graphics g,Color c){
        g.setColor(c);
        isForehand = false;
        isBackhand = true;
        //Draw the hand that is hitting
        Color skinColor = new Color(255,232,142);
        int[] backhandX = new int[] {0,-30,-55,-55,-30,0,0};
        int[] backhandY = new int[] {15,30,30,35,40,25,15};
        for(int i=0; i<backhandX.length; i++)
        {
            backhandX[i] += startX;
        }
        for(int i=0; i<backhandY.length; i++)
        {
            backhandY[i] += startY;
        }
        g.fillPolygon(backhandX, backhandY, 6);
        int[] shirtX = new int[] {0,-10,-10,0,0};
        int[] shirtY = new int[] {15,20,30,25,15};
        for(int i=0; i<shirtX.length; i++)
        {
            shirtX[i] += startX;
        }
        for(int i=0; i<shirtY.length; i++)
        {
            shirtY[i] += startY;
        }
        g.fillPolygon(shirtX,shirtY, 4);
        //Draw the Racket
        int[] racketX = new int[] {80,105,105,80,80};
        int[] racketY = new int[] {30,30,35,35,30};
        for(int i=0; i<racketX.length; i++)
        {
            racketX[i] -= 160;
            racketX[i] += startX;
        }
        for(int i=0; i<racketY.length; i++)
        {
            racketY[i] += startY;
        }
        g.fillPolygon(racketX,racketY,4);
        g.fillOval(-120+startX,20+startY,40,25);
        g.fillOval(-117+startX,23+startY,34,19);
        this.racketX = -120+startX;
        this.racketY = 20+startY;
        this.racketWidth = 40;
        this.racketHeight = 25;
    }

    public int[] getRacketDimensions() {
        return new int[] {this.racketX,this.racketY,this.racketWidth,this.racketHeight};
    }

    public boolean isForehand(){
        return isForehand;
    }

    public boolean isBackhand(){
        return isBackhand;
    }
}
