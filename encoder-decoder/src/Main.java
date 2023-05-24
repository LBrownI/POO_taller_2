import javax.imageio.ImageIO;
import javax.imageio.ImageIO.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

        String fullWord = "hola#";
        String[] fullWordArray = fullWord.split("");


        System.out.println("rojo: " +red);
        System.out.println("en binario es: " +binaryRed);
        System.out.println("verde: " +green);
        System.out.println("en binario es: " +binaryRed);
        System.out.println("azul: " +blue);
        System.out.println("en binario es: " +binaryRed);


        //convierte el input del usuario a un array de bytes por cada caracter
        String inputWord = "hola";
        byte[] bytes = inputWord.getBytes();

        String[] inputWordBytesArray = new String[inputWord.length()];
        int wordToByteIterator = 0;
        for(int iterator : bytes){
            inputWordBytesArray[wordToByteIterator] = Integer.toBinaryString(iterator);
            wordToByteIterator++;
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
        color = new Color(colors[0], colors[1], colors[2]);
        image.setRGB(x, y, color.getRGB());
        
        if(charPosition > 7){
            charPosition = 0;
            //change position of the char to operate
        }


        System.out.println(binaryRed);



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