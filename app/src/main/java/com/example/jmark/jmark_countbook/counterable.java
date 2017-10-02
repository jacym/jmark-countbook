
/*
 *  Counterable
 *
 *  Version 1.0
 *
 *  September 27, 2017
 *
 *  Copyright © 2017 Jacy Mark, CMPUT301, University of Alberta - All Rights Reserved.
 *  You may use, distribute, or modify this code under terms and conditions of the Code of Student Behavior at University of Alberta.
 *  You can find a copy of the license in this project. Otherwise please contact contact@abc.ca
 */

package com.example.jmark.jmark_countbook;

import java.util.Date;

/**
 * Created by jmark on 2017-09-29.
 */

public interface counterable {
    public String getName();
    public Date getDate();
    public String getCurrent();
    public String getInitial();
    public String getComment();
}
