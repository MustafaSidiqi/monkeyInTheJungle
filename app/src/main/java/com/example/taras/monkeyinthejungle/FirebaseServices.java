package com.example.taras.monkeyinthejungle;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FirebaseServices {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int score;

    public void helloFirebase() {// Create a new user with a first and last name

        Map<String, Object> score = new HashMap<>();
        score.put("Mustafa", 4);
        List<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(4);
        Lobby lobby = new Lobby("Taras", "John Wich", true, score, new GameObject(1,2,"Tap", list));

        // Add a new document with a generated ID
        db.collection("Multi").document().set(lobby)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void loadData () {
        final DocumentReference document =  db.collection("Multi").document("MpVxCM1uiAy9Dl4MM13q");

        Map<String, Object> score = new HashMap<>();

        score.put("V1", 100);
        score.put("Termin", 40);
        score.put("TerminV2", 4000);




        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Lobby l = documentSnapshot.toObject(Lobby.class);
                    l.addUserScore("Mustafa", 1123);
                    l.addUserScore("terminator", 8888);


                    document.set(l);


                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void setScore() {
        DocumentReference document =  db.collection("Multi").document("MpVxCM1uiAy9Dl4MM13q");

        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Lobby lobby = documentSnapshot.toObject(Lobby.class);
            }
        });



        Map<String, Integer> score = new HashMap<>();

        score.put("MustafaV2", 100);
        score.put("Termin", 40);
        score.put("TerminV2", 4000);
        db.collection("Multi").document("z07EN9vVldFoZfiOidWZ").update(
                "score", score
        );
    }
}
