package com.function;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
}
