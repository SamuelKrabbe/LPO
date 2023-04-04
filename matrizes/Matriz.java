package matrizes;
import java.util.Scanner;

public class Matriz{
    private int[][] matriz;
    int linha, coluna;
    Scanner sc = new Scanner(System.in);

    //Construtor
    public Matriz(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
        matriz = new int[linha][coluna];
    }

    //Métodos
    public int[][] getMat(){
        return this.matriz;
    }

    public void setMat(int[][] matriz){
        this.matriz = matriz;
    }

    public void lerMatriz(String M){
        for (int i = 0; i < linha; i++){
            for (int j = 0; j < coluna; j++){
                this.matriz[i][j] = sc.nextInt();
            }
        }
    }

    public void imprimirMatriz(String M){
        for (int i = 0; i < linha; i++){
            for (int j = 0; j < coluna; j++){
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public int somarElementosMatriz(String M){
        int soma = 0;
        for (int i = 0; i < linha; i++){
            for (int j = 0; j < coluna; j++){
                soma += this.matriz[i][j];
            }
        }
        return soma;
    }

    public void procurarElemento(int x){
        boolean estaNaMatriz = false;

        for (int i = 0; i < linha; i++){
            for (int j = 0; j < coluna; j++){
                if (this.matriz[i][j] == x){
                    estaNaMatriz = true;
                    System.out.println(String.format("O elemento %d está na linha %d coluna %d", x, i, j));
                    break;
                }
            }
            if (estaNaMatriz){
                break;
            }
        }
        if (!estaNaMatriz){
            System.out.println("O elemento não está na matriz");
        }
    }

    public static int[][] somarMatrizes (int[][] matA, int[][] matB){
        Matriz novaMat = new Matriz(matA.length, matA[0].length);

        if (matA.length == matB.length && matA[0].length == matB[0].length)
            for (int i = 0; i < matA.length; i++){
                for (int j = 0; j < matA[0].length; j++){
                    novaMat.matriz[i][j] = matA[i][j] + matB[i][j];
                }
            }
        return novaMat.matriz;
    }

    public static int[][] subtrairMatrizes (int[][] matA, int[][] matB){
        Matriz novaMat = new Matriz(matA.length, matA[0].length);

        if (matA.length == matB.length && matA[0].length == matB[0].length)
            for (int i = 0; i < matA.length; i++){
                for (int j = 0; j < matA[0].length; j++){
                    novaMat.matriz[i][j] = matA[i][j] - matB[i][j];
                }
            }
        return novaMat.matriz;
    }

    public void multiplicarEscalar(int x){
        for (int i = 0; i < linha; i++){
                for (int j = 0; j < coluna; j++){
                    this.matriz[i][j] = this.matriz[i][j] * x;
                }
            }
    }

    // public int[][] transposta(String M){
        
    // }

    // public void verificaSimetria(String M){
        
    // }

    // public void verificaIdentidade(String M){
        
    // }
}

