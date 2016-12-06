package com.AJKH.SpotiQueue.Spotify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.AJKH.SpotiQueue.MainActivity;
import com.AJKH.SpotiQueue.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.FirebaseDatabase;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SpotifySignInActivity extends Activity {
    private static final String SPOTIFY_CLIENT_ID = "d70f8e7b83274fa0a9e47fa0b3a49107";
    private static final String SPOTIFY_REDIRECT_URI = "spotiqueue://callback";
    private static final int REQUEST_CODE = 123;
    private String SPOTIFY_AUTH_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(SPOTIFY_CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                SPOTIFY_REDIRECT_URI);
        String[] userScopes = new String[] {
                "user-read-private", "streaming", "playlist-modify-public", "playlist-modify-private"
        };
        builder.setScopes(userScopes);
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                SPOTIFY_AUTH_TOKEN = response.getAccessToken();
                getUserInformation();
            }
        }
    }

    protected void getUserInformation() {
        String searchUrl = "https://api.spotify.com/v1/me";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.GET, searchUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        InputStream spotifyResponse = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));
                        JsonReader reader;
                        try {
                            reader = new JsonReader(new InputStreamReader(spotifyResponse, "UTF-8"));
                            reader.beginObject();
                            while (reader.hasNext()) {
                                String name = "";
                                JsonToken token = reader.peek();
                                if (token == JsonToken.NULL) {
                                } else if (token == JsonToken.STRING) {
                                    name = reader.nextString();
                                } else if (token == JsonToken.NAME) {
                                    name = reader.nextName();
                                }

                                if (name.equals("id")) {
                                    Intent spotifySuccessLogin = new Intent(getApplicationContext(), MainActivity.class);
                                    spotifySuccessLogin.putExtra("SPOTIFY_AUTH_TOKEN", SPOTIFY_AUTH_TOKEN);
                                    spotifySuccessLogin.putExtra("SPOTIFY_USERNAME", reader.nextString());
                                    spotifySuccessLogin.putExtra("SESSION_ROLE", "OWNER");

                                    startActivity(spotifySuccessLogin);
                                } else {
                                    reader.skipValue();
                                }
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + SPOTIFY_AUTH_TOKEN);

                return params;
            }
        };
        queue.add(postRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

