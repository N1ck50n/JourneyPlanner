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

    class locationData //Becomes a data type that stores data for the nodes in the graph. 
    {
        Double time;
        String color;
    }

    ArrayList<locationData>[][] graph;

    public FileRead()
    {
        
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("Metrolink_times_linecolour.csv"));    
            String currentLine; // Temporary String that stores the current line, as it inputs line by line.
            reader.readLine(); // Put it before the loop so you can skip the first line.

            while ((currentLine = reader.readLine()) != null) // Until line is empty.
            {
                String[] currentRow = currentLine.split(","); // Split the data using the keyword , and store it in the string array.
                
                // Store the string in the correct arraylist.
                startingLocations.add(currentRow[0]); 
                endingLocations.add(currentRow[1]);
                lineType.add(currentRow[2]);
                time.add(currentRow[3]);
                
                if (locationIndexes.containsKey(currentRow[0]) == false) // Check if the location already has an assigned index number.
                {
                    locationName.add(currentRow[0]);
                    locationIndexes.put(currentRow[0], indexNum);
                    indexNum++;
                }
                if (locationIndexes.containsKey(currentRow[1]) == false) // Check if the location already has an assigned index number.
                {
                    locationName.add(currentRow[1]);
                    locationIndexes.put(currentRow[1], indexNum);
                    indexNum++;
                }
            }  
            
            // System.out.println(startingLocations.size());
            // System.out.println(endingLocations.size());
            // System.out.println(lineType.size());
            // System.out.println(time.size());
            
            graph = new ArrayList[indexNum][indexNum]; // Instantiate the whole 2D array implementation. 

            for (int i = 0; i < startingLocations.size(); i++)
            {
                // Instantiate each node
                if (graph[locationIndexes.get(startingLocations.get(i))][locationIndexes.get(endingLocations.get(i))] == null)
                {
                    graph[locationIndexes.get(startingLocations.get(i))][locationIndexes.get(endingLocations.get(i))] = new ArrayList<>(); 
                }
                
                if (graph[locationIndexes.get(endingLocations.get(i))][locationIndexes.get(startingLocations.get(i))] == null)
                {
                    graph[locationIndexes.get(endingLocations.get(i))][locationIndexes.get(startingLocations.get(i))] = new ArrayList<>();
                }
                
                locationData forward = new locationData(); // Create an object of the class to store the forward data.
                forward.time = Double.parseDouble(time.get(i));
                forward.color = lineType.get(i);
                graph[locationIndexes.get(startingLocations.get(i))][locationIndexes.get(endingLocations.get(i))].add(forward);

                locationData reversed = new locationData(); // Create an object to store the reversed data.
                reversed.time = Double.parseDouble(time.get(i));
                reversed.color = lineType.get(i);
                graph[locationIndexes.get(endingLocations.get(i))][locationIndexes.get(startingLocations.get(i))].add(reversed);
            }

            reader.close(); //close the scanner 
            
        }
        catch (IOException e) // If the file is unable to open or some other error.
        {
            System.out.println("Error.");
        }
        
    }
    

}
