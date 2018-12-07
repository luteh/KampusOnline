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

    private long  oneDayOnMillisecond = 86400000L;

    public int getCurrentDate(){
        Date today = Calendar.getInstance().getTime();
        return (int) (today.getTime() / oneDayOnMillisecond);
    }

    public int getLastDate(){
        return (int) (lastDate.getTime() / oneDayOnMillisecond);
    }

    public int getDifferenceDate(){
        return getLastDate() - getCurrentDate();
    }


}
