package com.quandoo.reservoir.model;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.CustomerRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ercanozcan on 03/09/16.
 */
@Parcel(implementations = { CustomerRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Customer.class })
public class Customer extends RealmObject {
    @SerializedName("id")
    @PrimaryKey
    private int id;
    @SerializedName("customerFirstName")
    private String firstName;
    @SerializedName("customerLastName")
    private String lastName;

    int reservedTableNumber = -1;// means none

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getReservedTableNumber() {
        return reservedTableNumber;
    }

    public void setReservedTableNumber(int reservedTableNumber) {
        this.reservedTableNumber = reservedTableNumber;
    }
}
