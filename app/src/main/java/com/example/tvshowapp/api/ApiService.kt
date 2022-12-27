package com.example.tvshowapp.api


import com.example.tvshowapp.model.PeopleImagesResponse
import com.example.tvshowapp.model.TvShowResponse
import com.example.tvshowapp.util.Constanst
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constanst.END_POINT)
    suspend fun getTvShows():Response<TvShowResponse>
    @GET(Constanst.PEOPLE_POINT)
    suspend fun getPeople() : Response<PeopleImagesResponse>
}