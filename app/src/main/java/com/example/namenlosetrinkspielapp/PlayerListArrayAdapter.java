package com.example.namenlosetrinkspielapp;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerListArrayAdapter extends RecyclerView.Adapter<PlayerListArrayAdapter.ViewHolder>{

    private ArrayList<Player> mData;
    private LayoutInflater mInflater;
    private PlayerListArrayAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    PlayerListArrayAdapter(Context context, ArrayList<Player> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public PlayerListArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.players_list_item, parent, false);
        return new PlayerListArrayAdapter.ViewHolder(view);
    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(PlayerListArrayAdapter.ViewHolder holder, int position) {
        Player player = mData.get(position);
        holder.playerCount.setText("Player"+mData.get(position).getNumber());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView playerCount;
        EditText playerNameField;

        ViewHolder(View itemView) {
            super(itemView);

            playerCount = (TextView) itemView.findViewById(R.id.playerNumberView);
            playerNameField = (EditText) itemView.findViewById(R.id.playerNameField);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Player getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(PlayerListArrayAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

    /*
    public PlayerListArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Player> objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //the values are put in the list item
        View rowView = inflater.inflate(R.layout.players_list_item, parent, false);
        TextView playerCount = (TextView) rowView.findViewById(R.id.playerNumberView);
        EditText playerNameField = (EditText) rowView.findViewById(R.id.playerNameField);

        playerCount.setText("Player"+items.get(position).getNumber());
        //playerNameField.setText("Spieler"+items.get(position).getNumber());

        return rowView;
    }
     */

//}
