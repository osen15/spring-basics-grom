package com.lesson6.hw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws ParseException {
        String myDate = "2014/10/29 18:10:45";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(myDate);
        long millis = date.getTime();
        System.out.println(millis);

      LocalDate now = LocalDate.now();
    LocalDate old =  now.minusYears(20);



        System.out.println(old);



    }
}
