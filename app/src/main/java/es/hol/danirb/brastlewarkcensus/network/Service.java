package es.hol.danirb.brastlewarkcensus.network;

import es.hol.danirb.brastlewarkcensus.models.Brastlewark;
import es.hol.danirb.brastlewarkcensus.models.Village;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by dani on 14/12/15.
 */
public interface Service {

    @GET("androidtest.json")
    Call<Brastlewark> repoVillage();



}

