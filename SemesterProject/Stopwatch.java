import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Main {
   static JTextArea textArea = new JTextArea();
    public static void main(String[] args) {

        Stopwatch myStopwatch = new Stopwatch();

        //Creation of the panel and setting it up
        JFrame frame = new JFrame("Stopwatch");
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(670, 910);
        frame.setLayout(new GridLayout(4, 1));

        //Creation of a font with the standard font, but different size
        Font coolFont = new Font("...", Font.PLAIN, 58);

        //App title
        JLabel label = new JLabel("Stopwatch");
        label.setFont(new Font("Garamond", Font.PLAIN, 85));

        //Set label spacing and center it
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.white);

        //Add label to the frame
        frame.add(label);

        //New JLabel will display the time of the stopwatch, makes it darkMode and centers it
        JLabel timeDisplay = new JLabel("0 0 : 0 0 , 0 0");
        timeDisplay.setFont(coolFont);
        timeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        timeDisplay.setVerticalAlignment(SwingConstants.CENTER);
        timeDisplay.setForeground(Color.white);
        frame.add(timeDisplay);

        //Panel dealing with the buttons for start and stop
        JPanel stopwatchControl = new JPanel();
        stopwatchControl.setLayout(new GridLayout(1, 3));

        //Sets the background to black
        stopwatchControl.setBackground(new Color(0,0,0,0));

        //Creates a cricular button which will act as both the reset and lap.
        CircularButton LapResetButton = new CircularButton("Reset", Color.gray);
        LapResetButton.setPreferredSize( new Dimension(100,100));
        LapResetButton.setFont(new Font("Garamond", Font.PLAIN, 40));
        stopwatchControl.add(LapResetButton);

        //Adds empty space between the two buttons so they are in both ends
        JLabel emptySpace = new JLabel(" ");
        stopwatchControl.add(emptySpace);

        //Creates another circular button which is going to be the start and stop
        CircularButton StartStopButton = new CircularButton("Start", new Color(35, 159, 5, 167));
        StartStopButton.setPreferredSize( new Dimension(100,100));
        StartStopButton.setForeground(new Color(35, 240, 5, 225));
        StartStopButton.setFont(new Font("Garamond", Font.PLAIN, 40));
        stopwatchControl.add(StartStopButton);

        //Adds the button controls to the frame
        frame.add(stopwatchControl);

        //Fixes the text area, sets font and parameters.
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Garamond", Font.PLAIN, 30));
        textArea.setForeground(Color.white);
        textArea.setText("");
        textArea.setOpaque(true);
        //For some reason grid layouts did not work so manually set bounds
        textArea.setBounds(0, 600, 670, 200);
        textArea.setBackground(Color.black);

        //Creates a scroll pane for the text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));

        //Customizes the scroll pane
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            //Changes the overall color scheme of the scroll pane
            protected void configureScrollBarColors() {
                this.thumbColor = Color.white;
                this.thumbLightShadowColor = Color.white;
                this.trackColor = Color.black;
            }

            //Customizes the decrease button to fit the minimal aesthetic
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(Color.BLACK);
                button.setOpaque(true);
                button.setForeground(Color.white);
                button.setBorder(BorderFactory.createLineBorder(Color.black));
                return button;
            }

            //Customizes the decrease button to fit the minimal aesthetic
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(Color.BLACK);
                button.setOpaque(true);
                button.setForeground(Color.white);
                button.setBorder(BorderFactory.createLineBorder(Color.black));
                return button;
            }
        });

        //Adds the text area along with the scroll pane
        frame.add(scrollPane);

        //Displays the frame
        frame.setVisible(true);

        //Action listener for the star and stop button
        StartStopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Start the timer or stop it depending on whether it is running or not

                //Started timer
                if(myStopwatch.StartOrStopTimer() == 1){
                    StartStopButton.setText("Stop");
                    //Change color and text of the button to display a change in functionality
                    StartStopButton.SetNotPressedColor(new Color(146, 0, 0, 255));
                    StartStopButton.setForeground(new Color(255, 0, 0, 255));

                    //Change the lap/reset button to fit the current functionality
                    LapResetButton.setText("Lap");
                }else
                { //Stopped timer
                    StartStopButton.setText("Start");
                    StartStopButton.SetNotPressedColor(new Color(23, 93, 1, 255));
                    StartStopButton.setForeground(new Color(35, 240, 5, 225));

                    //Change color and text of the button to display a change in functionality

                    //Change the lap/reset button to fit the current functionality
                    LapResetButton.setText("Reset");
                }
            }
        });

        //Handles the lap and reset button
        LapResetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //It is running so it will make a lap
                if(myStopwatch.getIsRunning() == 1){
                    //Add a lap to the array
                    myStopwatch.AddLap();
                    //Display Laps
                    textArea.setText(myStopwatch.getLaps());
                }else{
                    //Reset Stopwatch
                    myStopwatch.ResetStopwatch();
                    //Display that time is Zero
                    timeDisplay.setText(myStopwatch.convTimeToStringForDisplay(myStopwatch.getTime(), true));
                    //Reset the lap display text area
                    textArea.setText("");
                }
            }
        });

        //Handle the program to run continously
        while(true){
            System.out.print("");
            //If the stopwatch is running update the time
            while(myStopwatch.getIsRunning() == 1){
                timeDisplay.setText("" + myStopwatch.convTimeToStringForDisplay(myStopwatch.getTime(),true));
                myStopwatch.IncrementTime();
            }
        }
    }

}

class CircularButton extends JButton {

    private Color NotPressedColor = Color.CYAN;

    public void SetNotPressedColor(Color notPressedColor) {
        this.NotPressedColor = notPressedColor;
    }

    //Disable standard things, but text remains, set not pressed color because standard thing doesnt work (it draws here)
    public CircularButton(String text, Color notPressedColor) {
        super(text);
        this.NotPressedColor = notPressedColor;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setRolloverEnabled(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        // Create a circle by drawing an oval with the same width and height
        if (getModel().isPressed()) {
            g.setColor(Color.DARK_GRAY); // Set color for when the button is pressed
        } else {
            g.setColor(NotPressedColor); // Set default color
        }
        g.fillOval(0, 0, getHeight(), getHeight()); // Circle handler thingy

        super.paintComponent(g); // Paint the button on the screen
    }

    //Raycast target handler, I believe I am not sure if it does it the same way as Unity
    @Override
    public boolean contains(int x, int y) {
        // Make sure the button is circular for hit testing
        double radius = getHeight() / 2.0;
        double centerX = getHeight() / 2.0;
        double centerY = getHeight() / 2.0;
        double distance = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
        return distance <= Math.pow(radius, 2);
    }
}

//Stopwatch handler class
class Stopwatch extends Main{
    private double time;
    private double startTime;
    private double ElapsedTime;
    private int isRunning = 0;

    //handle all flagged times
    private double[] laps = new double[100];

    //get if ti is running
    public int getIsRunning()
    {
        return isRunning;
    }

    //Start the timer if it is stopped, and stop it if it is running
    public int StartOrStopTimer(){
        if(isRunning == 0){
            isRunning = 1;
            startTime = System.currentTimeMillis();
        }else {
            isRunning = 0;
            ElapsedTime = time;
        }
        return isRunning;
    }

    //get current time
    public double getTime(){
        return time;
    }

    //Change time perpetually
    public void IncrementTime(){
        if(isRunning == 1) time = System.currentTimeMillis() - startTime + ElapsedTime;
    }

    //Reset all attirbutes of the stopwatch
    public void ResetStopwatch(){
        time = 0;
        startTime = 0;
        ElapsedTime = 0;
        isRunning = 0;

        for(int i = 0; i < laps.length; i++){
            laps[i] = 0;
        }
    }

    //Add a flagged time based on the current time stamp
    public void AddLap(){
        for(int i = 0; i < laps.length; i++){
            if(laps[i] == 0){
                laps[i] = time;
                break;
            }
        }
    }

    //Get laps converted to strings with correct spacing and punctuation
    public String getLaps(){
        String lapsString = "";

        //Cycle through all laps
        for(int i = 0; i < laps.length; i++){
            if(laps[i] != 0){
                //Converts the time to a readable time for humans
                lapsString += "Lap " + (i+1) + " " + convTimeToStringForDisplay(laps[i], false);
                lapsString += "\n";
            }else break; //stop if the current lap is empty, there are no more
        }

        return lapsString;
    }

    //Converts the time for display purposes
    public String convTimeToStringForDisplay(double t, boolean addSpacing){
        String tDisplay = "";

        //Conversion from milliseconds to seconds
        t = t/1000.0;

        //Get hours
        int hours = (int)(t/3600);

        //Display hours if there are hours, if not don't do it, and start from minutes
        if(hours >= 1){
            if(hours < 10)
                tDisplay += "0" + hours;
            else tDisplay += hours;

            tDisplay += ":";
        }

        //Remove hours from the time
        t -= hours*3600;

        //Get mins
        int mins = (int)(t/60.0);

        //Display mins
        if(mins < 10)
            tDisplay += "0" + mins;
        else tDisplay += mins;

        tDisplay += ":";

        //Remove mins from time
        t -= mins*60;

        //Get seconds and display
        int secs = (int)(t);
        if(secs < 10)
            tDisplay += "0" + secs;
        else tDisplay += secs;

        tDisplay += ",";
        //remove seconds
        t -= secs;

        //Add the hunderds past the comma
        tDisplay += Math.round(t*100.0);

        //Check if there are 2 charcters past the comma if there are not add 0s to remove jittering of the time.
        String[] tDisplaySplitByComma = tDisplay.split(",", 2);

        //Add 0s to remove jittering
        for(int i = 0; i <  2 - tDisplaySplitByComma[1].length(); i++){
            tDisplay += "0";
        }


        String finalString = "";

        //Adds spaces between characters because swing doesn't support Kerning for some reason at least in an easy way
        if(addSpacing){
            for(int i = 0 ; i < tDisplay.length(); i++){
                finalString += tDisplay.charAt(i) + " ";
            }
        }else finalString = tDisplay;

        //return the final version of the string
        return finalString;
    }
}
