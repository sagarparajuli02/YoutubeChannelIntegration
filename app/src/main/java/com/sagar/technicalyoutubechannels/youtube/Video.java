package com.sagar.technicalyoutubechannels.youtube;


public class Video {
    private String videoId;
    private String videoThumbnail;
    private String videoTitle;


    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Video(String videoTitle, String videoThumbnail, String videoId) {
        super();
        this.videoTitle = videoTitle;
        this.videoThumbnail = videoThumbnail;
        this.videoId = videoId;
    }
    public Video(){
        super();
    }

    @Override
    public String toString() {
        return "Video [videoTitle=" + this.videoTitle + ", videoThumbnail=" + this.videoThumbnail + ", videoId=" + this.videoId + "]";
    }
}
