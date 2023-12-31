package com.publisher.RegisterPublisher.HelperFunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AppBundleIdValidator {
    public static String validatorFunction(String app_bundle_id){
        try {
            System.out.println("Inside thisQ!!!");
            //URL for docker
            //URL url = new URL("http://flaskapp:5000/home?app_bundle_id="+app_bundle_id);
            //URL for kubernetes
            //URL url = new URL("http://flask-app-service:5000/home?app_bundle_id="+app_bundle_id);
            //URL for localhost
            URL url = new URL("http://localhost:5000/home?app_bundle_id="+app_bundle_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String finaloutput="";
            String output;
            while ((output = br.readLine()) != null) {
                finaloutput+=output;
            }
            conn.disconnect();
            return finaloutput;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
