package com.tommywebdesigns.apiassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tommywebdesigns.apiassignment.databinding.ActivityAnimeDetailsBinding;
import com.tommywebdesigns.apiassignment.databinding.AnimeListItemBinding;
import com.tommywebdesigns.apiassignment.model.data.AnimeResult;

import static com.tommywebdesigns.apiassignment.util.Constansts.ANIME_KEY;

public class AnimeDetailsActivity extends AppCompatActivity {

     ActivityAnimeDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAnimeDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        populateAnimeDetails(); //It creates and populates the details of the anime selected.

        binding.backBt.setOnClickListener(v -> {
            Intent intent= new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }//onCreate

    private void populateAnimeDetails(){
        AnimeResult animeResult= getIntent().getParcelableExtra(ANIME_KEY);
        Glide.with(binding.animeIv)
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(animeResult.getImageUrl())
                .into(binding.animeIv);
        binding.titleTv.setText("Title: " + animeResult.getTitle());
        binding.synopsisTv.setText("Synopis: " + animeResult.getTitle());
        binding.airingTv.setText("Release Date: " + animeResult.getStartDate());
        binding.ratedTv.setText("Rated: " + animeResult.getRated());
    }
}