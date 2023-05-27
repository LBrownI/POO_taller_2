import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class choosenOption {

    public void encode(String hiddenMessage, String inputPhoto, String outputPhoto) throws IOException {

        File file = new File(inputPhoto);
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        int letterBytePosition = 0;
        int charPosition = 0;

        char[] inputTextCharArray = hiddenMessage.toCharArray();

        String[] binaryTextWithLeadingZeros = new String[hiddenMessage.length()];
        for (int i = 0; i < hiddenMessage.length(); i++) {
            String binaryString = Integer.toBinaryString(inputTextCharArray[i]);
            binaryTextWithLeadingZeros[i] = String.format("%8s", binaryString).replace(' ', '0');
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);

                Color color = new Color(pixel, true);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int binaryRed = Integer.parseInt(Integer.toBinaryString(red));
                int binaryGreen = Integer.parseInt(Integer.toBinaryString(green));
                int binaryBlue = Integer.parseInt(Integer.toBinaryString(blue));

                int[] colors = {binaryRed, binaryGreen, binaryBlue};
                for (int i = 0; i < 3; i++) {
                    String[] letterBytes = binaryTextWithLeadingZeros[letterBytePosition].split("");
                    if (charPosition > 7){
                        charPosition = 0;
                        if (letterBytePosition != hiddenMessage.length()-1){
                            letterBytePosition++;
                        }
                        else{
                            break;
                        }
                    }
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
            }
        }
        file = new File(outputPhoto);
        ImageIO.write(image, "png", file);
        System.out.println("Done!");
    }

}