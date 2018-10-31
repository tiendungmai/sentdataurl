package com.mycompany.sentdata;

import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.File;

public class TestServer {
    public static void main(String[] args) {
        Spark.get("/primes",(Request rpst, Response rsns) ->{
            String pri = rpst.queryParams("requestId");
            System.out.println("ok");
            return pri;
        });
        Spark.get("/url",(Request rpst, Response rsns) ->{
            String pri = rpst.queryParams("requestId");
            System.out.println("ok");
            return pri;
        });
    }
    public void readFile(String path){
        String fl = null;
        File folder = new File(path);
        File[] listFile = folder.listFiles();
        for (File file : listFile) {
            if(file.getAbsolutePath().endsWith(".json"))
            {
                fl = file.getPath();
                break;
            }
        }
    }
}
