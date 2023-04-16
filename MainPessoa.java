import java.util.Scanner;

class Pessoa{
    private String nome;
    private int idade;

    //construtor
    Pessoa(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }

    //métodos
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public String getNome(){
        return this.nome;
    }

    public int getIdade(){
        return this.idade;
    }

    @Override
    public String toString(){
        return this.nome + " tem " + this.idade + " anos";
    }   
}

public class MainPessoa{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Pessoa pessoa = new Pessoa(sc.nextLine(), sc.nextInt());

        System.out.println(pessoa);
        pessoa.setIdade(sc.nextInt());
        System.out.println(pessoa);
        sc.close();
    }
}