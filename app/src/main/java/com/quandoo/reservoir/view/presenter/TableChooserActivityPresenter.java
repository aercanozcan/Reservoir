package com.quandoo.reservoir.view.presenter;

import android.util.Log;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.quandoo.reservoir.TableChooserActivity;
import com.quandoo.reservoir.model.Customer;
import com.quandoo.reservoir.rest.ReservoirService;
import com.quandoo.reservoir.util.DatabaseUtils;
import com.quandoo.reservoir.view.adapter.CustomerAdapter;
import com.quandoo.reservoir.view.adapter.TableAdapter;

import org.parceler.Parcels;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ercanozcan on 04/09/16.
 */
public class TableChooserActivityPresenter implements Presenter {

    public static final String DEBUG_TAG = "TableChooserAcPresenter";
    private TableChooserActivity activity;

    public TableChooserActivityPresenter(TableChooserActivity activity) {
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

    }
    @Override
    public void onCreate() {
        populateTableGrid();
    }

    public void populateTableGrid(){
        ReservoirService.getInstance().getRestaurantService().tables().enqueue(new Callback<List<Boolean>>() {
            @Override
            public void onResponse(Call<List<Boolean>> call, Response<List<Boolean>> response) {

                if (response.isSuccessful()) {
                    if (activity.getTableAdapter() == null) {
                        activity.setTableAdapter(new TableAdapter(response.body()));
                    } else {
                        activity.getTableAdapter().setTables(response.body());
                        activity.getTableAdapter().notifyDataSetChanged();
                    }
                } else {
                    Log.d(DEBUG_TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Boolean>> call, Throwable t) {

            }
        });
    }

}
