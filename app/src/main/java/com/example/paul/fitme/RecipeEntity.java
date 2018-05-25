package com.example.paul.fitme;

import java.util.List;

/**
 * Created by Paul on 25/05/2018.
 */

public class RecipeEntity {
    private String image;
    private String url;
    private String name;

    public RecipeEntity(String image, String url, String name) {
        this.image = image;
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
