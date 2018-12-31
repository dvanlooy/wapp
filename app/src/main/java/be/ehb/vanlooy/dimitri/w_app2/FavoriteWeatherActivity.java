package be.ehb.vanlooy.dimitri.w_app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import be.ehb.vanlooy.dimitri.w_app2.Utils.IconHelper;
import be.ehb.vanlooy.dimitri.w_app2.entities.CurrentWeather;
import be.ehb.vanlooy.dimitri.w_app2.repositories.WappRepository;
import cz.msebera.android.httpclient.Header;

public class FavoriteWeatherActivity extends AppCompatActivity {

    // Constants
    final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    final String APP_ID = "1e41911417841305361f8f6d203b4d9c";


    String TAG = FavoriteWeatherActivity.class.getSimpleName();
    CharSequence textToast;
    TextView mLocationText;
    TextView mMaxTempText;
    TextView mMinTempText;
    TextView mDescriptionText;
    TextView mTempText;
    ImageView mWeatherIcon;
    ImageView mBackground;

    WappRepository mRepository;

    private double mLon;
    private double mLat;

    private void getIncomingIntent(){
        if (getIntent().hasExtra("lat") && getIntent().hasExtra("lon")){
            setLat(getIntent().getDoubleExtra("lat",0));
            setLon(getIntent().getDoubleExtra("lon",0));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_weather);

        mLocationText = (TextView) findViewById(R.id.locationText);
        mMaxTempText = (TextView) findViewById(R.id.maxTempText);
        mMinTempText = (TextView) findViewById(R.id.minTempText);
        mDescriptionText = (TextView) findViewById(R.id.descriptionText);
        mTempText = (TextView) findViewById(R.id.tempText);
        mWeatherIcon = (ImageView) findViewById(R.id.weatherIcon);
        mBackground = (ImageView) findViewById(R.id.background);

        getIncomingIntent();
        if (getLat() != 0 && getLon() != 0){
            getWeatherForFavorite(getLat(), getLon());
        }

    }



    private void getWeatherForFavorite(double lat, double lon) {
        RequestParams params = new RequestParams();
        params.put("lat", lat);
        params.put("lon", lon);
        params.put("appid", APP_ID);
        requestData(params);
    }

    private void requestData(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEATHER_URL, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("WAPP", "JSON: " + response.toString());

                Gson gson = new Gson();
                CurrentWeather currentWeather = gson.fromJson(response.toString(), CurrentWeather.class);
                Log.d("WAPP", "CurrentWeather: " + currentWeather.toString());
                setBackground(currentWeather);
                updateActivity(currentWeather);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("WAPP","FAILED "+ throwable.toString());
                Log.d("WAPP", "Status code " + statusCode);

            }

        });
    }
    private void updateActivity(CurrentWeather currentWeather){
        String temp = String.valueOf(currentWeather.getMain().getInCelcius(currentWeather.getMain().getTemp()))+"°C";
        String maxTemp = String.valueOf(currentWeather.getMain().getInCelcius(currentWeather.getMain().getTemp_max()))+"°C";
        String minTemp = String.valueOf(currentWeather.getMain().getInCelcius(currentWeather.getMain().getTemp_min()))+"°C";
        String location = currentWeather.getName()+", "+currentWeather.getSys().getCountry();
        String descriptionCode = "c"+String.valueOf(currentWeather.getWeather().get(0).getId());


        mTempText.setText(temp);
        mLocationText.setText(location);
        mMaxTempText.setText(maxTemp);
        mMinTempText.setText(minTemp);

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
            }else{
                prefix = "day_";
            }
        }
        int imageResourceID = getResources().getIdentifier(prefix+suffix, "drawable", getPackageName());
        mWeatherIcon.setImageResource(imageResourceID);
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

    public double getLon() {
        return mLon;
    }

    public void setLon(double lon) {
        mLon = lon;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        mLat = lat;
    }
}
