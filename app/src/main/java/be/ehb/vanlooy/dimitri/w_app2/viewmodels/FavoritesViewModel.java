package be.ehb.vanlooy.dimitri.w_app2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import be.ehb.vanlooy.dimitri.w_app2.entities.Favorite;
import be.ehb.vanlooy.dimitri.w_app2.repositories.WappRepository;


public class FavoritesViewModel extends AndroidViewModel {

    private final WappRepository mRepository;
    private final LiveData<Favorite[]> mFavorites;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        mRepository = WappRepository.getInstance(application);
        mFavorites = mRepository.getFavorites();
    }

    public LiveData<Favorite[]> getFavorites() {
        return mFavorites;
    }


}
