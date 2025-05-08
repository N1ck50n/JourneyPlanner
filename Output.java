import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Output extends FileRead
{
    
    JFrame outputFrame = new JFrame("Best Route for the Chosen Option");
    JPanel outputPanel = new JPanel();
    FlowLayout outputLayout = new FlowLayout();
    JTextArea outputArea = new JTextArea(50, 30);
    JScrollPane scrollPane = new JScrollPane(outputArea);

    int currentLocationIndex;
    int nextLocationIndex = 0;
    Double totalTime = 0.0;
    int numOfChanges = 0;
    String currentColor;    
    String previousColor;

    public Output(int pathfindingType, int startingIndex, LinkedList<Integer> tripData, String[] tripLineColor) 
    {           
    
        outputPanel.setLayout(new BorderLayout()); //Use borderlayout.
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        outputArea.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        outputFrame.setContentPane(outputPanel);
        outputFrame.setSize(500, 500);
        outputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        outputFrame.setVisible(true);


        StringBuilder output = new StringBuilder();
        
        if (pathfindingType == 1)
        {
            output.append("*** Minimal Time Route ***\n");

        }
        if (pathfindingType == 2)
        {
            output.append("*** Route with Fewest Changes ***\n");
        }
        
        // Print the first location and the trip color should be the line color used to go to the second location.
        output.append(locationName.get(startingIndex) + " on " + tripLineColor[tripData.get(1)] + " line\n"); 

        for (int j = 0; j < tripData.size() - 1; j++) 
        {
            currentLocationIndex = tripData.get(j);
            nextLocationIndex = tripData.get(j + 1);

            currentColor = tripLineColor[nextLocationIndex];

            double bestTime = -1; 
            // use bestTime to compare times for the same route between two locations.
            for (locationData bestWay : graph[currentLocationIndex][nextLocationIndex])
            {
                if (bestWay.color.equals(currentColor))
                {
                    bestTime = bestWay.time;
                    break;
                }
            }

            if (previousColor != null && !currentColor.equals(previousColor)) // If next color is different then current color.
            {
                output.append("** Change Line to " + currentColor + " **\n");
                output.append(locationName.get(currentLocationIndex) + " on " + currentColor + " line\n");
                bestTime = bestTime + 2;
                numOfChanges++;
            }

            output.append(locationName.get(nextLocationIndex) + " on " + currentColor + " line\n");
            
            totalTime = totalTime + bestTime;
            // save the current color as previous for comparing with the next tram line color.
            previousColor = currentColor;
        }

        output.append("\n");
        output.append("Overall Journey Time (mins) = " + totalTime + "\n");
        output.append("Number of changes = " + numOfChanges + "\n");
        
        outputArea.setText(output.toString()); //use JtextArea to print the ouput

        
    }
}
