package com.quandoo.reservoir.view.presenter;

/**
 * Created by ercanozcan on 03/09/16.
 */
public interface Presenter {
    void onPause();
    void onResume();
    void onDestroy();
    void onCreate();
}
