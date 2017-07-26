package com.example.sunsh.connectnus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by sunshengran on 6/7/2017.
 * This is the user information page.
 * All the features of this app are explained here.
 */

public class UserInformationActivity extends AppCompatActivity {

    private static Button button_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        OnClickButtonListener();
    }

    //Scroll the text to the end to click the 'close' button and return to the menu page
    public void OnClickButtonListener() {
        button_close = (Button)findViewById(R.id.buttonInfoClose);
        button_close.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.sunsh.connectnus.MenuActivity");
                        startActivity(intent);
                    }
                }
        );
    }
}
