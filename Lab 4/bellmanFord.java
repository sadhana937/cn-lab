// Write a program to find the shortest path between vertices using bellman-ford algorithm

import java.util.Arrays;
import java.util.Scanner;

public class bellmanFord {
    private static int N;
    private static int[][] graph;

    public static void bellmanford(int src){
        int [] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for(int i = 0; i < N; i++){
            for(int u = 0; u < N; u++){
                for(int v = 0; v < N; v++){
                    if(graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]){
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }
        for(int u = 0; u < N; u++){
            for(int v = 0; v < N; v++){
                if(graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]){
                    System.out.println("Negative weight cycle detected");
                    return;
                }
            }
        }
        System.out.println("Vertex \t Distance from source");
        for(int i = 0; i < N; i++){
            System.out.println((i + 1) + "\t\t" + dist[i]);
        }
    }

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices");
        N = sc.nextInt();
        System.out.println("Enter the weight matrix of graph");
        graph = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                graph[i][j] = sc.nextInt();
            }
        }
        System.out.print("Enter the souce vertex: ");
        int source = sc.nextInt();
        bellmanford(source - 1);

        sc.close();
    }
}
/*
OUTPUT:
Enter number of vertices
5
Enter the weight matrix of graph
0  -1   4   0   0
0   0   3   2   2
0   0   0   5   0
0   1   0   0   0
0   0   0  -3   0
Enter the souce vertex: 1
Vertex   Distance from source
1               0
2               -1
3               2
4               -2
5               1
 */
