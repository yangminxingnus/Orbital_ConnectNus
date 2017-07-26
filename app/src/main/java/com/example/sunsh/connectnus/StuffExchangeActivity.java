package com.example.sunsh.connectnus;

import android.app.AlertDialog;
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

public class StuffExchangeActivity extends AppCompatActivity {

    ListView lv_se;

    Button btn_submit;

    public static String str_id = "";
    public static String date = "";
    public static String category = "";
    public static String desc = "";
    public static String price = "";
    public static String pic_link = "";
    public static String address = "";
    public static String contact = "";
    public static String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff_exchange);

        btn_submit = (Button)findViewById(R.id.buttonSECreate);

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
                Intent intent = new Intent("com.example.sunsh.connectnus.SECreateActivity");
                startActivity(intent);
            }
        });
    }

    public void listView_2(String pros_category) throws ExecutionException, InterruptedException {

        lv_se = (ListView)findViewById(R.id.lvStuffExchange);

        String type = "se_search";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,pros_category);
        String result = backgroundWorker.get();

        if (result.equals("no result")) {
            Toast.makeText(StuffExchangeActivity.this, "There is no result", Toast.LENGTH_SHORT).show();
        }
        else {
            final String[] info_list = result.split("\t");

            final ArrayList<String> al_id = new ArrayList<String>();
            for (int i = 0; i < info_list.length; i = i + 9) al_id.add(info_list[i]);

            final ArrayList<String> al_date = new ArrayList<String>();
            for (int i = 1; i < info_list.length; i = i + 9) al_date.add(info_list[i]);

            final ArrayList<String> al_category = new ArrayList<String>();
            for (int i = 2; i < info_list.length; i = i + 9) al_category.add(info_list[i]);

            final ArrayList<String> al_description = new ArrayList<String>();
            for (int i = 3; i < info_list.length; i = i + 9) al_description.add(info_list[i]);

            final ArrayList<String> al_price = new ArrayList<String>();
            for (int i = 4; i < info_list.length; i = i + 9) al_price.add(info_list[i]);

            final ArrayList<String> al_piclink = new ArrayList<String>();
            for (int i = 5; i < info_list.length; i = i + 9) al_piclink.add(info_list[i]);

            final ArrayList<String> al_address = new ArrayList<String>();
            for (int i = 6; i < info_list.length; i = i + 9) al_address.add(info_list[i]);

            final ArrayList<String> al_contact = new ArrayList<String>();
            for (int i = 7; i < info_list.length; i = i + 9) al_contact.add(info_list[i]);

            final ArrayList<String> al_username = new ArrayList<String>();
            for (int i = 8; i < info_list.length; i = i + 9) al_username.add(info_list[i]);

            String[] show_list = new String[al_id.size()];
            for (int i = al_id.size() - 1; i >= 0; i--)
                show_list[al_id.size() - 1 - i] = "\n" + al_category.get(i) + "\n\n" + al_description.get(i) + "\n\n" + al_date.get(i) + "\n";

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.se_items_list, show_list);
            lv_se.setAdapter(adapter);
            Toast.makeText(StuffExchangeActivity.this, al_id.size()+" items found", Toast.LENGTH_SHORT).show();
            lv_se.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            str_id = al_id.get(al_id.size() - position - 1);
                            date = al_date.get(al_id.size() - position - 1);
                            category = al_category.get(al_id.size() - position - 1);
                            desc = al_description.get(al_id.size() - position - 1);
                            price = al_price.get(al_id.size() - position - 1);
                            pic_link = al_piclink.get(al_id.size() - position - 1);
                            address = al_address.get(al_id.size() - position - 1);
                            contact = al_contact.get(al_id.size() - position - 1);
                            username = al_username.get(al_id.size() - position - 1);

                            Intent myIntent = new Intent("com.example.sunsh.connectnus.SeItemsActivity");
                            startActivity(myIntent);

                        }
                    }
            );
        }
    }

    public void listView() throws ExecutionException, InterruptedException {
        lv_se = (ListView)findViewById(R.id.lvStuffExchange);

        String type = "se_info";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);
        String result = backgroundWorker.get();
        final String[] info_list = result.split("\t");

        final ArrayList<String> al_id = new ArrayList<String>();
        for (int i = 0; i < info_list.length; i = i + 9) al_id.add(info_list[i]);

        final ArrayList<String> al_date = new ArrayList<String>();
        for (int i = 1; i < info_list.length; i = i + 9) al_date.add(info_list[i]);

        final ArrayList<String> al_category = new ArrayList<String>();
        for (int i = 2; i < info_list.length; i = i + 9) al_category.add(info_list[i]);

        final ArrayList<String> al_description = new ArrayList<String>();
        for (int i = 3; i < info_list.length; i = i + 9) al_description.add(info_list[i]);

        final ArrayList<String> al_price = new ArrayList<String>();
        for (int i = 4; i < info_list.length; i = i + 9) al_price.add(info_list[i]);

        final ArrayList<String> al_piclink = new ArrayList<String>();
        for (int i = 5; i < info_list.length; i = i + 9) al_piclink.add(info_list[i]);

        final ArrayList<String> al_address = new ArrayList<String>();
        for (int i = 6; i < info_list.length; i = i + 9) al_address.add(info_list[i]);

        final ArrayList<String> al_contact = new ArrayList<String>();
        for (int i = 7; i < info_list.length; i = i + 9) al_contact.add(info_list[i]);

        final ArrayList<String> al_username = new ArrayList<String>();
        for (int i = 8; i < info_list.length; i = i + 9) al_username.add(info_list[i]);

        String[] show_list = new String[al_id.size()];
        for (int i=al_id.size()-1;i>=0;i--) show_list[al_id.size()-1-i]="\n"+al_category.get(i)+"\n\n"+al_description.get(i)+"\n\n"+al_date.get(i)+"\n";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.se_items_list,show_list);
        lv_se.setAdapter(adapter);
        lv_se.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        str_id = al_id.get(al_id.size() - position - 1);
                        date = al_date.get(al_id.size() - position - 1);
                        category = al_category.get(al_id.size() - position - 1);
                        desc = al_description.get(al_id.size() - position - 1);
                        price = al_price.get(al_id.size() - position - 1);
                        pic_link = al_piclink.get(al_id.size() - position - 1);
                        address = al_address.get(al_id.size() - position - 1);
                        contact = al_contact.get(al_id.size() - position - 1);
                        username = al_username.get(al_id.size() - position - 1);

                        Intent myIntent = new Intent("com.example.sunsh.connectnus.SeItemsActivity");
                        startActivity(myIntent);

                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_stuff_exchange,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id==R.id.action_backfrom_se) {
            Intent intent = new Intent("com.example.sunsh.connectnus.MenuActivity");
            startActivity(intent);
        }
        else if (res_id==R.id.action_refresh_se) {
            try {
                listView();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if (res_id==R.id.action_search_se) {

            //redirect to a layout page
            AlertDialog.Builder builder = new AlertDialog.Builder(StuffExchangeActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_se_search, null);
            final EditText search_category = (EditText) view.findViewById(R.id.editTextSESearchCategory);
            Button btn_search = (Button) view.findViewById(R.id.buttonSESearch);
            btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String process_category = search_category.getText().toString();
                    try {
                        listView_2(process_category);
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
