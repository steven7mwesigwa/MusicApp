package com.stevenmwesigwa.musicapp;

public class Songs {
    private Long songId;
    private String songTitle;
    private String songArtist;
    private String songData;
    private  Long songDateAdded;

    public Songs(Long songId, String songTitle, String songArtist, String songData, Long songDateAdded) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songData = songData;
        this.songDateAdded = songDateAdded;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongData() {
        return songData;
    }

    public void setSongData(String songData) {
        this.songData = songData;
    }

    public Long getSongDateAdded() {
        return songDateAdded;
    }

    public void setSongDateAdded(Long songDateAdded) {
        this.songDateAdded = songDateAdded;
    }
}
