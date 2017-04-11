package com.mridul.smartbin;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

public class BackgroundWorkerSmsHandler extends AsyncTask<String, Void, String >{

    Context context;

    BackgroundWorkerSmsHandler(Context context1){
        context = context1;
    }

    public BackgroundWorkerSmsHandler() {

    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];

        String bin_update_sms_url = "http://172.16.176.179/bin_filled_status.php";

        if (type.equals("updateSmsData")){

            String bin_id = params[1];
            String percentage = params[2];


            Log.d("just entered work",bin_id+"  "+percentage);

            URL url = null;
            try {
                url = new URL(bin_update_sms_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("bin_id", "UTF-8") + "=" + URLEncoder.encode(bin_id, "UTF-8") + "&"
                        + URLEncoder.encode("percentage_filled", "UTF-8") + "=" + URLEncoder.encode(percentage, "UTF-8");
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

                Log.d("think work done",""+result);

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null ;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }



}
