/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sentdata;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author tiendungmai
 */
public class UnirestSent {

    public static void main(String[] args) {

        ArrayList<String> lineR = new ArrayList<String>();

        FileReader fr = null;
        try {
            File f = new File("/home/tiendungmai/Desktop/sample text/pt-v-1533869954405.dat");
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            //ArrayList<String> words = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                lineR.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SentData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SentData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(SentData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(lineR.size());
        /*
        for(int i = 0 ; i < 10000; i ++){
            Future<HttpResponse<String>> future = Unirest.post("http://lg1.logging.admicro.vn/dsp_adx")
                    .header("accept", "application/json")
                    .field("last", "Polo")
                    .asStringAsync(new Callback<String>() {

                        public void failed(UnirestException e) {
                            System.out.println("The request has failed");
                            System.out.println(e.fillInStackTrace());
                        }

                        public void completed(HttpResponse<String> response) {
                            int code = response.getStatus();
                            System.out.println(code);

                        }

                        public void cancelled() {
                            System.out.println("The request has been cancelled");
                        }

                    });
        } */  
        
        ExecutorService executor = Executors.newFixedThreadPool(6);
        /*
        if (lineR != null) {

            for (String line : lineR) {

                GetThread getThread = new GetThread(line);

                executor.submit(getThread);
            }
        }*/
        
          
            for (int i = 0; i < 10000; i++) {
            GetThread getThread = new GetThread(i);
            executor.submit(getThread);
            }
            executor.shutdown();
            // Wait until all threads are finish
            while (!executor.isTerminated()) {
            }
        }

    

    public static Map<String, String> request(String s) {

        // String s represents ParamAndMacro
        String[] split = s.split("\t");
        int length = split.length;
        int i = 0;
        Map<String, String> maps = new HashMap<String, String>();
        //List<NameValuePair> urlParameter = new ArrayList<NameValuePair>();
        maps.put("requestId", split[0]);
        maps.put("sspName", split[1]);
        maps.put("dspName", split[2]);
        maps.put("timestamp", split[3]);
        maps.put("ua", split[4]);
        maps.put("osCode", split[5]);
        maps.put("ip", split[6]);
        maps.put("loc", split[7]);
        maps.put("domain", split[8]);
        maps.put("url", split[9]);
        maps.put("pos", split[10]);
        maps.put("width", split[11]);
        maps.put("height", split[12]);
        maps.put("guid", split[13]);
        maps.put("sspZoneId", split[14]);
        maps.put("dspZoneId", split[15]);
        maps.put("notifyCode", split[16]);
        maps.put("lstBanner", split[17]);
        maps.put("bidPrice", split[18]);
        maps.put("currencyId", split[19]);
        maps.put("browserCode", split[20]);
        return maps;
    }

    static class GetThread implements Runnable {

        private int request;

        public GetThread(int request) {
            this.request = request;
        }

        @Override
        public void run() {

            //System.out.println("Done");
            //Map<String, Object> params = new HashMap<String, Object>();
            //params.putAll(request(request));
            /*
            HttpResponse<String> asString = Unirest.post("http://lg1.logging.admicro.vn/dsp_adx")
            .fields(params)
            .asString();
            System.out.println("done");
             */
            Unirest.setTimeouts(500, 500);
            
            Future<HttpResponse<String>> future = Unirest.post("http://lg1.logging.admicro.vn/dsp_adx")
                    .header("accept", "application/json")
                    .field("last", "Polo")
                    .asStringAsync(new Callback<String>() {

                        public void failed(UnirestException e) {
                            System.out.println("The request has failed");
                            System.out.println(e.fillInStackTrace());
                        }

                        public void completed(HttpResponse<String> response) {
                            int code = response.getStatus();
                            System.out.println(code);

                        }

                        public void cancelled() {
                            System.out.println("The request has been cancelled");
                        }

                    });
        }

    }

}
