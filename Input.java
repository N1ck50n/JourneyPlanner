import java.util.Scanner;

public class Input extends FileRead
{
    boolean firstLoc = false;
    boolean secondLoc = false;
    protected String startLocation;
    protected String endLocation;

    public Input() 
    {
        super();
        
        Scanner scanner = new Scanner(System.in);


        while (firstLoc == false && secondLoc == false)
        {
            // User input for starting location.
            System.out.print("Enter the starting location: ");
            startLocation = scanner.nextLine();

            //User input for ending location.
            System.out.print("Enter the ending location: ");
            endLocation = scanner.nextLine();

            
            // System.out.println(startLocation);
            // System.out.println(endLocation);

            if (startingLocations.contains(startLocation))
            {
                firstLoc = true;
            }
            if (endingLocations.contains(endLocation))
            {
                secondLoc = true;   
            }
            if (firstLoc && secondLoc)
            {
                System.out.println("Valid.");
                break;
            }
            if (firstLoc == false && secondLoc == false)
            {
                System.out.println("Invalid input.");
            }
        }
    
        scanner.close();
        
    }
    
}
