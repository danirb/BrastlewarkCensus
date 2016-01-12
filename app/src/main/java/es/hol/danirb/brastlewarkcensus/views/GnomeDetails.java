package es.hol.danirb.brastlewarkcensus.views;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import es.hol.danirb.brastlewarkcensus.R;
import es.hol.danirb.brastlewarkcensus.models.Gnome;

public class GnomeDetails extends AppCompatActivity {
    private Gnome gnome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gnome_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gnome = (Gnome) getIntent().getSerializableExtra("gnome");

        setHeader();
        setDetails();


    }


    private void setDetails() {
        TextView id = (TextView) findViewById(R.id.gnome_details_id_number);
        id.setText(String.valueOf(gnome.getId()));
        TextView age = (TextView) findViewById(R.id.gnome_details_age_number);
        age.setText(String.valueOf(gnome.getAge()));
        TextView weight = (TextView) findViewById(R.id.gnome_details_weight_number);
        weight.setText(String.valueOf(gnome.getWeight()));
        TextView height = (TextView) findViewById(R.id.gnome_details_height_number);
        height.setText(String.valueOf(gnome.getHeight()));
        TextView hairColor = (TextView) findViewById(R.id.gnome_details_hair_color_string);
        hairColor.setText(gnome.getHair_color());
        TextView friends = (TextView) findViewById(R.id.friends_list);
        for (String str : gnome.getFriends())
            friends.setText(friends.getText() + "· " + str + "\n");
        TextView works = (TextView) findViewById(R.id.works_list);
        for (String str : gnome.getProfessions())
            works.setText(works.getText() + "· " + str + "\n");
    }

    private void setHeader() {
        ImageView picture;
        CollapsingToolbarLayout header = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        header.setTitle(gnome.getName());
        picture = (ImageView) header.findViewById(R.id.header_picture);
        Picasso.with(this)
                .load(gnome.getPicture())
                .fit().centerCrop()
                .into(picture);
    }
}
