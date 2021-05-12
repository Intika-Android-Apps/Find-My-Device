package de.nulide.findmydevice.sender;

import android.content.Context;
import android.telephony.SmsManager;

import java.io.Serializable;
import java.util.ArrayList;

public class SMS extends Sender implements Serializable {

    public final static String TYPE = "SMS";

    public SMS(Context context, String destination) {
        super(destination, TYPE);
    }

    @Override
    protected void sendMessage(String destination, String msg) {
        SmsManager smsManager = SmsManager.getDefault();
        if (msg.length() <= 160) {
            smsManager.sendTextMessage
                    (destination, null, msg,
                            null, null);
        } else {
            ArrayList<String> parts = smsManager.divideMessage(msg);
            smsManager.sendMultipartTextMessage(destination, null, parts, null, null);
        }
    }
}
