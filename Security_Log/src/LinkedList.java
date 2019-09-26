import java.io.*;
import java.util.*;

/** Programmer name: Keilon Robertson
 *  Vehicle Log Final Project
 *  Date: 27/10/2018
 *  Course: Computer Science II **/

/* THIS IS THE LINKED_LIST CLASS FOR THE PROJECT */

public class LinkedList {

    //reference objects
    Node head;
    Node current;
    Node trailCurrent;
    Node tail;

    //variables
    int listLength;
    String search;
    final String fileName = "vehicle_data.vd";
    final String historyFile = "vehicle_log_history.vlh";

    //default constructor
    public LinkedList() {
        head = null;
        current = null;
        trailCurrent = null;
        tail = null;
        search = "";
        listLength = 0;
    }//end default constructor

    /*ALL LINKED LIST METHODS*/

    public void addVehicle(String plate, String fn, String ln, String mak, String mod, String col, String dat, String entry) throws IOException {
        try (FileWriter outFile = new FileWriter(fileName, true);
             PrintWriter printFile = new PrintWriter(outFile)) {
            printFile.println("---Start of Entry---");
            printFile.println(plate);
            printFile.println(fn);
            printFile.println(ln);
            printFile.println(mak);
            printFile.println(mod);
            printFile.println(col);
            printFile.println(dat);
            printFile.println(entry);
            printFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("!!!ERROR!!! The file was not found!!!!!!");
        }//end file not found exception
    }//end addVehicle

    public int countVehicles() {
        return listLength;
    }//end countVehicles

    public final void printList() {
        //sortLinkedList();
        int num = 0;
        current = head;
        while (current != null) {
            System.out.println("Log Entry #" + (num + 1) + "\n");
            System.out.println("Registration Plate: " + current.regPlate);

            System.out.println("First Name: " + current.firstName);

            System.out.println("Last Name: " + current.lastName);

            System.out.println("Vehicle Make: " + current.make);

            System.out.println("Vehicle Model: " + current.model);

            System.out.println("Vehicle Colour: " + current.color);

            System.out.println("Current Date: " + current.date);

            System.out.println("Entry Time: " + current.entryTime + "\n");
            current = current.link;
            num++;
        }//end while loop
    }//end printList

    public void readLog() throws IOException {
        int num = 0;
        listLength = 0;
        boolean found = false;
        head = null;

        String start = "";
        boolean next = false;
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader readFile = new BufferedReader(fileReader)) {
            while ((start = readFile.readLine()) != null) {
                Node newNode = new Node();
                newNode.regPlate = readFile.readLine();
                String reg = newNode.regPlate;

                newNode.firstName = readFile.readLine();

                newNode.lastName = readFile.readLine();

                newNode.make = readFile.readLine();

                newNode.model = readFile.readLine();

                newNode.color = readFile.readLine();

                newNode.date = readFile.readLine();

                newNode.entryTime = readFile.readLine();

                if (head == null) {
                    head = newNode;
                    tail = newNode;
                    listLength++;
                } else {
                    current = head;
                    trailCurrent = head;
                    found = false;

                    while (current != null && !found) {
                        if (compareNode(reg) < 0)
                            found = true;

                        else {
                            trailCurrent = current;
                            current = current.link;
                        }
                    }

                    if (current == head) {
                        newNode.link = head;
                        head = newNode;
                        listLength++;
                    } else {
                        trailCurrent.link = newNode;
                        newNode.link = current;

                        if (current == null)
                            tail = newNode;

                        listLength++;
                    }
                }// end else

            }//end while loop

            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("!!!ERROR!!! The file was not found!!!!!!");
        }//end file not found exception

    }//end readLog

    public int compareNode(String reg) {
        return (reg.compareTo(current.regPlate));
    }//compare function end

    public void updateLog(String searchItem, String departure) throws IOException {
        /* FIND SEARCH ITEM*/
        boolean found = false;
        Node trailCurrent;

        //check for empty list
        if (head == null)
            System.err.println("Cannot delete from an empty list. ");
        else {
            //is the item in the first node?
            if (head.regPlate.equals(searchItem)) {
                head = head.link;
                listLength--;
                System.gc();

                //the list had one node
                if (head == null)
                    tail = null;

                listLength--;
            } else {
                trailCurrent = head;
                current = head.link;

                while (current != null && !found) {
                    if (current.regPlate.equals(searchItem))
                        found = true;
                    else {
                        trailCurrent = current;//point to first node
                        current = current.link;//point to second node

                    }
                }//end while
                if (found) {
                    System.out.println("Search Item was found and log was deleted successfully!!!!");

                    try (FileWriter outFile = new FileWriter(historyFile, true);
                         PrintWriter printFile = new PrintWriter(outFile)) {
                        printFile.println("--- Start of Entry ---");
                        printFile.println(current.regPlate);
                        printFile.println(current.firstName);
                        printFile.println(current.lastName);
                        printFile.println(current.make);
                        printFile.println(current.model);
                        printFile.println(current.color);
                        printFile.println(current.date);
                        printFile.println(current.entryTime);
                        printFile.println(departure);
                        printFile.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("!!!ERROR!!! The file was not found!!!!!!");
                    }//end file not found exception

                    //remove node from the list
                    trailCurrent.link = current.link;
                    System.gc();
                    listLength--;
                    //deleted node was the last node
                    if (tail == current)
                        tail = trailCurrent;

                }//END IF FOUND
                else
                    System.out.println(" Sorry, the item to be deleted is not in the list. ");
            }//end if else
        }//end if else
// PRINT LINKED LIST INTO FILE
        printUpdatedLog();
    }//end updateLog

    public void printUpdatedLog() throws IOException {
        current = head;
        try (FileWriter outFile = new FileWriter(fileName, false);
             PrintWriter printFile = new PrintWriter(outFile)) {
            while (current != null) {
                printFile.println("--- Start of Entry ---");
                printFile.println(current.regPlate);
                printFile.println(current.firstName);
                printFile.println(current.lastName);
                printFile.println(current.make);
                printFile.println(current.model);
                printFile.println(current.color);
                printFile.println(current.date);
                printFile.println(current.entryTime);
                current = current.link;
            }
            printFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("!!!ERROR!!! The file was not found!!!!!!");
        }//end file not found exception
    }//end printUpdatedLog

    public void printHistory() throws IOException {
        int num = 0;
        current = head; //will reference each line in the file
        String start = "";
        boolean next = false;
        try (FileReader fileReader = new FileReader(historyFile);
             BufferedReader readFile = new BufferedReader(fileReader)) {
            while (!next) {
                if ((start = readFile.readLine()) != null) {
                    System.out.println("Log Entry: #" + (num + 1) + "\n");
                    current.regPlate = readFile.readLine();
                    System.out.println("Registration Plate: " + current.regPlate);
                    current.firstName = readFile.readLine();
                    System.out.println("First Name: " + current.firstName);
                    current.lastName = readFile.readLine();
                    System.out.println("Last Name: " + current.lastName);
                    current.make = readFile.readLine();
                    System.out.println("Vehicle Make: " + current.make);
                    current.model = readFile.readLine();
                    System.out.println("Vehicle Model: " + current.model);
                    current.color = readFile.readLine();
                    System.out.println("Vehicle Colour: " + current.color);
                    current.date = readFile.readLine();
                    System.out.println("Current Date: " + current.date);
                    current.entryTime = readFile.readLine();
                    System.out.println("Entry Time: " + current.entryTime);
                    current.departTime = readFile.readLine();
                    System.out.println("Departure Time: " + current.departTime + "\n");
                    num++;
                } else
                    next = true;
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("!!!ERROR!!! The file was not found!!!!!!");
        }//end file not found exception
    }//end print History
}



