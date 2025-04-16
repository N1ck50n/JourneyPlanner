import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.IOException;

public class FileRead
{
    ArrayList<String> startingLocations = new ArrayList<String>();
    ArrayList<String> endingLocations = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<String> time = new ArrayList<String>();

    HashMap<String, Integer> startLocIndexes = new HashMap<>();
    HashMap<String, Integer> endLocIndexes = new HashMap<>();
    HashMap<String, Integer> timeIndexes = new HashMap<>();

    int[][] graph;
    int startIndex = 0;
    int endIndex = 0;
    int numLocations = 0;

    public FileRead()
    {
        
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("Metrolink_times_linecolour.csv"));    
            String currentLine;
            while ((currentLine = reader.readLine()) != null)
            {
                String[] currentRow = currentLine.split(",");
                startingLocations.add(currentRow[0]);
                endingLocations.add(currentRow[1]);
                type.add(currentRow[2]);
                time.add(currentRow[3]);
                
                if (startLocIndexes.containsKey(currentRow[0]) == false) 
                {
                    startLocIndexes.put(currentRow[0], startIndex);
                    startIndex++;
                }
                if (endLocIndexes.containsKey(currentRow[1]) == false)
                {
                    endLocIndexes.put(currentRow[1], endIndex);
                    endIndex++;
                }
               
                numLocations++;
                
            }  
            
            // The starting locations read from the file.
            //System.out.println(startingLocations);

            // The ending locations read from the file.
            //System.out.println(endingLocations);


            reader.close();
            
        }
        catch (IOException e)
        {
            System.out.println("Error.");
        }
        
    }
    public void graph()
    {
        graph = new int[numLocations][numLocations];

        for (int i = 0; i < numLocations; i++)
        {
            
        }
    }

}
