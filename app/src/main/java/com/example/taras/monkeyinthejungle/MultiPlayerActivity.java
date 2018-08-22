package com.example.taras.monkeyinthejungle;

import android.app.ListActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MultiPlayerActivity extends AppCompatActivity {
    private ListView listView;
    private String[] values;
    private EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
        input = findViewById(R.id.edt_lobby_name_field);
        listView = (ListView) findViewById(R.id.lobby_list);
        values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2"};  // replace this with real stuff

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                System.out.println(values[position]);
            }
        });
        Button newLobby = findViewById(R.id.btn_create_lobby);
        Button search = findViewById(R.id.btn_search_lobby);
        newLobby.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                System.out.println(getText());
            }
        });
        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                System.out.println(getText());
            }
        });

    }
    private String getText() {
     return input.getText().toString();
    }
}
