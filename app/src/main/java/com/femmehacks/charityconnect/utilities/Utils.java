package com.femmehacks.charityconnect.utilities;
import java.util.Date;

public class Utils {

    public static long dateDifference(Date d1) {
        Date today = new Date();
        long difference_In_Time = today.getTime() - d1.getTime();
        // Calculate time difference
        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

        return difference_In_Days;
    }
}
