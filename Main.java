import java.util.Scanner;
import matrizes.Matriz;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int linha = sc.nextInt();
        int coluna = sc.nextInt();
        int soma;
        Matriz A = new Matriz(linha, coluna);
        Matriz B = new Matriz(linha, coluna);
        Matriz C = new Matriz(linha, coluna);;

        A.lerMatriz("A");
        B.lerMatriz("B");
        A.imprimirMatriz("A");
        B.imprimirMatriz("B");
        A.procurarElemento(2);
        soma = A.somarElementosMatriz("A");
        C.setMat(Matriz.somarMatrizes(A.getMat(), B.getMat()));
        C.setMat(Matriz.subtrairMatrizes(A.getMat(), B.getMat()));

        System.out.println(soma);
    }
}
