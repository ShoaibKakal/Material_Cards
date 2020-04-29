package com.shoaib.materialcard;

public class Sport {
    private String title;
    private String info;
    private int imageResource;

    public Sport(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public int getimageResource() {
        return imageResource;
    }
}
