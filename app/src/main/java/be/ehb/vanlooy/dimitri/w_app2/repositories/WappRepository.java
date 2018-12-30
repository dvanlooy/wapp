package be.ehb.vanlooy.dimitri.w_app2.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.ArrayList;

import be.ehb.vanlooy.dimitri.w_app2.Weather;
import be.ehb.vanlooy.dimitri.w_app2.daos.FavoriteDAO;
import be.ehb.vanlooy.dimitri.w_app2.databases.WappRoomDatabase;
import be.ehb.vanlooy.dimitri.w_app2.entities.Favorite;


public class WappRepository {

    private FavoriteDAO favoriteDAO;

    private static volatile WappRepository sWappRepository;
    private static WappRoomDatabase sWappRoomDatabase;

    private WappRepository(Application application){
        sWappRoomDatabase = WappRoomDatabase.getDatabase(application);
        favoriteDAO = sWappRoomDatabase.favoriteDAO();
    }

    public static WappRepository getInstance(Application application) {
        if (sWappRepository == null) { //if there is no instance available... create new one
            synchronized (WappRepository.class) {
                if (sWappRepository == null) sWappRepository = new WappRepository(application);
            }
        }

        return sWappRepository;
    }

    public void insertFavorite(final Favorite... favorites){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                favoriteDAO.insert(favorites);
            }
        };
        Thread thread = new Thread(run);
        thread.start();
    }

    public LiveData<Favorite[]> getFavorites(){
        return favoriteDAO.getAllFavorites();
    }

    public ArrayList<Weather> getForecast(){
        ArrayList<Weather> forecast = new ArrayList<Weather>();

        return forecast;
    }
}
