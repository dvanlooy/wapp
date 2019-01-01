package be.ehb.vanlooy.dimitri.w_app2.entities;


import java.util.ArrayList;
import java.util.List;
/*
built with http://www.jsonschema2pojo.org/
 */
public class Forecast {

    private City city;
    private Coord coord;
    private String country;
    private String cod;
    private double message;
    private int cnt;
    private List<Item> list;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public class City {

        private Integer id;
        private String name;

        /**
         * No args constructor for use in serialization
         *
         */
        public City() {
        }

        /**
         *
         * @param id
         * @param name
         */
        public City(Integer id, String name) {
            super();
            this.id = id;
            this.name = name;
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

        @Override
        public String toString() {
            return "City{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public class Coord {

        private Double lon;
        private Double lat;

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
        public Coord(Double lon, Double lat) {
            super();
            this.lon = lon;
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
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

    public class Item {

        private Integer dt;
        private Main main;
        private List<WeatherForecast> weather = new ArrayList<WeatherForecast>();
        private Clouds clouds;
        private Wind wind;
        private Sys sys;
        private String dt_txt;

        /**
         * No args constructor for use in serialization
         *
         */
        public Item() {
        }

        /**
         *
         * @param clouds
         * @param dt
         * @param wind
         * @param sys
         * @param weather
         * @param dt_txt
         * @param main
         */
        public Item(Integer dt, Main main, List<WeatherForecast> weather, Clouds clouds, Wind wind, Sys sys, String dt_txt) {
            super();
            this.dt = dt;
            this.main = main;
            this.weather = weather;
            this.clouds = clouds;
            this.wind = wind;
            this.sys = sys;
            this.dt_txt = dt_txt;
        }

        public Integer getDt() {
            return dt;
        }

        public void setDt(Integer dt) {
            this.dt = dt;
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public List<WeatherForecast> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherForecast> weather) {
            this.weather = weather;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public void setClouds(Clouds clouds) {
            this.clouds = clouds;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Sys getSys() {
            return sys;
        }

        public void setSys(Sys sys) {
            this.sys = sys;
        }

        public String getDt_txt() {
            return dt_txt;
        }

        public void setDt_txt(String dt_txt) {
            this.dt_txt = dt_txt;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "dt=" + dt +
                    ", main=" + main +
                    ", weather=" + weather +
                    ", clouds=" + clouds +
                    ", wind=" + wind +
                    ", sys=" + sys +
                    ", dt_txt='" + dt_txt + '\'' +
                    '}';
        }
    }

    public class Main {

        private Double temp;
        private Double temp_min;
        private Double temp_max;
        private Double pressure;
        private Double sea_level;
        private Double grnd_level;
        private Double humidity;
        private Double temp_kf;

        /**
         * No args constructor for use in serialization
         *
         */
        public Main() {
        }

        /**
         *
         * @param temp_kf
         * @param humidity
         * @param pressure
         * @param temp_max
         * @param sea_level
         * @param temp_min
         * @param temp
         * @param grnd_level
         */
        public Main(Double temp, Double temp_min, Double temp_max, Double pressure, Double sea_level, Double grnd_level, Double humidity, Double temp_kf) {
            super();
            this.temp = temp;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.pressure = pressure;
            this.sea_level = sea_level;
            this.grnd_level = grnd_level;
            this.humidity = humidity;
            this.temp_kf = temp_kf;
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

        public Double getPressure() {
            return pressure;
        }

        public void setPressure(Double pressure) {
            this.pressure = pressure;
        }

        public Double getSea_level() {
            return sea_level;
        }

        public void setSea_level(Double sea_level) {
            this.sea_level = sea_level;
        }

        public Double getGrnd_level() {
            return grnd_level;
        }

        public void setGrnd_level(Double grnd_level) {
            this.grnd_level = grnd_level;
        }

        public Double getHumidity() {
            return humidity;
        }

        public void setHumidity(Double humidity) {
            this.humidity = humidity;
        }

        public Double getTemp_kf() {
            return temp_kf;
        }

        public void setTemp_kf(Double temp_kf) {
            this.temp_kf = temp_kf;
        }

        @Override
        public String toString() {
            return String.valueOf(getInCelcius(getTemp()))+"°C\n"+
                    "Min: "+String.valueOf(getInCelcius(getTemp_min()))+"°C\n"+
                    "Max: "+String.valueOf(getInCelcius(getTemp_max()))+"°C\n";
            /*            return "Main{" +
                    "temp=" + temp +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    ", pressure=" + pressure +
                    ", sea_level=" + sea_level +
                    ", grnd_level=" + grnd_level +
                    ", humidity=" + humidity +
                    ", temp_kf=" + temp_kf +
                    '}';*/
        }
    }

    public class Sys {

        private String pod;

        /**
         * No args constructor for use in serialization
         *
         */
        public Sys() {
        }

        /**
         *
         * @param pod
         */
        public Sys(String pod) {
            super();
            this.pod = pod;
        }

        public String getPod() {
            return pod;
        }

        public void setPod(String pod) {
            this.pod = pod;
        }

        @Override
        public String toString() {
            return "Sys{" +
                    "pod='" + pod + '\'' +
                    '}';
        }
    }

    public class WeatherForecast {

        private Integer id;
        private String main;
        private String description;
        private String icon;

        /**
         * No args constructor for use in serialization
         *
         */
        public WeatherForecast() {
        }

        /**
         *
         * @param id
         * @param icon
         * @param description
         * @param main
         */
        public WeatherForecast(Integer id, String main, String description, String icon) {
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
            return "WeatherForecast{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
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

    @Override
    public String toString() {
        return "Forecast{" +
                "city=" + city +
                ", coord=" + coord +
                ", country='" + country + '\'' +
                ", cod='" + cod + '\'' +
                ", message=" + message +
                ", cnt=" + cnt +
                ", list=" + list +
                '}';
    }
}
