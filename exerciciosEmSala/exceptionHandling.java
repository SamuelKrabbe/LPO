package exerciciosEmSala;
import java.util.Scanner;

public class exceptionHandling {
    public static void main(String[] args) {
        int[] vetor = {500, 1000, 1500, 3000, 5000};
        Scanner sc = new Scanner(System.in);
        int divisor;

        try {
            divisor = sc.nextInt();
            if (divisor == 0) {
                System.out.println("Erro de divisão por zero\n");
            }else{
                for (int i = 0; i < 4; i++) 
                    System.out.print(vetor[i] / divisor + " ");
                System.out.println(vetor[4] / divisor);
            }
        } catch (Exception e) {
            System.out.println("Caractere inválido\n");
        }
        finally {
            sc.close();
        }
    }
}
