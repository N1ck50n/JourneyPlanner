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

    ArrayList<String> locationName = new ArrayList<>(); 
    HashMap<String, Integer> locationIndexes = new HashMap<>();
    ArrayList<int[]> row = new ArrayList<>();

    int indexNum = 0;
    int numLocations = 0;

    int[][] graph;

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
                
                if (locationIndexes.containsKey(currentRow[0]) == false) 
                {
                    locationName.add(currentRow[0]);
                    locationIndexes.put(currentRow[0], indexNum);
                    indexNum++;
                }
                if (locationIndexes.containsKey(currentRow[1]) == false)
                {
                    locationName.add(currentRow[1]);
                    locationIndexes.put(currentRow[1], indexNum);
                    indexNum++;
                }
               
                int startingLocationIndex = locationIndexes.get(currentRow[0]); 
                int endingLocationIndex = locationIndexes.get(currentRow[1]); 
                
                row.add(new int[] {startingLocationIndex, endingLocationIndex, Integer.parseInt(currentRow[3])});
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
        graph = new int[locationIndexes.size()][locationIndexes.size()];

        for (int[] selectedRow : row)
        {
            int i = selectedRow[0]; 
            int j = selectedRow[1];
            graph[i][j] = selectedRow[2];
            graph[j][i] = selectedRow[2];
        }
    }

}
