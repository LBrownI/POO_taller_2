import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class choosenOption {

    public void encode(String hiddenMessage, String inputPhoto, String outputPhoto) throws IOException {

        File file = new File(inputPhoto);
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        int bitesIterator = 0;

        hiddenMessage = hiddenMessage.concat("~");          //add a key to indicate the end of the hidden message
        char[] inputTextCharArray = hiddenMessage.toCharArray();

        String[] binaryTextWithLeadingZeros = new String[hiddenMessage.length()];
        for (int i = 0; i < hiddenMessage.length(); i++) {
            String binaryString = Integer.toBinaryString(inputTextCharArray[i]);
            binaryTextWithLeadingZeros[i] = String.format("%8s", binaryString).replace(' ', '0');
        }

        ArrayList<String> bytesList = new ArrayList<>();
        for (int i = 0; i < hiddenMessage.length(); i++) {
            String[] splitBinaryLetter = binaryTextWithLeadingZeros[i].split("");
            for (int j = 0; j < 8; j++) {
                bytesList.add(splitBinaryLetter[j]);
            }
        }

        boolean allBytesWereWritten = false;

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
                    if(bitesIterator > bytesList.size()-1){
                        allBytesWereWritten = true;
                        break;
                    }
                    if((colors[i]%10) != Integer.parseInt(bytesList.get(bitesIterator))){
                        if((colors[i]%10) == 1){colors[i]--;}
                        else{colors[i]++;}
                    }
                    bitesIterator++;
                }
                int redBinaryToDecimal = Integer.parseInt(String.valueOf(colors[0]), 2);
                int greenBinaryToDecimal = Integer.parseInt(String.valueOf(colors[1]), 2);
                int blueBinaryToDecimal = Integer.parseInt(String.valueOf(colors[2]), 2);
                color = new Color(redBinaryToDecimal, greenBinaryToDecimal, blueBinaryToDecimal);
                image.setRGB(x, y, color.getRGB());
                if (allBytesWereWritten){
                    file = new File(outputPhoto);
                    ImageIO.write(image, "png", file);
                    System.out.println("Done!");
                    System.exit(0);
                }
            }
        }
    }

    public void decode(String outputPhoto) throws IOException {
        File file = new File(outputPhoto);
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        int letterBytePosition = 0;
        int charPosition = 0;

        for (int y = 0; y < 1; y++) {
            for (int x = 0; x < 40; x++) {
                int pixel = image.getRGB(x, y);

                Color color = new Color(pixel, true);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                ArrayList toDecimal = new ArrayList();

                int binaryRed = Integer.parseInt(Integer.toBinaryString(red));
                int binaryGreen = Integer.parseInt(Integer.toBinaryString(green));
                int binaryBlue = Integer.parseInt(Integer.toBinaryString(blue));

                int[] colors = {binaryRed, binaryGreen, binaryBlue};
                for (int i = 0; i < 3; i++) {
                    System.out.print(colors[i]%10);
                }
            }
        }
    }
}