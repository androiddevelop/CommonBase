package me.codeboy.common.base.io;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片操作
 * <p>
 * Created by Yuedong Li on 11/21/15.
 */
public class CBImage {
    private BufferedImage image;
    private int width; // 图片宽度
    private int height; // 图片高度

    public CBImage(BufferedImage bufferedImage) {
        this.image = bufferedImage;
    }

    public CBImage(File file) {
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在图片上写字
     *
     * @param text     字符串
     * @param fontSize 字体大小
     * @param color    字体颜色
     * @return 图片
     */
    public BufferedImage drawText(String text, int fontSize, Color color) {
        Graphics g = image.getGraphics();
        g.setColor(color);
        Font f = new Font("宋体", Font.BOLD, fontSize);
        g.setFont(f);
        int len = text.length();
        g.drawString(text, (width - fontSize * len) / 2,
                (height + (int) (fontSize / 1.5)) / 2);
        g.dispose();
        return image;
    }

    /**
     * 缩放图片
     *
     * @param scaleW 水平缩放比例
     * @param scaleH 垂直缩放比例
     * @return bufferedImage
     */
    public BufferedImage scale(double scaleW, double scaleH) {
        width = (int) (width * scaleW);
        height = (int) (height * scaleH);

        BufferedImage newImage = new BufferedImage(width, height,
                image.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        image = newImage;
        return image;
    }

    /**
     * 旋转90度旋转
     *
     * @return 对应图片
     */
    public BufferedImage rotate() {
        BufferedImage dest = new BufferedImage(height, width,
                BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                dest.setRGB(height - j - 1, i, image.getRGB(i, j));
            }
        image = dest;
        return image;
    }

    /**
     * 保存图片
     *
     * @param file 图片文件
     */
    public void save(File file) {
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到对应的图片
     *
     * @return bufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }
}
