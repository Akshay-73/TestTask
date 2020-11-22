package com.elfstudio.testtask.api.retro;

import com.elfstudio.testtask.model.SearchedData;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetroApi {

    @GET("search_by_date?tags=story&page=1")
    Observable<SearchedData> getSearchedData();
}
