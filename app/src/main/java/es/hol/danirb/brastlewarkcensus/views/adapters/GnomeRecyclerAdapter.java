package es.hol.danirb.brastlewarkcensus.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.hol.danirb.brastlewarkcensus.R;
import es.hol.danirb.brastlewarkcensus.models.Gnome;


public class GnomeRecyclerAdapter extends RecyclerView.Adapter<GnomeRecyclerAdapter.GnomeViewHolder> implements View.OnClickListener {

    private final List<Gnome> listGnomes;
    private final Context mContext;
    private View.OnClickListener listener;

    public GnomeRecyclerAdapter(List<Gnome> listGnomes, Context context) {
        this.listGnomes = listGnomes;
        this.mContext = context;
    }

    @Override
    public GnomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gnome, parent, false);
        v.setOnClickListener(this);
        return new GnomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GnomeViewHolder holder, int position) {
        Gnome gnome = listGnomes.get(position);
        holder.gnomeName.setText(gnome.getName());
        holder.gnomeAge.setText(String.valueOf(gnome.getAge()));
        holder.gnomeWeight.setText(String.valueOf(gnome.getWeight()));
        holder.gnomeHeight.setText(String.valueOf(gnome.getHeight()));
        holder.gnomeHairColor.setText(gnome.getHair_color());

        Picasso.with(mContext)
                .load(gnome.getPicture())
                .fit().centerCrop()
                .into(holder.gnomePicture);

    }

    @Override
    public int getItemCount() {
        return listGnomes.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public class GnomeViewHolder extends RecyclerView.ViewHolder {
        final TextView gnomeAge;
        final TextView gnomeWeight;
        final TextView gnomeHeight;
        final TextView gnomeHairColor;
        final TextView gnomeName;
        final ImageView gnomePicture;

        public GnomeViewHolder(View itemView) {
            super(itemView);
            gnomePicture = (ImageView) itemView.findViewById(R.id.gnome_picture);
            gnomeName = (TextView) itemView.findViewById(R.id.gnome_name);
            gnomeAge = (TextView) itemView.findViewById(R.id.gnome_age);
            gnomeHeight = (TextView) itemView.findViewById(R.id.gnome_height);
            gnomeWeight = (TextView) itemView.findViewById(R.id.gnome_weight);
            gnomeHairColor = (TextView) itemView.findViewById(R.id.gnome_hair_color);

        }
    }
}
