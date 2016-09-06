package com.quandoo.reservoir;

import android.app.Activity;

import com.quandoo.reservoir.model.Customer;
import com.quandoo.reservoir.util.DatabaseUtils;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ercanozcan on 06/09/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class DatabaseUtilsTest {

    @Before
    public void prepareDatabase(){
        //RealmConfiguration realmConfig = new RealmConfiguration.Builder(Robolectric.setupActivity(Activity.class)).build();
       // Realm.setDefaultConfiguration(realmConfig);
    }

    @Test
    public void shouldReturnTrueIfTheTableIsReserved(){
        Realm realm = Realm.getDefaultInstance();
        Customer customer = new Customer();
        customer.setFirstName("Ercan");
        customer.setLastName("Ozcan");
        customer.setReservedTableNumber(2);
        realm.beginTransaction();
        realm.copyToRealm(customer);
        realm.commitTransaction();// we create a customer that booked table number 2
        Assert.assertTrue(DatabaseUtils.isReserved(2));
    }
}
