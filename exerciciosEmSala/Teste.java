package exerciciosEmSala;
import java.util.Scanner;

//INTERFACES

interface Motorizado {
    void start();
};

interface Passageiros {
    void numDePassageiros();
};

interface Carga {
    void pesoMax();
};

interface Eletrico extends Motorizado {
    void carregarBateria();
    void tempoDaBateria();
};

interface Combustivel extends Motorizado {
    void consumoMedio();
    void restoCombustivel();
};

//CLASSES

public class Teste {
    public static void main(String[] args) {
        int numPassageiros;
        int pesoMax;
        double consumoMedio;
        double restoCombustivel;
        Scanner sc = new Scanner(System.in);

        numPassageiros = sc.nextInt();
        pesoMax = sc.nextInt();
        consumoMedio = sc.nextDouble();
        restoCombustivel = sc.nextDouble();

        Moto myMoto = new Moto(numPassageiros, pesoMax, consumoMedio, restoCombustivel);
        myMoto.start();
        myMoto.drive();
        myMoto.numDePassageiros();
        myMoto.pesoMax();
        myMoto.restoCombustivel();
        myMoto.consumoMedio();

        sc.close();
    };
}

abstract class Veiculo implements Motorizado, Passageiros, Carga {
    public void drive() {
        System.out.println("Driving...\n");
    };

    public void start() {
        System.out.println("Starting...\n");
    };
};

class Moto extends Veiculo implements Combustivel {
    private int numPassageiros;
    private int pesoMax;
    private double consumoMedio;
    private double restoCombustivel;

    //Contrutor
    Moto(int numDePassageiros, int pesoMax, double consumoMedio, double restoCombustivel) {
        this.numPassageiros = numDePassageiros;
        this.pesoMax = pesoMax;
        this.consumoMedio = consumoMedio;
        this.restoCombustivel = restoCombustivel;   
    };

    //Métodos
    public void numDePassageiros() {
        System.out.println(String.format("A moto permite %d passageiros...\n", this.numPassageiros));
    };
    
    public void pesoMax() {
        System.out.println(String.format("A moto permite no máximo %d kg...\n", this.pesoMax));
    };

    public void consumoMedio() {
        System.out.println(String.format("A moto consome em média %.2f l/km...\n", this.consumoMedio));
    };

    public void restoCombustivel() {
        System.out.println(String.format("A moto tem %.2f litros de combustível sobrando...\n", this.restoCombustivel));
    };
};

class Carro extends Veiculo implements Combustivel {
    private int numPassageiros;
    private int pesoMax;
    private double consumoMedio;
    private double restoCombustivel;

    //Contrutor
    Carro(int numDePassageiros, int pesoMax, double consumoMedio, double restoCombustivel) {
        this.numPassageiros = numDePassageiros;
        this.pesoMax = pesoMax;
        this.consumoMedio = consumoMedio;
        this.pesoMax = pesoMax;   
    };

    //Métodos
    public void numDePassageiros() {
        System.out.println(String.format("O carro permite %d passageiros...\n", this.numPassageiros));
    };
    
    public void pesoMax() {
        System.out.println(String.format("O carro permite no máximo %d kg...\n", this.pesoMax));
    };

    public void consumoMedio() {
        System.out.println(String.format("O carro consome em média %.2f l/km...\n", this.consumoMedio));
    };

    public void restoCombustivel() {
        System.out.println(String.format("O carro tem %.2f litros de combustível sobrando...\n", this.restoCombustivel));
    };
};

class Caminhao extends Veiculo implements Combustivel {
    private int numPassageiros;
    private int pesoMax;
    private double consumoMedio;
    private double restoCombustivel;

    //Contrutor
    Caminhao(int numDePassageiros, int pesoMax, double consumoMedio, double restoCombustivel) {
        this.numPassageiros = numDePassageiros;
        this.pesoMax = pesoMax;
        this.consumoMedio = consumoMedio;
        this.pesoMax = pesoMax;   
    };

    //Métodos
    public void numDePassageiros() {
        System.out.println(String.format("o caminhao permite %d passageiros...\n", this.numPassageiros));
    };
    
    public void pesoMax() {
        System.out.println(String.format("o caminhao permite no máximo %d kg...\n", this.pesoMax));
    };

    public void consumoMedio() {
        System.out.println(String.format("o caminhao consome em média %.2f l/km...\n", this.consumoMedio));
    };

    public void restoCombustivel() {
        System.out.println(String.format("o caminhao tem %.2f litros de combustível sobrando...\n", this.restoCombustivel));
    };
};

class Onibus extends Veiculo implements Combustivel {
    private int numPassageiros;
    private int pesoMax;
    private double consumoMedio;
    private double restoCombustivel;

    //Contrutor
    Onibus(int numDePassageiros, int pesoMax, double consumoMedio, double restoCombustivel) {
        this.numPassageiros = numDePassageiros;
        this.pesoMax = pesoMax;
        this.consumoMedio = consumoMedio;
        this.pesoMax = pesoMax;   
    };

    //Métodos
    public void numDePassageiros() {
        System.out.println(String.format("O onibus permite %d passageiros...\n", this.numPassageiros));
    };
    
    public void pesoMax() {
        System.out.println(String.format("O onibus permite no máximo %d kg...\n", this.pesoMax));
    };

    public void consumoMedio() {
        System.out.println(String.format("O onibus consome em média %.2f l/km...\n", this.consumoMedio));
    };

    public void restoCombustivel() {
        System.out.println(String.format("O onibus tem %.2f litros de combustível sobrando...\n", this.restoCombustivel));
    };
};

class Skate {

};

class Geladeira {

};

class CortadorDeGrama {

};

class MotoEletrica extends Moto implements Eletrico {
    private int tempoBateria;

    //Construtor
    MotoEletrica(int numDePassageiros, int pesoMax, double consumoMedio, double restoCombustivel) {
        super(numDePassageiros, pesoMax, consumoMedio, restoCombustivel);
    };

    //Métodos
    public void tempoDaBateria() {
        System.out.println(String.format("A moto elétrica tem %d h de bateria sobrando", this.tempoBateria));
    };

    public void carregarBateria() {
        System.out.println("Carregando...");
    };
};

class CarroEletrico extends Carro implements Eletrico {
    private int tempoBateria;

    //Construtor
    CarroEletrico(int numDePassageiros, int pesoMax, double consumoMedio, double restoCombustivel) {
        super(numDePassageiros, pesoMax, consumoMedio, restoCombustivel);
    };

    //Métodos
    public void tempoDaBateria() {
        System.out.println(String.format("O carro elétrica tem %d h de bateria sobrando", this.tempoBateria));
    };

    public void carregarBateria() {
        System.out.println("Carregando...");
    };
};
