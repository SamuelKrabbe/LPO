package exerciciosEmSala;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ListGeneric {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = lerQuantidadeElementos(scanner);
        ArrayList<Integer> integerList = lerElementosInteger(scanner, n);

        if(n == 0 || integerList == null)
            return;
        
        ArrayList<Double> doubleList = lerElementosDouble(scanner, n);

        if (doubleList == null)
            return;

        System.out.println("Lista Inteiros: " + integerList);
        System.out.println("Soma Inteiros: " + somaList(integerList));

        System.out.println("Lista Doubles: " + doubleList);
        System.out.println("Soma Doubles: " + somaList(doubleList));

        scanner.close();
    }

    public static int lerQuantidadeElementos(Scanner scanner) {
        int n = 0;
        boolean inputValido = false;

        while (!inputValido) {
            try {
                n = scanner.nextInt();
                if (n > 2)
                    inputValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada Inválida");
                return 0;
            }
        }
        return n;
    }

    public static ArrayList<Integer> lerElementosInteger(Scanner scanner, int n) {
        ArrayList<Integer> integerList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean inputValido = false;

            while (!inputValido) {
                try {
                    int elemento = scanner.nextInt();
                    integerList.add(elemento);
                    inputValido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada Inválida");
                    return null;
                }
            }
        }
        return integerList;
    }

    public static ArrayList<Double> lerElementosDouble(Scanner scanner, int n) {
        ArrayList<Double> doubleList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean inputValido = false;

            while (!inputValido) {
                try {
                    double elemento = scanner.nextDouble();
                    doubleList.add(elemento);
                    inputValido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada Inválida");
                    return null;
                }
            }
        }
        return doubleList;
    }

    public static double somaList(ArrayList<? extends Number> list) {
        double sum = 0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }
}
