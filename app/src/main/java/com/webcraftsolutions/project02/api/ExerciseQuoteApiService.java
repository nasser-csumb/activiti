package com.webcraftsolutions.project02.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExerciseQuoteApiService {
    @GET("api/quotes")
    Call<List<QuoteResponse>> getRandomQuote();
}
