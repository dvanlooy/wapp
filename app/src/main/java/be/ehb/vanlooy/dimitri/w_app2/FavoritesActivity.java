package be.ehb.vanlooy.dimitri.w_app2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import be.ehb.vanlooy.dimitri.w_app2.entities.Favorite;
import be.ehb.vanlooy.dimitri.w_app2.viewmodels.FavoritesViewModel;

public class FavoritesActivity extends AppCompatActivity {

    private FavoritesViewModel mFavoritesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);



        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FavoriteListAdapter adapter = new FavoriteListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFavoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
        mFavoritesViewModel.getFavorites().observe(this, new Observer<Favorite[]>() {
            @Override
            public void onChanged(@Nullable Favorite[] favorites) {
                adapter.setFavorites(favorites);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.menu_favorites:
                intent = new Intent(this, FavoritesActivity.class);
                this.startActivity(intent);
                break;
            case R.id.menu_forecast:
                // another startActivity, this is for item with id "menu_item2"
                break;
            case R.id.menu_today:
                intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
