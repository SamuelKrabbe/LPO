import java.util.Scanner;

public class Fatura {
    private String item;
    private int quantidade;
    private double valor;

    //Construtor
    public Fatura(String item, int quantidade, double valor) {
        this.item = item;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    //Getters
    public double getPrecoTotal() {
        return this.quantidade * this.valor;
    }

    //Methods
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nomeProduto = sc.nextLine();
        int quantidade = sc.nextInt();
        int valor = sc.nextInt();
        Fatura fatura = new Fatura(nomeProduto, quantidade, valor);
        System.out.println(String.format("%d %s a R$ %.1f = R$ %.1f", fatura.quantidade, fatura.item, fatura.valor, fatura.getPrecoTotal()));
        sc.close();
    }
}
