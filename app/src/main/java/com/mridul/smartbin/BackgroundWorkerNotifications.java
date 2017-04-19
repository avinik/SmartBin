package com.mridul.smartbin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.mridul.smartbin.Drivers.ViewDrivers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.mridul.smartbin.BackgroundWorker.IP_MAIN;


public class BackgroundWorkerNotifications extends AsyncTask<String, Void, String>{
    Context context;

    public BackgroundWorkerNotifications(Context context1){
        context = context1;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String get_notification_url = IP_MAIN+"notification.php";

        if (type.equals("getNotificationFromServer")){
            //connect & get string of data.
            InputStream is = null;
            String line = null;

            try {
                URL url = new URL(get_notification_url);
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

                return data;
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
    protected void onPostExecute(String data) {

        if(data.trim().equals("No data found")){
                /*AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("No Data Recieved...");
                alert.setMessage("Sorry! No Drivers found in database. Please add one.");
                alert.setPositiveButton("OK", null);
                alert.show();*/
            Toast.makeText(context,data,Toast.LENGTH_LONG).show();
        }
        else if( !data.trim().equals("No data found") ){
            Intent intent = new Intent(context, ShowNotifications.class);
            intent.putExtra("jsonString_getNotification", data);
            context.startActivity(intent);
        }

    }
}
