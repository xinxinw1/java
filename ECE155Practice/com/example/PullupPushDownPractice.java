package com.example;

class Good {

    protected int valueGood = 2;

    public void setValueGood(int x){
        valueGood = x;
    }

    public int getValueGood(){
        return valueGood;
    }

}

class TwiceAsGood extends Good {

    public void setValueGood(int x){
        valueGood = x * 2;
    }
    
    public int setToOneAndReturn() {
        int x = getValueGood();
        setValueGood(1);
        return x;
    }

    

}

public class PullupPushDownPractice {

    public static void main(String[] args) {

        TwiceAsGood g = new TwiceAsGood();
        g.setValueGood(4);
        System.out.println("The value is " + g.getValueGood());
        System.out.println("I got value " + g.setToOneAndReturn());
        System.out.println("The value is now " + g.getValueGood());

    }
}

