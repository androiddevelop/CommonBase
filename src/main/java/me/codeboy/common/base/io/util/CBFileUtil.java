package me.codeboy.common.base.io.util;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * 文件操作工具类
 *
 * @author Yuedong Li
 */
public class CBFileUtil {

    /**
     * 按照UTF-8编码得到文件内容
     *
     * @param filePath 源文件路径
     * @return 文件内容
     * @throws IOException io exception
     */
    public static String getFileContent(String filePath) throws IOException {
        return getFileContent(filePath, "UTF-8");
    }

    /**
     * 按照指定编码得到文件内容
     *
     * @param filePath 源文件路径
     * @param encoding 文件编
     * @return 文件内容
     * @throws IOException io exception
     */
    public static String getFileContent(String filePath, String encoding)
            throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath), encoding));
        String content = getContent(buff);
        buff.close();
        return content;
    }

    /**
     * 按照UTF-8编码得到文件内容,保存到list中
     *
     * @param filePath 源文件路径
     * @return 文件内容
     * @throws IOException io exception
     */
    public static List<String> getFileContentAsList(String filePath)
            throws IOException {
        return Arrays.asList(getFileContent(filePath).split("\\n"));
    }

    /**
     * 按照UTF-8编码得到文件内容,保存到list中
     *
     * @param file 源文件
     * @return 文件内容
     * @throws IOException io exception
     */
    public static List<String> getFileContentAsList(File file)
            throws IOException {
        return getFileContentAsList(file, "UTF-8");
    }

    /**
     * 按照指定编码得到文件内容,保存list中
     *
     * @param filePath 源文件路径
     * @param encoding 文件编
     * @return 文件内容
     * @throws IOException io exception
     */
    public static List<String> getFileContentAsList(String filePath, String encoding)
            throws IOException {
        return Arrays.asList(getFileContent(filePath, encoding).split("\\n"));
    }

    /**
     * 按照指定编码得到文件内容,保存list中
     *
     * @param file     源文件
     * @param encoding 文件编
     * @return 文件内容
     * @throws IOException io exception
     */
    public static List<String> getFileContentAsList(File file, String encoding)
            throws IOException {
        return Arrays.asList(getFileContent(new FileInputStream(file), encoding).split("\\n"));
    }


    /**
     * 按照UTF-8编码得到输入流内容
     *
     * @param file 文件
     * @return 文件内容
     * @throws IOException io exception
     */
    public static String getFileContent(File file) throws IOException {
        return getFileContent(new FileInputStream(file));
    }

    /**
     * 按照UTF-8编码得到输入流内容
     *
     * @param is 输入流
     * @return 文件内容
     * @throws IOException io exception
     */
    public static String getFileContent(InputStream is) throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(is,
                "UTF-8"));
        String content = getContent(buff);
        buff.close();
        return content;
    }

    /**
     * 按照指定编码得到输入流内容
     *
     * @param is       输入流
     * @param encoding 文件编
     * @return 文件内容
     * @throws IOException io exception
     */
    public static String getFileContent(InputStream is, String encoding)
            throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(is,
                encoding));
        String content = getContent(buff);
        buff.close();
        return content;
    }

    /**
     * 获取BufferedReader对应字符串
     *
     * @param buff bufferedReader
     * @return 内容
     * @throws IOException io exception
     */
    private static String getContent(BufferedReader buff) throws IOException {
        String line;
        StringBuffer content = new StringBuffer();
        while ((line = buff.readLine()) != null) {
            content.append('\n');
            content.append(line);
        }
        return content.substring(1).toString();
    }

    /**
     * 按照UTF-8编码保存到相应内容到指定路径的文件
     *
     * @param content  文件内容
     * @param filePath 文件路径
     * @throws IOException io exception
     */
    public static void saveContentToFile(String content, String filePath)
            throws IOException {
        saveContentToFile(content, filePath, "UTF-8");
    }

    /**
     * 按照UTF-8编码保存到相应内容到指定路径的文件
     *
     * @param content  文件内容
     * @param filePath 文件路径
     * @param append 是否追加
     * @throws IOException io exception
     */
    public static void saveContentToFile(String content, String filePath, boolean append)
            throws IOException {
        saveContentToFile(content, new File(filePath), "UTF-8", append);
    }

    /**
     * 按照UTF-8编码保存到相应内容到指定文件
     *
     * @param content 文件内容
     * @param file    文件
     * @throws IOException io exception
     */
    public static void saveContentToFile(String content, File file)
            throws IOException {
        saveContentToFile(content, file, "UTF-8", false);
    }

    /**
     * 按照UTF-8编码保存到相应内容到指定文件
     *
     * @param content 文件内容
     * @param file    文件
     * @param append 是否追加
     * @throws IOException io exception
     */
    public static void saveContentToFile(String content, File file, boolean append)
            throws IOException {
        saveContentToFile(content, file, "UTF-8", append);
    }


    /**
     * 保存网络文件到指定路径
     *
     * @param url      文件地址
     * @param filePath 文件路径
     * @throws IOException io exception
     */
    public static void saveContentToFile(URL url, String filePath) throws IOException {
        saveContentToFile(url, new File(filePath));
    }

    /**
     * 保存网络文件到指定文件
     *
     * @param url  文件地址
     * @param file 文件
     * @throws IOException io exception
     */
    public static void saveContentToFile(URL url, File file) throws IOException {
        InputStream is = url.openStream();
        FileOutputStream fos = new FileOutputStream(file);
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        fos.flush();
        fos.close();
        is.close();
    }


    /**
     * 按照指定编码保存相应内容到指定路径的文件
     *
     * @param content  文件内容
     * @param filePath 文件路径
     * @param encoding 编码
     * @throws IOException io exception
     */

    public static void saveContentToFile(String content, String filePath,
                                         String encoding) throws IOException {
        saveContentToFile(content, new File(filePath), encoding, false);
    }

    /**
     * 按照指定编码保存相应内容到指定文件
     *
     * @param content  文件内容
     * @param file     文件
     * @param encoding 编码
     * @param append 是否追加
     * @throws IOException io exception
     */

    public static void saveContentToFile(String content, File file,
                                         String encoding, boolean append) throws IOException {
        BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file, append), encoding));
        buff.write(content);
        buff.flush();
        buff.close();
    }
}