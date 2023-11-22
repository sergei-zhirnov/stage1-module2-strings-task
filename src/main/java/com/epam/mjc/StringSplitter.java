package com.epam.mjc;

import java.util.*;
import java.lang.Math;

public class StringSplitter {

    public static String getRandomString(int len) {
        {
            String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
            StringBuilder s = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                int ch = (int)(AlphaNumericStr.length() * Math.random());
                s.append(AlphaNumericStr.charAt(ch));
            }
            return s.toString();
        }
    }



    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> delimetersList = new ArrayList<>(delimiters);
        String randomDelimeter = "";
        for (Object o: delimetersList) {
            do {
                randomDelimeter = getRandomString(1);
            } while (source.contains(randomDelimeter) || o.toString().contains(randomDelimeter));
        }


        for (Object o : delimetersList) {
            String del = o.toString();
            source = source.replace(del, randomDelimeter);
        }

        String[] preresult = source.split(randomDelimeter);
        ArrayList<String> arrPreresult = new ArrayList<>(List.of(preresult));
        ArrayList<String> arrResult = new ArrayList<>();
        Iterator<String> iterator = arrPreresult.iterator();

        while (iterator.hasNext()) {
            Object element = iterator.next();
            if (Objects.equals(element.toString(), "")) {
                iterator.remove();
            } else {
                arrResult.add(element.toString());
            }
        }


        return arrResult;
    }
}
