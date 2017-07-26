package com.example.sunsh.connectnus;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SeSendMessageActivity extends AppCompatActivity {

    EditText number, text;

    Button send,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_send_message);

        number = (EditText)findViewById(R.id.editTextSeSMSNumber);
        text = (EditText)findViewById(R.id.editTextSeSMSText);

        send = (Button)findViewById(R.id.buttonSeSMSSend);
        cancel = (Button)findViewById(R.id.buttonSeSMSCancel);

        number.setText(SeItemsActivity.number);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mynum = number.getText().toString();
                String mytext = text.getText().toString();

                String SENT = "SMS_SENT";
                String DELIVERED = "SMS_DELIVERED";
                PendingIntent sentPI, deliveredPI;
                BroadcastReceiver smsSentReceiver,smsDeliverReceiver;

                sentPI = PendingIntent.getBroadcast(SeSendMessageActivity.this,0,new Intent(SENT),0);
                deliveredPI = PendingIntent.getBroadcast(SeSendMessageActivity.this,0,new Intent(DELIVERED),0);

                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(mynum,null,mytext,sentPI,deliveredPI);
                Toast.makeText(SeSendMessageActivity.this, "Message sent successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
