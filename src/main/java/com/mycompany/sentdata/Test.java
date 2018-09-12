/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sentdata;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
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
public class Test {
    private final Logger logger = Logger.getLogger(SentData.class.getName());
    private final String USER_AGENT = "Sent Data";

    public static void main(String[] args) throws Exception {
        HttpURLConnection connection = null;
        URL url = new URL("http://lg1.logging.admicro.vn/dsp_adx");
        connection = (HttpURLConnection) url.openConnection();
             connection.setRequestMethod("POST");
             connection.setRequestProperty("Content-Type",   "application/x-www-form-urlencoded");
             
            //  connection.setRequestProperty("Content-Length",   Integer.toString(request.getBytes().length));
             connection.setRequestProperty("Content-Language", "en-US");
             
             connection.setUseCaches(false);
             connection.setDoOutput(true);
        
        ArrayList<String> lineR = new ArrayList<String>();
                //lineR= logPage();
                //int RUNS= 2;
             //while (RUNS > 0) {
             
             FileReader fr = null;
            try {
                File f = new File("/home/tiendungmai/Desktop/sample text/pt-v-1533869954405.dat");
                fr = new FileReader(f);
                //Bước 2: Đọc dữ liệu
                BufferedReader br = new BufferedReader(fr);
                String line;
                //ArrayList<String> words = new ArrayList<>();
                while ((line = br.readLine()) != null){
                    lineR.add(line);
                }
                //ConcurrentMap<String, Integer> words = new ConcurrentHashMap<String, Integer>();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SentData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SentData.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(SentData.class.getName()).log(Level.SEVERE, null, ex);
                }}
            System.out.println(lineR.size());
            long start = System.currentTimeMillis();
           ExecutorService executor = Executors.newFixedThreadPool(10);
           Iterator<String> iterator = lineR.iterator();    
        while(iterator.hasNext()){
        
                      // Send more messages if there are more messages to send.
                            Map<String, String> params = request(iterator.next());
                             GetThread getThread = new GetThread(getParamsString(params), connection, url);
                    //getThread.run();
                        executor.submit(getThread);
        }
                        /*
            if (lineR != null) {

                for (String line : lineR) {
                    Map<String, String> params = request(line);
                    GetThread getThread = new GetThread(getParamsString(params), connection, url);
                    //getThread.run();
                    executor.submit(getThread);
                }
            }*/

            executor.shutdown();

            // Wait until all threads are finish
            while (!executor.isTerminated()) {
            }
            long finish = System.currentTimeMillis();
            System.out.println((finish-start));
            
            //int Runs = 0;
           
            }
            
    public static String getParamsString(Map<String, String> params) 
      throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
 
        for (Map.Entry<String, String> entry : params.entrySet()) {
          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
          result.append("=");
          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
          result.append("&");
        }
 
        String resultString = result.toString();
        return resultString.length() > 0
          ? resultString.substring(0, resultString.length() - 1)
          : resultString;
    }
    
    public static Map<String, String> request(String s) {
        Map<String, String> urlParameter = new HashMap<String, String>();
        // String s represents ParamAndMacro
        String[] split = s.split("\t");
        int length = split.length;
        int i =0;
        //Map<String, String> maps = new HashMap<String,String>();
        //List<NameValuePair> urlParameter = new ArrayList<NameValuePair>();
        urlParameter.put("requestId",split[0]);
        urlParameter.put("sspName",split[1]);
        urlParameter.put("dspName",split[2]);
        urlParameter.put("timestamp",split[3]);
        urlParameter.put("ua",split[4]);
        urlParameter.put("osCode",split[5]);
        urlParameter.put("ip",split[6]);
        urlParameter.put("loc",split[7]);
        urlParameter.put("domain",split[8]);
        urlParameter.put("url",split[9]);
        urlParameter.put("pos",split[10]);
        urlParameter.put("width",split[11]);
        urlParameter.put("height",split[12]);
        urlParameter.put("guid",split[13]);
        urlParameter.put("sspZoneId",split[14]);
        urlParameter.put("dspZoneId",split[15]);
        urlParameter.put("notifyCode",split[16]);
        urlParameter.put("lstBanner",split[17]);
        urlParameter.put("bidPrice",split[18]);
        urlParameter.put("currencyId",split[19]);
        urlParameter.put("browserCode",split[20]);
        return urlParameter;
    }


    private static ArrayList<String> logPage() {
        ArrayList<String> logpage = new ArrayList<String>();
        File folder = new File("/home/tiendungmai/Desktop/sample text");
        File[] listFile = folder.listFiles();
        //System.out.println("so file: " + listFile.length);
        System.out.println("Dang doc file...");
        long start = System.currentTimeMillis();
        for(int i = 0; i < listFile.length; i++){
            FileReader fr = null;
            try {
                File f = new File(listFile[i].getPath());
                fr = new FileReader(f);
                //Bước 2: Đọc dữ liệu
                BufferedReader br = new BufferedReader(fr);
                String line;
                //ArrayList<String> words = new ArrayList<>();
                while ((line = br.readLine()) != null){
                    logpage.add(line);
                }
                //ConcurrentMap<String, Integer> words = new ConcurrentHashMap<String, Integer>();
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

        }
        long finish = System.currentTimeMillis();
        System.out.println("time readfile: "+(finish-start));
        System.out.println(logpage.size());
        System.out.println("DONE");




        return logpage;
    
    }
    static class GetThread implements Runnable{
     private String request;
     private HttpURLConnection connection;
     private URL url;

        public GetThread(String request, HttpURLConnection connection, URL url) {
            this.request = request;
            this.connection = connection;
            this.url = url;
        }

        
     @Override
        public void run(){
            
         try {
             
             
             //Send request
             DataOutputStream wr = new DataOutputStream ( connection.getOutputStream());
             wr.writeBytes(request);
             //System.out.println("done");
             wr.close();
         } catch (MalformedURLException ex) {
             Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
         }
            
            }
        }

        
 }


