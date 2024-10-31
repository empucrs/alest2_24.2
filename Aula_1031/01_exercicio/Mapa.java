import java.util.HashMap;

public class Mapa {

    HashMap<Integer, Ponto> dicionario;    

    private class Ponto{
        public double x, y;
        public Ponto(double px, double py){
            this.x=px;
            this.y=py;
        }
        public String toString(){
            return "("+x+", "+y+")";
        }
    }

    public Mapa(String fn){
        dicionario = new HashMap<>();
        int count=0;

        // CONSOME O ARQUIVO DE COORDENADAS
        In pts = new In(fn);
        while (pts.hasNextLine()) {
            String[] line = pts.readLine().split(";");
            dicionario.put(count++, new Ponto(Double.parseDouble(line[0]), Double.parseDouble(line[1])));
        }
        pts.close();

        // GERA O GRAFO COM PESOS
        EdgeWeightedGraph g = new EdgeWeightedGraph(dicionario.size());

        for(int source=0; source<dicionario.size(); source++){
            Ponto p1=dicionario.get(source);
            for(int target=0; target<dicionario.size(); target++)
                if(source!=target){
                    Ponto p2=dicionario.get(target);
                    // calculo da distancia entre os pontos
                    double distancia = Math.sqrt( Math.pow(p1.x-p2.x, 2)+Math.pow(p1.y-p2.y, 2));
                    g.addEdge(new Edge(source, target, distancia));
                }
        }

        // CALCULA A MST
        KruskalMST k = new KruskalMST(g);
        int[][] mapeados= new int[dicionario.size()][dicionario.size()];
        for(Edge e: k.edges())
            mapeados[e.either()][e.other(e.either())]=1;
            

        for(int source=0; source<dicionario.size(); source++)
            for(int target=source+1; target<dicionario.size(); target++)
                System.out.println(source+" "+target+" "+mapeados[source][target]);

    }    
}
