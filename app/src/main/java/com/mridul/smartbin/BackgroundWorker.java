package com.mridul.smartbin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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


/**
 * Presently this class is used to fetch data from server for login and user_registration & much more.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    public static String CURRENT_USER_EMAIL;

    Context context;
    //AlertDialog alertDialog;
    ProgressDialog progressDialog;

    protected  String json_NAME;
    protected  String json_EMAIL;
    protected  String json_MOB_NO;

    BackgroundWorker(Context context1){
        context = context1;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];

        String login_url = "http://172.16.190.235/login.php";
        String register_url = "http://172.16.190.235/register.php";
        String delete_url = "http://172.16.190.235/deletebin.php";
        String resetPassword_url = "http://172.16.190.235/mailer/reset-password-send-mail.php";
        String accountInfo_url = "http://172.16.190.235/account-info.php";
        String changePassword_url = "http://172.16.190.235/change-password.php";
        String path_starting_point_url = "http://172.16.190.235/path_start_position.php";
        String path_ending_point_url = "http://172.16.190.235/path_end_position.php";
        String install_bin_url = "http://172.16.190.235/install_bin.php";

        if (type.equals("login")) {
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
        else if (type.equals("register")){
            String email = params[1];
            String name = params[2];
            String mob_no = params[3];
            String password = params[4];
            URL url = null;
            try {
                url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("mob_no", "UTF-8") + "=" + URLEncoder.encode(mob_no, "UTF-8") + "&"
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
        }else if(type.equals("deletebin")){


            String binid = params[1];

            URL url = null;
            try {
                url = new URL(delete_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("bin_id", "UTF-8") + "=" + URLEncoder.encode(binid, "UTF-8");
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
        else if (type.equals("resetpassword")) {
            String email = params[1];

            URL url = null;
            try {
                url = new URL(resetPassword_url);
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
        else if(type.equals("changePassword")){
            String email = params[1];
            String pass_o = params[2];
            String pass_n = params[3];

            URL url = null;
            try {
                url = new URL(changePassword_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("oldPassword", "UTF-8") + "=" + URLEncoder.encode(pass_o, "UTF-8") + "&"
                        + URLEncoder.encode("newPassword", "UTF-8") + "=" + URLEncoder.encode(pass_n, "UTF-8");
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
        else if (type.equals("accountInfo")){
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
        else if (type.equals("pathStartingPosition")){
            String placeId = params[1];
            String name = params[2];
            String address = params[3];
            String lat = params[4];
            String lng = params[5];

            URL url = null;
            try {
                url = new URL(path_starting_point_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(placeId, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("mob_no", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&"
                        + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8") + "&"
                        + URLEncoder.encode("lng", "UTF-8") + "=" + URLEncoder.encode(lng, "UTF-8");
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
        else if (type.equals("pathEndingPosition")){
            String placeId = params[1];
            String name = params[2];
            String address = params[3];
            String lat = params[4];
            String lng = params[5];

            URL url = null;
            try {
                url = new URL(path_ending_point_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(placeId, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("mob_no", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&"
                        + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8") + "&"
                        + URLEncoder.encode("lng", "UTF-8") + "=" + URLEncoder.encode(lng, "UTF-8");
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
        else if (type.equals("installBin")){
            String placeId = params[1];
            String name = params[2];
            String address = params[3];
            String lat = params[4];
            String lng = params[5];

            URL url = null;
            try {
                url = new URL(install_bin_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(placeId, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("mob_no", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&"
                        + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8") + "&"
                        + URLEncoder.encode("lng", "UTF-8") + "=" + URLEncoder.encode(lng, "UTF-8");
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
        return null ;
    }

    @Override
    protected void onPreExecute() {

        //alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Status");


        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Request in Progress...");
        progressDialog.show();

    }


    public static String ACCOUNT_INFO_json_EMAIL = "";
    public static String ACCOUNT_INFO_json_NAME = "";
    public static String ACCOUNT_INFO_json_MOB_NO = "";

    @Override
    protected void onPostExecute(String result) {

        //alertDialog.setMessage(result);
        //alertDialog.show();

        progressDialog.dismiss();

        if( !result.equals("Your Account Info") ) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }

        if(result.equals("Your Account Info")){
            // show information about user's account here...
            //gotoAccountInfoLayout();

            ACCOUNT_INFO_json_EMAIL = json_EMAIL ;
            ACCOUNT_INFO_json_NAME = json_NAME ;
            ACCOUNT_INFO_json_MOB_NO = json_MOB_NO ;

        }
        else if(result.equals("You are successfully Logged In")) {
            /**
             * on successful log in , opening AfterLogin1 Activity...
             */
            openAfterLogin();

        }else if(result.equals("Bin Has been Deleted")){
            // Do nothing.
        }else if(result.equals("Password successfully changed") || result.equals("Your OLD password do not match") || result.equals("Database insertion ERROR !")){
            // Do nothing.
        }else if(result.equals("Bin Position successfully inserted on server") || result.equals("Error Inserting Bin-Data on Server")){
            // Do nothing.
        }
        else if(result.equals("Sorry! Input credentials are WRONG...Please Login with valid credentials!") || result.equals("Registration Successful...You can now Log In !") || result.equals("Password successfully resetted and sent to your registered email-address") ){
            /**
             * else returning to login page again...
             * After registration , also , returning to login activity...
             */
            gotoLoginLayout();
        }else {
            // Do nothing.
        }
        //alertDialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    private void openAfterLogin() {
        Intent intent = new Intent(context,AfterLogin1.class);
        /*intent.putExtra("email",EMAIL);*/
        context.startActivity(intent);
    }

    private void gotoLoginLayout() {
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    private void gotoAccountInfoLayout(){
        //Intent intent = new Intent(context,AccountInfo.class);
        Intent intent = new Intent(context,AccountInfo.class);
        intent.putExtra("email",json_EMAIL);
        intent.putExtra("name",json_NAME);
        intent.putExtra("mob_no",json_MOB_NO);
        context.startActivity(intent);
    }



}
