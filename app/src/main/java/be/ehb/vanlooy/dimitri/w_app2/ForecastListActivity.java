package be.ehb.vanlooy.dimitri.w_app2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.ehb.vanlooy.dimitri.w_app2.Utils.IconHelper;
import be.ehb.vanlooy.dimitri.w_app2.entities.Forecast;
import cz.msebera.android.httpclient.Header;

/**
 * An activity representing a list of Forecasts. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ForecastDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ForecastListActivity extends AppCompatActivity {


    // Constants
    final int REQUEST_CODE = 123;
    final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/forecast";
    final String APP_ID = "1e41911417841305361f8f6d203b4d9c";
    final long MIN_TIME = 0;
    final float MIN_DISTANCE = 1000;
    final String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;
    final int DURATION = Toast.LENGTH_SHORT;

    String TAG = ForecastListActivity.class.getSimpleName();
    CharSequence textToast;
    List<Forecast.Item> mItems = new ArrayList<Forecast.Item>();


    LocationManager mLocationManager;
    LocationListener mLocationListener;



    SimpleItemRecyclerViewAdapter mAdapter;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.forecast_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.forecast_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
        getForecastForCurrentLocation();

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
                intent = new Intent(this, ForecastListActivity.class);
                this.startActivity(intent);
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

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mAdapter = new SimpleItemRecyclerViewAdapter(this, mItems, mTwoPane);
        recyclerView.setAdapter(mAdapter);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ForecastListActivity mParentActivity;
        private final List<Forecast.Item> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Forecast.Item item = (Forecast.Item) view.getTag();
                String dt = item.getDt_txt();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                String formattedDate= "";
                try {
                    date = format.parse(dt);
                    System.out.println(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (date != null){
                    format = new SimpleDateFormat("EEEE, HH:mm");
                    formattedDate = format.format(date);
                }

                if (mTwoPane) {
                    Bundle arguments = new Bundle();

                    arguments.putString(ForecastDetailFragment.ARG_TITLE, formattedDate);
                    arguments.putString(ForecastDetailFragment.ARG_DETAIL, item.getMain().toString());
                    ForecastDetailFragment fragment = new ForecastDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.forecast_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ForecastDetailActivity.class);
                    intent.putExtra(ForecastDetailFragment.ARG_TITLE, formattedDate);
                    intent.putExtra(ForecastDetailFragment.ARG_DETAIL, item.getMain().toString());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ForecastListActivity parent,
                                      List<Forecast.Item> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.forecast_list_content, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            String temp = String.valueOf(mValues.get(position).getMain().getInCelcius(mValues.get(position).getMain().getTemp()))+"°C";
            String descriptionCode = "c"+String.valueOf(mValues.get(position).getWeather().get(0).getId());
            int descriptionResourceID = holder.itemView.getContext().getResources().getIdentifier(descriptionCode, "string", holder.itemView.getContext().getPackageName());
            String description = holder.itemView.getContext().getResources().getString(descriptionResourceID);

            String dt = mValues.get(position).getDt_txt();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = format.parse(dt);
                System.out.println(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (date != null){
                format = new SimpleDateFormat("EEEE, HH:mm");
                holder.mContentView.setText(format.format(date));


                String suffix = IconHelper.getWeatherIcon(mValues.get(position).getWeather().get(0).getId());
                String prefix = "day_";
                int imageResourceID = holder.itemView.getContext().getResources().getIdentifier(prefix+suffix, "drawable", holder.itemView.getContext().getPackageName());
                holder.mIconView.setImageResource(imageResourceID);
            }


            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mContentView;
            final ImageView mIconView;

            ViewHolder(View view) {
                super(view);
                mContentView = (TextView) view.findViewById(R.id.content);
                mIconView = (ImageView) view.findViewById(R.id.forecastIcon);
            }
        }
    }

    private void getForecastForCurrentLocation() {
        Log.d("WAPP","GETTING FORECAST FOR CURRENT LOCATION");
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("WAPP","LOCATION CHANGED");
                String lat = String.valueOf(location.getLatitude());
                String lon = String.valueOf(location.getLongitude());
                Log.d("WAPP","LAT: "+lat);
                Log.d("WAPP","LON: "+lon);

                RequestParams params = new RequestParams();
                params.put("lat", lat);
                params.put("lon", lon);
                params.put("appid", APP_ID);
                requestData(params);
            }



            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("WAPP","STATUS CHANGED");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d("WAPP","PROVIDER ENABLED");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("WAPP","PROVIDER DISABLED");
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        mLocationManager.requestLocationUpdates(LOCATION_PROVIDER, MIN_TIME, MIN_DISTANCE, mLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("WAPP","PERMISSION GRANTED");
                getForecastForCurrentLocation();
            } else{
                Log.d("WAPP","PERMISSION DENIED");
            }
        }
    }

    private void requestData(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEATHER_URL, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("WAPP", "JSON: " + response.toString());
                Gson gson = new Gson();
                Forecast forecast = gson.fromJson(response.toString(), Forecast.class);
                Log.d("WAPP", "Forecast: " + forecast.toString());
                mItems.clear();
                mItems.addAll(forecast.getList());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("WAPP","FAILED "+ throwable.toString());
                Log.d("WAPP", "Status code " + statusCode);

            }

        });
    }
}
