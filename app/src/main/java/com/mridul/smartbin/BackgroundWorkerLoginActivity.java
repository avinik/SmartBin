package com.mridul.smartbin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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


public class BackgroundWorkerLoginActivity extends AsyncTask<String, Void, String> {

    public static String START_POSITION_SELECTED = "noWork" ;
    public static String CURRENT_USER_EMAIL;

    Context context;
    ProgressDialog progressDialog;

    public BackgroundWorkerLoginActivity(Context context1){
        context = context1 ;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Login in Progress...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = IP_MAIN + "login.php";

        if(type.equals("login")){



            String email = params[1];
            CURRENT_USER_EMAIL = email;
            String password = params[2];
            URL url = null;
            try {
                url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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
    protected void onPostExecute(String result) {

        progressDialog.dismiss();

        if(result.equals("You are successfully Logged In")) {

            START_POSITION_SELECTED = "NO" ;

            /**
             * on successful log in , opening AfterLogin1 Activity...
             */
            openAfterLogin();

        }else {
            gotoLoginLayout();
        }

    }

    private void openAfterLogin() {
        Intent intent = new Intent(context,AfterLogin1.class);
        context.startActivity(intent);
    }

    private void gotoLoginLayout() {
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }
}
