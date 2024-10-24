import java.util.HashSet;
import java.util.Set;

public class DCNaoDirigido {

    private Graph g;
    private boolean [] marked;
    private Set<String> edges;

    public DCNaoDirigido(String filename){
        g = new Graph(new In(filename));
        marked=new boolean[g.V()];        
    }

    public boolean contemCiclo(){
        for(int i=0; i<marked.length; i++)
          marked[i]=false;    
        edges = new HashSet<>();
        for(int v=0; v<g.V(); v++)
          if(marked[v]==false && contemCiclo(g,v))
                return true;
        return false;
    }

    private boolean contemCiclo(Graph g, int v){
        marked[v]=true;
        for (int u : g.adj(v)) {
            if(marked[u]==false){
                edges.add(u+"."+v);
                if(contemCiclo(g, u)) 
                    return true;
            }
            else{
                if(!edges.contains(v+"."+u))
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        if(args.length!=1){
            System.out.println("java DCNaoDirigido <caminhoSoArquivo>/<nomeDoArquivo>");
            System.exit(0);
        }

        DCNaoDirigido dcnd = new DCNaoDirigido(args[0]);

        if(dcnd.contemCiclo())
            System.out.println("O grafo contem ciclos");
        else
            System.out.println("O grafo livre de ciclos");
        
    }

    
}