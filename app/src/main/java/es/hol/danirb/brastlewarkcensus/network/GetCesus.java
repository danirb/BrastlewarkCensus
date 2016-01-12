package es.hol.danirb.brastlewarkcensus.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import es.hol.danirb.brastlewarkcensus.models.Brastlewark;
import es.hol.danirb.brastlewarkcensus.models.Gnome;
import es.hol.danirb.brastlewarkcensus.views.GnomeDetails;
import es.hol.danirb.brastlewarkcensus.views.adapters.GnomeRecyclerAdapter;
import retrofit.Call;

/**
 * Created by dani on 9/01/16.
 */
public class GetCesus extends AsyncTask<Void, Context, List<Gnome>> {
    private RecyclerView rv;
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
            writeFile(br.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return br.getGnomeList();
    }

    private void writeFile(String str) throws IOException {
        FileWriter fw = new FileWriter(Environment.getExternalStorageDirectory().getPath() + "/census/");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(str);
        bw.close();
    }

    @Override
    protected void onPostExecute(final List<Gnome> aVoid) {
        pDialog.dismiss();
        GnomeRecyclerAdapter adapter = new GnomeRecyclerAdapter(aVoid, context);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, GnomeDetails.class);
                i.putExtra("gnome", aVoid.get(rv.getChildAdapterPosition(v)));
                context.startActivity(i);
            }
        });

    }

    public Brastlewark getBr() {
        return br;
    }
}
