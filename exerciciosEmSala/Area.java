package exerciciosEmSala;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Area {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            String nome = scanner.next();
            int numeroLados = scanner.nextInt();
            double diagonal1 = scanner.nextDouble();
            double diagonal2 = scanner.nextDouble();

            Losango losango = new Losango(nome, numeroLados, diagonal1, diagonal2);
            System.out.println(losango);

            diagonal1 = scanner.nextDouble();
            diagonal2 = scanner.nextDouble();

            losango.setDiagonal1(diagonal1);
            losango.setDiagonal2(diagonal2);

            System.out.println(losango);
            scanner.close();
        } catch (InputMismatchException e) {
            System.out.println("Entrada Inválida!");
        }
    }
}

interface Forma {
    double calcularArea();
}

abstract class Poligono implements Forma {
    private String nome;
    private int numeroLados;

    public Poligono(String nome, int numeroLados) {
        this.nome = nome;
        this.numeroLados = numeroLados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numeroLados;
    }

    public void setNumero(int numeroLados) {
        this.numeroLados = numeroLados;
    }
}

class Losango extends Poligono {
    private double diagonal1;
    private double diagonal2;

    public Losango(String nome, int numeroLados, double diagonal1, double diagonal2) {
        super(nome, numeroLados);
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
    }

    public double getDiagonal1() {
        return diagonal1;
    }

    public void setDiagonal1(double diagonal1) {
        this.diagonal1 = diagonal1;
    }

    public double getDiagonal2() {
        return diagonal2;
    }

    public void setDiagonal2(double diagonal2) {
        this.diagonal2 = diagonal2;
    }

    @Override
    public double calcularArea() {
        return (diagonal1 * diagonal2) / 2;
    }

    @Override
    public String toString() {
        return this.getNome() + ", Area= " + calcularArea();
    }
}
