package com.example.fanetworking;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("search_all_teams.php?l=English%20Premier%20League")
    Call<DataResponse> getData();

}
