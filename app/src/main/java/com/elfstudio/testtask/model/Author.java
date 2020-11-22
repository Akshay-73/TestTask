package com.elfstudio.testtask.model;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Author extends BaseObservable {
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("matchLevel")
    @Expose
    private String matchLevel;
    @SerializedName("matchedWords")
    @Expose
    private List<Object> matchedWords = null;

    public String getValue() {
        return value;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public List<Object> getMatchedWords() {
        if (matchedWords == null) {
            matchedWords = new ArrayList<>();
        }
        return matchedWords;
    }
}
