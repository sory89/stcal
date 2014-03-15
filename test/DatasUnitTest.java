import com.stcal.control.DBTools;
import com.stcal.control.DBsettings;
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
        Assert.assertFalse(DBTools.isset(set));
    }

}
