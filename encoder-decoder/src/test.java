import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

        ArrayList<Character> hiddenMessageList = new ArrayList<>();
        ArrayList<Character> a = new ArrayList<>();
        ArrayList<Character> b = new ArrayList<>();


        int iterator = 9;

        hiddenMessageList.add('a');
        hiddenMessageList.add('b');
        hiddenMessageList.add('c');
        hiddenMessageList.add('d');
        hiddenMessageList.add('e');
        hiddenMessageList.add('f');
        hiddenMessageList.add('g');
        hiddenMessageList.add('h');
        hiddenMessageList.add('i');
        hiddenMessageList.add('j');
        hiddenMessageList.add('k');
        hiddenMessageList.add('l');
        hiddenMessageList.add('m');
        hiddenMessageList.add('n');
        hiddenMessageList.add('o');
        hiddenMessageList.add('p');
        hiddenMessageList.add('q');
        hiddenMessageList.add('r');
        hiddenMessageList.add('s');
        hiddenMessageList.add('t');
        hiddenMessageList.add('v');
        hiddenMessageList.add('w');
        hiddenMessageList.add('x');
        hiddenMessageList.add('y');
        hiddenMessageList.add('z');
        hiddenMessageList.add('1');
        hiddenMessageList.add('2');
        hiddenMessageList.add('3');
        hiddenMessageList.add('4');
        hiddenMessageList.add('5');
        hiddenMessageList.add('6');
        hiddenMessageList.add('7');
        hiddenMessageList.add('8');
        hiddenMessageList.add('9');

        for (int i = 0; i < 8; i++) {
            a.add(hiddenMessageList.get(i));
        }
        System.out.println("Primera lista: " +a);

        int xPosicion = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 3; i++) {
                a.remove(0);
                a.add(hiddenMessageList.get(iterator));
                iterator++;
            }
            xPosicion++;
            System.out.println("Segunda lista: " +a);
        }


        for (int i = xPosicion; i < xPosicion*12+8; i++) {
            b.add(hiddenMessageList.get(i));
        }
        System.out.println("Tercera lista: " +b);
    }
}