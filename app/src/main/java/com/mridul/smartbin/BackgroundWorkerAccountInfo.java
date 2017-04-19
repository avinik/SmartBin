package com.mridul.smartbin;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
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

public class BackgroundWorkerAccountInfo extends AsyncTask<String, Void, String>{
    Context context;

    protected  String json_NAME;
    protected  String json_EMAIL;
    protected  String json_MOB_NO;

    public BackgroundWorkerAccountInfo(Context context1){
        context = context1;
    }
    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        String accountInfo_url = IP_MAIN+"account-info.php";

        if (type.equals("accountInfo")){
            String email = params[1];
            URL url = null;

            try {
                url = new URL(accountInfo_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                StringBuilder sb= new StringBuilder();
                String line = "";
                if(br != null){
                    while((line=br.readLine()) != null){
                        sb.append(line).append('\n');
                    }
                }
                String data = sb.toString();
                Log.d("String from server : ", "" + data);

                try {
                    JSONObject jo = new JSONObject(data);

                    json_NAME = jo.getString("name");
                    json_EMAIL = jo.getString("email");
                    json_MOB_NO = jo.getString("mob_no");

                    br.close();
                    is.close();
                    httpURLConnection.disconnect();

                    String result = "Your Account Info" ;
                    return result;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    protected void onPreExecute() {

    }

    public static String ACCOUNT_INFO_json_EMAIL = "";
    public static String ACCOUNT_INFO_json_NAME = "";
    public static String ACCOUNT_INFO_json_MOB_NO = "";

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("Your Account Info")){
            // show information about user's account here...
            //gotoAccountInfoLayout();

            ACCOUNT_INFO_json_EMAIL = json_EMAIL ;
            ACCOUNT_INFO_json_NAME = json_NAME ;
            ACCOUNT_INFO_json_MOB_NO = json_MOB_NO ;
        }
    }
}
