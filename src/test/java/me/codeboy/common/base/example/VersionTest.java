package me.codeboy.common.base.example;

import me.codeboy.common.base.util.CBVersionUtil;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 版本测试
 * Created by yuedong.li on 21/01/2018.
 */
public class VersionTest {

    @Test
    public void testHttp() {
        assertTrue(CBVersionUtil.hasNewVersion("1.1.2", "1.2.1"));
        assertFalse(CBVersionUtil.hasNewVersion("1.1.2", "1.1.2"));
        assertTrue(CBVersionUtil.hasNewVersion("1.1.2", "1.1.2.1"));
        assertFalse(CBVersionUtil.hasNewVersion("1.10.2", "1.2.1.1"));
        assertFalse(CBVersionUtil.hasNewVersion("1.10.2", "1.1.1"));
    }
}
