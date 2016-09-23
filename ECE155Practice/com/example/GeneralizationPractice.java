package com.example;

abstract class Guitar {

    protected int numberOfStrings = 6;
    protected int numberOfFrets = 22;
    protected String guitarBrand;
    protected boolean[] standardHarmonics = {false, false, true, true, true,
                                                false, true, false, true, false,
                                                false, true, false, false, false,
                                                true, false, false, true, false,
                                                false, false, false, true, false};
    
    public Guitar(String guitarBrand){
        this.guitarBrand = guitarBrand;
    }
    
    public void getNaturalHarmonics(){
        System.out.println("The common natural harmonic frets of this guitar are:");
        for(int i = 0; i < numberOfFrets; i++){
            if(standardHarmonics[i]) {
                System.out.print("Fret " + (i + 1) + "  ");
            }
        }
        System.out.println(" ");
    }
    
    public String getBrandName(){
        return guitarBrand;
    }
}
    
    

class RegularGuitar extends Guitar {
    protected int numberOfStrings = 6;
    protected int numberOfFrets = 22;
    
    public RegularGuitar(String guitarBrand){
      super(guitarBrand);
    }
}

class SevenStringGuitar extends Guitar {
    protected int numberOfStrings = 7;
    protected int numberOfFrets = 24;
    
    public SevenStringGuitar(String guitarBrand){
      super(guitarBrand);
    }
}

class RegularBassGuitar extends Guitar {
    protected int numberOfStrings = 4;
    protected int numberOfFrets = 22;
    
    public RegularBassGuitar(String guitarBrand){
      super(guitarBrand);
    }
}

class FretlessGuitar extends Guitar {
    protected int numberOfStrings = 6;
    protected int numberOfFrets = -1;
    
    public FretlessGuitar(String guitarBrand){
      super(guitarBrand);
    }

    @Override
    public void getNaturalHarmonics(){
        System.out.println("The common harmonics on a fretless guitar are all the " +
                            "locations along the strings divisible by 2, 3, and 5.");
    }
}

public class GeneralizationPractice {

    public static void main(String[] args) {
        RegularGuitar myRegGuitar = new RegularGuitar("Fender");
        SevenStringGuitar my7StringGuitar = new SevenStringGuitar("Ibanez");
        RegularBassGuitar myRegBass = new RegularBassGuitar("Hofner");
        FretlessGuitar myFretlessGuitar = new FretlessGuitar("SmoothBoard");

        myRegGuitar.getNaturalHarmonics();
        my7StringGuitar.getNaturalHarmonics();
        myRegBass.getNaturalHarmonics();
        myFretlessGuitar.getNaturalHarmonics();

    }
}
