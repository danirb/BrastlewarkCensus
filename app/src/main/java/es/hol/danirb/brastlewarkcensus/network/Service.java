package es.hol.danirb.brastlewarkcensus.network;

import es.hol.danirb.brastlewarkcensus.models.Brastlewark;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by dani on 14/12/15.
 */
interface Service {

    @GET("androidtest.json")
    Call<Brastlewark> repoVillage();



}

