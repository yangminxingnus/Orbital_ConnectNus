package com.example.sunsh.connectnus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class LnfCreateActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    String location,date,category,description,contact;
    
    TextView tvdate,tvcategory,tvdescription,tvcontact;
    
    Button btncreate, btndelete,btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lnf_create);

        spinner = (Spinner)findViewById(R.id.spinnerLnfCreateLocation);
        adapter = ArrayAdapter.createFromResource(this,R.array.address,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                location = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        tvdate = (TextView)findViewById(R.id.editTextLnfCreateDate);
        tvcategory = (TextView)findViewById(R.id.editTextLnfCreateCategory);
        tvdescription = (TextView)findViewById(R.id.editTextLnfCreateDescription);
        tvcontact = (TextView)findViewById(R.id.editTextLnfCreateContact);
        
        btncreate = (Button)findViewById(R.id.buttonLnfCreateCreate);
        btndelete = (Button)findViewById(R.id.buttonLnfCreateDelete);
        btncancel = (Button)findViewById(R.id.buttonLnfCreateCancel);
        
        CreateButton();
        DeleteButton();
        CancelButton();
    }

    public void button_onclick_create(String mydate,String mycate,String mydesc,String mycont) throws ExecutionException, InterruptedException {
        String type = "lnf_create";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,mydate,mycate,mydesc,location,mycont,LoginActivity.curr_user);

        String result = backgroundWorker.get();

        if (result.equals("create success")) {
            Toast.makeText(LnfCreateActivity.this, "Post Created!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if (result.equals("create fail")) {
            Toast.makeText(LnfCreateActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    public void CreateButton() {
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = tvdate.getText().toString();
                category = tvcategory.getText().toString();
                description = tvdescription.getText().toString();
                contact = tvcontact.getText().toString();

                try {
                    button_onclick_create(date,category,description,contact);
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
