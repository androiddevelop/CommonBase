package me.codeboy.common.base.io;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * 文件操作
 *
 * @author Yuedong Li
 */
public class CBFile {

    /**
     * 按照UTF-8编码得到文件内容
     *
     * @param filePath
     *         源文件路径
     * @return 文件内容
     * @throws IOException
     */
    public String getFileContent(String filePath) throws IOException {
        return getFileContent(filePath, "UTF-8");
    }

    /**
     * 按照指定编码得到文件内容
     *
     * @param filePath
     *         源文件路径
     * @param encoding
     *         文件编
     * @return 文件内容
     * @throws IOException
     */
    public String getFileContent(String filePath, String encoding)
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
     * @param filePath
     *         源文件路径
     * @return 文件内容
     * @throws IOException
     */
    public List<String> getFileContentAsList(String filePath)
            throws IOException {
        return Arrays.asList(getFileContent(filePath).split("\\n"));
    }

    /**
     * 按照UTF-8编码得到文件内容,保存到list中
     *
     * @param file
     *         源文件
     * @return 文件内容
     * @throws IOException
     */
    public List<String> getFileContentAsList(File file)
            throws IOException {
        return getFileContentAsList(file, "UTF-8");
    }

    /**
     * 按照指定编码得到文件内容,保存list中
     *
     * @param filePath
     *         源文件路径
     * @param encoding
     *         文件编
     * @return 文件内容
     * @throws IOException
     */
    public List<String> getFileContentAsList(String filePath, String encoding)
            throws IOException {
        return Arrays.asList(getFileContent(filePath, encoding).split("\\n"));
    }

    /**
     * 按照指定编码得到文件内容,保存list中
     *
     * @param file
     *         源文件
     * @param encoding
     *         文件编
     * @return 文件内容
     * @throws IOException
     */
    public List<String> getFileContentAsList(File file, String encoding)
            throws IOException {
        return Arrays.asList(getFileContent(new FileInputStream(file), encoding).split("\\n"));
    }


    /**
     * 按照UTF-8编码得到输入流内容
     *
     * @param file
     *         文件
     * @return 文件内容
     * @throws IOException
     */
    public String getFileContent(File file) throws IOException {
        return getFileContent(new FileInputStream(file));
    }

    /**
     * 按照UTF-8编码得到输入流内容
     *
     * @param is
     *         输入流
     * @return 文件内容
     * @throws IOException
     */
    public String getFileContent(InputStream is) throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(is,
                "UTF-8"));
        String content = getContent(buff);
        buff.close();
        return content;
    }

    /**
     * 按照指定编码得到输入流内容
     *
     * @param is
     *         输入流
     * @param encoding
     *         文件编
     * @return 文件内容
     * @throws IOException
     */
    public String getFileContent(InputStream is, String encoding)
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
     * @param buff
     *         bufferedReader
     * @return 内容
     * @throws IOException
     */
    private String getContent(BufferedReader buff) throws IOException {
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
     * @param content
     *         文件内容
     * @param filePath
     *         文件路径
     * @throws IOException
     */
    public void saveContentToFile(String content, String filePath)
            throws IOException {
        saveContentToFile(content, filePath, "UTF-8");
    }

    /**
     * 保存网络文件到指定路径
     *
     * @param url
     *         文件地址
     * @param filePath
     *         文件路径
     * @throws IOException
     */
    public void saveContentToFile(URL url, String filePath) throws IOException {
        InputStream is = url.openStream();
        FileOutputStream fos = new FileOutputStream(filePath);
        byte[] b = new byte[1024];
        int len = 0;
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
     * @param content
     *         文件内容
     * @param filePath
     *         文件路径
     * @param encoding
     *         编码
     * @throws IOException
     */

    public void saveContentToFile(String content, String filePath,
                                  String encoding) throws IOException {
        BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath), encoding));
        buff.write(content);
        buff.flush();
        buff.close();
    }
}