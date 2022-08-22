import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FuelStation {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String Customer = null;
        int place_r=1;
        int place_c=2;
        int place_count=0;
        String[][] fuelStation = new String[6][3];// 2D array
        int fullFuelStock=6600;
        boolean condition = true;

        while (condition == true) {
            System.out.println("VFQ: View all Fuel Queues.");
            System.out.println("VEQ: View all Empty Queues.");
            System.out.println("ACQ: Add customer to a Queue.");
            System.out.println("RCQ: Remove a customer from a Queue. (From a specific location)");
            System.out.println("PCQ: Remove a served customer.");
            System.out.println("VCS: View Customers Sorted in alphabetical order (Do not use library sort routine)");
            System.out.println("SPD: Store Program Data into file.");
            System.out.println("LPD: Load Program Data from file.");
            System.out.println("STK: View Remaining Fuel Stock.");
            System.out.println("AFS: Add Fuel Stock.");
            System.out.println("EXT: Exit the Program.");

            String Option = input.next();

            if (Option.equals("VFQ")) { // view all fuel queues
                view(fuelStation, Customer);
            }
            if (Option.equals("VEQ")){  // view all empty queues
                q_vacant(fuelStation, Customer);
            }
            if (Option.equals("SDP")){  //store program data into file
                readDetails();

            }


            if (Option.equals("ACQ")) { //Add customer to a Queue.
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (fuelStation[i][j] == null)
                            System.out.println("Place " + (i) +" "+ (j) +" " +" is empty");
                        if (fuelStation[i][j] != null)
                            System.out.println("Place " + (i) +" "+ (j) +" " +" is already occupied");
                    }
                    System.out.println("");
                    /*check that specific position is empty or not*/
                }
                System.out.println("Enter the Place in row(0-6) and columns(0-3) or EXT to stop:");
                System.out.println("There are three columns/queues for the Filling Station");
                place_r = input.nextInt();
                place_c=  input.nextInt();
                System.out.println("Enter name for Place " + place_r+" "+place_c + " :");
                Customer = input.next();
                fuelStation[place_r][place_c] = Customer;
                fullFuelStock=(fullFuelStock-10);
                if (fullFuelStock<=500){
                    System.out.println("Low fuel stock:"+fullFuelStock);
                    break;
                }
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.println("Place " + (i) +" "+ (j) + " "+ "is occupied by" +" "+ fuelStation[i][j]);
                        System.out.println("  ");
                    }
                }
                System.out.println("remaining Fuel Stock:"+fullFuelStock);
                System.out.println(" ");
            }
            if (Option.equals("EXT")){  //Exit the Program.
                exit(fuelStation, Customer);
            }
            if (Option.equals("STK")){  //View Remaining Fuel Stock
                System.out.println("\nRemaining Stock");
                System.out.println("Fuel Stock in Liters  "+" "+fullFuelStock);
            }


            if (Option.equals("RCQ")){  //Remove a customer from a Queue. (From a specific location)

                //Searches if cabin is occupied. if it is, then it will delete customer from that room
                view(fuelStation, Customer);
                System.out.println("Enter the place which you want to delete a customer from: ");
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (fuelStation[i][j] == null)
                            System.out.println("Place " + (i) +" "+ (j) + " is empty");
                    }
                }
                System.out.println("Enter the Place (0-18) that customer should remove:");
                place_r = input.nextInt();
                place_c=  input.nextInt();
                System.out.println("Enter name for Place " + place_r+" "+place_c + " :");
                Customer = input.next();
                fuelStation[place_r][place_c] = "empty";

                delete(fuelStation, Customer);
                System.out.println(" ");

            }
            if (Option.equals("AFS")){  //Add Fuel Stock.
                System.out.println("\nRemaining Stock when Customer is removed:");
                fullFuelStock=(fullFuelStock+10);
                System.out.println("Fuel Stock in Liters  "+" "+fullFuelStock);
            }

            if (Option.equals("PCQ")){  //Remove a served customer.
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        int served_Cus;
                        if (fuelStation[i][j] != null) {
                        }
                        served_Cus = Integer.parseInt(Customer);
                        fuelStation[i][j] = String.valueOf(served_Cus);
                        String served = String.valueOf(10);
                        if (fuelStation[i][j] == served)
                            System.out.println("Place " + (i) +" "+ (j) + " is served");

                        delete(fuelStation, Customer);
                        System.out.println("");

                    }
                }
            }
            System.out.println(" ");
        }
    }

    public static void readDetails() {
        System.out.println("Read saved details");
        try{
            File file=new File("Fuel_details.txt");
            //file = Paths.get("F:\IIT");
            Scanner reader=new Scanner(file);
            while(reader.hasNextLine())
            {
                String detail=reader.nextLine();
                System.out.println(detail);
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("File not found: ");
        }

    }

    public static void exit(String[][] fuelStation, String customer) {
        System.exit(0);
    }

    public static void q_vacant(String[][] fuelStation, String Customer) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                //int z1 = 0;
                //int z2 = 0;
                String Customername = Customer;
                fuelStation[i][j] = Customername;
                if (fuelStation[i][j] == null)
                    System.out.println("Place " +(i)+" "+(j)+ " is empty");
            }
        }
        System.out.println(" ");
    }

    public static void view(String[][] fuelStation, String Customer) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                String Customername = Customer;
                fuelStation[i][j] = Customername;
                if (fuelStation[i][j] !=null)
                    System.out.println("Place " +(i)+" "+(j)+ " is occupied by " + fuelStation[i][j]);
                else {
                    System.out.println("Place " +(i)+" "+(j)+ " is empty");
                }
            }
            System.out.println(" ");
        }
    }

    public static void delete(String[][] fuelStation, String Customer) {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                //int z1 = 0;
                //int z2 = 0;
                String Customername = Customer;
                fuelStation[i][j] = Customername;
                if (fuelStation[i][j] !=null)
                    //if (fuelStation[z1][z2].equals("empty"))
                    System.out.println("Place " +(i)+" "+(j)+ " is empty");
                else {
                    System.out.println("Place " +(i)+" "+(j)+ " is occupied by " + " "+ fuelStation[i][j]);
                }
            }
            System.out.println(" ");
            System.out.println("Enter STK option to see new fuel stock");
        }
    }
}

