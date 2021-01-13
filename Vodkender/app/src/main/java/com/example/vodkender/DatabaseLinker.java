package com.example.vodkender;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class DatabaseLinker {

    private final static String DATABASE_API_URL ="https://script.google.com/macros/s/AKfycbxYxpDlr5W4nx5oL0hMaWIDb9eAq2T1lz87Kha4ULNhvRuNmsQ/exec";
    private static final String MANU_PATH = "/storage/emulated/0/Documents/";
    private final static String TAG ="DatabaseSync";
    static  {
        Thread thread1,thread2,thread3;
        thread1=new Thread(new DatabaseGet("Map"));
        thread2=new Thread(new DatabaseGet("Menu"));
        thread3=new Thread(new DatabaseGet("MachineCode"));
        thread1.start();
        thread2.start();
        thread3.start();




    }
    public static void  pushDataTGoogleSheet (int id,String time,String cocktail,String material)
    {
        Thread thread=new Thread(new DatabasePost(id,time,cocktail,material));
        thread.start();

    }

    public DatabaseLinker(){
    }


    private static class DatabasePost implements Runnable
    {

        int id;
        String time;
        String cocktail;
        String materail;

        public  DatabasePost(int id,String time,String cocktail,String material)
        {
                this.id=id;
                this.time=time;
                this.cocktail=cocktail;
                this.materail=material;
        }

        @Override
        public void run() {
            HttpURLConnection connection;
            try {
                URL url = new URL(DATABASE_API_URL);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("id=").append(URLEncoder.encode(""+id, "UTF-8")).append("&");
                stringBuilder.append("time=").append(URLEncoder.encode(time, "UTF-8")).append("&");
                stringBuilder.append("cocktail=").append(URLEncoder.encode(cocktail, "UTF-8")).append("&");
                stringBuilder.append("material=").append(URLEncoder.encode(materail, "UTF-8")).append("&");
                outputStream.writeBytes(stringBuilder.toString());
                outputStream.flush();
                outputStream.close();


                InputStream inputStream = connection.getInputStream();
                int status = connection.getResponseCode();
                Log.d(TAG, String.valueOf(status));
                if(inputStream != null){
                    InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
                    BufferedReader in = new BufferedReader(reader);

                    String line="";
                } else{

                }
                //return  result;
            } catch (Exception e) {
                Log.d("ATask InputStream", e.getLocalizedMessage());
                e.printStackTrace();

            }

        }
    }



    private static class DatabaseGet implements Runnable
    {
        String fileName ="";

        public DatabaseGet(String fileName)
        {
            this.fileName=fileName;
            Log.i(TAG,"Data base  connectet init");

        }

        @Override
        public void run() {
            HttpURLConnection conn = null;
            try {
                String mWeatherUrl = DATABASE_API_URL+"?file="+fileName;

                URL url = new URL(mWeatherUrl);
                conn = (HttpURLConnection) url.openConnection(); // open connect
                conn.setRequestMethod("GET");										 // using GET method
                int responseCode = conn.getResponseCode();       // responseCode will return what eror.
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));   //read your data
                String inputLine = "";
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //initDrinksList(response.toString())
                createFile (fileName,response.toString());

                Log.d(TAG, response.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) conn.disconnect();
            }

            System.out.println("Hello world");
        }
    }
    static void  createFile (String filename, String content)
    {
        Log.i(TAG,"Create file");



        try {
            File f = new File(MANU_PATH+filename+".txt");
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter fileWriter =new FileWriter(MANU_PATH+filename+".txt");
            fileWriter.write(content);
            fileWriter.close();


        }catch (IOException e)
        {
            e.printStackTrace();

        }
    }


}
