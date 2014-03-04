import com.stcal.control.DBsettings;
import com.stcal.control.Datas;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Jean
 * @version 04/03/2014
 */

public class DatasUnitTest {

    @Test
    public void test() throws Exception {
        DBsettings set = new DBsettings();
        set.set("DBPassword","nothing");
        Assert.assertFalse(Datas.isset(set));
    }

}
