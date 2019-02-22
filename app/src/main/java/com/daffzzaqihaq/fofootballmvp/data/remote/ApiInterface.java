package com.daffzzaqihaq.fofootballmvp.data.remote;

import com.daffzzaqihaq.fofootballmvp.model.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/v1/json/1/search_all_teams.php")
    Call<TeamResponse> getAllTeams(@Query("s")String s, @Query("c")String c);
}
