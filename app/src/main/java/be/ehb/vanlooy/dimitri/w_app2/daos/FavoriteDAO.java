package be.ehb.vanlooy.dimitri.w_app2.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import be.ehb.vanlooy.dimitri.w_app2.entities.Favorite;

@Dao
public abstract interface FavoriteDAO {

    @Insert
    List<Long> insert(Favorite... favorites);

    @Delete
    int delete(Favorite... favorites);

    @Query("SELECT * FROM favorites ORDER BY country, city ASC")
    LiveData<Favorite[]> getAllFavorites();
}
