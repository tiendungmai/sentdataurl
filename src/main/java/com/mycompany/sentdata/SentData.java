package com.mycompany.sentdata;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SentData {
    private final Logger logger = Logger.getLogger(SentData.class.getName());
    private final String USER_AGENT = "Sent Data";
    public void sentPost() throws IOException {
        String url ="http://lg1.logging.admicro.vn/dsp_adx";
        List<NameValuePair> urlParameter = new ArrayList<NameValuePair>();
        List<List<NameValuePair>> listRq = new ArrayList<List<NameValuePair>>();
        //Map<String,String > listParameters = new HashMap<String, String>();
       // listParameters.put("requestId")
        //String line = "";
        System.out.println("\nSending 'POST' request to URL : " + url);

        
        
        ArrayList<String> lineR = new ArrayList<String>();
        lineR= logPage();
        
        System.out.println("Tong so requets: "+lineR.size());
         /**
        listRq = listRequest(lineR);
        long start = System.currentTimeMillis()%1000;
        Iterator<List<NameValuePair>> iterator = listRq.iterator();
        while(iterator.hasNext()){
            List<NameValuePair> rp = iterator.next();
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.addHeader("User-Agent",USER_AGENT);
            post.setEntity(new UrlEncodedFormEntity(rp,"UTF-8"));
            HttpResponse response = client.execute(post);
            // System.out.println("Response Code : " +response.getStatusLine().getStatusCode());
            post.getEntity();
        }
        long finish = System.currentTimeMillis()%1000;
        long time = finish -start;
        System.out.println("Done");
        System.out.println("Toc do: "+ (lineR.size()+1)/time);*/
         
        long start = System.currentTimeMillis()%1000;
         List<List<NameValuePair>> lisst = listRequest(lineR);
        Iterator<List<NameValuePair>> iterator = lisst.iterator();
        while(iterator.hasNext()){
        
                      // Send more messages if there are more messages to send.
                             List<NameValuePair> log = iterator.next();
                             HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.addHeader("User-Agent",USER_AGENT);
            
            post.setEntity(new UrlEncodedFormEntity(log,"UTF-8"));
            HttpResponse response = client.execute(post);
            System.out.println("Response Code : " +response.getStatusLine().getStatusCode());
            post.getEntity();
                         
                   
                      
        }             
       
        long finish = System.currentTimeMillis()%1000;
        long time = finish -start;
        System.out.println("Done");
        System.out.println("Toc do: "+ (lineR.size()+1)/time);

        
        

        //post.setEntity(new UrlEncodedFormEntity(urlParameter));
        //HttpResponse response = client.execute(post);


        //System.out.println("Post parameters : " + post.getEntity().toString());

        /*
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        //StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            //result.append(line);
            System.out.println(line);

        }*/
    }
    public List<List<NameValuePair>> listRequest (ArrayList<String> s ){
        List<List<NameValuePair>> list = new ArrayList<List<NameValuePair>>();
        List<NameValuePair> urlParameter = new ArrayList<NameValuePair>();
        //s = new ArrayList<String>();
        //s= logPage();
        //System.out.println("Tong so requets: "+s.size());
        long start = System.currentTimeMillis()%1000;
        Iterator<String> iterator = s.iterator();
        while(iterator.hasNext()){
        
                      // Send more messages if there are more messages to send.
                             String log = iterator.next();
                             list.add(request(log,urlParameter));
                      
        }              
       
        long finish = System.currentTimeMillis()%1000;
        long time = finish -start;System.out.println(time);
        return list;
    }
    public List<NameValuePair> request(String s,List<NameValuePair> urlParameter) {
        
        // String s represents ParamAndMacro
        String[] split = s.split("\t");
        int length = split.length;
        int i =0;
        //Map<String, String> maps = new HashMap<String,String>();
        //List<NameValuePair> urlParameter = new ArrayList<NameValuePair>();
        urlParameter.add(new BasicNameValuePair("requestId",split[0]));
        urlParameter.add(new BasicNameValuePair("sspName",split[1]));
        urlParameter.add(new BasicNameValuePair("dspName",split[2]));
        urlParameter.add(new BasicNameValuePair("timestamp",split[3]));
        urlParameter.add(new BasicNameValuePair("ua",split[4]));
        urlParameter.add(new BasicNameValuePair("osCode",split[5]));
        urlParameter.add(new BasicNameValuePair("ip",split[6]));
        urlParameter.add(new BasicNameValuePair("loc",split[7]));
        urlParameter.add(new BasicNameValuePair("domain",split[8]));
        urlParameter.add(new BasicNameValuePair("url",split[9]));
        urlParameter.add(new BasicNameValuePair("pos",split[10]));
        urlParameter.add(new BasicNameValuePair("width",split[11]));
        urlParameter.add(new BasicNameValuePair("height",split[12]));
        urlParameter.add(new BasicNameValuePair("guid",split[13]));
        urlParameter.add(new BasicNameValuePair("sspZoneId",split[14]));
        urlParameter.add(new BasicNameValuePair("dspZoneId",split[15]));
        urlParameter.add(new BasicNameValuePair("notifyCode",split[16]));
        urlParameter.add(new BasicNameValuePair("lstBanner",split[17]));
        urlParameter.add(new BasicNameValuePair("bidPrice",split[18]));
        urlParameter.add(new BasicNameValuePair("currencyId",split[19]));
        urlParameter.add(new BasicNameValuePair("browserCode",split[20]));
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

    public static void main(String[] args) throws IOException {
        SentData http = new SentData();

        System.out.println("Send Http POST request");
        http.sentPost();
    }
}
