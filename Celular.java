import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Celular implements Comparable<Celular>{
    private int codigo;
    private String marca;
    private String modelo;

    public Celular(int codigo, String marca, String modelo){
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getModelo(){
        return modelo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    @Override
    public String toString(){
        return "COD:" + this.codigo + ",marca:" + this.marca + ",modelo:" + this.modelo;
    }

    @Override
    public int compareTo(Celular outraCelular){
        return this.marca.compareTo(outraCelular.getMarca());
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<Celular> celulares = new ArrayList<>();
        int codigo;
        String marca;
        String modelo;

        for(int i = 0; i < 5; i++){
            try{
                codigo = sc.nextInt();
                marca = sc.next();
                modelo = sc.next();
                Celular celular = new Celular(codigo, marca, modelo);
                celulares.add(celular);
            }catch(Exception E){
                System.out.println("Entrada Inválida!");
                return;
            }
        }

        System.out.print("[");
        for(Celular cel : celulares){
            System.out.print(cel);
            if(celulares.get(4) != cel)
                System.out.print(", ");
        }
        System.out.println("]");

        celulares.sort(null);

        System.out.println("O menor elemento é: " + celulares.get(0));
        System.out.print("[");
        for(Celular cel : celulares){
            System.out.print(cel);
            if(celulares.get(4) != cel)
                System.out.print(", ");
        }
        System.out.println("]");
        sc.close();
    }
}