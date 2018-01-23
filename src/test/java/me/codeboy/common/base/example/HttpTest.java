package me.codeboy.common.base.example;

import me.codeboy.common.base.log.CBPrint;
import me.codeboy.common.base.net.CBHttp;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * http测试
 * Created by yuedong.li on 21/01/2018.
 */
public class HttpTest {

    /**
     * 测试http
     */
    @Test
    public void testHttp() {
        String url = "http://codeboy.me";
        try {
            String abc = CBHttp.getInstance().connect(url).execute();
            CBPrint.print(abc);
        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }
    }

}
