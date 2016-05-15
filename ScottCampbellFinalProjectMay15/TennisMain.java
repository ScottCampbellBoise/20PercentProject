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
/**
 * Write a description of class TennisMain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TennisMain implements ActionListener
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

    private JTextField colorField;
    private JPanel colorPanel;
    private JPanel colorSample;

    private final int easy = 1;
    private final int normal = 2;
    private final int hard = 3;

    private Color gameColor = Color.white;
    private int gameDifficulty = normal;
    private BufferedImage gameImage;

    private boolean isImage = false;
    private boolean isColor = true;

    private final String imagePathwayStart = "BackgroundImages/";

    private Color buttonSelected = new Color(0,147,221);
    public static void main(String[] args){ 
        TennisMain tennisPanel = new TennisMain();
    }

    public TennisMain(){
        frame = new JFrame("Tennis Tour Pro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setResizable(false);

        setUpInitialButtons();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(onePlayerButton) )//Open Buttons: initial Buttons, Close Buttons:
        {
            onePlayerButton.setForeground(buttonSelected);
            removeInitialButtons();
            NewOnePlayer onePlayer = null;
            if(isImage){
                onePlayer = new NewOnePlayer(gameDifficulty, gameImage,"");
            }
            else{
                onePlayer = new NewOnePlayer(gameDifficulty, gameColor,"");
            }
            gameFrame = new JFrame("Tennis Tour Pro: One Player Game");
            gameFrame.setSize(900,800);
            gameFrame.add(onePlayer, BorderLayout.CENTER);
            gameFrame.setVisible(true);
            gameFrame.setResizable(false);
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else if(e.getSource().equals(twoPlayerButton) ){
            twoPlayerButton.setForeground(buttonSelected);
            removeInitialButtons();
            TwoPlayer twoPlayer = null;
            if(isImage){
                twoPlayer = new TwoPlayer(gameImage,"","");
            }
            else{
                twoPlayer = new TwoPlayer(gameColor,"","");
            }
            gameFrame = new JFrame("Tennis Tour Pro: Two Player Game");
            gameFrame.setSize(820,600);
            gameFrame.add(twoPlayer, BorderLayout.CENTER);
            gameFrame.setVisible(true);
            gameFrame.setResizable(false);
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else if(e.getSource().equals(optionsButton) ){
            optionsButton.setForeground(buttonSelected);
            removeInitialButtons();
            setUpGameOptionButtons();
        }
        else if(e.getSource().equals(colorButton) ){
            colorButton.setForeground(buttonSelected);
            removeGameOptionButtons();
            setUpColorTextField();
        }
        else if(e.getSource().equals(difficultyButton) ){
            difficultyButton.setForeground(buttonSelected);
            removeGameOptionButtons();
            setUpGameDifficultyButtons();
        }
        else if(e.getSource().equals(returnButton) ){
            returnButton.setForeground(buttonSelected);
            frame.removeAll();
            setUpInitialButtons();
        }
        else if(e.getSource().equals(colorField) ){
            String text = colorField.getText();
            colorField.selectAll();
            findColor(text);
        }
        else if(e.getSource().equals(easyButton) ){
            easyButton.setForeground(buttonSelected);
            gameDifficulty = easy;
            removeGameDifficultyButtons();
            setUpInitialButtons();
        }
        else if(e.getSource().equals(normalButton) ){
            normalButton.setForeground(buttonSelected);
            gameDifficulty = normal;
            removeGameDifficultyButtons();
            setUpInitialButtons();
        }
        else if(e.getSource().equals(hardButton) ){
            hardButton.setForeground(buttonSelected);
            gameDifficulty = hard;
            removeGameDifficultyButtons();
            setUpInitialButtons();
        }
        else if(e.getSource().equals(oceanBkgrd) )
        {
            oceanBkgrd.setForeground(buttonSelected);
            try{
                gameImage = ImageIO.read(new File(imagePathwayStart+"ocean.jpeg"));
                isColor = false; 
                isImage = true;
            }
            catch(IOException ex){
                System.out.println("Ocean Background image was not found");
            }
        }
        else if(e.getSource().equals(spaceBkgrd) )
        {
            spaceBkgrd.setForeground(buttonSelected);
            try{
                gameImage = ImageIO.read(new File(imagePathwayStart+"space.jpeg"));
                isColor = false; 
                isImage = true;
            }
            catch(IOException ex){
                System.out.println("Space Background image was not found");
            }
        }
        else if(e.getSource().equals(grassBkgrd) )
        {
            grassBkgrd.setForeground(buttonSelected);
            try{
                gameImage = ImageIO.read(new File(imagePathwayStart+"grass.jpeg"));
                isColor = false; 
                isImage = true;
            }
            catch(IOException ex){
                System.out.println("Grass Background image was not found");
            }
        }
        else if(e.getSource().equals(wimbeltonBkgd) )
        {
            wimbeltonBkgd.setForeground(buttonSelected);
            try{
                gameImage = ImageIO.read(new File(imagePathwayStart+"wimbelton.jpeg"));
                isColor = false; 
                isImage = true;
            }
            catch(IOException ex){
                System.out.println("Wimbelton Background image was not found");
            }
        }
        frame.validate();
        frame.repaint();
    }

    public void setUpInitialButtons(){
        onePlayerButton = new JButton();
        onePlayerButton.setText("One Player");
        frame.add(onePlayerButton);
        onePlayerButton.setBounds(50,50,150,30);
        onePlayerButton.addActionListener(this);

        twoPlayerButton = new JButton();
        twoPlayerButton.setText("Two Players");
        frame.add(twoPlayerButton);
        twoPlayerButton.setBounds(50,100,150,30);
        twoPlayerButton.addActionListener(this);

        optionsButton = new JButton();
        optionsButton.setText("Game Options");
        frame.add(optionsButton);
        optionsButton.setBounds(50,150,150,30);
        optionsButton.addActionListener(this);

        returnButton = new JButton();
        returnButton.setText("Return to Main");
        frame.add(returnButton);
        returnButton.setBounds(50,350,150,30);
        returnButton.addActionListener(this);
    }

    public void removeInitialButtons(){
        frame.remove(onePlayerButton);
        frame.remove(twoPlayerButton);
        frame.remove(optionsButton);
    }

    public void setUpGameOptionButtons(){
        colorButton = new JButton();
        colorButton.setText("Change Background");
        frame.add(colorButton);
        colorButton.setBounds(50,50,150,30);
        colorButton.addActionListener(this);

        difficultyButton = new JButton();
        difficultyButton.setText("Difficulty");
        frame.add(difficultyButton);
        difficultyButton.setBounds(50,100,150,30);
        difficultyButton.addActionListener(this);

        returnButton = new JButton();
        returnButton.setText("Return to Main");
        frame.add(returnButton);
        returnButton.setBounds(50,350,150,30);
        returnButton.addActionListener(this);
    }

    public void removeGameOptionButtons(){
        frame.remove(colorButton);
        frame.remove(difficultyButton);
    }

    public void setUpColorTextField(){
        String backgroundSelected = "Enter an Image Pathway, Color, or RGB in nine digit form";
        JLabel colorFieldMsg = new JLabel(backgroundSelected);
        colorFieldMsg.setBounds(0,50,500,30);
        colorFieldMsg.setVisible(true);

        oceanBkgrd = new JButton();
        oceanBkgrd.setText("Ocean Scene");
        frame.add(oceanBkgrd);
        oceanBkgrd.setBounds(250,100,150,30);
        oceanBkgrd.addActionListener(this);
        oceanBkgrd.setVisible(true);

        spaceBkgrd = new JButton();
        spaceBkgrd.setText("Space Scene");
        frame.add(spaceBkgrd);
        spaceBkgrd.setBounds(250,150,150,30);
        spaceBkgrd.addActionListener(this);
        spaceBkgrd.setVisible(true);

        grassBkgrd = new JButton();
        grassBkgrd.setText("Grass Scene");
        frame.add(grassBkgrd);
        grassBkgrd.setBounds(250,200,150,30);
        grassBkgrd.addActionListener(this);
        grassBkgrd.setVisible(true);

        wimbeltonBkgd = new JButton();
        wimbeltonBkgd.setText("Wimbledon Scene");
        frame.add(wimbeltonBkgd);
        wimbeltonBkgd.setBounds(250,250,150,30);
        wimbeltonBkgd.addActionListener(this);
        wimbeltonBkgd.setVisible(true);

        //Return to main buttons
        onePlayerButton = new JButton();
        onePlayerButton.setText("One Player");
        frame.add(onePlayerButton);
        onePlayerButton.setBounds(50,100,150,30);
        onePlayerButton.addActionListener(this);

        twoPlayerButton = new JButton();
        twoPlayerButton.setText("Two Players");
        frame.add(twoPlayerButton);
        twoPlayerButton.setBounds(50,150,150,30);
        twoPlayerButton.addActionListener(this);

        optionsButton = new JButton();
        optionsButton.setText("Game Options");
        frame.add(optionsButton);
        optionsButton.setBounds(50,200,150,30);
        optionsButton.addActionListener(this);

        //Add in the text field
        colorField = new JTextField(10);
        colorField.setHorizontalAlignment(JTextField.CENTER);
        colorField.addActionListener(this);

        colorPanel = new JPanel();
        frame.add(colorPanel);
        colorPanel.add(colorField);
        frame.setVisible(true);
        colorPanel.add(colorFieldMsg);
    }

    public void findColor(String input)
    {
        if(input.toLowerCase().equals("red")){gameColor = Color.red; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("green")){gameColor = Color.green; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("blue")){gameColor = Color.blue; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("yellow")){gameColor = Color.yellow; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("orange")){gameColor = Color.orange; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("black")){gameColor = Color.black; isColor = true; isImage = false;}
        else if(input.toLowerCase().equals("white")){gameColor = Color.white; isColor = true; isImage = false;}
        else
        {
            try
            {
                int redConc = Integer.parseInt(input.substring(0,3));
                int greenConc = Integer.parseInt(input.substring(3,6));
                int blueConc = Integer.parseInt(input.substring(6,9));
                gameColor = new Color(redConc,greenConc,blueConc);
                isColor = true; 
                isImage = false;
            }
            catch(IndexOutOfBoundsException e)
            {
                JLabel errorMessage = new JLabel("Did not Recognize the Number Format. Please try again.");
                errorMessage.setBounds(50,200,300,30);
                errorMessage.setVisible(true);
                colorPanel.add(errorMessage);
            }
            catch(NumberFormatException e)
            {
                JLabel errorMessage = new JLabel("Did not Recognize the Number Format. Please try again.");
                errorMessage.setBounds(50,200,300,30);
                errorMessage.setVisible(true);
                colorPanel.add(errorMessage);
            }
            try
            {
                gameImage = ImageIO.read(new File(input));
                isColor = false; 
                isImage = true;
            }
            catch(IOException e)
            {
                JLabel errorMessage = new JLabel("Did not Recognize the Image Pathway. Please try again.");
                errorMessage.setBounds(50,200,300,30);
                errorMessage.setVisible(true);
                colorPanel.add(errorMessage);
            }
        }
    }

    public void setUpGameDifficultyButtons()
    {
        JButton hardButton = new JButton();
        hardButton.setText("Hard");
        hardButton.setBounds(50,50,100,30);
        frame.add(hardButton);
        hardButton.addActionListener(this);

        JButton normalButton = new JButton();
        normalButton.setText("Normal");
        normalButton.setBounds(50,100,100,30);
        frame.add(normalButton);
        normalButton.addActionListener(this);

        JButton easyButton = new JButton();
        easyButton.setText("Easy");
        easyButton.setBounds(50,150,100,30);
        frame.add(easyButton);
        easyButton.addActionListener(this);

        returnButton = new JButton();
        returnButton.setText("Return to Main");
        frame.add(returnButton);
        returnButton.setBounds(50,350,150,30);
        returnButton.addActionListener(this);
    }

    public void removeGameDifficultyButtons()
    {
        frame.remove(hardButton);
        frame.remove(normalButton);
        frame.remove(easyButton);
        frame.remove(returnButton);
    }
}