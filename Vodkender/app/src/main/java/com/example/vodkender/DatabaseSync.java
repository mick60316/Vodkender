package com.example.vodkender;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DatabaseSync {

    private final static String DATABASE_API_URL ="https://script.google.com/macros/s/AKfycbxYxpDlr5W4nx5oL0hMaWIDb9eAq2T1lz87Kha4ULNhvRuNmsQ/exec";
    private static final String MANU_PATH = "/storage/emulated/0/Documents/Menu.txt";
    private final static String TAG ="DatabaseSync";
    public DatabaseSync (){

        Thread thread=new Thread(new DatabaseGet());
        thread.start();


    }
    private class DatabaseGet implements Runnable
    {
        public DatabaseGet()
        {
            Log.i(TAG,"Data base  connectet init");

        }

        @Override
        public void run() {
            HttpURLConnection conn = null;
            try {
                String mWeatherUrl = "https://script.google.com/macros/s/AKfycbxYxpDlr5W4nx5oL0hMaWIDb9eAq2T1lz87Kha4ULNhvRuNmsQ/exec";//"lat=55.5&lon=37.5&cnt=10" openweathermap.org

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
                //initDrinksList(response.toString());

                createFile (response.toString());

                Log.d(TAG, response.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) conn.disconnect();
            }

            System.out.println("Hello world");
        }
    }
    void createFile (String content)
    {
        Log.i(TAG,"Create file");



        try {
            File f = new File(MANU_PATH);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter fileWriter =new FileWriter(MANU_PATH);
            fileWriter.write(content);
            fileWriter.close();


        }catch (IOException e)
        {
            e.printStackTrace();

        }
    }


}
