import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class FileRead
{
    ArrayList<String> startingLocations = new ArrayList<String>();
    ArrayList<String> endingLocations = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<String> time = new ArrayList<String>();

    public FileRead()
    {

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("Metrolink_times_linecolour.csv"));    
            String currentLine;
            while ((currentLine = reader.readLine()) != null)
            {
                currentLine = reader.readLine();
                String[] currentRow = currentLine.split(",");
                startingLocations.add(currentRow[0]);
                endingLocations.add(currentRow[1]);
                type.add(currentRow[2]);
                time.add(currentRow[3]);
            }  
           
            

            reader.close();
            
        }
        catch (IOException e)
        {
            System.out.println("Error.");
        }
        
    }

}
