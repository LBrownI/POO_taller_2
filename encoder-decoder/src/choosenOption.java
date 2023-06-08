import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class choosenOption {

    /**
     * @param hiddenMessage el mensaje que el usuario quiere ocultar
     * @return una ArrayList con el mensaje oculto convertido a bits
     */
    public static ArrayList<String> HiddenMessageToByteList(String hiddenMessage){
        String initialSpecialCharacter = "~~";
        hiddenMessage = initialSpecialCharacter.concat(hiddenMessage.concat("~~"));
        char[] inputTextCharArray = hiddenMessage.toCharArray();

        String[] binaryTextWithLeadingZeros = new String[hiddenMessage.length()];
        for (int i = 0; i < hiddenMessage.length(); i++) {
            String binaryString = Integer.toBinaryString(inputTextCharArray[i]);
            binaryTextWithLeadingZeros[i] = String.format("%8s", binaryString).replace(' ', '0');
        }

        ArrayList<String> bytesList = new ArrayList<>();
        for (int i = 0; i < hiddenMessage.length(); i++) {
            String[] splitBinaryLetter = binaryTextWithLeadingZeros[i].split("");
            bytesList.addAll(Arrays.asList(splitBinaryLetter).subList(0, 8));
        }
        return bytesList;
    }

    /**
     * Esconde el mensaje del usuario en la imagen
     * @param hiddenMessage el mensaje que el usuario quiere ocultar
     * @param inputPhoto la foto donde se quiere ocultar la foto
     * @param outputPhoto la foto con el mensaje oculto
     * @throws IOException arroja un error si el usuario especifica una imagen no existente
     */
    public void encode(String hiddenMessage, String inputPhoto, String outputPhoto) throws IOException {
        try{
            File file = new File(inputPhoto);
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            int bitesIterator = 0;
            ArrayList<String> bytesList = HiddenMessageToByteList(hiddenMessage);

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
                        if (bitesIterator > bytesList.size() - 1) {
                            allBytesWereWritten = true;
                            break;
                        }
                        if ((colors[i] % 10) != Integer.parseInt(bytesList.get(bitesIterator))) {
                            if ((colors[i] % 10) == 1) {
                                colors[i]--;
                            } else {
                                colors[i]++;
                            }
                        }
                        bitesIterator++;
                    }
                    int redBinaryToDecimal = Integer.parseInt(String.valueOf(colors[0]), 2);
                    int greenBinaryToDecimal = Integer.parseInt(String.valueOf(colors[1]), 2);
                    int blueBinaryToDecimal = Integer.parseInt(String.valueOf(colors[2]), 2);
                    color = new Color(redBinaryToDecimal, greenBinaryToDecimal, blueBinaryToDecimal);
                    image.setRGB(x, y, color.getRGB());
                    if (allBytesWereWritten) {
                        file = new File(outputPhoto);
                        ImageIO.write(image, "png", file);
                        System.out.println("Done!");
                        System.exit(0);
                    }
                }
            }
            System.out.println("Tu mensaje es muy largo para la imagen seleccionada. Prueba acortando el mensaje o seleccionar " +
                    "una foto de mayor resolución.");
            System.exit(1);
        } catch (javax.imageio.IIOException e) {
            System.out.println("La foto no se encontró en el directorio src");
        }
    }

    /**
     * Revela el usuario escondido en la imagen
     * @param outputPhoto imagen que se quiere decodificar
     * @throws IOException arroja un error si el usuario especifica una imagen no existente
     */
    public void decode(String outputPhoto) throws IOException {
        try{
            File file = new File(outputPhoto);
            BufferedImage image = ImageIO.read(file);

            int width = image.getWidth();
            int height = image.getHeight();

            ArrayList<Integer> bitMerge = new ArrayList<>();
            boolean saveHiddenMessage = false;
            ArrayList<Character> hiddenMessageList = new ArrayList<>();
            ArrayList<Character> TwoFirstDigits = new ArrayList<>();
            boolean checkTwoFirstDigits = true;

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
                        bitMerge.add(colors[i] % 10);
                        if (bitMerge.size() == 8) {
                            int fromArrayToInt = 0;
                            for (int j = 0; j < 8; j++) {
                                fromArrayToInt = fromArrayToInt * 10 + bitMerge.get(j);
                            }
                            bitMerge.clear();
                            int fromIntToDecimal = Integer.parseInt(String.valueOf(fromArrayToInt), 2);
                            char fromDecimalToChar = (char) fromIntToDecimal;
                            if(checkTwoFirstDigits){
                                TwoFirstDigits.add(fromDecimalToChar);
                            }
                            if (saveHiddenMessage) {
                                hiddenMessageList.add(fromDecimalToChar);
                            }
                            if (hiddenMessageList.size() > 0) {
                                if ((String.valueOf(hiddenMessageList.get(hiddenMessageList.size() - 1))).equals("~") && (String.valueOf(hiddenMessageList.get(hiddenMessageList.size() - 2))).equals("~")) {
                                    String hiddenMessage = "";
                                    for (int j = 0; j < hiddenMessageList.size() - 2; j++) {
                                        hiddenMessage = hiddenMessage.concat(String.valueOf(hiddenMessageList.get(j)));
                                    }
                                    System.out.println("El mensaje secreto es: " + hiddenMessage);
                                    System.exit(0);
                                }
                            }
                            if(TwoFirstDigits.size()==2){
                                if((String.valueOf(TwoFirstDigits.get(0))).equals("~") && (String.valueOf(TwoFirstDigits.get(1))).equals("~")){
                                    saveHiddenMessage = true;
                                    checkTwoFirstDigits = false;
                                }
                                else {
                                    System.out.println("No se encontró un mensaje oculto");
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
            }
        } catch (javax.imageio.IIOException e) {
            System.out.println("La foto no se encontró en el directorio src. Si el nombre de la foto tiene " +
                    "espacios, se debe de poner la imagen entre comillas simples. Ejemplo: 'imagen con espacios.png'");
        }
    }
}