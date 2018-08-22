package com.example.taras.monkeyinthejungle;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FirebaseServices {

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
}
