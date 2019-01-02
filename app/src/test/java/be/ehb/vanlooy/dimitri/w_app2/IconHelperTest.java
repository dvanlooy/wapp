package be.ehb.vanlooy.dimitri.w_app2;

import org.junit.Assert;
import org.junit.Test;

import be.ehb.vanlooy.dimitri.w_app2.Utils.IconHelper;

public class IconHelperTest {
    @Test
    public void testIcon(){

        String thunderstorm = IconHelper.getWeatherIcon(910);
        String cloudy = IconHelper.getWeatherIcon(751);
        String clear = IconHelper.getWeatherIcon(800);
        String storm_rain = IconHelper.getWeatherIcon(299);

        Assert.assertEquals("thunderstorm", thunderstorm);
        Assert.assertEquals("cloudy", cloudy);
        Assert.assertEquals("clear", clear);
        Assert.assertEquals("storm_rain", storm_rain);
    }
}
