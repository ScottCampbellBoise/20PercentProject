import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;
import javax.swing.*;
public class NewTennisMain2 implements ActionListener
{
    private static JFrame frame;
    public static JFrame gameFrame;

    private JButton onePlayerButton;
    private JButton twoPlayerButton;
    private JButton optionsButton;
    private JButton colorButton;
    private JButton difficultyButton;
    private JButton returnButton;
    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;
    private JButton oceanBkgrd;
    private JButton spaceBkgrd;
    private JButton grassBkgrd;
    private JButton wimbeltonBkgd;
    private JButton playButton;

    private JTextField colorField;
    private JTextField plyr1Name;
    private JTextField plyr2Name;
    private JPanel panel;
    private JLabel errorMessage;

    private JPanel colorPanel;

    private final int easy = 1;
    private final int normal = 2;
    private final int hard = 3;

    private String player1String = "Plyr 1";
    private String player2String = "Plyr 2";

    private Color gameColor = Color.white;
    private int gameDifficulty = normal;
    private BufferedImage gameImage;

    private boolean isImage = false;
    private boolean isColor = true;

    private boolean isOnePlayer = false;
    private boolean isTwoPlayer = false;

    private final String imagePathwayStart = "BackgroundImages/";
    private String backgroundString;

    private Color buttonSelected = new Color(0,147,221);
    public static void main(String[] args){ 
        NewTennisMain2 tennisPanel = new NewTennisMain2();
    }

    public NewTennisMain2(){
        frame = new JFrame("Tennis Tour Pro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.validate();
        frame.repaint();
        setUpInitialButtons();
    }

    public void setUpInitialButtons(){
        onePlayerButton = new JButton();
        onePlayerButton.setText("One Player");
        frame.add(onePlayerButton);
        onePlayerButton.setBounds(50,50,150,30);
        onePlayerButton.addActionListener(this);
        onePlayerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(onePlayerButton);
                    frame.remove(twoPlayerButton);
                    frame.validate();
                    frame.repaint();
                    isOnePlayer = true;
                    isTwoPlayer = false;
                    setUpGameOptionsPlyr1();
                }
            });

        twoPlayerButton = new JButton();
        twoPlayerButton.setText("Two Players");
        frame.add(twoPlayerButton);
        twoPlayerButton.setBounds(50,100,150,30);
        twoPlayerButton.addActionListener(this);
        twoPlayerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(onePlayerButton);
                    frame.remove(twoPlayerButton);
                    frame.validate();
                    frame.repaint();
                    isTwoPlayer = true;
                    isOnePlayer = false;
                    setUpGameOptionsPlyr2();
                }
            });
    }

    public void setUpGameOptionsPlyr1(){
        JLabel msg = new JLabel("Enter Your Name: ");
        msg.setBounds(50,100,350,50);
        colorButton = new JButton();
        colorButton.setText("Change Background");
        frame.add(colorButton);
        colorButton.setBounds(50,150,150,30);
        colorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(plyr1Name);
                    frame.remove(panel);
                    frame.remove(msg);
                    frame.remove(colorButton);
                    frame.remove(difficultyButton);
                    frame.remove(returnButton);
                    frame.remove(playButton);
                    frame.validate();
                    frame.repaint();
                    setUpChangeBackground();
                }
            });

        difficultyButton = new JButton();
        difficultyButton.setText("Difficulty");
        frame.add(difficultyButton);
        difficultyButton.setBounds(50,200,150,30);
        difficultyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(plyr1Name);
                    frame.remove(panel);
                    frame.remove(msg);
                    frame.remove(colorButton);
                    frame.remove(difficultyButton);
                    frame.remove(returnButton);
                    frame.remove(playButton);
                    frame.validate();
                    frame.repaint();
                    setUpDifficulty();
                }
            });

        playButton = new JButton();
        playButton.setText("Play!");
        frame.add(playButton);
        playButton.setBounds(50,250,150,30);
        playButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(plyr1Name);
                    frame.remove(panel);
                    frame.remove(msg);
                    frame.remove(colorButton);
                    frame.remove(difficultyButton);
                    frame.remove(returnButton);
                    frame.remove(playButton);
                    frame.validate();
                    frame.repaint();
                    startGameOnePlayer();
                    setUpInitialButtons();
                }
            });

        returnButton = new JButton();
        returnButton.setText("Return to Main");
        frame.add(returnButton);
        returnButton.setBounds(50,300,150,30);
        returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(plyr1Name);
                    frame.remove(panel);
                    frame.remove(msg);
                    frame.remove(colorButton);
                    frame.remove(difficultyButton);
                    frame.remove(returnButton);
                    frame.remove(playButton);
                    frame.validate();
                    frame.repaint();
                    setUpInitialButtons();
                }
            });

        plyr1Name = new JTextField(10);
        plyr1Name.setText(player1String);
        plyr1Name.setHorizontalAlignment(JTextField.CENTER);
        panel = new JPanel();
        frame.add(panel);
        panel.add(msg);
        panel.add(plyr1Name);
        plyr1Name.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player1String = plyr1Name.getText();
                    plyr1Name.selectAll();
                    frame.validate();
                    frame.repaint();
                }
            });
        frame.validate();
        frame.repaint();
    }

    public void setUpGameOptionsPlyr2(){
        JLabel msg = new JLabel("Enter Your Names: ");
        colorButton = new JButton();
        colorButton.setText("Change Background");
        frame.add(colorButton);
        colorButton.setBounds(50,50,150,30);
        colorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(plyr1Name);
                    frame.remove(panel);
                    frame.remove(msg);
                    frame.remove(colorButton);
                    frame.remove(difficultyButton);
                    frame.remove(returnButton);
                    frame.remove(playButton);
                    frame.validate();
                    frame.repaint();
                    setUpChangeBackground();
                }
            });

        difficultyButton = new JButton();
        difficultyButton.setText("Difficulty");
        frame.add(difficultyButton);
        difficultyButton.setBounds(50,100,150,30);
        difficultyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(plyr1Name);
                    frame.remove(panel);
                    frame.remove(msg);
                    frame.remove(colorButton);
                    frame.remove(difficultyButton);
                    frame.remove(returnButton);
                    frame.remove(playButton);
                    frame.validate();
                    frame.repaint();
                    setUpDifficulty();
                }
            });

        playButton = new JButton();
        playButton.setText("Play!");
        frame.add(playButton);
        playButton.setBounds(50,250,150,30);
        playButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(plyr1Name);
                    frame.remove(panel);
                    frame.remove(msg);
                    frame.remove(colorButton);
                    frame.remove(difficultyButton);
                    frame.remove(returnButton);
                    frame.remove(playButton);
                    frame.remove(playButton);
                    frame.validate();
                    frame.repaint();
                    startGameTwoPlayer();
                    setUpInitialButtons();
                }
            });

        returnButton = new JButton();
        returnButton.setText("Return to Main");
        frame.add(returnButton);
        returnButton.setBounds(50,300,150,30);
        returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(plyr1Name);
                    frame.remove(panel);
                    frame.remove(msg);
                    frame.remove(colorButton);
                    frame.remove(difficultyButton);
                    frame.remove(returnButton);
                    frame.remove(playButton);
                    frame.validate();
                    frame.repaint();
                    setUpInitialButtons();
                }
            });
        plyr1Name = new JTextField(10);
        plyr2Name = new JTextField(10);
        plyr1Name.setText(player1String);
        plyr2Name.setText(player2String);
        plyr1Name.setHorizontalAlignment(JTextField.LEFT);
        plyr2Name.setHorizontalAlignment(JTextField.RIGHT);
        panel = new JPanel();
        frame.add(panel);
        panel.add(msg);
        panel.add(plyr1Name);
        panel.add(plyr2Name);
        plyr1Name.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player1String = plyr1Name.getText();
                    plyr1Name.selectAll();
                    frame.validate();
                    frame.repaint();
                }
            });
        plyr2Name.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player2String = plyr2Name.getText();
                    plyr2Name.selectAll();
                    frame.validate();
                    frame.repaint();
                }
            });
        frame.validate();
        frame.repaint();
    }

    public void setUpChangeBackground(){
        errorMessage = new JLabel("");

        String backgroundSelected = "Enter an Image Pathway, Color, or RGB in nine digit form";
        JLabel colorFieldMsg = new JLabel(backgroundSelected);
        colorFieldMsg.setBounds(0,50,500,30);
        colorFieldMsg.setVisible(true);

        oceanBkgrd = new JButton();
        oceanBkgrd.setText("Ocean Scene");
        frame.add(oceanBkgrd);
        oceanBkgrd.setBounds(275,100,150,30);
        oceanBkgrd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    oceanBkgrd.setForeground(buttonSelected);
                    try{
                        gameImage = ImageIO.read(new File(imagePathwayStart+"ocean.jpeg"));
                        isColor = false; 
                        isImage = true;
                        backgroundString = "Ocean Scene";
                    }
                    catch(IOException ex){
                        System.out.println("Ocean Background image was not found");
                    }
                    frame.validate();
                    frame.repaint();
                }
            });
        oceanBkgrd.setVisible(true);

        spaceBkgrd = new JButton();
        spaceBkgrd.setText("Space Scene");
        frame.add(spaceBkgrd);
        spaceBkgrd.setBounds(275,150,150,30);
        spaceBkgrd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    spaceBkgrd.setForeground(buttonSelected);
                    try{
                        gameImage = ImageIO.read(new File(imagePathwayStart+"space.jpeg"));
                        isColor = false; 
                        isImage = true;
                        backgroundString = "Space Scene";
                    }
                    catch(IOException ex){
                        System.out.println("Space Background image was not found");
                    }
                    frame.validate();
                    frame.repaint();
                }
            });
        spaceBkgrd.setVisible(true);

        grassBkgrd = new JButton();
        grassBkgrd.setText("Grass Scene");
        frame.add(grassBkgrd);
        grassBkgrd.setBounds(275,200,150,30);
        grassBkgrd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    grassBkgrd.setForeground(buttonSelected);
                    try{
                        gameImage = ImageIO.read(new File(imagePathwayStart+"grass.jpeg"));
                        isColor = false; 
                        isImage = true;
                        backgroundString = "Grass Scene";
                    }
                    catch(IOException ex){
                        System.out.println("Grass Background image was not found");
                    }
                    frame.validate();
                    frame.repaint();
                }
            });
        grassBkgrd.setVisible(true);

        wimbeltonBkgd = new JButton();
        wimbeltonBkgd.setText("Wimbledon Scene");
        frame.add(wimbeltonBkgd);
        wimbeltonBkgd.setBounds(275,250,150,30);
        wimbeltonBkgd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    wimbeltonBkgd.setForeground(buttonSelected);
                    try{
                        gameImage = ImageIO.read(new File(imagePathwayStart+"wimbelton.jpeg"));
                        isColor = false; 
                        isImage = true;
                        backgroundString = "Wimbledon Scene";
                    }
                    catch(IOException ex){
                        System.out.println("Wimbelton Background image was not found");
                    }
                    frame.validate();
                    frame.repaint();
                }
            });
        wimbeltonBkgd.setVisible(true);

        optionsButton = new JButton();
        optionsButton.setText("Game Options");
        frame.add(optionsButton);
        optionsButton.setBounds(50,100,150,30);
        optionsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(colorField);
                    frame.remove(colorFieldMsg);
                    frame.remove(colorPanel);
                    frame.remove(oceanBkgrd);
                    frame.remove(spaceBkgrd);
                    frame.remove(grassBkgrd);
                    frame.remove(wimbeltonBkgd);
                    frame.remove(optionsButton);
                    frame.remove(returnButton);
                    frame.remove(errorMessage);
                    frame.validate();
                    frame.repaint();
                    if(isOnePlayer){setUpGameOptionsPlyr1();}
                    else{setUpGameOptionsPlyr2();}
                }
            });

        //Add in the text field
        colorField = new JTextField(10);
        colorField.setHorizontalAlignment(JTextField.RIGHT);
        colorField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    String text = colorField.getText();
                    colorField.selectAll();
                    frame.validate();
                    frame.repaint();
                    findColor(text);
                }
            });

        returnButton = new JButton();
        returnButton.setText("Return to Main");
        frame.add(returnButton);
        returnButton.setBounds(50,150,150,30);
        returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(colorField);
                    frame.remove(colorFieldMsg);
                    frame.remove(colorPanel);
                    frame.remove(oceanBkgrd);
                    frame.remove(spaceBkgrd);
                    frame.remove(grassBkgrd);
                    frame.remove(wimbeltonBkgd);
                    frame.remove(optionsButton);
                    frame.remove(returnButton);
                    frame.remove(errorMessage);
                    frame.validate();
                    frame.repaint();
                    setUpInitialButtons();
                }
            });

        colorPanel = new JPanel();
        frame.add(colorPanel);
        colorPanel.add(colorField);
        frame.setVisible(true);
        colorPanel.add(colorFieldMsg);
        colorPanel.add(errorMessage);
        if(backgroundString != null){errorMessage.setText("Background: '"+backgroundString+"'.");}
        errorMessage.setBounds(50,300,350,30);
        errorMessage.setVisible(true);
        frame.validate();
        frame.repaint();
    }

    public void setUpDifficulty(){
        JButton hardButton = new JButton();
        hardButton.setText("Hard");
        hardButton.setBounds(50,50,100,30);
        frame.add(hardButton);
        hardButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    hardButton.setForeground(buttonSelected);
                    gameDifficulty = hard;
                }
            });

        JButton normalButton = new JButton();
        normalButton.setText("Normal");
        normalButton.setBounds(50,100,100,30);
        frame.add(normalButton);
        normalButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    normalButton.setForeground(buttonSelected);
                    gameDifficulty = normal;
                }
            });

        JButton easyButton = new JButton();
        easyButton.setText("Easy");
        easyButton.setBounds(50,150,100,30);
        frame.add(easyButton);
        easyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    easyButton.setForeground(buttonSelected);
                    gameDifficulty = easy;
                }
            });

        optionsButton = new JButton();
        optionsButton.setText("Game Options");
        frame.add(optionsButton);
        optionsButton.setBounds(50,200,150,30);
        optionsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(hardButton);
                    frame.remove(normalButton);
                    frame.remove(easyButton); 
                    frame.remove(optionsButton);
                    frame.remove(returnButton);
                    frame.validate();
                    frame.repaint();
                    if(isOnePlayer){setUpGameOptionsPlyr1();}
                    else{setUpGameOptionsPlyr2();}
                }
            });

        returnButton = new JButton();
        returnButton.setText("Return to Main");
        frame.add(returnButton);
        returnButton.setBounds(50,250,150,30);
        returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.remove(hardButton);
                    frame.remove(normalButton);
                    frame.remove(easyButton); 
                    frame.remove(optionsButton);
                    frame.remove(returnButton);
                    frame.validate();
                    frame.repaint();
                    setUpInitialButtons();
                }
            });
    }

    public void findColor(String input)
    {
        boolean numFormatError = false;
        boolean imagePathError = false;
        if(input.toLowerCase().equals("red")){gameColor = Color.red; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("green")){gameColor = Color.green; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("blue")){gameColor = Color.blue; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("yellow")){gameColor = Color.yellow; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("orange")){gameColor = Color.orange; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("black")){gameColor = Color.black; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("white")){gameColor = Color.white; isColor = true; isImage = false;}
        else{
            try{
                int redConc = Integer.parseInt(input.substring(0,3));
                int greenConc = Integer.parseInt(input.substring(3,6));
                int blueConc = Integer.parseInt(input.substring(6,9));
                gameColor = new Color(redConc,greenConc,blueConc);
                isColor = true; 
                isImage = false;
            }
            catch(Exception e){
                numFormatError = true;
            }
            try{
                gameImage = ImageIO.read(new File(input));
                isColor = false; 
                isImage = true;
            }
            catch(Exception e){
                imagePathError = true;
            }
            if(numFormatError == true 
            && imagePathError == true){
                errorMessage.setText("Did not Recognize '" +input+ "'. Please try again.");
                errorMessage.setBounds(50,300,350,30);
                errorMessage.setVisible(true);
            }
            else{
                if(input.length() >= 45){backgroundString = input.substring(0,45)+"...";}
                errorMessage.setText("Background: '"+backgroundString+"'.");
                errorMessage.setBounds(50,300,350,30);
                errorMessage.setVisible(true);
            }
        }
    }

    public void startGameOnePlayer(){
        onePlayerButton.setForeground(buttonSelected);
        NewOnePlayer onePlayer = null;
        if(isImage){
            onePlayer = new NewOnePlayer(gameDifficulty, gameImage,player1String);
        }
        else{
            onePlayer = new NewOnePlayer(gameDifficulty, gameColor, player1String);
        }
        gameFrame = new JFrame("Tennis Tour Pro: One Player Game");
        gameFrame.setSize(900,800);
        gameFrame.add(onePlayer, BorderLayout.CENTER);
        gameFrame.setVisible(true);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void startGameTwoPlayer(){
        twoPlayerButton.setForeground(buttonSelected);
        TwoPlayer twoPlayer = null;
        if(isImage){
            twoPlayer = new TwoPlayer(gameImage,player1String,player2String);
        }
        else{
            twoPlayer = new TwoPlayer(gameColor,player1String,player2String);
        }
        gameFrame = new JFrame("Tennis Tour Pro: Two Player Game");
        gameFrame.setSize(820,600);
        gameFrame.add(twoPlayer, BorderLayout.CENTER);
        gameFrame.setVisible(true);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){
    }
}