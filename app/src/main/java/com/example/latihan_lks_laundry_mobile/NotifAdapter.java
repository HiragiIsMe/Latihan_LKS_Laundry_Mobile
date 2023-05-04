package com.example.latihan_lks_laundry_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.ViewHolder> {
    List<Notif> notifs;
    Context ctx;
    public NotifAdapter(List<Notif> notifs, Context ctx){
        this.notifs = notifs;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public NotifAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.notif_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifAdapter.ViewHolder holder, int position) {
        holder.pack.setText(notifs.get(position).getPack() + " Has Been Completed");
        holder.datetime.setText(notifs.get(position).getDatetime());
    }

    @Override
    public int getItemCount() {
        return notifs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView pack, datetime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pack = itemView.findViewById(R.id.pack);
            datetime = itemView.findViewById(R.id.datetime);
        }
    }
}
