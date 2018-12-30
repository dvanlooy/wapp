package be.ehb.vanlooy.dimitri.w_app2;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class Weather {

    private int mWeatherCode;
    private String mCountryCode;
    private String mDescription;
    private String mIcon;
    private String mCity;
    private String mTemp;
    private String mTempMin;
    private String mTempMax;
    private BigDecimal mHumidity;
    private BigDecimal mWindspeed;
    private BigDecimal mWindDirection;
    private long mSunset;
    private long mSunrise;
    private double lat;
    private double lon;




    public static Weather fromJSON (JSONObject jsonObject){
        Weather weather = new Weather();

        try {
            weather.setCity(jsonObject.getString("name"));
            weather.setWeatherCode(jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id"));
            weather.setTemp(String.valueOf(getTempCelcius(jsonObject.getJSONObject("main").getDouble("temp")))+"°");
            weather.setTempMin(String.valueOf(getTempCelcius(jsonObject.getJSONObject("main").getDouble("temp_min")))+"°");
            weather.setTempMax(String.valueOf(getTempCelcius(jsonObject.getJSONObject("main").getDouble("temp_max")))+"°");
            weather.setDescription(jsonObject.getJSONArray("weather").getJSONObject(0).getString("description"));
            weather.setSunrise(jsonObject.getJSONObject("sys").getLong("sunrise"));
            weather.setSunset(jsonObject.getJSONObject("sys").getLong("sunset"));
            weather.setCountryCode(jsonObject.getJSONObject("sys").getString("country"));
            weather.setLat(jsonObject.getJSONObject("coord").getDouble("lat"));
            weather.setLon(jsonObject.getJSONObject("coord").getDouble("lon"));
            weather.setIcon(getWeatherIcon(weather.getWeatherCode()));
            return weather;
        } catch (JSONException e) {
            Log.e("WAPP", "ERROR: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static int getTempCelcius(double tempKelvin){
        return (int) Math.rint(tempKelvin - 273.15);
    }

    private static String getWeatherIcon(int code) {

        if (code >= 0 && code < 200) {
            return "storm";
        } else if (code >= 200 && code < 203) {
            return "storm_rain";
        }else if (code >= 203 && code < 221) {
            return "storm";
        }else if (code >= 221 && code < 300) {
            return "storm_rain";
        }else if (code >= 300 && code < 500) {
            return "rain";
        } else if (code >= 500 && code < 600) {
            return "showers";
        } else if (code >= 600 && code <= 700) {
            return "snow";
        } else if (code >= 701 && code <= 771) {
            return "cloudy";
        } else if (code >= 772 && code < 800) {
            return "thunderstorm";
        } else if (code == 800) {
            return "clear";
        } else if (code >= 801 && code <= 804) {
            return "partial_cloudy";
        } else if (code >= 900 && code <= 902) {
            return "thunderstorm";
        } else if (code == 903) {
            return "snow";
        } else if (code == 904) {
            return "clear";
        } else if (code >= 905 && code <= 1000) {
            return "thunderstorm";
        }

        return "rainbow";
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getSunset() {
        return mSunset;
    }

    public void setSunset(long sunset) {
        mSunset = sunset;
    }

    public long getSunrise() {
        return mSunrise;
    }

    public void setSunrise(long sunrise) {
        mSunrise = sunrise;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getWeatherCode() {
        return mWeatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        mWeatherCode = weatherCode;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getTemp() {
        return mTemp;
    }

    public void setTemp(String temp) {
        mTemp = temp;
    }

    public String getTempMin() {
        return mTempMin;
    }

    public void setTempMin(String tempMin) {
        mTempMin = tempMin;
    }

    public String getTempMax() {
        return mTempMax;
    }

    public void setTempMax(String tempMax) {
        mTempMax = tempMax;
    }

    public BigDecimal getHumidity() {
        return mHumidity;
    }

    public void setHumidity(BigDecimal humidity) {
        mHumidity = humidity;
    }

    public BigDecimal getWindspeed() {
        return mWindspeed;
    }

    public void setWindspeed(BigDecimal windspeed) {
        mWindspeed = windspeed;
    }

    public BigDecimal getWindDirection() {
        return mWindDirection;
    }

    public void setWindDirection(BigDecimal windDirection) {
        mWindDirection = windDirection;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "mWeatherCode=" + mWeatherCode +
                ", mCountryCode='" + mCountryCode + '\'' +
                ", mIcon='" + mIcon + '\'' +
                ", mCity='" + mCity + '\'' +
                ", mTemp='" + mTemp + '\'' +
                ", mTempMin='" + mTempMin + '\'' +
                ", mTempMax='" + mTempMax + '\'' +
                ", mHumidity=" + mHumidity +
                ", mWindspeed=" + mWindspeed +
                ", mWindDirection=" + mWindDirection +
                ", mDescription=" + mDescription +
                '}';
    }
}
