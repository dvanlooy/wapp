package be.ehb.vanlooy.dimitri.w_app2.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import be.ehb.vanlooy.dimitri.w_app2.daos.FavoriteDAO;
import be.ehb.vanlooy.dimitri.w_app2.entities.Favorite;


@Database(entities= {Favorite.class},  version= 1)
public abstract class WappRoomDatabase extends RoomDatabase {

    public abstract FavoriteDAO favoriteDAO();

    public static WappRoomDatabase getDatabase(final Context context){
        return Room.databaseBuilder(context.getApplicationContext(),
                WappRoomDatabase.class,
                "wapp_database").build();
    }
}
