package com.elfstudio.testtask.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.elfstudio.testtask.application.App;
import com.elfstudio.testtask.model.SearchedData;
import com.elfstudio.testtask.utils.ApiUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private final Application application;
    private MutableLiveData<String> liveMessage;
    private MutableLiveData<SearchedData> searchedDataMutableLiveData;
    private Map<Integer, Boolean> checks;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public Map<Integer, Boolean> getChecks() {
        if (checks == null) {
            checks = new HashMap<>();
        }
        return checks;
    }

    public MutableLiveData<String> getLiveMessage() {
        if (liveMessage == null) {
            liveMessage = new MutableLiveData<>();
        }
        return liveMessage;
    }

    MutableLiveData<SearchedData> getData() {
        searchedDataMutableLiveData = new MutableLiveData<>();
        if (ApiUtils.checkInternet(application))
            App.getInstance().getApi()
                    .getSearchedData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.single())
                    .subscribe(new Observer<SearchedData>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull SearchedData searchedData) {
                            searchedDataMutableLiveData.postValue(searchedData);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                            getLiveMessage().postValue(e.getLocalizedMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        else {
            getLiveMessage().postValue("Internet Is Not Connected");
        }
        return searchedDataMutableLiveData;
    }
}
