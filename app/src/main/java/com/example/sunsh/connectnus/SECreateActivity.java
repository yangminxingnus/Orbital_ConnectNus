package com.example.sunsh.connectnus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SECreateActivity extends AppCompatActivity {

    String date,category,description,price,piclink,address,contact;

    TextView tvdate,tvcategory,tvdescription,tvprice,tvpiclink,tvaddress,tvcontact;

    Button btncreate, btndelete,btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secreate);

        tvdate = (TextView)findViewById(R.id.editTextseCreateDate);
        tvcategory = (TextView)findViewById(R.id.editTextseCreateCategory);
        tvdescription = (TextView)findViewById(R.id.editTextseCreateDescription);
        tvprice = (TextView)findViewById(R.id.editTextseCreatePrice);
        tvpiclink = (TextView)findViewById(R.id.editTextseCreateLink);
        tvaddress = (TextView)findViewById(R.id.editTextseCreateAddress);
        tvcontact = (TextView)findViewById(R.id.editTextseCreateContact);

        btncreate = (Button)findViewById(R.id.buttonseCreateCreate);
        btndelete = (Button)findViewById(R.id.buttonseCreateDelete);
        btncancel = (Button)findViewById(R.id.buttonseCreateCancel);

        CreateButton();
        DeleteButton();
        CancelButton();
    }

    public void button_onclick_create(String mydate,String mycate,String mydesc,String myprice,String mylink,String myaddr, String mycont) throws ExecutionException, InterruptedException {
        String type = "se_create";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,mydate,mycate,mydesc,"$"+myprice,mylink,myaddr,mycont,LoginActivity.curr_user);

        String result = backgroundWorker.get();

        if (result.equals("create success")) {
            Toast.makeText(SECreateActivity.this, "Post Created!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if (result.equals("create fail")) {
            Toast.makeText(SECreateActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    public void CreateButton() {
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date = tvdate.getText().toString();
                category = tvcategory.getText().toString();
                description = tvdescription.getText().toString();
                price = tvprice.getText().toString();
                piclink = tvpiclink.getText().toString();
                address = tvaddress.getText().toString();
                contact = tvcontact.getText().toString();

                try {
                    button_onclick_create(date,category,description,price,piclink,address,contact);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void DeleteButton() {
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdate.setText("");
                tvcategory.setText("");
                tvdescription.setText("");
                tvprice.setText("");
                tvpiclink.setText("");
                tvaddress.setText("");
                tvcontact.setText("");
            }
        });
    }

    public void CancelButton() {
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
