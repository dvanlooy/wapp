package be.ehb.vanlooy.dimitri.w_app2.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
built with http://www.jsonschema2pojo.org/
 */
public class CurrentWeather {
    private Coord coord;
    private Sys sys;
    private List<Observation> weather = new ArrayList<Observation>();
    private Main main;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private Integer dt;
    private Integer id;
    private String name;
    private Integer cod;

    /**
     * No args constructor for use in serialization
     *
     */
    public CurrentWeather() {
    }

    /**
     *
     * @param id
     * @param dt
     * @param clouds
     * @param coord
     * @param wind
     * @param cod
     * @param sys
     * @param name
     * @param weather
     * @param rain
     * @param main
     */
    public CurrentWeather(Coord coord, Sys sys, List<Observation> weather, Main main, Wind wind, Rain rain, Clouds clouds, Integer dt, Integer id, String name, Integer cod) {
        super();
        this.coord = coord;
        this.sys = sys;
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.rain = rain;
        this.clouds = clouds;
        this.dt = dt;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List<Observation> getWeather() {
        return weather;
    }

    public void setWeather(List<Observation> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "coord=" + coord +
                ", sys=" + sys +
                ", weather=" + weather +
                ", main=" + main +
                ", wind=" + wind +
                ", rain=" + rain +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }

    public class Coord {

        private double lon;
        private double lat;

        /**
         * No args constructor for use in serialization
         *
         */
        public Coord() {
        }

        /**
         *
         * @param lon
         * @param lat
         */
        public Coord(double lon, double lat) {
            super();
            this.lon = lon;
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }
    public class Clouds {

        private Integer all;

        /**
         * No args constructor for use in serialization
         *
         */
        public Clouds() {
        }

        /**
         *
         * @param all
         */
        public Clouds(Integer all) {
            super();
            this.all = all;
        }

        public Integer getAll() {
            return all;
        }

        public void setAll(Integer all) {
            this.all = all;
        }

        @Override
        public String toString() {
            return "Clouds{" +
                    "all=" + all +
                    '}';
        }
    }
    public class Main {

        private Double temp;
        private Double humidity;
        private Double pressure;
        private Double temp_min;
        private Double temp_max;

        /**
         * No args constructor for use in serialization
         *
         */
        public Main() {
        }

        /**
         *
         * @param humidity
         * @param pressure
         * @param temp_max
         * @param temp_min
         * @param temp
         */
        public Main(Double temp, Double humidity, Double pressure, Double temp_min, Double temp_max) {
            super();
            this.temp = temp;
            this.humidity = humidity;
            this.pressure = pressure;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
        }
        public int getInCelcius(double temp){
            return (int) Math.rint(temp - 273.15);
        }

        public Double getTemp() {
            return temp;
        }

        public void setTemp(Double temp) {
            this.temp = temp;
        }

        public Double getHumidity() {
            return humidity;
        }

        public void setHumidity(Double humidity) {
            this.humidity = humidity;
        }

        public Double getPressure() {
            return pressure;
        }

        public void setPressure(Double pressure) {
            this.pressure = pressure;
        }

        public Double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(Double temp_min) {
            this.temp_min = temp_min;
        }

        public Double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(Double temp_max) {
            this.temp_max = temp_max;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", humidity=" + humidity +
                    ", pressure=" + pressure +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    '}';
        }
    }
    public class Rain {

        private Integer _3h;

        /**
         * No args constructor for use in serialization
         *
         */
        public Rain() {
        }

        /**
         *
         * @param _3h
         */
        public Rain(Integer _3h) {
            super();
            this._3h = _3h;
        }

        public Integer get3h() {
            return _3h;
        }

        public void set3h(Integer _3h) {
            this._3h = _3h;
        }

        @Override
        public String toString() {
            return "Rain{" +
                    "_3h=" + _3h +
                    '}';
        }
    }
    public class Sys {

        private String country;
        private long sunrise;
        private long sunset;

        /**
         * No args constructor for use in serialization
         *
         */
        public Sys() {
        }

        /**
         *
         * @param sunset
         * @param sunrise
         * @param country
         */
        public Sys(String country, long sunrise, long sunset) {
            super();
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public String getTimeNotation(long unix){
            String time;
            Date date = new java.util.Date(unix*1000L);
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mmz");
            time = sdf.format(date);
            return time;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            return "Sys{" +
                    "country='" + country + '\'' +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }
    public class Observation {

        private Integer id;
        private String main;
        private String description;
        private String icon;

        /**
         * No args constructor for use in serialization
         *
         */
        public Observation() {
        }

        /**
         *
         * @param id
         * @param icon
         * @param description
         * @param main
         */
        public Observation(Integer id, String main, String description, String icon) {
            super();
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        @Override
        public String toString() {
            return "Observation{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    public class Wind {

        private Double speed;
        private Double deg;

        /**
         * No args constructor for use in serialization
         *
         */
        public Wind() {
        }

        /**
         *
         * @param speed
         * @param deg
         */
        public Wind(Double speed, Double deg) {
            super();
            this.speed = speed;
            this.deg = deg;
        }

        public Double getSpeed() {
            return speed;
        }

        public void setSpeed(Double speed) {
            this.speed = speed;
        }

        public Double getDeg() {
            return deg;
        }

        public void setDeg(Double deg) {
            this.deg = deg;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "speed=" + speed +
                    ", deg=" + deg +
                    '}';
        }
    }

}
