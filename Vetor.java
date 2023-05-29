import java.util.Scanner;
import java.util.InputMismatchException;


public class Vetor {
    public static <T extends Comparable<T>> T maiorValor(T[] vetor) {
        if (vetor == null || vetor.length == 0) {
            return null;
        }
        
        T maior = vetor[0];
        
        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i].compareTo(maior) > 0) {
                maior = vetor[i];
            }
        }
        return maior;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Integer[] vetorInt = new Integer[n];
        String[] vetorStr = new String[n];

        for (int i = 0; i < n; i++) {
            try {
                vetorInt[i] = scanner.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("Entrada Inválida para Inteiros");
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            vetorStr[i] = scanner.next();
        }

        scanner.close();

        try{
            Integer maiorInt = Vetor.maiorValor(vetorInt);
            System.out.println("Maior inteiro: " + maiorInt);
        }catch(Exception e){}
        String maiorStr = Vetor.maiorValor(vetorStr);
        System.out.println("Maior string: " + maiorStr);
    }
}

