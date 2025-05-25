import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;

public class Interface extends JFrame {

    // Colors
    public static final Color COLOR1 = new Color(211, 250, 214); // D3FAD6
    public static final Color COLOR2 = new Color(209, 239, 181); // D1EFB5
    public static final Color COLOR3 = new Color(237, 235, 160); // EDEBA0
    public static final Color COLOR4 = new Color(195, 196, 141); // C3C48D
    public static final Color BACKGROUND_COLOR = new Color(146, 140, 111); // 928C6F

    // Components (made public for easier access)
    public JTextField cyclesField;
    public JTextField betField;
    public JTextField balanceField;
    public JTextField chosenNumberField;
    public JToggleButton toggleButton;
    public JButton runSimButton;
    private JPanel chartPanel;
    private JTextArea console;

    public Interface() {
        // Set up the JFrame
        setTitle("Gamba Simulator");
        setSize(1000, 955); // Adjusted for the chart space and input fields
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Create input panel
        JPanel inputPanel = createInputPanel();
        //inputPanel.add(console);
        add(inputPanel, BorderLayout.NORTH);


        // Create chart panel
        chartPanel = new JPanel();
        chartPanel.setPreferredSize(new Dimension(1000, 500));
        chartPanel.setBackground(COLOR1);
        add(chartPanel, BorderLayout.CENTER);

        console = new JTextArea();
        console.setEditable(false);
        console.setBackground(BACKGROUND_COLOR);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setFont(new Font("Monospaced", Font.BOLD, 17));
        console.setForeground(COLOR1);


        add(console, BorderLayout.SOUTH);
        // Display the frame
        setVisible(true);
    }

    public void InterchangeChart(ChartPanel chart, String consoleLogText) {
        XYPlot plot = chart.getChart().getXYPlot();
        plot.setBackgroundPaint(COLOR1);
        plot.setDomainCrosshairPaint(COLOR4);

       /* XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, BACKGROUND_COLOR);
        renderer.setSeriesPaint(1, COLOR2);
        renderer.setOutlinePaint(COLOR2);
        renderer.setSeriesFillPaint(0, COLOR2);
        renderer.setSeriesStroke(0, new BasicStroke(0.5f));
        plot.setRenderer(renderer);*/

        chartPanel.removeAll();
        chartPanel.add(chart);
        chartPanel.revalidate();
        console.setText(consoleLogText);
        console.revalidate();

    }


    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 1, 10, 20)); // 5 rows, 1 column for taller inputs
        inputPanel.setBackground(BACKGROUND_COLOR);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Number of Cycles
        JPanel cyclesPanel = new JPanel(new BorderLayout());
        cyclesPanel.setBackground(BACKGROUND_COLOR);
        JLabel cyclesLabel = new JLabel("Number of Cycles:");
        cyclesLabel.setForeground(Color.WHITE);
        cyclesField = new JTextField();
        cyclesField.setPreferredSize(new Dimension(150, 50)); // Narrower and taller
        cyclesField.setBackground(COLOR2);
        cyclesField.setFont(new Font("Monospaced", Font.BOLD, 20));
        cyclesPanel.add(cyclesLabel, BorderLayout.WEST);
        cyclesPanel.add(cyclesField, BorderLayout.CENTER);
        inputPanel.add(cyclesPanel);

        // Bet
        JPanel betPanel = new JPanel(new BorderLayout());
        betPanel.setBackground(BACKGROUND_COLOR);
        JLabel betLabel = new JLabel("Bet:    ");
        betLabel.setForeground(Color.WHITE);
        betField = new JTextField();
        betField.setPreferredSize(new Dimension(150, 50)); // Narrower and taller
        betField.setBackground(COLOR2);
        betField.setFont(new Font("Monospaced", Font.BOLD, 20));
        betPanel.add(betLabel, BorderLayout.WEST);
        betPanel.add(betField, BorderLayout.CENTER);
        inputPanel.add(betPanel);

        // Starting Balance
        JPanel balancePanel = new JPanel(new BorderLayout());
        balancePanel.setBackground(BACKGROUND_COLOR);
        JLabel balanceLabel = new JLabel("Starting Balance:  ");
        balanceLabel.setForeground(Color.WHITE);
        balanceField = new JTextField();
        balanceField.setPreferredSize(new Dimension(150, 50)); // Narrower and taller
        balanceField.setBackground(COLOR2);
        balanceField.setFont(new Font("Monospaced", Font.BOLD, 20));
        balancePanel.add(balanceLabel, BorderLayout.WEST);
        balancePanel.add(balanceField, BorderLayout.CENTER);
        inputPanel.add(balancePanel);

        // Chosen Number
        JPanel chosenNumberPanel = new JPanel(new BorderLayout());
        chosenNumberPanel.setBackground(BACKGROUND_COLOR);
        JLabel chosenNumberLabel = new JLabel("Num: ");
        chosenNumberLabel.setForeground(Color.WHITE);
        chosenNumberField = new JTextField();
        chosenNumberField.setPreferredSize(new Dimension(150, 50)); // Narrower and taller
        chosenNumberField.setBackground(COLOR2);
        chosenNumberField.setFont(new Font("Monospaced", Font.BOLD, 20));
        chosenNumberPanel.add(chosenNumberLabel, BorderLayout.WEST);
        chosenNumberPanel.add(chosenNumberField, BorderLayout.CENTER);
        inputPanel.add(chosenNumberPanel);

        // Toggle Button for Smaller/Larger
        JPanel togglePanel = new JPanel(new BorderLayout());
        togglePanel.setBackground(BACKGROUND_COLOR);
        toggleButton = new JToggleButton("Calculate One Player");
        toggleButton.setBackground(COLOR3);

        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.CalculateOnePlayerAndDisplay();
            }
        });

        togglePanel.add(toggleButton, BorderLayout.CENTER);
        inputPanel.add(togglePanel);

        // RunSim Button
        JPanel runSimPanel = new JPanel(new BorderLayout());
        runSimPanel.setBackground(BACKGROUND_COLOR);
        runSimButton = new JButton("RunSim");
        runSimButton.setBackground(COLOR4);
        runSimButton.setForeground(Color.WHITE);
        runSimButton.setPreferredSize(new Dimension(500, 50)); // Size of the button
        runSimPanel.add(runSimButton, BorderLayout.EAST);
        inputPanel.add(runSimPanel);

        runSimButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.RunSim();
            }
        });

        return inputPanel;
    }

    public int getNumOfCycles(){
        int i = -1;
        try{
            i = Integer.parseInt(cyclesField.getText());
        }catch (Exception e){
            console.setText("Invalid Entry of information");
        }
        return i;
    }

    public int getBetAmount(){
        int i = -1;
        try{
            i = Integer.parseInt(betField.getText());
        }catch (Exception e){
            console.setText("Invalid Entry of information");
        }
        return i;
    }

    public int getChosenNumber(){
        int i = -1;
        try{
            i = Integer.parseInt(chosenNumberField.getText());
        }catch (Exception e){
            console.setText("Invalid Entry of information");
        }

        if(i > 100 || i < 0){
            i = -1;
            console.setText("Invalid Entry of information");
        }
        return i;
    }

    public int getStartingBalance(){
        int i = -1;
        try{
            i = Integer.parseInt(balanceField.getText());
        }catch (Exception e){
            console.setText("Invalid Entry of information");
        }
        return i;
    }
}
