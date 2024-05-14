package com.example.fanetworking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.HolderData>{

    List<Team> listData;
    LayoutInflater inflater;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;

    // Add a setter for the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyAdapter(Context context, List<Team> listData) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyAdapter.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_data, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.HolderData holder, int position) {
        Team team = listData.get(position);
        holder.textName.setText(team.getStrTeam());
        holder.textID.setText(team.getIdTeam());

        Glide.with(holder.itemView.getContext())
                .load(team.getStrTeamBadge())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{

        TextView textName;
        TextView textID;
        ImageView imageView;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.teamNameView);
            textID = itemView.findViewById(R.id.teamIDView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
