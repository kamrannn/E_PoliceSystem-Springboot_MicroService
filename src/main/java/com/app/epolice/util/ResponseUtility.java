package com.app.epolice.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;

/**
 * The type Response utility.
 */
@Service
public class ResponseUtility {
    /**
     * Get list response hash map.
     *
     * @param message the message
     * @param obj     the obj
     * @param path    the path
     * @param status  the status
     * @return the hash map
     * @throws ParseException the parse exception
     */
    public static HashMap<String, Object> getResponse(String message, Object obj, String path, HttpStatus status) throws ParseException {
        HashMap<String, Object> map = new HashMap<>();
        if (obj == null) {
            map.put("Message", message);
            map.put("Status", status);
            map.put("Timestamp", DateTime.getStringDateTime());
            map.put("uri", path);
        } else {
            map.put("Message", message);
            map.put("Timestamp", DateTime.getStringDateTime());
            map.put("uri", path);
            map.put("Result", obj);
        }
        return map;
    }
}
