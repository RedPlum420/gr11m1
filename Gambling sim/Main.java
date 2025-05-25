import java.util.Random;
import java.math.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Main {
    public static double houseEdge = 0.01;

    public static int numOfCycles = 1000;
    public static double startingBalance = 1000;
    public static double currentBalance = startingBalance;
    public static boolean lessThanChosenNumber = true;

    public static double bet = 10;
    public static double chosenNumber = 34;
    public static double payOut;
    public static double[] balanceAtEndOfRound;

    public static double[] averagedBalanceAtEndOfRound;

    static Random r = new Random();

    static int won = 0;
    static int lost = 0;

    static double chanceToWin = 0;

    static Interface ui;

    public static void main(String[] args) {
        ui = new Interface();
        //RunSim();
    }

    public static void RunSim(){
        numOfCycles = ui.getNumOfCycles();
        startingBalance = ui.getStartingBalance();
        bet = ui.getBetAmount();
        chosenNumber = ui.getChosenNumber();

        averagedBalanceAtEndOfRound = new double[numOfCycles];

        if(numOfCycles == -1 || startingBalance == -1 || bet == -1 || chosenNumber == -1) return;

        payOut = CalculatePayOut();

        balanceAtEndOfRound = new double[numOfCycles];
        boolean round;

        for(int j = 0; j < 200; j++){
            currentBalance = startingBalance;
            won = 0;
            lost = 0;
            for(int i = 0; i < numOfCycles; i++){
                round = RunRound();

                currentBalance -= bet;

                if(round){
                    currentBalance += payOut;
                    won++;
                }else{
                    lost++;
                }
                balanceAtEndOfRound[i] = currentBalance;

                if(j == 0) averagedBalanceAtEndOfRound[i] = balanceAtEndOfRound[i];
                else {
                    averagedBalanceAtEndOfRound[i] += balanceAtEndOfRound[i];
                }
            }
        }

        for(int i = 0; i < numOfCycles; i++) averagedBalanceAtEndOfRound[i] = averagedBalanceAtEndOfRound[i]/200;

        System.out.println("won: " + won + " lost: " + lost);
        System.out.println("balance at the end: " + balanceAtEndOfRound[numOfCycles-1]);
        ui.InterchangeChart(CreateMoneyChart(), "PayOut: " + Math.floor(payOut*100)/100 + "\n" + "balance at the end: " + Math.floor(balanceAtEndOfRound[numOfCycles-1]*100)/100 + "\nwon: " + won + "   lost:  " + lost + "  yielding a: " + Math.floor((double)won/numOfCycles*10000)/100 + "% win rate, chance to win is: " + Math.floor(chanceToWin*100) + "%");
        //CreateCSVFile(balanceAtEndOfRound);
    }

    public static void CalculateOnePlayerAndDisplay(){
        currentBalance = startingBalance;
        won = 0;
        lost = 0;
        boolean round;

        for(int i = 0; i < numOfCycles; i++) {
            round = RunRound();

            currentBalance -= bet;

            if(round){
                currentBalance += payOut;
                won++;
            }else{
                lost++;
            }
            balanceAtEndOfRound[i] = currentBalance;
        }
        ui.InterchangeChart(CreateMoneyChart(), "PayOut: " + Math.floor((payOut)*100)/100 + "\n" + "balance at the end: " + Math.floor(balanceAtEndOfRound[numOfCycles-1]*100)/100 + "\nwon: " + won + "   lost:  " + lost + "  yielding a: " + Math.floor((double)won/numOfCycles*10000)/100 + "% win rate, chance to win is: " + Math.floor(chanceToWin*100) + "%");
    }

    public static boolean RunRound(){
        int rand = r.nextInt(1, 101);

        if((rand < chosenNumber && lessThanChosenNumber) || (rand > chosenNumber && !lessThanChosenNumber)){
            return true;
        }
        return false;
    }

    //Calcualtes the pay_out so no matter the chosenNumber the casino would always have a 1% edge over the player
    public static double CalculatePayOut(){
        double p_o = 0;

        chanceToWin = 0;
        if(lessThanChosenNumber){
            chanceToWin = (chosenNumber -1)/100.0;
        }else{
            chanceToWin = (100 - chosenNumber)/100.0;
        }

        if(chanceToWin < 0.5)
        return p_o = ((1-chanceToWin-houseEdge)/chanceToWin)*bet + bet;
        else return p_o = bet*(1-chanceToWin)/chanceToWin*(1-houseEdge) + bet;


    }

    public static ChartPanel CreateMoneyChart(){
        XYSeries series = new XYSeries("Balance Over Time");
        XYSeries averagedSeries = new XYSeries("Average over 200 runs");

        // Step 2: Add your data to the XYSeries
        for (int i = 0; i < balanceAtEndOfRound.length; i++) {
            series.add(i + 1, balanceAtEndOfRound[i]); // (X, Y) points (i+1 is for the X-axis)
            averagedSeries.add(i + 1, averagedBalanceAtEndOfRound[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        dataset.addSeries(averagedSeries);


        JFreeChart chart = ChartFactory.createXYLineChart(
                "Simulation Results", // Chart title
                "Bet", // X-axis label
                "Balance", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot orientation
                true, // Include legend
                true, // Tooltips
                false // URLs
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));

        return chartPanel;
    }

    public static void CreateCSVFile(double[] balance){
        String csv = "Trial, Bet, Balance, Net\n";

        for(int i = 0; i < balance.length; i++){
            csv += i + "," + bet+ "," + balance[i] + "," + (balance[i] - startingBalance);
            csv += "\n";
        }
        System.out.println(csv);
    }

}
