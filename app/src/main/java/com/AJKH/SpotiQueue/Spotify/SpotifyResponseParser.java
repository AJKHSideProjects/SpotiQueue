package com.AJKH.SpotiQueue.Spotify;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SpotifyResponseParser {
    public JsonReader getFirstTrackObjectFromTrackSearchJsonResponse(InputStream in) throws IOException {
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

    public JsonReader readTracksObject(JsonReader reader) throws IOException {
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

    public JsonReader readItemsArray(JsonReader reader) throws IOException {
        reader.beginArray();
        return reader;
    }

    public String getTrackId(JsonReader trackObject) throws IOException {
        trackObject.beginObject();

        while (trackObject.hasNext()) {
            String name = trackObject.nextName();
            if (name.equals("id")) {
                return trackObject.nextString();
            } else {
                trackObject.skipValue();
            }
        }

        trackObject.close();
        return null;
    }

    public String getTrackName(JsonReader trackObject) throws  IOException {
        trackObject.beginObject();

        while (trackObject.hasNext()) {
            String name = trackObject.nextName();
            if(name.equals("name")) {
                return trackObject.nextString();
            } else {
                trackObject.skipValue();
            }
        }

        trackObject.close();
        return null;
    }
}
