package com.AJKH.SpotiQueue.Spotify;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SpotifyResponseParser {
    public String readSpotifyJsonResponse(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            reader.beginObject();
            while (reader.hasNext()){
                String name = reader.nextName();
                if (name.equals("tracks")){
                    return readTracksObject(reader);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String readTracksObject(JsonReader reader) throws IOException {
        reader.beginObject();
        reader.skipValue();
        reader.skipValue();

        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("items")){
                return readItemsArray(reader);
            }
        }

        return null;
    }

    public String readItemsArray(JsonReader reader) throws IOException {
        reader.beginArray();
        return getSongId(reader);
    }

    public String getSongId(JsonReader reader) throws IOException {
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                return reader.nextString();
            } else {
                reader.skipValue();
            }
        }

        reader.close();
        return null;
    }
}
