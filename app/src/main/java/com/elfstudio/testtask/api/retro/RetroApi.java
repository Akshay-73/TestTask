package com.elfstudio.testtask.api.retro;

import com.elfstudio.testtask.model.SearchedData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroApi {

    @GET("search_by_date")
    Observable<SearchedData> getSearchedData(@Query("tags") String story, @Query("page") int page);
}
