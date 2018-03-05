
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.ArrayList;


public class BestFirstSearch
{
    private PriorityQueue<Vertex> priorityQueue;
    private int heuristicvalues[];
    private int numberOfNodes;

    public static final int MAX_VALUE = 100;

    public BestFirstSearch(int numberOfNodes)
    {
        this.numberOfNodes = numberOfNodes;
        this.priorityQueue = new PriorityQueue<Vertex>(this.numberOfNodes,
        new Vertex());
    }

    public void bestFirstSearch(int adjacencyMatrix[][], int[] heuristicvalues,int source)
    {
        int currentNode;
        int destinationNode;
        int visited[] = new int [numberOfNodes + 1];
        this.heuristicvalues = heuristicvalues;

        priorityQueue.add(new Vertex(source, this.heuristicvalues[source]));
        visited[source] = 1;

        while (!priorityQueue.isEmpty())
        {
            currentNode = getNodeWithMinimumHeuristicValue();
            destinationNode = 1;

            System.out.print(currentNode + "\t");
            while (destinationNode <= numberOfNodes)
            {
                Vertex vertex = new Vertex(destinationNode,this.heuristicvalues[destinationNode]);
                if ((adjacencyMatrix[currentNode][destinationNode] != MAX_VALUE
                      && currentNode != destinationNode)&& visited[destinationNode] == 0)
                {
                    priorityQueue.add(vertex);
                    visited[destinationNode] = 1;
                }
                destinationNode++;
            }
        }
    }

    private int getNodeWithMinimumHeuristicValue()
    {
        Vertex vertex = priorityQueue.remove();
        return vertex.node;
    }

    public static void main(String... arg)
    {

        Scanner scan = new Scanner(System.in);
        BufferedReader br = null;
        String filename = null;
        char map[][] = null;
        String line = "";
        try
        {   //open a file
            System.out.println("Enter the name of a file: ");
            filename = scan.nextLine();
            br = new BufferedReader(new FileReader(filename));

            //get first line of input file - N
            line = br.readLine();
            int N = Integer.parseInt(line);
            System.out.println(N);

            //get the map in a 2D array
            map = new char[N][N];
            //line = br.readLine();
            for(int i = 0; i< N;i++){
                line = br.readLine();
                for(int j = 0; j < N; j++){
                      map[i][j] = line.charAt(j);
                  }
            }
            for(int i = 0; i< N;i++){
                  for(int j = 0; j < N; j++){
                      System.out.print(map[i][j]);
                  }
                  System.out.println();
            }
/*
            //Use N for the number of Vertices
            number_of_vertices = N;
            map = new int[number_of_vertices + 1][number_of_vertices + 1];
            heuristicvalues = new int[number_of_vertices + 1];

            //System.out.println("Enter the Weighted Matrix for the graph");
            for (int i = 1; i <= number_of_vertices; i++)
            {
                for (int j = 1; j <= number_of_vertices; j++)
                {
                    map[i][j] = scan.nextInt();
                    if (i == j)
                    {
                        map[i][j] = 0;
                        continue;
                    }
                    if (map[i][j] == 0)
                    {
                        map[i][j] = MAX_VALUE;
                    }
                }
            }
            for (int i = 1; i <= number_of_vertices; i++)
            {
                for (int j = 1; j <= number_of_vertices; j++)
                {
                    if (map[i][j] == 1 && map[j][i] == 0)
                    {
                        map[j][i] = 1;
                    }
                }
            }

            System.out.println("Enter the heuristic values of the nodes");
            for (int vertex = 1; vertex <= number_of_vertices; vertex++)
            {
                System.out.print(vertex + ".");
                heuristicvalues[vertex] = scan.nextInt();
                System.out.println();
            }

            System.out.println("Enter the source ");
            source = scan.nextInt();

            System.out.println("The graph is explored as follows");
            BestFirstSearch bestFirstSearch = new BestFirstSearch(number_of_vertices);
            bestFirstSearch.bestFirstSearch(map, heuristicvalues,source);

*/
       } catch (InputMismatchException inputMismatch){
           System.out.println("Wrong Input Format");
       }catch (FileNotFoundException e){
           System.out.println("File not found :(");
       }catch (IOException e) {
           e.printStackTrace();}
       scan.close();
   }
}

class Vertex implements Comparator<Vertex>
{
    public int heuristicvalue;
    public int node;

    public Vertex(int node, int heuristicvalue)
    {
        this.heuristicvalue = heuristicvalue;
        this.node = node;
    }

    public Vertex()
    {

    }

    @Override
    public int compare(Vertex vertex1, Vertex vertex2)
    {
        if (vertex1.heuristicvalue < vertex2.heuristicvalue)
            return -1;
        if (vertex1.heuristicvalue > vertex2.heuristicvalue)
            return 1;
        return 0;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Vertex)
        {
            Vertex node = (Vertex) obj;
            if (this.node == node.node)
            {
                return true;
            }
        }
        return false;
    }
}
