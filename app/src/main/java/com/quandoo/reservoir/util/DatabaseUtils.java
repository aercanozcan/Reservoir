package com.quandoo.reservoir.util;

import com.quandoo.reservoir.model.Customer;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by ercanozcan on 04/09/16.
 */
public class DatabaseUtils {

    public static void insertOrUpdateCustomersToDatabase(List<Customer> customers){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(customers);
        realm.commitTransaction();
    }

    public static List<Customer> getCustomersFromDatabase(){
        RealmQuery<Customer> query = Realm.getDefaultInstance().where(Customer.class);
        return query.findAll();
    }

    public static boolean isReserved(int tableNumber){
        if(tableNumber<0) {
            return false;//no haxors!
        }
      return   Realm.getDefaultInstance()
              .where(Customer.class)
              .equalTo("reservedTableNumber",tableNumber)
              .findAll().size()
              >0;
    }
}
