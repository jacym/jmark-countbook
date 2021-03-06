
/*
 *  Counter class
 *
 *  Version 1.0
 *
 *  September 28, 2017
 *
 *  Copyright © 2017 Jacy Mark, CMPUT301, University of Alberta - All Rights Reserved.
 *  You may use, distribute, or modify this code under terms and conditions of the Code of Student Behavior at University of Alberta.
 *  You can find a copy of the license in this project. Otherwise please contact contact@abc.ca
 */

package com.example.jmark.jmark_countbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jmark on 2017-09-28.
 */

public abstract class counter implements counterable{
    private String name;
    private Date date;
    private String current;
    private String initial;
    private String comment;

    public counter(String name, String initial, String current, String comment){
        date = new Date();
        this.name = name;
        this.current = current;
        this.initial = initial;
        this.comment = comment;
    }

    public counter(String name, String initial, String current, String comment,Date date){
        this.date = date;
        this.name = name;
        this.current = current;
        this.initial = initial;
        this.comment = comment;
    }
    public String getName() {return name;}
    public String getCurrent() {return current;}
    public String getInitial() {return initial;}
    public String getComment() {return comment;}

    public void setCurrentCount(String current) throws counterNonNumeric{
        if (current == null || current.matches("[-+]?\\d*\\.?\\d+")){
            throw new counterNonNumeric();
        }
        else{
            this.current = current;
        }
    }
    public void setInitialCount(String initial) throws counterNonNumeric{
        if (initial == null || initial.matches("[-+]?\\d*\\.?\\d+")){
            throw new counterNonNumeric();
        }
        else{
            this.initial = initial;
        }
    }

    public Date getDate() {return date;}


    @Override
    public String toString(){
        return name + "\nDate: " +  new SimpleDateFormat("yyyy-MM-dd").format(date).toString() + "\nCurrent Count: " + current + "\nInitial Count: " + initial + "\nComments: " + comment;
    }


}
