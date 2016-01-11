package es.hol.danirb.brastlewarkcensus.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.hol.danirb.brastlewarkcensus.R;
import es.hol.danirb.brastlewarkcensus.models.Gnome;


public class GnomeRecyclerAdapter extends RecyclerView.Adapter<GnomeRecyclerAdapter.GnomeViewHolder> {

    private List<Gnome> listGnomes ;

    public GnomeRecyclerAdapter(List<Gnome> listGnomes) {
      this.listGnomes = listGnomes;
    }

    @Override
    public GnomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gnome, parent, false);
       return new GnomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GnomeViewHolder holder, int position) {
      holder.gnomeName.setText(listGnomes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listGnomes.size();
    }

    public class GnomeViewHolder extends RecyclerView.ViewHolder {
       final TextView gnomeName;

        public GnomeViewHolder(View itemView) {
            super(itemView);
          gnomeName = (TextView) itemView.findViewById(R.id.gnome_name);
        }
    }
}
