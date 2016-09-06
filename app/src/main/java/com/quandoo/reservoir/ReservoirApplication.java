package com.quandoo.reservoir;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ercanozcan on 04/09/16.
 */
public class ReservoirApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //preparing the realm database
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
