package me.codeboy.common.base.example;

import me.codeboy.common.base.log.CBPrint;
import me.codeboy.common.base.security.CBBase64;
import static  org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;

/**
 * base64测试
 * Created by yuedong.li on 21/01/2018.
 */
public class Base64Test {

 @Test
 public void test(){
     String str = "小胖轩(codeboy.me)";
     String tmp = CBBase64.encode(str);
     CBPrint.print(tmp);
     try {
         CBPrint.print(CBBase64.decode(tmp));
         assertEquals("小胖轩(codeboy.me)",CBBase64.decode(tmp));
     } catch (IOException e) {
         fail();
         e.printStackTrace();
     }
 }
}
