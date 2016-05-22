package com.example.josien.josienpset5;

/* Josien Jansen
*  11162295
*  Universiteit van Amsterdam
*/

/*
This Activity declares the data needed for this App. Like title, producer, longtitle and the
productionplace. You can get the data through getters and setters.
 */

public class CenturyData {

    private String title;
    private String producer;
    private String longtitle;
    private String productionplace;

    public CenturyData(String title, String producer, String longtitle, String productionplace){
        super();
        this.title = title;
        this.producer = producer;
        this.longtitle = longtitle;
        this.productionplace =productionplace;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getProducer(){
        return producer;
    }

    public String getLongtitle(){
        return longtitle;
    }

    public String getProductionplace(){
        return productionplace;
    }

}
