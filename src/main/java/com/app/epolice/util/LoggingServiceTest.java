package com.app.epolice.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class LoggingServiceTest {
    public void postLogging(Object request, Object response,String serviceName) throws JSONException {
        HashMap<String, Object> dataMap = new HashMap<>();

        JSONObject jsonObjectRequest = new JSONObject(request);
        JSONObject jsonObjectResponse = new JSONObject();
        jsonObjectResponse.put("response",response);
/*        try{
            Object str = jsonObjectRequest.get("email");
            System.out.println(str.toString());
        }catch (Exception e){
            System.out.println("Source app doesn't exist in the Request but its in object");
        }*/
        dataMap.put("requestobject", request);
        dataMap.put("responseobject", response);
        if (serviceName != null) {
            dataMap.put("servicename", serviceName);
        }

        dataMap.put("sourceapi", "ApplicantPortalApi");
        dataMap.put("sourceapp", "ApplicantPortal");
        dataMap.put("sourceappid", jsonObjectRequest.get("email"));
        dataMap.put("sourceappip", null);

        if (jsonObjectResponse != null && jsonObjectResponse.length()!=0) {
            dataMap.put("status", "Success");
            dataMap.put("errordetail", null);
        } else {
            dataMap.put("status", "Failed");
            dataMap.put("errordetail", jsonObjectResponse.get("message"));
        }
    }

    public void getLogging(Object request, Object response,String serviceName) throws JSONException {
        HashMap<String, Object> dataMap = new HashMap<>();

        JSONObject jsonObjectRequest = new JSONObject();
        jsonObjectRequest.put("request",request);

        JSONObject jsonObjectResponse = new JSONObject();
        jsonObjectResponse.put("response",response);

        HashMap<String,Object> value = (HashMap<String, Object>) jsonObjectRequest.get("request");
        JSONObject jsonObjectRequest1 = new JSONObject(value);
/*
        try{
            Object str = jsonObjectRequest1.get("sourceAppId");
            System.out.println(str.toString());
        }catch (Exception e){
            System.out.println("Source app doesn't exist in the Request but its in object ");
        }
*/
        dataMap.put("requestobject", request);
        dataMap.put("responseobject", response);
        if (serviceName != null) {
            dataMap.put("servicename", serviceName);
        }
        dataMap.put("sourceapi", "ApplicantPortalApi");
        dataMap.put("sourceapp", "ApplicantPortal");
        dataMap.put("sourceappid", jsonObjectRequest1.get("sourceAppId"));
        dataMap.put("sourceappip", null);

        if (jsonObjectResponse != null && jsonObjectResponse.length()!=0) {
            dataMap.put("status", "Success");
            dataMap.put("errordetail", null);
        } else {
            dataMap.put("status", "Failed");
            dataMap.put("errordetail", jsonObjectResponse.get("message"));
        }
    }
}
