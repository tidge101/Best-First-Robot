
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

    public BestFirstSearch()
    {
      
    }

    public int Euclidean(){

    }

    public int Manhattan(){

    }

    public int AStarEuclidean(){

    }

    public int AStarManhattan(){

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
            //line = br.readLine();
            for(int i = 0; i< N;i++){
                line = br.readLine();
                for(int j = 0; j < N; j++){
                      map[i][j] = line.charAt(j);
                  }
            }
            // Output map to test
            for(int i = 0; i< N;i++){
                  for(int j = 0; j < N; j++){
                      System.out.print(map[i][j]);
                  }
                  System.out.println();
            }
       } catch (InputMismatchException inputMismatch){
           System.out.println("Wrong Input Format");
       }catch (FileNotFoundException e){
           System.out.println("File not found :(");
       }catch (IOException e) {
           e.printStackTrace();}
       scan.close();
   }

}
