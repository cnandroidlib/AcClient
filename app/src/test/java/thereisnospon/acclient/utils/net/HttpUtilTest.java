package thereisnospon.acclient.utils.net;

import org.junit.Before;
import org.junit.Test;

import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Created by yzr on 16/8/22.
 */
public class HttpUtilTest {


    HttpUtil util;

    @Before public void before(){
        util=HttpUtil.getInstance();
    }

    @Test
    public void testGet() throws Exception {
        Response response=util.get("http://acm.split.hdu.edu.cn/")
                .execute();
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
    }
}