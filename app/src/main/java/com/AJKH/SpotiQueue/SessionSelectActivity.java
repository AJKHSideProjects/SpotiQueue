package com.AJKH.SpotiQueue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.AJKH.SpotiQueue.Firebase.DatabaseUtils;
import com.AJKH.SpotiQueue.Firebase.SignInActivity;
import com.AJKH.SpotiQueue.Fragments.CreateNewSessionFragment;
import com.AJKH.SpotiQueue.Fragments.ExistingSessionsFragment;
import com.AJKH.SpotiQueue.Spotify.SpotifyHttpUtil;
import com.AJKH.SpotiQueue.Spotify.SpotifySignInActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SessionSelectActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private SharedPreferences mSharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_select);
        DatabaseUtils.getInstance();
        mSharedPreferences = getSharedPreferences(Constants.PROPERTIES, MODE_PRIVATE);
        // Initialize Firebase Auth
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("SessionSelectActivity", "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    public void viewActiveSessions(View view) {
        new ExistingSessionsFragment().show(getSupportFragmentManager(), "activeSessions");
    }

    public void createNewSession(View view) {
        if (mSharedPreferences.getString(Constants.SPOTIFY_AUTH_TOKEN, "").isEmpty()) {
            startActivity(new Intent(this, SpotifySignInActivity.class));
        } else {
            new CreateNewSessionFragment().show(getSupportFragmentManager(), "createNewSession");
        }
    }
}
