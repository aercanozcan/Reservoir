package com.quandoo.reservoir.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.quandoo.reservoir.R;
import com.quandoo.reservoir.model.Customer;
import com.quandoo.reservoir.view.presenter.GreetActivityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ercanozcan on 03/09/16.
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {


    private GreetActivityPresenter presenter;
    private List<Customer> customers;

    public CustomerAdapter(List<Customer> customers, GreetActivityPresenter presenter) {
        this.customers = customers;
        this.presenter = presenter;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_customer_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.lblName.setText(customers.get(position).getFirstName());
        holder.lblSurname.setText(customers.get(position).getLastName());
        int color = ColorGenerator.MATERIAL.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(customers.get(position).getFirstName().substring(0, 1), color);//the first letter of the customers name
        holder.imgCustomer.setImageDrawable(drawable);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onCustomerItemClick(customers.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lblName)
        TextView lblName;
        @BindView(R.id.lblSurname)
        TextView lblSurname;
        @BindView(R.id.imgCustomer)
        ImageView imgCustomer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
