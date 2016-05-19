package com.example.josien.josienpset5;

/**
 * Created by Josien on 18-5-2016.
 */
public class TrainData {

    private String vertrektijd;
    private String eindbestemming;

    public TrainData(String vertrektijd, String eindbestemming){
        super();
        this.vertrektijd = vertrektijd;
        this.eindbestemming = eindbestemming;
    }

    public String getVertrektijd(){
        return vertrektijd;
    }

    public void setVertrektijd(String vertrektijd){
        this.vertrektijd = vertrektijd;
    }

    public String getEindbestemming(){
        return eindbestemming;
    }

    public void setEindbestemming(String eindbestemming){
        this.eindbestemming = eindbestemming;
    }
}
