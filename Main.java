import java.util.Scanner;

class Veiculo{
    private String cor;
    private int numPassageiros;

    //construtor
    Veiculo(String cor, int numPassageiros){
        this.cor = cor;
        this.numPassageiros = numPassageiros;
    }

    //métodos
    public void setCor(String cor){
        this.cor = cor;
    }

    public void setNumPassageiros(int numPassageiros){
        this.numPassageiros = numPassageiros;
    }

    public String getCor(){
        return this.cor;
    }

    public int getNumPassageiros(){
        return this.numPassageiros;
    }

    @Override
    public String toString(){
        return this.cor + " numPassageiros " + this.numPassageiros;
    }   
}

class Carro extends Veiculo{
    private String marca;
    private String modelo;

    //construtor
    Carro(String cor, int numPassageiros, String marca, String modelo){
        super(cor, numPassageiros);
        this.marca = marca;
        this.modelo = modelo;
    }

    //métodos
    public void setMarca(String marca){
        this.marca = marca;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public String getMarca(){
        return this.marca;
    }

    public String getModelo(){
        return this.modelo;
    }
    
    public void acelerar(){
        System.out.println(String.format("O %s acelerou", modelo));
    }

    public void freiar(){
        System.out.println(String.format("O %s freou", modelo));
    }

    @Override
    public String toString(){
        return "Carro " + this.marca + " " + this.modelo + " " + super.getCor() + " numPassageiros " + super.getNumPassageiros();
    }   
}

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String cor = sc.next();
        int numPassageiros = sc.nextInt();
        String marca = sc.next();
        String modelo = sc.next();
        Carro carro = new Carro(cor, numPassageiros, marca, modelo);

        System.out.println(carro);
        carro.setModelo(sc.next());
        System.out.println(carro);
        carro.acelerar();
        carro.freiar();
    }
}
