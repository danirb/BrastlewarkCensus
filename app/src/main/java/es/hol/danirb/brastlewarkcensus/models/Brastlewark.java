package es.hol.danirb.brastlewarkcensus.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani on 9/01/16.
 */
public class Brastlewark implements Serializable, Village{

    List<Gnome> gnomeList = new ArrayList<>();

    public Brastlewark(List<Gnome> gnomeList) {
        this.gnomeList = gnomeList;
    }

    public List<Gnome> getGnomeList() {
        return gnomeList;
    }

    public void setGnomeList(List<Gnome> gnomeList) {
        this.gnomeList = gnomeList;
    }

    @Override
    public int countPeople() {
        return gnomeList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Brastlewark that = (Brastlewark) o;

        return !(gnomeList != null ? !gnomeList.equals(that.gnomeList) : that.gnomeList != null);

    }

    @Override
    public int hashCode() {
        return gnomeList != null ? gnomeList.hashCode() : 0;
    }
}
