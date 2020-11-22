package com.elfstudio.testtask.model;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchedData extends BaseObservable {
    @SerializedName("hits")
    @Expose
    private List<Hit> hits = null;
    @SerializedName("nbHits")
    @Expose
    private Integer nbHits;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("nbPages")
    @Expose
    private Integer nbPages;
    @SerializedName("hitsPerPage")
    @Expose
    private Integer hitsPerPage;
    @SerializedName("exhaustiveNbHits")
    @Expose
    private Boolean exhaustiveNbHits;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("params")
    @Expose
    private String params;
    @SerializedName("processingTimeMS")
    @Expose
    private Integer processingTimeMS;

    public List<Hit> getHits() {
        if (hits == null) {
            hits = new ArrayList<>();
        }
        return hits;
    }

    public Integer getNbHits() {
        return nbHits;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getNbPages() {
        return nbPages;
    }

    public Integer getHitsPerPage() {
        return hitsPerPage;
    }

    public Boolean getExhaustiveNbHits() {
        return exhaustiveNbHits;
    }

    public String getQuery() {
        return query;
    }

    public String getParams() {
        return params;
    }

    public Integer getProcessingTimeMS() {
        return processingTimeMS;
    }
}
