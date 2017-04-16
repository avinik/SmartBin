package com.mridul.smartbin.Drivers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import static com.mridul.smartbin.BackgroundWorker.IP_MAIN;


public class BackgroundWorkerDriverRemove extends AsyncTask<String, Void, String> {

    Context context;
    public BackgroundWorkerDriverRemove(Context context1){
        context = context1;
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];

        String remove_driver_url = IP_MAIN+"DriverManagement/remove_driver.php";

        if (type.equals("removeDriver")){
            String uName = params[1];

            URL url = null;
            try {
                url = new URL(remove_driver_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(uName, "UTF-8") ;

                Log.d("postdata :", ""+postdata);

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    protected void onPostExecute(String data) {

        if(data.equals("Driver Has been successfully Removed") || data.equals("Error in Removing Driver") || data.equals("Driver User Name NOT found in Database")){

            Toast.makeText(context,data,Toast.LENGTH_LONG).show();
        }
    }
}
