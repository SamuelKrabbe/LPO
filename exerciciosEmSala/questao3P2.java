package exerciciosEmSala;
import java.util.InputMismatchException;
import java.util.Scanner;

public class questao3P2 {
    public static <T extends Comparable<T>> int procuraValor(T[][] matriz, T valor) {
        int contador = 0;

        for (T[] linha : matriz) {
            for (T elemento : linha) {
                if (elemento.compareTo(valor) == 0) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            int ordemMatriz = sc.nextInt();

            Integer[][] matrizInteiros = new Integer[ordemMatriz][ordemMatriz];
            ;
            for (int i = 0; i < ordemMatriz; i++) {
                for (int j = 0; j < ordemMatriz; j++) {
                    matrizInteiros[i][j] = sc.nextInt();
                }
            }
            Integer elementoInteiroParaProcura = sc.nextInt();

            String[][] matrizStrings = new String[ordemMatriz][ordemMatriz];
            for (int i = 0; i < ordemMatriz; i++) {
                for (int j = 0; j < ordemMatriz; j++) {
                    matrizStrings[i][j] = sc.next();
                }
            }
            String elementoStringParaProcura = sc.next();

            // Procura e contagem do elemento na matriz de Integer
            int ocorrenciasDoElementoInteiro = procuraValor(matrizInteiros, elementoInteiroParaProcura);

            // Procura e contagem do elemento na matriz de String
            int ocorrenciasDoElementoString = procuraValor(matrizStrings, elementoStringParaProcura);

            // Impressão dos resultados
            System.out.println(ocorrenciasDoElementoInteiro + " ocorrências de " + elementoInteiroParaProcura + " na matriz de Inteiros");
            System.out.println(ocorrenciasDoElementoString + " ocorrências de " + elementoStringParaProcura + " na matriz de Strings");
        } catch (InputMismatchException e) {
            System.out.println("Entrada Inválida para Inteiros");
        }

        sc.close();
    }
}
