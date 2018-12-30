package be.ehb.vanlooy.dimitri.w_app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import be.ehb.vanlooy.dimitri.w_app2.entities.Favorite;
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
                Weather weather = Weather.fromJSON(response);
                Log.d("WAPP", "Weather: " + weather.toString());
                setBackground(weather);
                updateActivity(weather);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("WAPP","FAILED "+ throwable.toString());
                Log.d("WAPP", "Status code " + statusCode);

            }

        });
    }
    private void updateActivity(Weather weather){
        mTempText.setText(weather.getTemp());
        mLocationText.setText(weather.getCity());

        mMaxTempText.setText(weather.getTempMax());
        mMinTempText.setText(weather.getTempMin());

        int descriptionResourceID = getResources().getIdentifier("c"+String.valueOf(weather.getWeatherCode()), "string", getPackageName());
        mDescriptionText.setText(getResources().getString(descriptionResourceID));
        String suffix = weather.getIcon();
        String prefix = "day_";
        Long ts = System.currentTimeMillis()/1000;
        Long sunset = weather.getSunset();
        Long sunrise = weather.getSunrise();
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
    private void setBackground(Weather weather) {

        String type = "bg_day";
        Long ts = System.currentTimeMillis()/1000;
        Long sunset = weather.getSunset();
        Long sunrise = weather.getSunrise();
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
