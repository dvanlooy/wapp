package be.ehb.vanlooy.dimitri.w_app2.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favorites")
public class Favorite {

    @PrimaryKey(autoGenerate= true)
    private long id;
    private String country;
    private String city;
    private double lat;
    private double lon;

    public Favorite() {
    }

    public Favorite(String country, String city, double lat, double lon) {
        this.country = country;
        this.city = city;
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
