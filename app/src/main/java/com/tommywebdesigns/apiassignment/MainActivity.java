package com.tommywebdesigns.apiassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tommywebdesigns.apiassignment.databinding.ActivityMainBinding;
import com.tommywebdesigns.apiassignment.model.data.AnimeResponse;
import com.tommywebdesigns.apiassignment.model.data.AnimeResult;
import com.tommywebdesigns.apiassignment.model.network.AnimeRetrofit;
import com.tommywebdesigns.apiassignment.view.adapter.AnimeAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.tommywebdesigns.apiassignment.util.Constansts.ANIME_KEY;

public class MainActivity extends AppCompatActivity implements AnimeAdapter.OnAnimeListener{

    private ActivityMainBinding binding;
    //private AnimeAdapter adapter= new AnimeAdapter();
    private AnimeAdapter adapter= new AnimeAdapter(MainActivity.this::onAnimeClick);


    private AnimeRetrofit animeRetrofit= new AnimeRetrofit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.animeRv.setAdapter(adapter);

        animeRetrofit.getSearchResult("Vegeta")
                .enqueue(new Callback<AnimeResponse>() {
                    @Override
                    public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                        Log.d("TAG_X", ""+call.request().url());
                        adapter.setResults(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<AnimeResponse> call, Throwable t) {
                        Log.d("TAG_X", "OOPS:  :<"+call.request().url());
                    }
                });


    }// oncreate

    @Override
    public void onAnimeClick(int position) {
        AnimeResult animeResult= adapter.getAnime(position);
         Intent intent= new Intent(this, AnimeDetailsActivity.class);
         intent.putExtra(ANIME_KEY, animeResult);
        startActivity(intent);
       Log.d(TAG, "onNoteClick: clicked. " + position + "Anime: " +animeResult.getTitle());

    }
}