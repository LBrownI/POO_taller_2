import javax.imageio.ImageIO;
import javax.imageio.ImageIO.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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

        String binaryRed = Integer.toBinaryString(red);
        String binaryGreen = Integer.toBinaryString(green);
        String binaryBlue = Integer.toBinaryString(blue);

        String[] binaryRedArray = binaryRed.split("");
        String[] binaryGreenArray = binaryGreen.split("");
        String[] binaryBlueArray = binaryBlue.split("");

        System.out.println("rojo: " +red);
        System.out.println("en binario es: " +binaryRed);
        System.out.println("verde: " +green);
        System.out.println("en binario es: " +binaryRed);
        System.out.println("azul: " +blue);
        System.out.println("en binario es: " +binaryRed);

        char ch = 'a';
        int ascii = ch;
        String str = Integer.toBinaryString(ch);
        System.out.println("la palabra a en hex es: " +ascii);
        System.out.println("la palabra a en binario es: " +str);

        //separar el decimal en un array de varias weas

        String[] binaryCharArray = str.split("");

        binaryRedArray[binaryRedArray.length-1] = binaryCharArray[0];


        StringBuffer fullAscii = new StringBuffer();
        for (int i = 0; i < binaryRedArray.length; i++) {
            fullAscii.append(binaryRedArray[i]);
        }
        int converter = Integer.parseInt(String.valueOf(fullAscii), 2);


        System.out.println("en int el resultado es: " +converter);

        /**

         color = new Color(red, green, blue);
         image.setRGB(x, y, color.getRGB());

         //Creates another png with the previous settings
        file = new File("C:\\Users\\alanb\\Desktop\\modified_image.png");
        ImageIO.write(image, "png", file);
        System.out.println("Doners");
         */
    }
}