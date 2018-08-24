package com.example.taras.monkeyinthejungle;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FirebaseServices {
    public MutableLiveData<ArrayList<Object>> liveLobbyUpdater;

    public FirebaseServices(){
        liveLobbyUpdater = new MutableLiveData<>();
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int score;
    private String collectionPath = "Multi";

    public void addLobby(String ln, String lo, Boolean gs, Map<String, Object> s, GameObject go) {
        Lobby lobby = new Lobby(ln, lo, gs, s, go);
        // Add a new document with a generated ID
        db.collection(collectionPath).document().set(lobby)
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
                    ArrayList<Object> result = documentSnapshot == null || documentSnapshot.getData() == null ?
                            new ArrayList<Object>() : (ArrayList<Object>) documentSnapshot.getData().get("ReadyLobby");
                    liveLobbyUpdater.postValue(result);
                }
            });

    }

    public void addUserScore(final String name, final int score, String lobbyId) {
        final DocumentReference document =  db.collection(collectionPath).document(lobbyId);
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Lobby l = documentSnapshot.toObject(Lobby.class);
                    l.addUserScore(name, score);
                    document.set(l);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void changeGameStart(final boolean val, String lobbyId) {
        final DocumentReference document =  db.collection(collectionPath).document(lobbyId);
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Lobby l = documentSnapshot.toObject(Lobby.class);
                    l.setStart(val);
                    document.set(l);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public Lobby getLobbyObject(String lobbyId) {
        final DocumentReference document =  db.collection(collectionPath).document(lobbyId);
        Lobby l = null;
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Lobby l = documentSnapshot.toObject(Lobby.class);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        return l;
    }
}
