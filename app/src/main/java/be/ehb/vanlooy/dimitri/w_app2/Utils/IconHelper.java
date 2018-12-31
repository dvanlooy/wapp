package be.ehb.vanlooy.dimitri.w_app2.Utils;

public class IconHelper {

    public static String getWeatherIcon(int code) {

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
}
