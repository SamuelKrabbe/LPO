package exerciciosEmSala;
import java.util.Scanner;


interface Tributavel {
    double calcularIPI();
}

abstract class Produto {
    private String nome;
    private float valor;

    public Produto(String nome, float valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}

class ProdutoNatural extends Produto {
    private boolean integral;

    public ProdutoNatural(String nome, float valor, boolean integral) {
        super(nome, valor);
        this.integral = integral;
    }

    public boolean ehIntegral() {
        return integral;
    }

    public void setIntegral(boolean integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "Produto: " + getNome() + ", R$: " + String.format("%.2f", super.getValor()) + ", " + (integral ? "é Integral" : "não é Integral");
    }
}

class ProdutoIndustrializado extends Produto implements Tributavel {
    private float frete;
    private float seguro;

    public ProdutoIndustrializado(String nome, float valor, float frete, float seguro) {
        super(nome, valor);
        this.frete = frete;
        this.seguro = seguro;
    }

    public float getFrete() {
        return frete;
    }

    public void setFrete(float frete) {
        this.frete = frete;
    }

    public float getSeguro() {
        return seguro;
    }

    public void setSeguro(float seguro) {
        this.seguro = seguro;
    }

    @Override
    public double calcularIPI() {
        float baseCalculo = getValor() + frete + seguro;
        return baseCalculo * (10.0 / 100.0);
    }

    @Override
    public String toString() {
        return "Produto: " + getNome() + ", R$: " + String.format("%.2f", super.getValor()) + ", IPI: " + String.format("%.2f", calcularIPI());

    }
}

public class questao1P2 {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            // Criar objeto ProdutoNatural
            String nomeNatural = sc.next();
            float valorNatural = sc.nextFloat();
            boolean integral = sc.next().equalsIgnoreCase("true");
            ProdutoNatural produtoNatural = new ProdutoNatural(nomeNatural, valorNatural, integral);

            // Criar objeto ProdutoIndustrializado
            String nomeIndustrializado = sc.next();
            float valorIndustrializado = sc.nextFloat();
            float frete = sc.nextFloat();
            float seguro = sc.nextFloat();
            ProdutoIndustrializado produtoIndustrializado = new ProdutoIndustrializado(
                    nomeIndustrializado, valorIndustrializado, frete, seguro);

            // Imprimir informações iniciais
            System.out.println(produtoNatural);
            System.out.println(produtoIndustrializado);
            System.out.println("Atualização de valores");
            // Alterar valor do Produto Natural
            float novoValorNatural = sc.nextFloat();
            produtoNatural.setValor(novoValorNatural);

            // Alterar frete do Produto Industrializado
            float novoFrete = sc.nextFloat();
            produtoIndustrializado.setFrete(novoFrete);

            // Imprimir informações após alterações
            System.out.println(produtoNatural);
            System.out.println(produtoIndustrializado);

            sc.close();
        } catch (Exception e) {
            System.out.println("Entrada Inválida!");
        }
    }
}


