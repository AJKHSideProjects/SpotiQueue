package com.AJKH.SpotiQueue;

public class SearchMessage {

    private String track;
    private String artist;
    private String name;
    private String photoUrl;

    public SearchMessage() {
    }

    public SearchMessage(String songText, String artistText, String name, String photoUrl) {
        this.track = songText;
        this.artist = artistText;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String text) {
        this.track = text;
    }

    public String getArtist() { return artist;}

    public void setArtist(String text) { this.artist = text;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
