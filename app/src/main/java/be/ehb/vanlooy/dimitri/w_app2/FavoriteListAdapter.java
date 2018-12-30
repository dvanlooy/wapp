package be.ehb.vanlooy.dimitri.w_app2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import be.ehb.vanlooy.dimitri.w_app2.entities.Favorite;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder> {
    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private final TextView favoriteItemView;

        private FavoriteViewHolder(View itemView) {
            super(itemView);
            favoriteItemView = itemView.findViewById(R.id.favoriteText);
        }
    }

    private final LayoutInflater mInflater;
    private Favorite[] mFavorites; // Cached copy of words

    FavoriteListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_favorite, parent, false);
        return new FavoriteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        if (mFavorites != null) {
            Favorite current = mFavorites[position];
            holder.favoriteItemView.setText(current.getCity()+", "+current.getCountry());
        } else {
            // Covers the case of data not being ready yet.
            holder.favoriteItemView.setText("No Favorites");
        }
    }

    void setFavorites(Favorite[] favorites){
        mFavorites = favorites;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mFavorites != null)
            return mFavorites.length;
        else return 0;
    }
}