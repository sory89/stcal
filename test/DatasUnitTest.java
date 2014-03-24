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
        Assert.assertFalse(DBTools.isset());
    }

}
