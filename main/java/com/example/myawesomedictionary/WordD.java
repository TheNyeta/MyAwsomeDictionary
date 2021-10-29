package com.example.myawesomedictionary;

//Name: Timothy Natan
//NIM: 2301870804

import java.io.Serializable;

public class WordD implements Serializable {

    private String word;
    private String imageurl;
    private String type;
    private String definition;

    public WordD(String word, String imageurl, String type, String definition) {
        this.word = word;
        this.imageurl = imageurl;
        this.type = type;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
