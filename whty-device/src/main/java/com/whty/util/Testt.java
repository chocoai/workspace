package com.whty.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * \* User: zjd
 * \* Date: 2018/7/7
 * \* Time: 17:17
 * \* Description:
 * \
 */
public class Testt {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dayTime = new Date();
            Date dayTime2 =format.parse(sdf.format(dayTime));
            System.out.println(dayTime);
            System.out.println(dayTime2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}