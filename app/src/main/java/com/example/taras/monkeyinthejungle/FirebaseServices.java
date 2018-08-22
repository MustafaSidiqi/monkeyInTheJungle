package com.example.taras.monkeyinthejungle;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class FirebaseServices {
    public MutableLiveData<ArrayList<Object>> liveLobbyUpdater;

    public FirebaseServices(){
        liveLobbyUpdater = new MutableLiveData<>();
    }

    public void helloFirebase() {// Create a new user with a first and last name
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Lobby lobby = new Lobby("Mustafa", "Terminator", false);

        // Add a new document with a generated ID
        db.collection("Lobbys").document().set(lobby)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void getLobbyNames() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Lobbies").document("PGismd8kH1ofTXOwdL8a").get().
            addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    liveLobbyUpdater.postValue((ArrayList<Object>) documentSnapshot.getData().get("ReadyLobby"));
                }
            });

    }

}
