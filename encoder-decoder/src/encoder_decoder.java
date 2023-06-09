/**
 * este programa oculta un texto en una imagen utilizando la técnica de Esteganografía.
 * Para mayor información, leer README.md
 */
import java.io.IOException;
/**
* Verifica que los inputs ingresados por el usuario son adecuados según el tipo de elección (encode o decode). Si están bien, sigue con su ejecución.
* En caso que no estén bien, maneja los errores.
*/
public class encoder_decoder {

    public static void main(String[] args) throws IOException {

        String encodeOrDecode = "";
        String hiddenMessage = "";
        String inputPhoto = "";
        String outputPhoto = "";

        choosenOption option = new choosenOption();
        try {
            encodeOrDecode = args[0];
            if (encodeOrDecode.equals("decode")) {
                try {
                    outputPhoto = args[1];
                    option.decode(outputPhoto);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Pon todos los argumentos necesarios para que el programa ejecute " +
                            "correctamente (decode 'out.png').");
                    System.exit(1);
                }
            }
            else {
                try {
                    hiddenMessage = args[1];
                    inputPhoto = args[2];
                    outputPhoto = args[3];
                    option.encode(hiddenMessage, inputPhoto, outputPhoto);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Pon todos los argumentos necesarios para que el programa ejecute " +
                            "correctamente (encode 'texto a ocultar' 'image.png' 'out.png').");
                    System.exit(1);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Elige que opcion quieres (encode o decode).");
            System.exit(1);
        }
    }
}
