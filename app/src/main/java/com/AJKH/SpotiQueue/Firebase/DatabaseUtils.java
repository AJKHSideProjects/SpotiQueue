package com.AJKH.SpotiQueue.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtils {
    private static DatabaseUtils ourInstance;
    public static DatabaseUtils getInstance() {
        if(ourInstance == null) {
            ourInstance = new DatabaseUtils();
        }
        return ourInstance;
    }
    private DatabaseUtils() {
        loadActiveSessions();
    }

    private Collection<String> activeSessions = new ArrayList<>();
    private DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

    public void createNewSession(String sessionName) {
        mFirebaseDatabaseReference.child("activeSessions").child(sessionName).setValue(sessionName);
    }

    public void loadActiveSessions() {
        mFirebaseDatabaseReference.child("activeSessions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Map<String, String> td = (HashMap<String,String>) snapshot.getValue();
                activeSessions = td.values();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public String[] getActiveSessions() {
         return activeSessions.toArray(new String[0]);
    }
}
