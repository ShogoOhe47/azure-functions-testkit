package com.function;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class Commons {

    public static String generateSampleResponse(){
        // generate response and contents
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXXZ");
        String datetimeStr = now.format(dtf);

        UUID eventID = UUID.randomUUID();
        String response = String.format("%s, UUID= %s", datetimeStr, eventID.toString());

        return response;
    }

    static final String alphaSets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String generateRandomString(int length){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i=0; i<length; i++){
            int index = random.nextInt(alphaSets.length());
            stringBuilder.append( alphaSets.charAt(index) );
        }

        return stringBuilder.toString();
    }
}
