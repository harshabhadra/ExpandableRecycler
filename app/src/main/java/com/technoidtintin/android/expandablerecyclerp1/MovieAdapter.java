package com.technoidtintin.android.expandablerecyclerp1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private LayoutInflater inflater;
    List<listItem>listItems = new ArrayList<>();

    public MovieAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(inflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {

        final listItem item = listItems.get(position);
        holder.bind(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean expanded = item.isExpanded();
                item.setExpanded(!expanded);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e("MovieAdapter", "Size: " + listItems.size());
        return listItems.size();

    }

    public void setListItems(List<listItem> listItem) {
        listItems = listItem;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView releaseDate;
        Button button;
        LinearLayout subItem;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_movie);
            releaseDate = itemView.findViewById(R.id.release_date);
            button = itemView.findViewById(R.id.details_button);
            subItem = itemView.findViewById(R.id.sub_item);
        }

        private void bind(listItem Item){

            boolean expanded = Item.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            title.setText(Item.getTitle());
            releaseDate.setText(Item.getRelease());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(),DetailsActivity.class);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
