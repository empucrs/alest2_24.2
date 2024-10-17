import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Oraculo {

    public static void main(String[] args) {
        In f = new  In(args[0]);
        int idx=0;
        Map<String, Integer> ref = new HashMap<>();
        String [] nomes = new String[120000];
        Graph g = new Graph(120000);

        while(f.hasNextLine()){
            String[] line = f.readLine().toLowerCase().split("/");
            
            if(!ref.containsKey(line[0].trim()))
                ref.put(line[0].trim(), idx++);

            int idMovie=ref.get(line[0].trim());
            nomes[idMovie]=line[0].trim();

            System.out.println(line[0].trim() + "["+idMovie+"]");
            System.out.println("-=-=-=-=-=-=-=-");            
            for(int i=1; i< line.length; i++){
                String [] pedacos = line[i].split(",");
                String nome;
                if(pedacos.length>1)
                    nome=pedacos[1].trim().split(" ")[0]+ " " + pedacos[0].split(" ")[0];
                else    
                    nome=pedacos[0].trim().split(" ")[0];                    
                
                if(!ref.containsKey(nome))
                    ref.put(nome, idx++);
                int idMember=ref.get(nome);
                nomes[idMember]=nome;
                g.addEdge(idMovie, idMember);
                System.out.println("  => "+ nome+" ["+idMember+"]");
            }
            System.out.println();
        }

        String ator1, ator2;
        Scanner sc = new Scanner(System.in);        
        while(sc!=null){
            System.out.print("Informe o nome do ator 1: ");
            ator1=sc.nextLine();
            if(ator1.equals("0")) break;
            Integer idAtor1 = ref.get(ator1);
            if(idAtor1==null) {
                System.out.println("O ator "+ator1+" não existe");
                break;
            }

            System.out.print("Informe o nome do ator 2: ");
            ator2=sc.nextLine();
            if(ator2.equals("0")) break;
            Integer idAtor2 = ref.get(ator2);
            if(idAtor2==null) {
                System.out.println("O ator "+ator2+" não existe");
                break;
            }

            CaminhamentoEmLargura cem = new CaminhamentoEmLargura(g, idAtor1);

            if(!cem.hasPath(idAtor2))
                System.out.println("Não há relação entre o/a ator/atriz "+ator1+" e o/a ator/atriz "+ator2);
            else{
                System.out.println("Há um caminho:");
                for(Integer i: cem.pathTo(idAtor2))
                    System.out.print(nomes[i]+", ");
                System.out.println();
            }
        }
        System.out.println("Fim do programa");

        
    }

    
}