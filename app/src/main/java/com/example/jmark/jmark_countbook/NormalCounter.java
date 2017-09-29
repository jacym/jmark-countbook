package com.example.jmark.jmark_countbook;

import java.util.Date;

/**
 * Created by jmark on 2017-09-29.
 */

public class NormalCounter extends counter{

    public NormalCounter(String name, String current, String initial, String comment){super(name,current,initial,comment);}


    public NormalCounter(String name, String current, String initial, String comment, Date date){
        super(name,current,initial,comment,date);
    }
}
