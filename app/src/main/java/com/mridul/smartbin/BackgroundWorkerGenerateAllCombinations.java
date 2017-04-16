package com.mridul.smartbin;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.mridul.smartbin.BackgroundWorker.IP_MAIN;

/**
 * Created by Mridul on 11-04-2017.
 */

public class BackgroundWorkerGenerateAllCombinations extends AsyncTask<String, Void, String> {

    Context context;

    public BackgroundWorkerGenerateAllCombinations(Context context1){
        context = context1;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];

        String generate_all_combinations_url = IP_MAIN+"distance.php";
        String BINSTART_all_combinations_url = IP_MAIN+"distance_start.php";
        String BINEND_all_combinations_url = IP_MAIN+"distance_end.php";


        if (type.equals("generateAllCombinations")){
            //connect & get string of data.
            InputStream is = null;
            String line = null;

            try {
                URL url = new URL(generate_all_combinations_url);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("GET");
                is = new BufferedInputStream(con.getInputStream());

                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                StringBuilder sb= new StringBuilder();


                if(br != null){
                    while((line=br.readLine()) != null){
                        sb.append(line).append('\n');
                    }
                }

                String data = "";
                data = sb.toString();
                Log.d("String from server : ", "" + data);

                String job = "Maybe Job Done";

                return job;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(is != null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        else if (type.equals("combinationsBINSTART")){
            //connect & get string of data.
            InputStream is = null;
            String line = null;

            try {
                URL url = new URL(BINSTART_all_combinations_url);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("GET");
                is = new BufferedInputStream(con.getInputStream());

                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                StringBuilder sb= new StringBuilder();


                if(br != null){
                    while((line=br.readLine()) != null){
                        sb.append(line).append('\n');
                    }
                }

                String data = "";
                data = sb.toString();
                Log.d("String from server : ", "" + data);

                String job = "Maybe Job Done START";

                return job;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(is != null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        else if (type.equals("combinationsBINEND")){
            //connect & get string of data.
            InputStream is = null;
            String line = null;

            try {
                URL url = new URL(BINEND_all_combinations_url);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("GET");
                is = new BufferedInputStream(con.getInputStream());

                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                StringBuilder sb= new StringBuilder();


                if(br != null){
                    while((line=br.readLine()) != null){
                        sb.append(line).append('\n');
                    }
                }

                String data = "";
                data = sb.toString();
                Log.d("String from server : ", "" + data);

                String job = "Maybe Job Done END";

                return job;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(is != null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("Maybe Job Done") || result.equals("Maybe Job Done START") || result.equals("Maybe Job Done END")){
            //do nothing :)
        }

    }
}
