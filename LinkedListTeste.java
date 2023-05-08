import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class LinkedListTeste {
    public static void main(String[] args) throws Exception {
        String str, strBusca;
        Scanner sc = new Scanner(System.in);
        List<String> lista = new LinkedList<String>();
        
        for (int i = 0; i < 6; i++) {
            str = sc.next();
            lista.add(str);
        }
        strBusca = sc.next();

        for (String string : lista)
            if (lista.indexOf(string) == 5)
                System.out.println(string);
            else
                System.out.print(string + " ");
            
        lista.sort(null);

        for (String string : lista)
            if (lista.indexOf(string) == 5)
                System.out.println(string);
            else
                System.out.print(string + " ");

        if (lista.contains(strBusca))
            System.out.println("encontrado");
        else
            System.out.println("não encontrado");
        sc.close();
    }
}
