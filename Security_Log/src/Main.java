/** Programmer name: Keilon Robertson
 *  Vehicle Log Final Project
 *  Date: 27/10/2018
 *  Course: Computer Science II **/

/* THIS IS THE MAIN CLASS FOR THE PROJECT */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args)throws IOException {

        Scanner input = new Scanner(System.in);//object to input data into the  file

        LinkedList vehicleLog = new LinkedList();
        String plate = "", fn = "", ln = "",mak = "", mod = "", col = "";
        String dat = "", entry = "", departure = "";
        String searchItem;
        int option = 0;
        boolean exitProg = false;
        boolean fnd = false;
        String BET = "";

        do {
            vehicleLog.readLog();

            System.out.println("1. Add New Vehicle Log\n "
            +"2. Update a log\n "
            +"3. Display the number of Vehicles on University compound\n "
            +"4. Display log entries for all the vehicles on the university\n "
            +"5. Display Vehicle Log History\n "
            +"6. HELP\n "
            +"7. EXIT\n ");


            try {
                System.out.println("Please enter an option from the menu: ");
                option = input.nextInt();
            }
            catch (Exception e)
            {
                System.err.println("Please enter a valid number: ");
            }

            switch(option)
            {
                //option to add vehicle log
                case 1:
                    System.out.println("\nYou have chosen to add a new vehicle log");
                    plate = input.nextLine();
                    System.out.println("Please enter the requested details where applicable.");
                    System.out.println("Vehicle's registration plate: ");
                    plate = input.nextLine();
                    System.out.println("Drivers's first name: ");
                    fn = input.nextLine();
                    System.out.println("Driver's last name: ");
                    ln = input.nextLine();
                    System.out.println("Vehicle's make (Manufacturer): ");
                    mak= input.nextLine();
                    System.out.println("Vehicle's model: ");
                    mod= input.nextLine();
                    System.out.println("Vehicle Colour: ");
                    col= input.nextLine();
                    System.out.println("Current date: ");
                    dat= input.nextLine();
                    System.out.println("Time of entry: ");
                    entry= input.nextLine();

                    vehicleLog.addVehicle(plate,fn, ln, mak,mod,col,dat,entry);
                    break;

                //option to update log
                case 2:
                    vehicleLog.printList();
                    BET = input.nextLine();
                    System.out.println("Please enter the registration plate number of the vehicle that you would like to update: ");
                    searchItem = input.nextLine();
                    System.out.println("Please enter the time of departure: ");
                    departure = input.nextLine();
                    vehicleLog.updateLog(searchItem,departure);
                    break;

                //option to display number of vehicles on the university compound
                case 3:
                        System.out.println("There are a total of "+vehicleLog.countVehicles()+" vehicle/s on the compound.");
                    break;

                //option to display the log entries for all vehicles on the compound sorted by registration plate
                case 4:
                    vehicleLog.printList();
                    break;

                //option to display the log entries from the vehicle_log_history file
                case 5:
                        vehicleLog.printHistory();
                    break;

                //Provide information on the application
                case 6:
                    System.out.println("HELP PAGE IN DEVELOPMENT!");
                    break;

                case 7:
                    System.out.println("Until next time!!!!");
                    exitProg = true;
                    break;

                default:
                    System.out.println(" !!!ERROR!!! That is not an option in the menu");
                    break;

            }//end switch
        }while(!exitProg);

    }//end main
}//end main class
