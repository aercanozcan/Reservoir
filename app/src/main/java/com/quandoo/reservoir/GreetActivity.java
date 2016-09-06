package com.quandoo.reservoir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quandoo.reservoir.view.adapter.CustomerAdapter;
import com.quandoo.reservoir.view.presenter.GreetActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GreetActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "GreetActivity";

    @BindView(R.id.customerList)
    public RecyclerView customerList;

    private CustomerAdapter customerAdapter;

    private GreetActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greet);
        ButterKnife.bind(this);
        customerList.setLayoutManager(new LinearLayoutManager(this));
        presenter = new GreetActivityPresenter(this);
        presenter.onCreate();
    }

    public CustomerAdapter getCustomerAdapter() {
        return customerAdapter;
    }

    public void setCustomerAdapter(CustomerAdapter customerAdapter) {
        this.customerAdapter = customerAdapter;
        customerList.setAdapter(customerAdapter);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        presenter = null;
        super.onDestroy();
    }
}
