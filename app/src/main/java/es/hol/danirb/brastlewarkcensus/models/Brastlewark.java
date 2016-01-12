package es.hol.danirb.brastlewarkcensus.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Brastlewark implements Serializable {

    @SerializedName("Brastlewark")
    List<Gnome> gnomeList = new ArrayList<>();

    public List<Gnome> getGnomeList() {
        return gnomeList;
    }


}
