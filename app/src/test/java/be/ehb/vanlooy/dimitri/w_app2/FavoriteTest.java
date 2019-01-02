package be.ehb.vanlooy.dimitri.w_app2;

import org.junit.Assert;
import org.junit.Test;

import be.ehb.vanlooy.dimitri.w_app2.entities.Favorite;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FavoriteTest {
    @Test
    public void test_Getters(){

        Favorite favorite = new Favorite();
        favorite.setCity("test");
        favorite.setCountry("XY");
        favorite.setLat(66);
        favorite.setLon(88);

        Assert.assertEquals(favorite.getCity(), "test");
        Assert.assertEquals(favorite.getCountry(), "XY");
        Assert.assertEquals(favorite.getLat(), 66,0);
        Assert.assertEquals(favorite.getLon(), 88, 0);

    }
}