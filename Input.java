import java.util.Scanner;

public class Input 
{
    public Input()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the starting location: ");
        String startLocation = scanner.nextLine();

        System.out.print("Enter the ending location: ");
        String endLocation = scanner.nextLine();
    }
    
}
