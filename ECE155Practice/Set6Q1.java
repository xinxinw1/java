import java.util.*;

public class Set6Q1 {

    public static double result = 0;
    public static double roundCounter = 100000;
    public static final int counterAPeriod = 1;
    public static final int counterBPeriod = 1;
    public static final int counterCPeriod = 1;

    public static void main(String[] args){

        double param1 = 0.5;
        double param2 = 3.72;

        TimerTask TimerATask = new TimerTask(){
            @Override
            public synchronized void run(){
                result += param1 * param2;
                result = (param1 / param2) / result;
                System.out.println("Result A: " + result);
            }
        };

        TimerTask TimerBTask = new TimerTask(){
            @Override
            public synchronized void run(){
                //if(result <= 0) result = Math.abs(result) + 1;
                result = Math.log(result);
                result ++;
                roundCounter--;
                System.out.println("Result B: " + result);
            }
        };

        TimerTask TimerCTask = new TimerTask(){
            @Override
            public synchronized void run(){
                if(roundCounter <= 0) {
                    System.exit(0);
                }
                else {
                    result = 0;
                }
            }
        };

        Timer TimerA = new Timer();
        Timer TimerB = new Timer();
        Timer TimerC = new Timer();

        TimerA.schedule(TimerATask, counterAPeriod, counterAPeriod);
        TimerB.schedule(TimerBTask, counterBPeriod, counterBPeriod);
        TimerC.schedule(TimerCTask, counterCPeriod, counterCPeriod);

    }

}

