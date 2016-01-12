package es.hol.danirb.brastlewarkcensus.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import es.hol.danirb.brastlewarkcensus.models.Brastlewark;
import es.hol.danirb.brastlewarkcensus.models.Gnome;
import es.hol.danirb.brastlewarkcensus.views.adapters.GnomeRecyclerAdapter;
import retrofit.Call;

/**
 * Created by dani on 9/01/16.
 */
public class GetCesus extends AsyncTask<Void, Context, List<Gnome>> {
    private final RecyclerView rv;
    private Context context;
    private ProgressDialog pDialog;
    private Brastlewark br;

    public GetCesus(Context context, RecyclerView rv) {
        this.context = context;
        this.rv = rv;
        this.pDialog = new ProgressDialog(context);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog.setMessage("Please wait.");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected List<Gnome> doInBackground(Void... params) {
        br = null;
        Service client = ServiceGenerator.createService(Service.class);
        Call<Brastlewark> call = client.repoVillage();
        try {
            br = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return br.getGnomeList();
    }

    @Override
    protected void onPostExecute(List<Gnome> aVoid) {
        pDialog.dismiss();
        GnomeRecyclerAdapter adapter = new GnomeRecyclerAdapter(aVoid, context);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public Brastlewark getBr() {
        return br;
    }
}
