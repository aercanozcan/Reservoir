package com.quandoo.reservoir.view.adapter;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.quandoo.reservoir.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ercanozcan on 04/09/16.
 */
public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<Boolean> tables;

    public TableAdapter(List<Boolean> tables) {
        this.tables = tables;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_table_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resources resources = holder.itemView.getContext().getResources();
        if(tables.get(position)){
            holder.itemView.setBackgroundColor(resources.getColor(R.color.greenPositive));
        } else {
            holder.itemView.setBackgroundColor(resources.getColor(R.color.redNegative));
        }
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+ position,resources.getColor(R.color.transparent) );//the first letter of the customers name
        holder.imgTable.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgTable)
        ImageView imgTable;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public List<Boolean> getTables() {
        return tables;
    }

    public void setTables(List<Boolean> tables) {
        this.tables = tables;
    }
}
