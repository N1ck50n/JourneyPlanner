import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends FileRead
{
    JFrame frame = new JFrame("Metrolink Journey Planner");
    JPanel panel = new JPanel();
    JButton shortestPathButton = new JButton("Find the Shortest Path.");
    JButton fewestChangesButton = new JButton("Find Route with the Fewest Changes.");
    JTextField startingInput = new JTextField(30);
    JTextField endingInput = new JTextField(30);
    FlowLayout layout = new FlowLayout();
    boolean firstLoc = false;
    boolean secondLoc = false;
    String startLocation;
    String endLocation;
    int pathfindingType;

    public UserInterface()
    {   
        super();

        panel.setLayout(layout);
        JLabel askStartLoc = new JLabel("Enter the Starting Location: ");
        askStartLoc.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        panel.add(askStartLoc);
        panel.add(startingInput);
        JLabel askEndLoc = new JLabel("Enter the Ending Destination: ");
        askEndLoc.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        panel.add(askEndLoc);
        panel.add(endingInput);
        shortestPathButton.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        panel.add(shortestPathButton);
        fewestChangesButton.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        panel.add(fewestChangesButton);

        shortestPathButton.addActionListener(event -> inputSubmitted(1));
        fewestChangesButton.addActionListener(event -> inputSubmitted(2));

        frame.setContentPane(panel);
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void inputSubmitted(int pathfindingType)
    {
        startLocation = startingInput.getText();
        endLocation = endingInput.getText();

        if (startingLocations.contains(startLocation)) //Checks if userInput matches any locations from the file.
        {
            firstLoc = true;
        }
        if (endingLocations.contains(endLocation)) //Checks if userInput matches any locations from the file.
        {
            secondLoc = true;   
        }
        if (firstLoc && secondLoc)
        {
            frame.dispose();

            Pathfinding p = new Pathfinding(startLocation, endLocation, pathfindingType);
        }
        if (firstLoc == false || secondLoc == false)
        {
            panel.add(new JLabel("Invalid Input. Try typing the locations again."));
            panel.revalidate(); //The components are laid out again like they were before.
            panel.repaint(); //Refresh the text and UI components.
            startingInput.setText("");
            endingInput.setText("");
        }

    }
}
