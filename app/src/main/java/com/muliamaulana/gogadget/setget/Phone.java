package com.muliamaulana.gogadget.setget;

/**
 * Created by muliamaulana on 10/30/16.
 */

public class Phone {
    private String title;
    private String image;
    private String slug;

    public Phone(){

    }

    public Phone(String title, String image, String slug) {
        this.title = title;
        this.image = image;
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
