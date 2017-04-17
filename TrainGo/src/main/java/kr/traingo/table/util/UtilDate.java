package kr.traingo.table.util;

import java.util.Calendar;

public class UtilDate{
    public String getServerTime(){
        StringBuffer today = new StringBuffer();
        
        // Get Time - Default TimeZone
        Calendar serverTime = Calendar.getInstance();
        int year=serverTime.get(Calendar.YEAR);
        int month=serverTime.get(Calendar.MONTH)+1;
        int date=serverTime.get(Calendar.DATE);
        int hour=serverTime.get(Calendar.HOUR_OF_DAY);
        int min=serverTime.get(Calendar.MINUTE);
        int sec=serverTime.get(Calendar.SECOND);
        
        // Build String
        today.append(year+"-"+month+"-"+date+"-"+hour+"-"+min+"-"+sec);
        
        return today.toString();
    }
}
