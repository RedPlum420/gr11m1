import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarInputStream;
import java.util.Random;


public class Main {
    public static void main(String[] args) {

        Random rand = new Random();


        Game currentGame = new Game(rand.nextInt(0,101), 0);
        System.out.println(currentGame.numToGuess);


        JFrame frame = new JFrame("Calc, slang");
        frame.setSize(800, 750);
        frame.setLayout(new GridLayout(3,1));

        Font buttonFontBig = new Font("Serif", Font.BOLD, 65);
        Font inputFieldFont = new Font("Serif", Font.BOLD, 45);

        TextField firstNumber = new TextField();
        firstNumber.setFont(buttonFontBig);

        JLabel firstNumberHeading = new JLabel("Guessing Game");
        firstNumberHeading.setBackground(Color.gray);
        firstNumberHeading.setHorizontalAlignment(SwingConstants.CENTER);
        Border blackline = BorderFactory.createLineBorder(Color.black , 4);
        firstNumberHeading.setBorder(blackline);
        firstNumberHeading.setFont(buttonFontBig);

        JPanel NumberEnterPanel = new JPanel(new FlowLayout());
        //NumberEnterPanel.setLayout(new GridLayout(2,2));
        //NumberEnterPanel.setBounds(0, 0, 500, 100);

        NumberEnterPanel.add(firstNumberHeading);
        NumberEnterPanel.add(firstNumber);
        NumberEnterPanel.setLayout(new GridLayout(2,1));

        frame.add(NumberEnterPanel);

        //*****************************************************NUMBER ENTER SLOT***************************************//

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JLabel space = new JLabel(" ");

        Button guessButton = new Button(" GUESS ");
        guessButton.setFont(inputFieldFont);
        guessButton.setBackground(Color.BLUE);

        Button resetButton = new Button(" RESET ");
        resetButton.setFont(inputFieldFont);
        resetButton.setBackground(Color.GRAY);

        JLabel feedbackOnGuess = new JLabel("Guess number from 1 to 100");
        feedbackOnGuess.setFont(inputFieldFont);
        feedbackOnGuess.setBorder(blackline);
        feedbackOnGuess.setHorizontalAlignment(SwingConstants.HORIZONTAL);

        buttonPanel.add(space);
        buttonPanel.add(guessButton);
        buttonPanel.add(feedbackOnGuess);
        buttonPanel.add(space);
        buttonPanel.add(resetButton);
        buttonPanel.setLayout(new GridLayout(6,1));

        frame.add(buttonPanel);

        //**********************Button And FeedbackField***************************//

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {



                try{
                    int guess = Integer.parseInt(firstNumber.getText());

                    if(guess == currentGame.lastEnteredNumber && !currentGame.hasGuessed) {
                        feedbackOnGuess.setText("You Already Entered This");
                        return;
                    }

                    if(guess > 100){feedbackOnGuess.setText("Enter num less than 100");}
                    else if (guess <= 0) feedbackOnGuess.setText("Enter num greater than 0");
                    else {
                        if(currentGame.hasGuessed) return;

                        currentGame.numOfGuesses++;

                        if(guess < currentGame.numToGuess) feedbackOnGuess.setText("Enter a bigger number");
                        else if(guess > currentGame.numToGuess) feedbackOnGuess.setText("Enter a smaller number");
                        else {
                            feedbackOnGuess.setText("!!!GOOD JOB!!! It took: " + currentGame.numOfGuesses);
                            currentGame.hasGuessed = true;
                        }
                        currentGame.lastEnteredNumber = guess;
                    }
                }catch(Exception ex) {
                    feedbackOnGuess.setText("!INVALID ENTRY!");
                }


            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNumber.setText("");
                feedbackOnGuess.setText("Guess number from 1 to 100");

                currentGame.numToGuess = rand.nextInt(1,101);
                currentGame.numOfGuesses = 0;
                currentGame.hasGuessed = false;

               // System.out.println(currentGame.numToGuess);
            }
        });

        //*******************Button Functionality********************//
        frame.setVisible(true);
    }


}

class Game{
    public int numToGuess;
    public int numOfGuesses;
    public boolean hasGuessed = false;
    public int lastEnteredNumber;

    public Game(int numToGuess, int numOfGuesses){
        this.numToGuess = numToGuess;
        this.numOfGuesses = numOfGuesses;
        hasGuessed = false;
    }
}

