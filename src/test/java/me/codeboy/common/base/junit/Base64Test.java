package me.codeboy.common.base.junit;

import me.codeboy.common.base.security.CBBase64;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class Base64Test {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        String str = "小胖轩(codeboy.me)";
        String tmp = CBBase64.encode(str);
        System.out.println(tmp);
        try {
            System.out.println(CBBase64.decode(tmp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}