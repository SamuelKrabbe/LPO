package exerciciosEmSala;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Produto implements Comparable<Produto> {
    private int codigo;
    private String nome;

    public Produto(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    @Override
    public int compareTo(Produto produto) {
        int dif = this.nome.compareTo(produto.nome);
        if (dif < 0)
            return -1;
        else if (dif > 0)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return this.codigo + ": " + this.nome;
    }

    public static void main(String[] args) throws Exception {
        int codigo;
        String nomeProd;
        Scanner sc = new Scanner(System.in);
        List<Produto> lista = new ArrayList<Produto>();

        for (int i = 0; i < 5; i++) {
            codigo = sc.nextInt();
            nomeProd = sc.next();
            Produto produto = new Produto(codigo, nomeProd);
            lista.add(produto);
        }

        System.out.println(lista);
        lista.sort(null);
        System.out.println(lista);
        System.out.println("Maior " + lista.get(lista.size() - 1));
        sc.close();
    }
}