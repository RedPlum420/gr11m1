import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarInputStream;


public class Main {
    public static void main(String[] args) {


        JFrame frame = new JFrame("Calc, slang");
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(3,1));

        Font buttonFontBig = new Font("Serif", Font.BOLD, 65);
        Font inputFieldFont = new Font("Serif", Font.BOLD, 45);

        TextField firstNumber = new TextField();
        TextField secondNumber = new TextField();
        //firstNumber.setBounds(20, 40, 100, 40);
        //secondNumber.setBounds(380, 40, 100, 40);
        firstNumber.setFont(inputFieldFont);
        secondNumber.setFont(inputFieldFont);

        JLabel firstNumberHeading = new JLabel("First Number");
        JLabel secondNumberHeading = new JLabel("Second Number");
        //firstNumberHeading.setBounds(20, 20, 100, 20);
        //secondNumberHeading.setBounds(380, 20, 100, 20);
        firstNumberHeading.setHorizontalAlignment(SwingConstants.CENTER);
        secondNumberHeading.setHorizontalAlignment(SwingConstants.CENTER);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        firstNumberHeading.setBorder(blackline);
        secondNumberHeading.setBorder(blackline);

        JPanel NumberEnterPanel = new JPanel(new FlowLayout());
        //NumberEnterPanel.setLayout(new GridLayout(2,2));
        //NumberEnterPanel.setBounds(0, 0, 500, 100);

        NumberEnterPanel.add(firstNumberHeading);
        NumberEnterPanel.add(firstNumber);
        NumberEnterPanel.add(secondNumberHeading);
        NumberEnterPanel.add(secondNumber);



        Button AdditionButton = new Button(" + ");
        Button SubtractionButton = new Button(" - ");
        Button MultiplicationButton = new Button(" * ");
        Button DivisionButton = new Button(" / ");


        //AdditionButton.setBounds(0, 100, 250, 80);
        //SubtractionButton.setBounds(125, 100, 125, 80);
        //MultiplicationButton.setBounds(250, 100, 125, 80);
        //DivisionButton.setBounds(375, 100, 125, 80);



        AdditionButton.setFont(buttonFontBig);
        SubtractionButton.setFont(buttonFontBig);
        MultiplicationButton.setFont(buttonFontBig);
        DivisionButton.setFont(buttonFontBig);


        AdditionButton.setBackground(Color.red);
        SubtractionButton.setBackground(Color.red);
        MultiplicationButton.setBackground(Color.red);
        DivisionButton.setBackground(Color.red);

        JPanel ButtonActionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        NumberEnterPanel.setLayout(new GridLayout(1,4));
        //NumberEnterPanel.setBounds(0, 200, 500, 100);
        ButtonActionPanel.add(AdditionButton);
        ButtonActionPanel.add(SubtractionButton);
        ButtonActionPanel.add(MultiplicationButton);
        ButtonActionPanel.add(DivisionButton);

        Font resetButtonFont = new Font("Times New Roman", Font.ITALIC, 30);
        Button ResetButton = new Button("RESET");
        //ResetButton.setBounds(0, 380, 100, 100);
        ResetButton.setFont(resetButtonFont);


        JLabel answer = new JLabel("100");
        answer.setBounds(150, 250, 200, 100);
        answer.setBorder(blackline);
        answer.setFont(buttonFontBig);
        answer.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel resultAndReset = new JPanel(new FlowLayout());
        resultAndReset.setBounds(0, 250, 500, 250);
        resultAndReset.add(answer);
        resultAndReset.add(ResetButton);

        frame.add(NumberEnterPanel);
        frame.add(ButtonActionPanel);
        frame.add(resultAndReset);

        AdditionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DoSmartThing("+", firstNumber, secondNumber, answer);
            }
        });

        SubtractionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DoSmartThing("-", firstNumber, secondNumber, answer);
            }
        });

        MultiplicationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DoSmartThing("*", firstNumber, secondNumber, answer);
            }
        });

        DivisionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             DoSmartThing("/", firstNumber, secondNumber, answer);
            }
        });

        ResetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                answer.setText("0");
                firstNumber.setText("");
                secondNumber.setText("");
            }
        });
        
        frame.setVisible(true);
    }

    public static void DoSmartThing(String operator, TextField firstNumber, TextField secondNumber, JLabel answer){
        if(CheckForValidNums(firstNumber, secondNumber) == 0) return;

        double fNum = Double.parseDouble(firstNumber.getText());
        double sNum = Double.parseDouble(secondNumber.getText());

        if(operator.equals("+")){
            double result = fNum + sNum;
            answer.setText(String.valueOf(result));
        }else if(operator.equals("-")){
            double result = fNum - sNum;
            answer.setText(String.valueOf(result));
        }else if(operator.equals("*")){
            double result = fNum*sNum;
            answer.setText(String.valueOf(result));
        }else if(operator.equals("/")){
            if(sNum == 0){
                answer.setText("Can't divide by 0");
                return;
            }
            double result = fNum/sNum;

            result = Math.round(result * 100000.0)/100000.0;

            answer.setText(String.valueOf(result));
        }
    }

    public static int CheckForValidNums(TextField firstNumber, TextField secondNumber){
        try{
            double fNum = Double.parseDouble(firstNumber.getText());
            double sNum = Double.parseDouble(secondNumber.getText());

            return 1;
        }catch(Exception e){
            return 0;
        }
    }

}
