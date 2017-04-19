package com.mridul.smartbin.Drivers;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.mridul.smartbin.AfterLogin1;
import com.mridul.smartbin.LoginActivity;

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

public class BackgroundWorkerDriver extends AsyncTask<String, Void, String>{

    ProgressDialog progressDialog;


    protected Context context;
    public BackgroundWorkerDriver(Context context1){
        context = context1;
    }

    @Override
    protected void onPreExecute() {
        /*progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Request in Progress...");
        progressDialog.show();*/
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];

        String add_driver_url = IP_MAIN+"DriverManagement/add_driver.php";
        String view_driver_url = IP_MAIN+"DriverManagement/view_drivers.php";


        if (type.equals("addDriver")){
            String uName = params[1];
            String name = params[2];
            String mobNo = params[3];
            String vehicleNo = params[4];
            String password = params[5];
            URL url = null;
            try {
                url = new URL(add_driver_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(uName, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("mob_no", "UTF-8") + "=" + URLEncoder.encode(mobNo, "UTF-8") + "&"
                        + URLEncoder.encode("vehicle_no", "UTF-8") + "=" + URLEncoder.encode(vehicleNo, "UTF-8")+ "&"
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
        else if (type.equals("viewDriver")){
            //connect & get string of data.
            InputStream is = null;
            String line = null;

            try {
                URL url = new URL(view_driver_url);
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
    protected void onProgressUpdate(Void... values) {

    }

    @Override
    protected void onPostExecute(String data) {
        /*progressDialog.dismiss();*/


        if(data.trim().equals("Sorry! This UserName is already taken, Try another.") || data.trim().equals("Sorry! Error Inserting Registering Data. Please try again.") || data.trim().equals("Driver Registration Successful...You can now give Driver their UserName & Password to access GarbageCollect App.")){

            /*AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Notification...");
            alert.setMessage(""+data+"");
            alert.setPositiveButton("OK", null);
            alert.show();*/
            Toast.makeText(context,data,Toast.LENGTH_LONG).show();
        }
        else{
            if(data.trim().equals("No data found !")){
                /*AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("No Data Recieved...");
                alert.setMessage("Sorry! No Drivers found in database. Please add one.");
                alert.setPositiveButton("OK", null);
                alert.show();*/
                Toast.makeText(context,data,Toast.LENGTH_LONG).show();
            }
            else if( !data.trim().equals("No data found !") ){
                Intent intent = new Intent(context, ViewDrivers.class);
                intent.putExtra("jsonString_driverDetails", data);
                context.startActivity(intent);
            }
        }


    }
}
