package com.quandoo.reservoir.view.presenter;

import android.content.Intent;
import android.util.Log;

import com.quandoo.reservoir.GreetActivity;
import com.quandoo.reservoir.TableChooserActivity;
import com.quandoo.reservoir.model.Customer;
import com.quandoo.reservoir.rest.ReservoirService;
import com.quandoo.reservoir.util.DatabaseUtils;
import com.quandoo.reservoir.view.adapter.CustomerAdapter;

import org.parceler.Parcels;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ercanozcan on 04/09/16.
 */
public class GreetActivityPresenter implements Presenter {

    private GreetActivity activity;
    public static final String DEBUG_TAG = "GreetActivityPresenter";

    public GreetActivityPresenter(GreetActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        activity = null;
    }

    @Override
    public void onCreate() {
        populateCustomerList();
    }

    /**
     * to populate the recyclerview
     */
    public void populateCustomerList() {
        ReservoirService.getInstance().getRestaurantService().customers().enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if (response.isSuccessful()) {
                    DatabaseUtils.insertOrUpdateCustomersToDatabase(response.body());
                    if (activity.getCustomerAdapter() == null) {
                        activity.setCustomerAdapter(new CustomerAdapter(DatabaseUtils.getCustomersFromDatabase(),GreetActivityPresenter.this));
                    } else {
                        activity.getCustomerAdapter().setCustomers(DatabaseUtils.getCustomersFromDatabase());
                        activity.getCustomerAdapter().notifyDataSetChanged();
                    }
                } else {
                    Log.e(DEBUG_TAG, response.message());
                    activity.getCustomerAdapter().setCustomers(DatabaseUtils.getCustomersFromDatabase());
                    activity.getCustomerAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Log.e(DEBUG_TAG, "Showing outdated data!");
                activity.getCustomerAdapter().setCustomers(DatabaseUtils.getCustomersFromDatabase());
                activity.getCustomerAdapter().notifyDataSetChanged();
            }
        });
    }

    public void onCustomerItemClick(Customer customer){
        Intent intent = new Intent(activity, TableChooserActivity.class);
        intent.putExtra(TableChooserActivity.EXTRA_CUSTOMER, Parcels.wrap(customer));
        activity.startActivity(intent);
    }
}
