package collection.graph.search;

import collection.graph.Graph;
import collection.graph.SymbolGraph;

import java.util.Scanner;

public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);
        Graph G = sg.G();
        String source = args[2];
        if (!sg.contains(source)) {
            System.out.println(source + "not in database.");
            return;
        }
        BreadFirstPaths bfp = new BreadFirstPaths(G, sg.index(source));
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            if (sg.contains(s)) {
                int index = sg.index(s);
                if (bfp.hasPaths(index))
                    for (Integer i : bfp.pathTo(index))
                        System.out.println(sg.name(i));
                else System.out.println(" Not connected");
            }
            else System.out.println("Not in database");
        }
    }
}
