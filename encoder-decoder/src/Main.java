import javax.imageio.ImageIO;
import javax.imageio.ImageIO.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\alanb\\IdeaProjects\\encoder-decoder\\image\\image.png");
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < 1; y++) {           //CHANGE THE "1" OF THE FIRST FOR TO HEIGHT LATER!!!1!!11!!1!!!!
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);

                Color color = new Color(pixel, true);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int binaryRed = Integer.parseInt(Integer.toBinaryString(red));
                int binaryGreen = Integer.parseInt(Integer.toBinaryString(green));
                int binaryBlue = Integer.parseInt(Integer.toBinaryString(blue));

                /**
                System.out.println("rojo: " + red);
                System.out.println("en binario es: " + binaryRed);
                System.out.println("verde: " + green);
                System.out.println("en binario es: " + binaryRed);
                System.out.println("azul: " + blue);
                System.out.println("en binario es: " + binaryRed);
                 */


                //convierte el input del usuario a un array de bytes por cada caracter
                String inputWord = "hola#";
                byte[] bytes = inputWord.getBytes();

                String[] inputWordBytesArray = new String[inputWord.length()];
                int wordToByteIterator = 0;
                for (int iterator : bytes) {
                    inputWordBytesArray[wordToByteIterator] = Integer.toBinaryString(iterator);
                    wordToByteIterator++;
                }

                int letterBytePosition = 0;
                int charPosition = 0;
                int letterBytesLenght = 0;

                int[] colors = {binaryRed, binaryGreen, binaryBlue};
                for (int i = 0; i < 3; i++) {
                    String[] letterBytes = inputWordBytesArray[letterBytePosition].split("");
                    letterBytesLenght = letterBytes.length;
                    if ((colors[i] % 10) != Integer.parseInt(letterBytes[charPosition])) {
                        if ((colors[i] % 10) == 1) {
                            colors[i]--;
                        } else {
                            colors[i]++;
                        }
                    }
                    charPosition++;
                }
                int redBinaryToDecimal = Integer.parseInt(String.valueOf(colors[0]), 2);
                int greenBinaryToDecimal = Integer.parseInt(String.valueOf(colors[1]), 2);
                int blueBinaryToDecimal = Integer.parseInt(String.valueOf(colors[2]), 2);
                color = new Color(redBinaryToDecimal, greenBinaryToDecimal, blueBinaryToDecimal);
                image.setRGB(x, y, color.getRGB());


                if (charPosition > letterBytesLenght) {
                    charPosition = 0;
                    letterBytePosition++;
                    //change position of the char to operate
                }
            }
        }


        /**
         StringBuffer fullAscii = new StringBuffer();
         for (int i = 0; i < binaryRedArray.length; i++) {
         fullAscii.append(binaryRedArray[i]);
         }
         int converter = Integer.parseInt(String.valueOf(fullAscii), 2);


         System.out.println("en int el resultado es: " +converter);

         */

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