package es.hol.danirb.brastlewarkcensus.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dani on 9/01/16.
 */
public class Gnome implements Serializable{
    private int id;
    private String name;
    private String picture;
    private int age;
    private double weight;
    private double height;
    private String hair_color;
    private List<String> professions;
    private List<String> friends;

    public Gnome() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public List<String> getProfessions() {
        return professions;
    }

    public void setProfessions(List<String> professions) {
        this.professions = professions;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Gnome{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", hair_color='" + hair_color + '\'' +
                ", professions=" + professions +
                ", friends=" + friends +
                '}';
    }
}
