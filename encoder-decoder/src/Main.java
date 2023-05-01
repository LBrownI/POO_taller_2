import javax.imageio.ImageIO;
import javax.imageio.ImageIO.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\alanb\\Desktop\\image.png");
        BufferedImage image= ImageIO.read(file);

        int x = 0;
        int y = 0;

        int pixel = image.getRGB(x, y);

        Color color = new Color(pixel, true);

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        System.out.println(red);
        System.out.println(green);
        System.out.println(blue);
    }
}