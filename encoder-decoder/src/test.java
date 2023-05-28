import java.util.Arrays;

public class test {
    public static void main(String[] args){

        String a = "alo";
        char[] b = a.toCharArray();

        String[] textWithLeadingZeros = new String[a.length()];
        String[] inputWordBytesArray = new String[a.length()*8];
        for (int i = 0; i < a.length(); i++) {
            String binaryString = Integer.toBinaryString(b[i]);
            textWithLeadingZeros[i] = String.format("%8s", binaryString).replace(' ', '0');
        }
        System.out.println(textWithLeadingZeros[0]);
    }
}