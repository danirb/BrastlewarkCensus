package es.hol.danirb.brastlewarkcensus.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.hol.danirb.brastlewarkcensus.R;
import es.hol.danirb.brastlewarkcensus.adapters.GnomeRecyclerAdapter;
import es.hol.danirb.brastlewarkcensus.models.Brastlewark;
import es.hol.danirb.brastlewarkcensus.models.Gnome;
import es.hol.danirb.brastlewarkcensus.models.Village;
import es.hol.danirb.brastlewarkcensus.network.Service;
import es.hol.danirb.brastlewarkcensus.network.ServiceGenerator;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity{


    private List<Gnome> gnomeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Service client = ServiceGenerator.createService(Service.class);
        Call<Brastlewark> call = client.repoVillage();

        call.enqueue(new Callback<Brastlewark>() {
            @Override
            public void onResponse(Response<Brastlewark> response, Retrofit retrofit) {
                Log.d("JSON", response.body().toString());
                if (response.isSuccess()) {
                    Brastlewark br = response.body();
                    gnomeList = br.getGnomeList();
                        System.out.print(response.toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        RecyclerView mRecycler = (RecyclerView) findViewById(R.id.rv_gnomelist);
        GnomeRecyclerAdapter adapter = new GnomeRecyclerAdapter(gnomeList);
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }




}
