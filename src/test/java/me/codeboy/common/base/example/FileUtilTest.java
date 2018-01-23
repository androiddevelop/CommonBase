package me.codeboy.common.base.example;

import me.codeboy.common.base.io.util.CBFileUtil;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * 文件操作测试
 * Created by yuedong.li on 21/01/2018.
 */
public class FileUtilTest {

    @Test
    public void testFileOperate() {
        try {
            String projectDir = new File("").getAbsolutePath();
            File saveFile = new File(projectDir, "src/test/resources/file");
            CBFileUtil.saveContentToFile("Hello World!", saveFile);
            CBFileUtil.saveContentToFile("\n", saveFile, true);
            CBFileUtil.saveContentToFile("Welcome to visit codeboy.me!", saveFile , true);
            assertEquals("Hello World!\nWelcome to visit codeboy.me!", CBFileUtil.getFileContent(saveFile));
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }
}
