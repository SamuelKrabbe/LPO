import java.util.Scanner;

interface Animal{
    String fazerBarulho();
    int numDePatas();
}

class Cachorro implements Animal{
    private String nome;
    private int nPatas;

    public Cachorro(String nome, int nPatas){
        this.nome = nome;
        this.nPatas = nPatas;
    }

    @Override
    public String fazerBarulho(){
        return "auau";
    }

    @Override
    public int numDePatas(){
        return this.nPatas;
    }

    @Override
    public String toString(){
        return "O cachorro " + this.nome + " com " + numDePatas() + " patas fez " + fazerBarulho();
    }
}

class Gato implements Animal{
    private String nome;
    private int nPatas;

    public Gato(String nome, int nPatas){
        this.nome = nome;
        this.nPatas = nPatas;
    }

    @Override
    public String fazerBarulho(){
        return "miau";
    }

    @Override
    public int numDePatas(){
        return this.nPatas;
    }

    @Override
    public String toString(){
        return "O gato " + this.nome + " com " + numDePatas() + " patas fez " + fazerBarulho();
    }
}

class Papagaio implements Animal{
    private String nome;
    private int nPatas;

    public Papagaio(String nome, int nPatas){
        this.nome = nome;
        this.nPatas = nPatas;
    }

    @Override
    public String fazerBarulho(){
        return "loro";
    }

    @Override
    public int numDePatas(){
        return this.nPatas;
    }

    @Override
    public String toString(){
        return "O papagaio " + this.nome + " com " + numDePatas() + " patas fez " + fazerBarulho();
    }
}

public class MainAnimal{
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String nome;
        int nPatas;

        nome = sc.next();
        nPatas = sc.nextInt();
        Cachorro cachorro = new Cachorro(nome, nPatas);
        
        nome = sc.next();
        nPatas = sc.nextInt();
        Gato gato = new Gato(nome, nPatas);
        
        nome = sc.next();
        nPatas = sc.nextInt();
        Papagaio papagaio = new Papagaio(nome, nPatas);
        
        System.out.println(cachorro);
        System.out.println(gato);
        System.out.println(papagaio);
        sc.close();
    }
}
