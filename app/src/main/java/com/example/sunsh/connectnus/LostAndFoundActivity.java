package com.example.sunsh.connectnus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LostAndFoundActivity extends AppCompatActivity {

    ListView lv_lnf;

    Button btn_submit;

    public static String str_id = "";
    public static String date = "";
    public static String category = "";
    public static String desc = "";
    public static String location = "";
    public static String contact = "";
    public static String username = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found);

        btn_submit = (Button)findViewById(R.id.buttonLnfCreate);

        try {
            listView();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CreateButton();
    }

    public void CreateButton() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.sunsh.connectnus.LnfCreateActivity");
                startActivity(intent);
            }
        });
    }

    public void listView_2(String pros_date, String pros_category) throws ExecutionException, InterruptedException {
        lv_lnf = (ListView)findViewById(R.id.lvLostAndFound);

        String type = "lnf_search";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,pros_date,pros_category);
        String result = backgroundWorker.get();

        if (result.equals("no result")) {
            Toast.makeText(LostAndFoundActivity.this, "There is no result", Toast.LENGTH_SHORT).show();
        }
        else {
            final String[] info_list = result.split("\t");

            final ArrayList<String> al_id = new ArrayList<String>();
            for (int i = 0; i < info_list.length; i = i + 7) al_id.add(info_list[i]);

            final ArrayList<String> al_date = new ArrayList<String>();
            for (int i = 1; i < info_list.length; i = i + 7) al_date.add(info_list[i]);

            final ArrayList<String> al_category = new ArrayList<String>();
            for (int i = 2; i < info_list.length; i = i + 7) al_category.add(info_list[i]);

            final ArrayList<String> al_description = new ArrayList<String>();
            for (int i = 3; i < info_list.length; i = i + 7) al_description.add(info_list[i]);

            final ArrayList<String> al_location = new ArrayList<String>();
            for (int i = 4; i < info_list.length; i = i + 7) al_location.add(info_list[i]);

            final ArrayList<String> al_contact = new ArrayList<String>();
            for (int i = 5; i < info_list.length; i = i + 7) al_contact.add(info_list[i]);

            final ArrayList<String> al_username = new ArrayList<String>();
            for (int i = 6; i < info_list.length; i = i + 7) al_username.add(info_list[i]);

            String[] show_list = new String[al_id.size()];
            for (int i = al_id.size() - 1; i >= 0; i--)
                show_list[al_id.size() - 1 - i] = "\n" + al_category.get(i) + "\n\n" + al_location.get(i) + "\n\n" + al_date.get(i) + "\n";

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.found_items_list, show_list);
            lv_lnf.setAdapter(adapter);
            Toast.makeText(LostAndFoundActivity.this, al_id.size()+" items found", Toast.LENGTH_SHORT).show();
            lv_lnf.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            str_id = al_id.get(al_id.size() - position - 1);
                            date = al_date.get(al_id.size() - position - 1);
                            category = al_category.get(al_id.size() - position - 1);
                            desc = al_description.get(al_id.size() - position - 1);
                            location = al_location.get(al_id.size() - position - 1);
                            contact = al_contact.get(al_id.size() - position - 1);
                            username = al_username.get(al_id.size() - position - 1);

                            Intent myIntent = new Intent(LostAndFoundActivity.this, MapsActivity.class);
                            startActivity(myIntent);

                        }
                    }
            );
        }
    }

    public void listView() throws ExecutionException, InterruptedException {
        lv_lnf = (ListView)findViewById(R.id.lvLostAndFound);

        String type = "lnf_info";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);
        String result = backgroundWorker.get();
        final String[] info_list = result.split("\t");

        final ArrayList<String> al_id = new ArrayList<String>();
        for (int i = 0;i<info_list.length;i=i+7) al_id.add(info_list[i]);

        final ArrayList<String> al_date = new ArrayList<String>();
        for (int i = 1;i<info_list.length;i=i+7) al_date.add(info_list[i]);

        final ArrayList<String> al_category = new ArrayList<String>();
        for (int i = 2;i<info_list.length;i=i+7) al_category.add(info_list[i]);

        final ArrayList<String> al_description = new ArrayList<String>();
        for (int i = 3;i<info_list.length;i=i+7) al_description.add(info_list[i]);

        final ArrayList<String> al_location = new ArrayList<String>();
        for (int i = 4;i<info_list.length;i=i+7) al_location.add(info_list[i]);

        final ArrayList<String> al_contact = new ArrayList<String>();
        for (int i = 5;i<info_list.length;i=i+7) al_contact.add(info_list[i]);

        final ArrayList<String> al_username = new ArrayList<String>();
        for (int i = 6;i<info_list.length;i=i+7) al_username.add(info_list[i]);

        String[] show_list = new String[al_id.size()];
        for (int i=al_id.size()-1;i>=0;i--) show_list[al_id.size()-1-i]="\n"+al_category.get(i)+"\n\n"+al_location.get(i)+"\n\n"+al_date.get(i)+"\n";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.found_items_list,show_list);
        lv_lnf.setAdapter(adapter);
        lv_lnf.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        str_id = al_id.get(al_id.size()-position-1);
                        date = al_date.get(al_id.size()-position-1);
                        category = al_category.get(al_id.size()-position-1);
                        desc = al_description.get(al_id.size()-position-1);
                        location = al_location.get(al_id.size()-position-1);
                        contact = al_contact.get(al_id.size()-position-1);
                        username = al_username.get(al_id.size()-position-1);

                        Intent myIntent = new Intent(LostAndFoundActivity.this, MapsActivity.class);
                        startActivity(myIntent);

                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_lost_and_found,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id==R.id.action_backfrom_lnf) {
            Intent intent = new Intent("com.example.sunsh.connectnus.MenuActivity");
            startActivity(intent);
        }
        else if (res_id==R.id.action_refresh_lnf) {
            try {
                listView();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if (res_id==R.id.action_search_lnf) {

            //redirect to another page
            AlertDialog.Builder builder = new AlertDialog.Builder(LostAndFoundActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_search, null);
            final EditText search_date = (EditText) view.findViewById(R.id.editTextSearchDate);
            final EditText search_category = (EditText) view.findViewById(R.id.editTextSearchCategory);
            Button btn_search = (Button) view.findViewById(R.id.buttonlnfSearch);
            btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String process_date = search_date.getText().toString();
                    String process_category = search_category.getText().toString();
                    try {
                        listView_2(process_date,process_category);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        return true;
    }
}
