
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class BestFirstSearch
{

    public static final int MAX_VALUE = 100;

    public BestFirstSearch(char[][] grid)
    {
      // Euclidean

      // Manhattan

      // AStarEuclidean

      // AStarManhattan

    }

    public static double Euclidean(int x1, int y1, int x2, int y2){
      return Math.sqrt(((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));
    }

    public static double Manhattan(int x1, int y1, int x2, int y2){
      return Math.abs(x2-x1) + Math.abs(y2-y1);
    }

    public static double AStarEuclidean(int x1, int y1, int x2, int y2, int cost){
      return cost + Math.sqrt(((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));
    }

    public static double AStarManhattan(int x1, int y1, int x2, int y2, int cost){
      return cost + Math.abs(x2-x1) + Math.abs(y2-y1);
    }

    public static void main(String... arg)
    {

        Scanner scan = new Scanner(System.in);
        BufferedReader br = null;
        String filename = null;
        char map[][] = null;
        String line = "";
        try
        {
            // Open a file
            System.out.println("Enter the name of a file: ");
            filename = scan.nextLine();
            br = new BufferedReader(new FileReader(filename));

            // Get first line of input file - N
            line = br.readLine();
            int N = Integer.parseInt(line);
            //System.out.println(N);

            // Get the map in a 2D array
            map = new char[N][N];
            Tuple<Integer, Integer> initialState = new Tuple<>(-1,-1);
            Tuple<Integer, Integer> goalState = new Tuple<>(-1,-1);
            ArrayList<Tuple> obstacles = new ArrayList<>();
            ArrayList<Tuple> path = new ArrayList<>();
            //line = br.readLine();
            for(int i = 0; i < N;i++){
                line = br.readLine();
                for(int j = 0; j < N; j++){
                      map[i][j] = line.charAt(j);
                      if(map[i][j] == 'i'){initialState =  new Tuple<>(i,j);}
              				if(map[i][j] == 'g'){goalState = new Tuple<>(i,j);}
              				if(map[i][j] == '+'){obstacles.add(new Tuple<>(i,j));}
                  }
            }
            // Output map to test
            for(int i = 0; i< N;i++){
                  for(int j = 0; j < N; j++){
                      System.out.print(map[i][j]);
                  }
                  System.out.println();
            }
            //
            BestFirstSearch bfs = new BestFirstSearch(map);

            System.out.println("Euclidean");
            // Euclidean
            int goalReached = 0;
            Tuple<Integer, Integer> currentNode = new Tuple<Integer, Integer>(initialState.a,initialState.b);
            ArrayList<Tuple> nodes = new ArrayList<>();

            while(goalReached == 0){
              Tuple<Integer, Integer> leftNode = new Tuple<Integer, Integer>(1000,1000);
              Tuple<Integer, Integer> rightNode = new Tuple<Integer, Integer>(1000,1000);
              Tuple<Integer, Integer> upNode = new Tuple<Integer, Integer>(1000,1000);
              Tuple<Integer, Integer> downNode = new Tuple<Integer, Integer>(1000,1000);
              if(currentNode.b > 0){leftNode = new Tuple<>(currentNode.a,currentNode.b-1);}
              if(currentNode.b < N-1){rightNode = new Tuple<>(currentNode.a,currentNode.b+1);}
              if(currentNode.a > 0){upNode = new Tuple<>(currentNode.a-1,currentNode.b);}
              if(currentNode.a < N-1){downNode = new Tuple<>(currentNode.a+1,currentNode.b);}

              obstacles.add(currentNode);
              if (!obstacles.contains(leftNode)){ nodes.add(leftNode);}
              if (!obstacles.contains(rightNode)){ nodes.add(rightNode);}
              if (!obstacles.contains(upNode)){ nodes.add(upNode);}
              if (!obstacles.contains(downNode)){ nodes.add(downNode);}

              double min = 1000;
              for(int i = 0; i < nodes.size(); i++){
                Tuple<Integer, Integer> tempNode = nodes.get(i);
                double tempMin = Euclidean(goalState.a, goalState.b, tempNode.a, tempNode.b);
                if (tempMin < min) {
                  min = tempMin;
                  currentNode = tempNode;
                }
              }
              nodes.clear();

              if(map[currentNode.a][currentNode.b] == 'g'){goalReached = 1;break;}
              path.add(new Tuple<>(currentNode.a, currentNode.b));
            }

            for(int i = 0; i< N;i++){
                  for(int j = 0; j < N; j++){
                      for(int k = 0; k < path.size(); k++){
                        Tuple<Integer,Integer> someTup = path.get(k);
                        if(i == someTup.a && j == someTup.b){map[i][j] = 'o';}
                      }

                  }
            }
            for(int i = 0; i< N;i++){
                  for(int j = 0; j < N; j++){
                      System.out.print(map[i][j]);
                  }
                  System.out.println();
            }

            obstacles.clear();
            obstacles = new ArrayList<>();
            for(int i = 0; i < N;i++){
                for(int j = 0; j < N; j++){
                      obstacles.add(new Tuple<>(i,j));
                  }
            }
            nodes = new ArrayList<>();

            System.out.println("Manhattan");
            // Manhattan
            goalReached = 0;
            currentNode = new Tuple<Integer, Integer>(initialState.a,initialState.b);
            while(goalReached == 0){
              Tuple<Integer, Integer> leftNode = new Tuple<Integer, Integer>(1000,1000);
              Tuple<Integer, Integer> rightNode = new Tuple<Integer, Integer>(1000,1000);
              Tuple<Integer, Integer> upNode = new Tuple<Integer, Integer>(1000,1000);
              Tuple<Integer, Integer> downNode = new Tuple<Integer, Integer>(1000,1000);
              if(currentNode.b > 0){leftNode = new Tuple<>(currentNode.a,currentNode.b-1);}
              if(currentNode.b < N-1){rightNode = new Tuple<>(currentNode.a,currentNode.b+1);}
              if(currentNode.a > 0){upNode = new Tuple<>(currentNode.a-1,currentNode.b);}
              if(currentNode.a < N-1){downNode = new Tuple<>(currentNode.a+1,currentNode.b);}
              System.out.println("Current Node: " + currentNode.a + "," + currentNode.b);
              obstacles.add(currentNode);
              if (!obstacles.contains(leftNode)){ nodes.add(leftNode);}
              if (!obstacles.contains(rightNode)){ nodes.add(rightNode);}
              if (!obstacles.contains(upNode)){ nodes.add(upNode);}
              if (!obstacles.contains(downNode)){ nodes.add(downNode);}

              double min = 1000;
              for(int i = 0; i < nodes.size(); i++){
                Tuple<Integer, Integer> tempNode = nodes.get(i);
                double tempMin = Manhattan(goalState.a, goalState.b, tempNode.a, tempNode.b);
                if (tempMin < min) {
                  min = tempMin;
                  currentNode = tempNode;
                }
              }
              nodes.clear();

              if(map[currentNode.a][currentNode.b] == 'g'){goalReached = 1;break;}
              path.add(new Tuple<>(currentNode.a, currentNode.b));
            }

            for(int i = 0; i< N;i++){
                  for(int j = 0; j < N; j++){
                      for(int k = 0; k < path.size(); k++){
                        Tuple<Integer,Integer> someTup = path.get(k);
                        if(i == someTup.a && j == someTup.b){map[i][j] = 'o';}
                      }

                  }
            }
            for(int i = 0; i< N;i++){
                  for(int j = 0; j < N; j++){
                      System.out.print(map[i][j]);
                  }
                  System.out.println();
            }

            // AStarEuclidean

            // AStarManhattan


       } catch (InputMismatchException inputMismatch){
           System.out.println("Wrong Input Format");
       }catch (FileNotFoundException e){
           System.out.println("File not found :(");
       }catch (IOException e) {
           e.printStackTrace();}
       scan.close();
   }

}

class Tuple<A, B> {

 public final A a;
 public final B b;

 public Tuple(A a, B b) {
     this.a = a;
     this.b = b;
 }

 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;

     Tuple<?, ?> tuple = (Tuple<?, ?>) o;
     if (!a.equals(tuple.a)) return false;
     return b.equals(tuple.b);
 }

 @Override
 public int hashCode() {
     int result = a.hashCode();
     result = 31 * result + b.hashCode();
     return result;
 }
}
