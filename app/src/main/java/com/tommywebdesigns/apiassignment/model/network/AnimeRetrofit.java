package com.tommywebdesigns.apiassignment.model.network;

import com.tommywebdesigns.apiassignment.model.data.AnimeResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.tommywebdesigns.apiassignment.util.Constansts.*;

public class AnimeRetrofit {
   //It creates the implementation of the AnimeService interface under the hood.
    private AnimeService animeService= createRetrofit().create(AnimeService.class);

    private Retrofit createRetrofit(){

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public Call<AnimeResponse> getSearchResult(String query){
        return animeService.searchAnime(query);
    }

    interface AnimeService{
        @GET(END_POINT)
        public Call<AnimeResponse> searchAnime(@Query(SEARCH_QUERY) String query);

    }
}
