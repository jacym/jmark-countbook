package com.example.jmark.jmark_countbook;

import java.util.Date;

/**
 * Created by jmark on 2017-09-29.
 */

public class NormalCounter extends counter{

    public NormalCounter(String name, String initial, String current, String comment){super(name,initial,current,comment);}


    public NormalCounter(String name, String initial, String current, String comment, Date date){
        super(name,initial,current,comment,date);
    }
}
