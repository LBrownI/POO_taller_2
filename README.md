# POO_taller_2
Programa que utiliza técnicas de [Esteganografía](https://es.wikipedia.org/wiki/Esteganograf%C3%ADa)
## Setup
+**1)** Descargar carpeta encode-decode.
+**2)** Colocar la imagen (en la cual se quiere ocultar el mensaje) al interior de la carpeta "src".
+**3)** Apretar tecla "Windows" y buscar "Windows PowerShell" o "cmd".
a <br />
b <br />
c
+**4)** Escribir "cd" y después de un espacio colocar la dirección de la carpeta "src" y apretar tecla "Enter".
+**5)** Luego colocar "javac encoder_decoder.java" para compilar el código.
## Modo de uso
+**-** Para codificar un mensaje al interior de de la imagen se ocupa *java encoder_decoder encode 'mensaje oculto' 'input.png' 'output.png'*
+**-** Para decodificar un mensaje de una imagen se ocupa *java encoder_decoder decode 'output.png'*
+**(Asegurarse de colocar las comillas simples ' para que pueda identificar espacios en su texto o nombre de la imagen)**
