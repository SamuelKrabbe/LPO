package exerciciosEmSala;
import java.util.Scanner;

public class Matriz{
    private int[][] matriz;
    private int xColuna;
    private static final int linha = 6;
    private static final int coluna = 6;
    Scanner sc = new Scanner(System.in);

    //Construtor
    public Matriz(){
        matriz = new int[linha][coluna];
    }

    //Métodos
    public int[][] getMat(){
        return this.matriz;
    }

    public void setMat(int[][] matriz){
        this.matriz = matriz;
    }

    public void setXColuna(){
        this.xColuna = sc.nextInt();
    }

    public void lerMatriz(){
        for (int i = 0; i < linha; i++){
            for (int j = 0; j < coluna; j++){
                this.matriz[i][j] = sc.nextInt();
            }
        }
    }

    public void procurarMaiorElemento(){
        int maior = this.matriz[0][this.xColuna];
        int indexMaior = 0;

        for (int i = 1; i < linha; i++){
            if (this.matriz[i][this.xColuna] > maior){
                maior = this.matriz[i][this.xColuna];
                indexMaior = i;
            }
        }
        System.out.println(maior);
        System.out.println(String.format("%d %d", indexMaior, this.xColuna));
    }

    public static void main(String[] args){
        Matriz matriz = new Matriz();
        matriz.setXColuna();
        matriz.lerMatriz();
        matriz.procurarMaiorElemento();
    }
}