package com.AJKH.SpotiQueue.Model;

public class Track {
    private String artist;
    private String track;
    private String name;
    private String photoUrl;

    public Track(String artist, String track, String name, String photoUrl) {
        this.artist = artist;
        this.track = track;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public Track() {
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
