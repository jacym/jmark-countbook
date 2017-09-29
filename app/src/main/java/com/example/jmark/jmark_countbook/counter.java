package com.example.jmark.jmark_countbook;

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

    public counter(String name, Date date, String current, String initial, String comment){
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

    public void setCount(String current, String initial) throws counterNonNumeric{
        if
    }
    public Date getDate() {return date;}


}
