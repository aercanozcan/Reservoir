package com.quandoo.reservoir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.quandoo.reservoir.model.Customer;
import com.quandoo.reservoir.rest.ReservoirService;
import com.quandoo.reservoir.view.adapter.TableAdapter;
import com.quandoo.reservoir.view.presenter.TableChooserActivityPresenter;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableChooserActivity extends AppCompatActivity {
    public static final String DEBUG_TAG = "TableChooserActivity";
    public static final String EXTRA_CUSTOMER = "customer";

    @BindView(R.id.imgCustomer)
    public ImageView imgCustomer;
    @BindView(R.id.lblName)
    public TextView lblName;
    @BindView(R.id.lblSurname)
    public TextView lblSurname;
    @BindView(R.id.tableList)
    public RecyclerView tableList;

    private TableChooserActivityPresenter presenter;

    private TableAdapter tableAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_chooser);
        ButterKnife.bind(this);
        tableList.setLayoutManager(new GridLayoutManager(this,getResources().getInteger(R.integer.grid)));
        presenter = new TableChooserActivityPresenter(this);
        presenter.onCreate();
        prepareCustomerView();
    }

    /**
     * Populates customerView
     */
    private void prepareCustomerView() {
        Customer customer = Parcels.unwrap(getIntent().getParcelableExtra(TableChooserActivity.EXTRA_CUSTOMER));
        lblName.setText(customer.getFirstName());
        lblSurname.setText(customer.getLastName());
        int color = ColorGenerator.MATERIAL.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(customer.getFirstName().substring(0, 1), color);//the first letter of the customers name
        imgCustomer.setImageDrawable(drawable);
        Log.d(DEBUG_TAG, "onCreate()");
    }

    public TableAdapter getTableAdapter() {
        return tableAdapter;
    }

    public void setTableAdapter(TableAdapter tableAdapter) {
        this.tableAdapter = tableAdapter;
        tableList.setAdapter(tableAdapter);

    }
}
