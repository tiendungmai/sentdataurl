/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sentdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.client.HttpClient;

/**
 *
 * @author tiendungmai
 */
public class MultiThreadedExample {
    
    public static void main(String[] args) throws InterruptedException {
	        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
                connectionManager.setMaxConnectionsPerHost(1000);
                connectionManager.setMaxTotalConnections(10000);
             //   connectionManager.
                HttpClient httpClient = new HttpClient(connectionManager);
               
                
                ArrayList<String> lineR = new ArrayList<String>();
                //lineR= logPage();
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
            /*
            List<ArrayList<String>> listRq = new ArrayList<ArrayList<String>>();
            int number = lineR.size()%10;
            for(int i = 0 ; i <10; i++){
                if(i<9){
                listRq.add(new ArrayList<String>( lineR.subList(i, (i+1)*number)));
                }
                else{
                 listRq.add(new ArrayList<String>( lineR.subList(number*9, lineR.size())));
                }
                
            }
            ExecutorService executor = Executors.newFixedThreadPool(6);
            
            for(int i = 0; i < 10; i++){
                GetThreadTemp getThread = new GetThreadTemp(listRq.get(i));
                executor.submit(getThread);
            }
            
            */
            
             long start = System.currentTimeMillis()%1000;
             ExecutorService executor = Executors.newFixedThreadPool(10);
             
              
              for(int i = 0; i< 20000; i++){
                  GetThread getThread = new GetThread(httpClient,"");
                 // getThread.run();
                  executor.submit(getThread);
                 // sentPostTo(httpClient);
              }
       /*
              Iterator<String> iterator = lineR.iterator();    
        while(iterator.hasNext()){
        
                      // Send more messages if there are more messages to send.
                             String line = iterator.next();
                             GetThread getThread = new GetThread(httpClient,line);
                   
                            executor.submit(getThread);
                      
        }  
             */
                    /*          
            if (lineR != null) {

                for (String line : lineR) {

                    GetThread getThread = new GetThread(httpClient,line);
                   
                    executor.submit(getThread);
                }
            }*/

            executor.shutdown();
            while (!executor.isTerminated()) {     }

            //Thread.sleep(500);
            long finish = System.currentTimeMillis()%1000;
            System.out.println((finish-start));

            //RUNS--;
        //}
                
}
 static class GetThread implements Runnable {
     private final HttpClient httpClient;
       // private final PostMethod postMethod;

     //private HttpClient httpClient;
     //private List<NameValuePair> paramas;
     private String request;
        public GetThread(HttpClient httpClient,  String request) {
            this.httpClient = httpClient;
           // this.postMethod = postMethod;
            this.request = request;
        }       
       public void run(){
                PostMethod postMethod = new PostMethod("http://lg1.logging.admicro.vn/dsp_adx");
            try{
               httpClient.setTimeout(10);
               httpClient.setConnectionTimeout(0);
                //postMethod.setRequestHeader("User-Agent","aaa");
                postMethod.setRequestHeader("Content-Type", "application/xml");
               
                //postMethod.setRequestBody(requestParam(request));
                
                //int statusCode =
                
                
                httpClient.executeMethod(postMethod);

                //InputStream body =  postMethod.getResponseBodyAsStream();

            //if (statusCode == HttpStatus.SC_OK) {
                System.out.println("Done");
                //OK
            //}
            /*else{  System.out.println("error");
                httpClient.executeMethod(postMethod);
            }*/
            }catch(Exception e){
                System.out.println(e.fillInStackTrace());
            }finally{
                postMethod.releaseConnection();
                
              //  connectionManager.shutdown();
                
            }
        }

        
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
 public static NameValuePair[] requestParam(String s) {
        
        
        String[] split = s.split("\t");
        int length = split.length;
        NameValuePair[] urlParameter = {
        new NameValuePair("requestId",split[0]),
        
        new NameValuePair("sspName",split[1]),
        new NameValuePair("dspName",split[2]),
        new NameValuePair("timestamp",split[3]),
        new NameValuePair("ua",split[4]),
        new NameValuePair("osCode",split[5]),
        new NameValuePair("ip",split[6]),
        new NameValuePair("loc",split[7]),
        new NameValuePair("domain",split[8]),
        new NameValuePair("url",split[9]),
        new NameValuePair("pos",split[10]),
        new NameValuePair("width",split[11]),
        new NameValuePair("height",split[12]),
        new NameValuePair("guid",split[13]),
        new NameValuePair("sspZoneId",split[14]),
        new NameValuePair("dspZoneId",split[15]),
        new NameValuePair("notifyCode",split[16]),
        new NameValuePair("lstBanner",split[17]),
        new NameValuePair("bidPrice",split[18]),
        new NameValuePair("currencyId",split[19]),
        new NameValuePair("browserCode",split[20]),};
                
        
        return urlParameter;
        
}
public static NameValuePair[] requestParams( ) {
        
        String s ="2018-08-10 09:55:19	2017-12-19 19:08:30	14	68.0.3440.85	1	5.1	1897356102	4	truyenfull.vn	-1	-1	/choc-tuc-vo-yeu-mua-mot-tang-mot-full/chuong-2161/	http://truyenfull.vn/choc-tuc-vo-yeu-mua-mot-tang-mot-full/trang-44/	8813685310712123492	-1.-1.	0	360x640	24	0	192.168.6.140	v;1533869717477;0;0;1;0;0;360x616;0;0;48cdcf22e78d3c3a833db8a92efb046c;42a5068ff66665938e88124cbf7afefb;GA1.2.1203412938.1526564724;-1533869703354;1;28;97;57;26;633;634	http://truyenfull.vn/choc-tuc-vo-yeu-mua-mot-tang-mot-full/chuong-2161/	42a5068ff66665938e88124cbf7afefb	-1	01CMGW50CQF1AKVE5XAX993KJD";
        String[] split = s.split("\t");
        int length = split.length;
        NameValuePair[] urlParameter = {
        new NameValuePair("requestId",split[0]),
        
        new NameValuePair("sspName",split[1]),
        new NameValuePair("dspName",split[2]),
        new NameValuePair("timestamp",split[3]),
        new NameValuePair("ua",split[4]),
        new NameValuePair("osCode",split[5]),
        new NameValuePair("ip",split[6]),
        new NameValuePair("loc",split[7]),
        new NameValuePair("domain",split[8]),
        new NameValuePair("url",split[9]),
        new NameValuePair("pos",split[10]),
        new NameValuePair("width",split[11]),
        new NameValuePair("height",split[12]),
        new NameValuePair("guid",split[13]),
        new NameValuePair("sspZoneId",split[14]),
        new NameValuePair("dspZoneId",split[15]),
        new NameValuePair("notifyCode",split[16]),
        new NameValuePair("lstBanner",split[17]),
        new NameValuePair("bidPrice",split[18]),
        new NameValuePair("currencyId",split[19]),
        new NameValuePair("browserCode",split[20]),};
                
        
        return urlParameter;
        
}
static class GetThreadTemp implements Runnable {
 
     //private HttpClient httpClient;
     //private List<NameValuePair> paramas;
     private ArrayList<String> request;

        public GetThreadTemp(ArrayList<String> request) {
            this.request = request;
        }
 
        public void run(){
            Iterator<String> iterator = request.iterator();
        while(iterator.hasNext()){
                MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
                HttpClient httpClient = new HttpClient(connectionManager);
                PostMethod postMethod = new PostMethod("http://lg1.logging.admicro.vn/dsp_adx");
            try{
                //postMethod.setRequestHeader("User-Agent","aaa");
                postMethod.setRequestHeader("Content-Type", "application/xml");
                //postMethod.addParameters((NameValuePair[]) requestParam(request).toArray());
                //postMethod.setRequestBody(requestParams());
                //postMethod.setRequestEntity(requestEntity);
                int statusCode = httpClient.executeMethod(postMethod);

                //InputStream body = postMethod.getResponseBodyAsStream();

            if (statusCode == HttpStatus.SC_OK) {
                System.out.println("Done");
                //OK
            }
            }catch(Exception e){
                System.out.println(e.fillInStackTrace());
            }finally{
                postMethod.releaseConnection();
                connectionManager.shutdown();
                
            }}
        }

        
 }
public static void sentPostTo( HttpClient httpClient){
    PostMethod postMethod = new PostMethod("http://lg1.logging.admicro.vn/dsp_adx");
            try{
               httpClient.setTimeout(0);
                //postMethod.setRequestHeader("User-Agent","aaa");
                postMethod.setRequestHeader("Content-Type", "application/xml");
                //postMethod.addParameters((NameValuePair[]) requestParam(request).toArray());
                //postMethod.setRequestBody(requestParam(request));
                //postMethod.setRequestEntity(requestEntity);
                int statusCode = httpClient.executeMethod(postMethod);

                //InputStream body = postMethod.getResponseBodyAsStream();

            if (statusCode == HttpStatus.SC_OK) {
                System.out.println("Done");
                //OK
            }
            else{
                System.out.println("error");
                httpClient.executeMethod(postMethod);
            }
            }catch(Exception e){
                System.out.println(e.fillInStackTrace());
            }finally{
                postMethod.releaseConnection();
                
              //  connectionManager.shutdown();
                
            }
        }
}



