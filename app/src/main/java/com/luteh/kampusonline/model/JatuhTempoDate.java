package com.luteh.kampusonline.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Luthfan Maftuh on 06/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class JatuhTempoDate {
    public Date startDate, lastDate;
    public String semester;

    private long oneDayInMillisecond = 86400000L;

    public int getCurrentDate(){
        Date today = Calendar.getInstance().getTime();
        return (int) (today.getTime() / oneDayInMillisecond);
    }

    public int getLastDate(){
        return (int) (lastDate.getTime() / oneDayInMillisecond);
    }

    public int getDifferenceDate(){
        return getLastDate() - getCurrentDate();
    }


}
