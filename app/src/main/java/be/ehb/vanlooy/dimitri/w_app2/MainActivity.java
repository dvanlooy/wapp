package be.ehb.vanlooy.dimitri.w_app2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import be.ehb.vanlooy.dimitri.w_app2.Utils.IconHelper;
import be.ehb.vanlooy.dimitri.w_app2.entities.CurrentWeather;
import be.ehb.vanlooy.dimitri.w_app2.entities.Favorite;
import be.ehb.vanlooy.dimitri.w_app2.repositories.WappRepository;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    // Constants
    final int REQUEST_CODE = 123;
    final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    final String APP_ID = "1e41911417841305361f8f6d203b4d9c";
    final long MIN_TIME = 10;
    final float MIN_DISTANCE = 1000;
    final String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;
    final int DURATION = Toast.LENGTH_SHORT;

    String TAG = MainActivity.class.getSimpleName();
    CharSequence textToast;
    TextView mLocationText;
    TextView mMaxTempText;
    TextView mMinTempText;
    TextView mDescriptionText;
    TextView mTempText;
    TextView mSunriseText;
    TextView mSunsetText;
    ImageView mWeatherIcon;
    ImageView mSunriseIcon;
    ImageView mSunsetIcon;
    ImageView mBackground;
    ProgressBar mProgressBar;
    FloatingActionButton mSaveLocationButton;


    LocationManager mLocationManager;
    LocationListener mLocationListener;
    FusedLocationProviderClient mFusedLocationClient;
    WappRepository mRepository;

    Favorite mCurrentlocation;
    CurrentWeather mCurrentWeather;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRepository = WappRepository.getInstance(this.getApplication());
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationText = (TextView) findViewById(R.id.locationText);
        mMaxTempText = (TextView) findViewById(R.id.maxTempText);
        mMinTempText = (TextView) findViewById(R.id.minTempText);
        mDescriptionText = (TextView) findViewById(R.id.descriptionText);
        mTempText = (TextView) findViewById(R.id.tempText);
        mWeatherIcon = (ImageView) findViewById(R.id.weatherIcon);
        mSunriseIcon = (ImageView) findViewById(R.id.sunriseImage);
        mSunsetIcon = (ImageView) findViewById(R.id.sunsetImage);
        mBackground = (ImageView) findViewById(R.id.background);
        mSunriseText = (TextView) findViewById(R.id.sunriseText);
        mSunsetText = (TextView) findViewById(R.id.sunsetText);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mSaveLocationButton = (FloatingActionButton) findViewById(R.id.saveLocationButton);

        getWeatherForCurrentLocation();


        mSaveLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Add location to database
                if (v.getId() == R.id.saveLocationButton && mCurrentlocation != null) {
                    mRepository.insertFavorite(mCurrentlocation);
                    textToast = getString(R.string.saveLocation);
                    Toast toast = Toast.makeText(v.getContext(), textToast, DURATION);
                    toast.show();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCurrentWeather != null) {
            setBackground(mCurrentWeather);
            updateActivity(mCurrentWeather);
        } else {
            getWeatherForCurrentLocation();
        }
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
        switch (item.getItemId()) {
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

    private void getWeatherForCurrentLocation() {
        Log.d("WAPP", "GETTING WEATHER FOR CURRENT LOCATION");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            requestWeather(location);
                        }
                    }
                });
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("WAPP","LOCATION CHANGED");
                requestWeather(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) { Log.d("WAPP","STATUS CHANGED"); }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d("WAPP","PROVIDER ENABLED");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("WAPP","PROVIDER DISABLED");
            }
        };

        mLocationManager.requestLocationUpdates(LOCATION_PROVIDER, MIN_TIME, MIN_DISTANCE, mLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("WAPP","PERMISSION GRANTED");
                getWeatherForCurrentLocation();
            } else{
                Log.d("WAPP","PERMISSION DENIED");
            }
        }
    }

    private void requestWeather(Location location){
        String lat = String.valueOf(location.getLatitude());
        String lon = String.valueOf(location.getLongitude());
        RequestParams params = new RequestParams();
        params.put("lat", lat);
        params.put("lon", lon);
        params.put("appid", APP_ID);
        executeRequest(params);
    }

    private void executeRequest(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEATHER_URL, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("WAPP", "JSON: " + response.toString());

                Gson gson = new Gson();
                CurrentWeather currentWeather = gson.fromJson(response.toString(), CurrentWeather.class);
                Log.d("WAPP", "CurrentWeather: " + currentWeather.toString());
                mProgressBar.setVisibility(View.INVISIBLE);
                setBackground(currentWeather);
                updateActivity(currentWeather);

                mCurrentlocation = new Favorite(
                        currentWeather.getSys().getCountry(),
                        currentWeather.getName(),
                        currentWeather.getCoord().getLat(),
                        currentWeather.getCoord().getLon()
                );
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("WAPP","FAILED "+ throwable.toString());
                Log.d("WAPP", "Status code " + statusCode);

            }
            @Override
            public void onStart() {
                super.onStart();
                mProgressBar.setVisibility(View.VISIBLE);
            }


        });
    }

    private void updateActivity(CurrentWeather currentWeather){
        String temp = String.valueOf(currentWeather.getMain().getInCelcius(currentWeather.getMain().getTemp()))+"°C";
        String maxTemp = String.valueOf(currentWeather.getMain().getInCelcius(currentWeather.getMain().getTemp_max()))+"°C";
        String minTemp = String.valueOf(currentWeather.getMain().getInCelcius(currentWeather.getMain().getTemp_min()))+"°C";
        String location = currentWeather.getName()+", "+currentWeather.getSys().getCountry();
        String descriptionCode = "c"+String.valueOf(currentWeather.getWeather().get(0).getId());
        String sunriseText = currentWeather.getSys().getTimeNotation(currentWeather.getSys().getSunrise());
        String sunsetText = currentWeather.getSys().getTimeNotation(currentWeather.getSys().getSunset());

        mTempText.setText(temp);
        mLocationText.setText(location);
        mMaxTempText.setText(maxTemp);
        mMinTempText.setText(minTemp);
        mSunsetText.setText(sunsetText);
        mSunriseText.setText(sunriseText);

        int descriptionResourceID = getResources().getIdentifier(descriptionCode, "string", getPackageName());
        mDescriptionText.setText(getResources().getString(descriptionResourceID));

        String suffix = IconHelper.getWeatherIcon(currentWeather.getWeather().get(0).getId());
        String prefix = "day_";
        Long ts = System.currentTimeMillis()/1000;
        Long sunset = currentWeather.getSys().getSunset();
        Long sunrise = currentWeather.getSys().getSunrise();
        if (ts != null && sunset != null && sunrise != null){
            if (ts < sunrise || ts >= sunset){
                prefix = "night_";
                mWeatherIcon.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorVeryLight));
                mSunriseIcon.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorVeryLight));
                mSunsetIcon.setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.colorVeryLight));
                mSunsetText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorVeryLight));
                mSunriseText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorVeryLight));
                mTempText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorVeryLight));
                mMaxTempText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorVeryLight));
                mMinTempText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorVeryLight));
                mLocationText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorVeryLight));
                mDescriptionText.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorVeryLight));
            }else{
                prefix = "day_";
            }
        }
        int imageResourceID = getResources().getIdentifier(prefix+suffix, "drawable", getPackageName());
        mWeatherIcon.setImageResource(imageResourceID);

        imageResourceID = getResources().getIdentifier("sunrise", "drawable", getPackageName());
        mSunriseIcon.setImageResource(imageResourceID);
        imageResourceID = getResources().getIdentifier("sunset", "drawable", getPackageName());
        mSunsetIcon.setImageResource(imageResourceID);
    }
    private void setBackground(CurrentWeather currentWeather) {

        String type = "bg_day";
        Long ts = System.currentTimeMillis()/1000;
        Long sunset = currentWeather.getSys().getSunset();
        Long sunrise = currentWeather.getSys().getSunrise();
        if (ts != null && sunset != null && sunrise != null){
            if (ts < sunrise || ts >= sunset){
                type = "bg_night";
            }else{
                type = "bg_day";
            }
        }

        int imageResourceID = getResources().getIdentifier(type, "drawable", getPackageName());
        mBackground.setImageResource(imageResourceID);
    }
}
