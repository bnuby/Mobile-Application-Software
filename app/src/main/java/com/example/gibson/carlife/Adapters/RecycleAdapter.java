package com.example.gibson.carlife.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gibson.carlife.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private List<String> mData;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTV, introTV, priceTV;
        ImageView img;
        public ViewHolder(View v) {
            super(v);
            titleTV = (TextView) v.findViewById(R.id.titleTV);
            introTV = (TextView) v.findViewById(R.id.introTV);
            priceTV = (TextView) v.findViewById(R.id.priceTV);
            img =v.findViewById(R.id.img);
        }
    }

    public RecycleAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        mData = data;
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclelayout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.titleTV.setText("All new car");
        holder.introTV.setText("carcarrcarcar");
        holder.img.setImageResource(R.mipmap.ic_launcher);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "Item " + position + " is long clicked.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
