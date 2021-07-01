package com.tommywebdesigns.apiassignment.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tommywebdesigns.apiassignment.databinding.AnimeListItemBinding;
import com.tommywebdesigns.apiassignment.model.data.AnimeResult;

import java.util.ArrayList;
import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AmineViewHolder> {

    private List<AnimeResult> results = new ArrayList<>();
    private OnAnimeListener onAnimeListener;

    public AnimeAdapter(){} //empty constructor

    public AnimeAdapter(OnAnimeListener onAnimeListener){
        this.onAnimeListener=onAnimeListener;
    }

    public void setResults(List<AnimeResult> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    public AnimeResult getAnime(int position){
        return results.get(position);
    }

    @NonNull
    @Override
    public AmineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AnimeListItemBinding binding = AnimeListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new AmineViewHolder(binding, onAnimeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeAdapter.AmineViewHolder holder, int position) {
        AnimeResult result = results.get(position);
        Glide.with(holder.itemView)
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(result.getImageUrl())
                .into(holder.binding.posterImageview);
        holder.binding.synopsisTv.setText(result.getSynopsis());
        holder.binding.titleTv.setText(result.getTitle());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class AmineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // ImageView poster; old school of doing it.
        AnimeListItemBinding binding;
        OnAnimeListener onAnimeListener;

        public AmineViewHolder(AnimeListItemBinding binding, OnAnimeListener onAnimeListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onAnimeListener=onAnimeListener;
            // poster= itemView.findViewById(R.id.poster_imageview)
            //This attach a listener to the whole view. (this) refers to the interface implemented
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           onAnimeListener.onAnimeClick(getAdapterPosition());
        }
    }

    //When a click is detedted in a recycler view; the best practice way is to use an interface and use that interface
    //inside my/this view holder class
    public interface OnAnimeListener{
        //This interface is used to detect the click. It's used in the main activity, to send that position of that item.
        void onAnimeClick(int position);
    }
}//anime adaptor
