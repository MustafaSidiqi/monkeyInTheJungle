package com.example.taras.monkeyinthejungle;

import android.app.ListActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MultiPlayerActivity extends AppCompatActivity {
    private ListView listView;
    private String[] values;
    private String[] defValues;
    private EditText input;
    private String[] lobbyIds;
    private String[] defLobbyIds;
    private FirebaseServices fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fb = new FirebaseServices();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
        input = findViewById(R.id.edt_lobby_name_field);
        listView = (ListView) findViewById(R.id.lobby_list);
        fb.getLobbyNames();
        fb.liveLobbyUpdater.observe(this, new android.arch.lifecycle.Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Object> result) {
                System.out.println(result);
                int i = 0;
                defLobbyIds = new String[result.size()];
                defValues = new String[result.size()];

                for (Object O: result) {
                    defLobbyIds[i] = ((HashMap<String, String>)O).get("LobbyId");
                    defValues[i] = ((HashMap<String, String>)O).get("LobbyName");
                    i++;
                }
                values = defValues;
                lobbyIds =defLobbyIds;
                setUpList();
            }
        });


        Button newLobby = findViewById(R.id.btn_create_lobby);
        Button search = findViewById(R.id.btn_search_lobby);
        newLobby.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String text = getText();
                if(text == "" ) { return;}
                text = text.trim();
                GamePlan.getGameLogic().newGame();
                GamePlan.getGameLogic().IsGameOwner = true;
                GamePlan.getGameLogic().IsMultiplayer = true;
                HashMap<String, Object> scoreMap = new HashMap<>();
                scoreMap.put("TEST", 0);
                fb.addLobby(text, "TEST", false, scoreMap, GamePlan.getGameLogic().getLobyGames() );
            }
        });
        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String text = getText();
                text = (text.trim()).toLowerCase();
                if(text == "" ) {
                    values = defValues;
                    lobbyIds = defLobbyIds;
                    return;
                }
                ArrayList<String> id = new ArrayList<>();
                ArrayList<String> name = new ArrayList<>();
                for(int i = 0; i < defValues.length; i++) {
                    if((defValues[i].toLowerCase()).contains(text) ||
                            (defLobbyIds[i].toLowerCase()).contains(text)) {
                        id.add(defLobbyIds[i]);
                        name.add(defValues[i]);
                    }
                }
                values = (String[])name.toArray(new String[0]);
                lobbyIds = (String[])id.toArray(new String[0]);
                setUpList();
            }
        });

    }

    private void setUpList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                System.out.println(lobbyIds[position]);
            }
        });
    }

    private String getText() {
     return (input.getText()).toString();
    }


}
