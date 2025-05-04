import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

public class FileRead
{
    ArrayList<String> startingLocations = new ArrayList<String>();
    ArrayList<String> endingLocations = new ArrayList<String>();
    ArrayList<String> lineType = new ArrayList<String>();
    ArrayList<String> time = new ArrayList<String>();

    ArrayList<String> locationName = new ArrayList<>(); 
    HashMap<String, Integer> locationIndexes = new HashMap<>();

    int indexNum = 0;
    int numLocations = 0;

    class locationData
    {
        Double time;
        String color;
    }

    locationData[][] graph;

    public FileRead()
    {
        
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("Metrolink_times_linecolour.csv"));    
            String currentLine;
            reader.readLine();

            while ((currentLine = reader.readLine()) != null)
            {
                String[] currentRow = currentLine.split(",");
                startingLocations.add(currentRow[0]);
                endingLocations.add(currentRow[1]);
                lineType.add(currentRow[2]);
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
                
                numLocations++;
            }  
            
            // System.out.println(startingLocations.size());
            // System.out.println(endingLocations.size());
            // System.out.println(lineType.size());
            // System.out.println(time.size());
            
            graph = new locationData[locationIndexes.size()][locationIndexes.size()];

            for (int i = 0; i < startingLocations.size(); i++)
            {
                graph[locationIndexes.get(startingLocations.get(i))][locationIndexes.get(endingLocations.get(i))] = new locationData();
                graph[locationIndexes.get(endingLocations.get(i))][locationIndexes.get(startingLocations.get(i))] = new locationData();
             
                graph[locationIndexes.get(startingLocations.get(i))][locationIndexes.get(endingLocations.get(i))].time = Double.parseDouble(time.get(i));
                graph[locationIndexes.get(startingLocations.get(i))][locationIndexes.get(endingLocations.get(i))].color = lineType.get(i);
                graph[locationIndexes.get(endingLocations.get(i))][locationIndexes.get(startingLocations.get(i))].time = Double.parseDouble(time.get(i));
                graph[locationIndexes.get(endingLocations.get(i))][locationIndexes.get(startingLocations.get(i))].color = lineType.get(i);

            }

            reader.close();
            
        }
        catch (IOException e)
        {
            System.out.println("Error.");
        }
        
    }
    

}
