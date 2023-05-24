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
        BufferedImage image= ImageIO.read(file);

        int x = 0;
        int y = 0;

        int pixel = image.getRGB(x, y);

        Color color = new Color(pixel, true);

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        int binaryRed = Integer.parseInt(Integer.toBinaryString(red));
        int binaryGreen = Integer.parseInt(Integer.toBinaryString(green));
        int binaryBlue = Integer.parseInt(Integer.toBinaryString(blue));

        System.out.println("rojo: " +red);
        System.out.println("en binario es: " +binaryRed);
        System.out.println("verde: " +green);
        System.out.println("en binario es: " +binaryRed);
        System.out.println("azul: " +blue);
        System.out.println("en binario es: " +binaryRed);


        //convierte el input del usuario a un array de bytes por cada caracter
        String inputWord = "hola#";
        byte[] bytes = inputWord.getBytes();

        String[] inputWordBytesArray = new String[inputWord.length()];
        int wordToByteIterator = 0;
        for(int iterator : bytes){
            inputWordBytesArray[wordToByteIterator] = Integer.toBinaryString(iterator);
            wordToByteIterator++;
        }

        ArrayList<Integer> allBytesFromText = new ArrayList<>();
        for (int i = 0; i < inputWord.length(); i++) {
            String[] letterBytes = inputWordBytesArray[i].split("");
            System.out.println(Arrays.toString(letterBytes));
            for (int j = 0; j < letterBytes.length; j++) {
                allBytesFromText.add(Integer.parseInt(letterBytes[j]));
            }
        }

        int charPosition = 0;
        int[] colors = {binaryRed, binaryGreen, binaryBlue};
        for (int i = 0; i < 3; i++) {
            if((colors[i] % 10) != Integer.parseInt(inputWordBytesArray[charPosition])){
                if((colors[i] % 10) == 1){
                    colors[i]--;
                }
                else{
                    colors[i]++;
                }
                charPosition++;
            }
        }
        int redBinaryToDecimal = Integer.parseInt(String.valueOf(colors[0]),2);
        int greenBinaryToDecimal = Integer.parseInt(String.valueOf(colors[1]),2);
        int blueBinaryToDecimal = Integer.parseInt(String.valueOf(colors[2]),2);
        color = new Color(redBinaryToDecimal, greenBinaryToDecimal, blueBinaryToDecimal);
        image.setRGB(x, y, color.getRGB());

        
        if(charPosition > 7){
            charPosition = 0;
            //change position of the char to operate
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