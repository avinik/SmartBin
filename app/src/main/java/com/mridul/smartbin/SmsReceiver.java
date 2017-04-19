package com.mridul.smartbin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class SmsReceiver extends BroadcastReceiver {

    private String incomingNumber;

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Bundle extras = intent.getExtras();

        if (action.equals("android.provider.Telephony.SMS_RECEIVED")) {

            Object[] pdus = (Object[]) extras.get("pdus");
            SmsMessage[] smsMessage = new SmsMessage[pdus.length];


            //for (int i = 0; i < smsMessage.length; i++) {
            int i = 0 ;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = extras.getString("format");

                smsMessage[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
            } else {
                smsMessage[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }

            incomingNumber = smsMessage[i].getOriginatingAddress();
            String incomingMsg = smsMessage[i].getMessageBody();
            Toast.makeText(context, "Incoming message From " + incomingNumber, Toast.LENGTH_LONG).show();
            //sendAutoReply(incomingNumber);

            Log.d("just before work",incomingNumber+"  "+incomingMsg);

            String str1 = incomingMsg.replaceAll("[(]+" , "{");
            String str2 = str1.replaceAll("[)]+" , "}");
            incomingMsg = str2.trim();


            Log.d("After json modification",incomingNumber+"  "+incomingMsg);



            String bin_id = "";
            String filled_percentage = "";

        // SMS sending FORMAT --- { "bin_id":"BIN020", "percentage":"85" }
            try {
                JSONObject jo = new JSONObject(incomingMsg);

                bin_id = jo.getString("bin_id");
                filled_percentage = jo.getString("percentage");

                String type = "updateSmsData";
                BackgroundWorkerSmsHandler bg = new BackgroundWorkerSmsHandler(context);
                bg.execute(type,bin_id,filled_percentage);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            /*String type = "updateSmsData";
                BackgroundWorkerSmsHandling bg = new BackgroundWorkerSmsHandling(context);
                bg.execute(type,bin_id,filled_percentage);*/

            //}
        }


    }
}
