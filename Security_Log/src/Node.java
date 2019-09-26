/** Programmer name: Keilon Robertson
 *  Security Log Final Project
 *  Date: 20/12/2018
 *  Course: Computer Science II **/

/* THIS IS THE NODE CLASS FOR THE PROJECT */

public class Node {
    String regPlate;
    String firstName;
    String lastName;
    String make;
    String model;
    String color;
    String date;
    String entryTime;
    String departTime;

    //reference
    Node link;

    //default constructor
    public Node()
    {
        regPlate = "";
        firstName = "";
        lastName = "";
        make = "";
        model = "";
        color = "";
        date = "";
        entryTime = "";
        departTime = "";
        link = null;
    }//end default constructor

    //overloaded constructor
    public Node(String plate, String fn,String ln, String mak, String mod, String col, String dat,String entry, String departure, Node lnk)
    {
        firstName = fn;
        lastName = ln;
        make = mak;
        model = mod;
        color = col;
        date = dat;
        entryTime = entry;
        departTime = departure;
        regPlate = plate;
        link = null;
    }//end overloaded constructor
}//end Node class
