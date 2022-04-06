package fun5i.module.week;


import android.content.Context;
import android.icu.util.LocaleData;
import android.os.Build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class MyDate {

    public static final int SATU_DETIK = 1000;

    private ZoneId defaultZoneId;
    private Date date;
    private LocaleData localeData;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;

    public String thisDay;

    public static final String[] HARI_DALAM_MINGGU = {
            "Minggu",
            "Senin",
            "Selasa",
            "Rabu",
            "Kamis",
            "Jumaat",
            "Sabtu"
    };

    public String currentDays(){
        SimpleDateFormat formatter= new SimpleDateFormat("dd");
        Date datecr = new Date(System.currentTimeMillis());

        return formatter.format(datecr);
    }

    public void setThisDay(String currentDays){
        this.thisDay = currentDays;
    }


    public MyDate(Context c){
        calendar = Calendar.getInstance();

        // SetCurrentDayInActivity
        SimpleDateFormat formatter= new SimpleDateFormat("dd");
        Date datecr = new Date(System.currentTimeMillis());
        this.thisDay = formatter.format(datecr);
    }

    public String getHHMMSS(){
        calendar.setTimeInMillis(System.currentTimeMillis());
        Date dates = calendar.getTime();
        int mHour = dates.getHours();
        int mMinute = dates.getMinutes();
        int mSecond = dates.getSeconds();

        return String.format("%02d:%02d:%02d", mHour, mMinute, mSecond);
    }

    public String getCurrentMonth(){
        return new SimpleDateFormat("MMM").format(calendar.getTime());
    }

    public String getCurrentYear(){
        return new SimpleDateFormat("yyy").format(calendar.getTime());
    }

    public String getDayFromDate(Date d){
        simpleDateFormat = new SimpleDateFormat("dd");
        return simpleDateFormat.format(d);
    }

    public String getCurrentDayFromDate(){
        simpleDateFormat = new SimpleDateFormat("dd");
        return simpleDateFormat.format(new Date());
    }

    public Date[] getWeek(){
        calendar = Calendar.getInstance();
        Date[] week = new Date[7];
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        for (int i=0; i < 7; i++) {
            week[i] = calendar.getTime(); //set start Sunday
            calendar.add(Calendar.DATE, 1);//incremnt
        }
        return week;
    }

    public Date backwardDate(){
        return date;
    }

}

