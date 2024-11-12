public class App {

    public static void main(String[] args) {

        if(args.length!=1){
            System.out.println("Informe o nome do arquivo a ser consumido");
            System.out.println("java App <caminho + nome do arquivo>");
        }


        In file = new In(args[0]);

        while(! file.isEmpty()){
            System.out.println(file.readLine());
        }   

        
        //AdjMatrixEdgeWeightedDigraph g = new AdjMatrixEdgeWeightedDigraph();



    }

    
}