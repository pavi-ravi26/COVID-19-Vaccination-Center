import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class ServiceCenterExample {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int tokenNo=0;
        int totvacStock=150;
        int[] ServiceCenter = new int[7];
        String[][] arrPatient = new String[7][10];

        //initialise
        //initialise(ServiceCenter); //better to initialise in a procedure
        initialise(ServiceCenter,arrPatient);
        boolean result=true;
        while(result) {
            System.out.println("--------- WELCOME TO COVID-19 VACCINATION CENTER --------");
            System.out.print("100 or VVB: View all Vaccination Booths\n" +
                    "101 or VEB: View all Empty Booths\n" +
                    "102 or APB: Add Patient to a Booth\n" +
                    "103 or RPB: Remove Patient from a Booth\n" +
                    "104 or VPS: View Sorted Patients list  \n" +
                    "105 or SPD: Store Program Data into file\n" +
                    "106 or LPD: Load Program Data from file\n" +
                    "107 or VRV: View Remaining Vaccinations\n" +
                    "108 or AVS: Add Vaccinations to the Stock\n" +
                    "999 or EXT: Exit the Program\n" +
                    "Enter your choice: ");
            String choice = input.nextLine();
            //selecting appropriate method
            switch (choice) {
                case "100":case "VVB":
                    viewAllBooth(ServiceCenter);
                    break;
                case "101":case "VEB":
                    viewEmptyBooth(ServiceCenter);
                    break;
                case "102":case "APB":
                    totvacStock=addPatient(arrPatient,tokenNo,totvacStock,ServiceCenter);
                    break;
                case "103":case "RPB":
                    removePatient(arrPatient,totvacStock,ServiceCenter);
                    break;
                case "104":case "VPS":
                    //sortPatient(arrPatient);
                    break;
                case "105":case "SPD":
                    storeProgram(arrPatient,totvacStock,ServiceCenter);
                    break;
                case "106":case "LPD":
                    loadProgram(arrPatient,ServiceCenter,totvacStock);
                    break;
                case "107":case "VRV":
                    remainingVaccination(totvacStock);
                    break;
                case "108":case "AVS":
                    addToStock(totvacStock);
                    break;
                case "999":case "EXT":
                    exitProgram();
                    result=false;
                default:

            }
        }

    }
    //initializing all booths
    private static void initialise( int arrCenter[],String arrRef[][]) {
        for (int x = 0; x < 6; x++ ) {
            arrCenter[x] = 0;
            switch(x){
                case 0:case 1:
                    arrRef[x][0]="AstraZeneca";
                    break;
                case 2: case 3:
                    arrRef[x][0]="Sinopharm";
                    break;
                case 4: case 5:
                    arrRef[x][0]="Pfizer";
                    break;
            }
        }
        System.out.println( "All booths  are initialised\n ");
    }

    //function to view all vaccination booth
    public static void viewAllBooth(int arrCenter[]) {
        for (int x = 0; x < 6; x++) {
            if (arrCenter[x]==0) {
                System.out.println("booth " + x + " is empty");
            } else {
                System.out.println("Booth " + x + ":" +"occupied by "+ arrCenter[x] +" patients.");

            }
        }
    }

    //function to view empty booth
    public static void viewEmptyBooth(int arrCenter[]){
        System.out.println("Empty booths are: ");
        for (int x = 0; x < 6; x++) {
            if (arrCenter[x]==0) {
                System.out.println("booth " + x );
            }
        }
    }

    //function to add patient to booth
    public static int addPatient(String arrRef[][],int tokenNo,int vacStock,int arrCenter[]){
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the booth to be add(AstraZeneca(booth 0 or 1)/Sinopharm(booth 2 or 3)/Pfizer(booth 4 or 5)) : " );
        String patientBooth = input.nextLine();
        System.out.println("Enter the booth number: " );
        int boothNumber= input.nextInt();
        tokenNo++;


        if (boothNumber<6){
            System.out.print("Enter Patient's First Name: ");
            String FirstName = input.next();
            System.out.print("Enter Patient's SurName: ");
            String SurName = input.next();
            switch(boothNumber){   //inserting elements to the 2d array
                case 0:
                    int a=arrCenter[0];
                    arrRef[boothNumber][a+1]=FirstName+" "+SurName;
                    arrCenter[0]=a+1;  //incrementing patient count
                    break;
                case 1:
                    int b=arrCenter[1];
                    arrRef[boothNumber][b+1]=FirstName+" "+SurName;
                    arrCenter[1]=b+1;  //incrementing patient count
                    break;
                case 2:
                    int c=arrCenter[2];
                    arrRef[boothNumber][c+1]=FirstName+" "+SurName;
                    arrCenter[2]=c+1;  //incrementing patient count
                    break;
                case 3:
                    int d=arrCenter[3];
                    arrRef[boothNumber][d+1]=FirstName+" "+SurName;
                    arrCenter[3]=d+1;  //incrementing patient count
                    break;
                case 4:
                    int e=arrCenter[4];
                    arrRef[boothNumber][e+1]=FirstName+" "+SurName;
                    arrCenter[4]=e+1;  //incrementing patient count
                    break;
                case 5:
                    int f=arrCenter[5];
                    arrRef[boothNumber][f+1]=FirstName+" "+SurName;
                    arrCenter[5]=f+1;  //incrementing patient count
                    break;
            }
            System.out.println("Patient added to booth " + patientBooth + " "+ boothNumber);
        }else{
            System.out.println("invalid booth number!! ");
        }
        return vacStock-1;
    }

    //function to remove patient from booth
    public static int removePatient(String arrRef[][],int vacStock,int arrCenter[]){
        Scanner input=new Scanner(System.in);
        System.out.print("Enter the name of patient to be removed(format: First Surname): ");
        String rPatientName= input.nextLine();

        //seeking for Patient name and remove it
        for (int i=0;i<arrRef.length;i++){
            for (int j=1;j<arrRef[i].length;j++){
                if (rPatientName.equals(arrRef[i][j])){
                    arrRef[j]=arrRef[j+1];
                    System.out.println("Patient"+rPatientName+"is removed from booth "+i);
                    arrCenter[i]=arrCenter[i]-1;
                }
            }
        }
        return vacStock;
    }

    //Patient sorting
    //public static void sortPatient(String arrRef[][] ) {
        //adding all patient to an array
        //char[] arrChar= new char[];
        //int n=0;
        //for (int i=0;i< arrRef.length;i++){
            //for(int j=0;j<arrRef[i].length;j++){
                //arrChar[n]=arrRef[i][j];
            //}

        //}
    //}
    //function to write data to VaccinationData file
    public static void storeProgram(String arrRef[][],int vacStock,int arrCenter[]) {    //referred from https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter data = new FileWriter("VaccinationData.txt");
            data.write("stocklevel"+" "+vacStock+"\n");
            for(int i=0;i<arrRef.length;i++){
                data.write(arrCenter[i]+" - ");
                data.write(arrRef[i][0]+" - ");
                for(int j=1;j<arrRef[i].length;j++){
                    data.write(arrRef[i][j]+" , ");
                }
                data.write("\n");
            }
            data.write("-----");
            System.out.println("program data updated");
            data.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    //function to load data from VaccinationData file
    public static int loadProgram(String arrRef[][],int arrCenter[],int vacStock){          //referred from https://www.w3schools.com/java/java_files_read.asp
        try{
            File data= new File("VaccinationData.txt");
            Scanner loadData = new Scanner(data);
            loadData.next();
            vacStock=Integer.parseInt(loadData.next());
            int i=0;
            while(loadData.hasNextLine()){
                int patientCount = Integer.parseInt(loadData.next());
                arrCenter[i] = patientCount;
                //loadData.next();
                arrRef[i][0] =loadData.next();
                //loadData.next();
                for(int j=1;j<10;j++) {
                    arrRef[i][j] =loadData.next();
                    //loadData.next();
                }
                //loadData.next();
                if (loadData.hasNext("-----")){ break; }
                i=i+1;

            }
            loadData.close();
        }catch (FileNotFoundException e) {
            System.out.print("Couldn't load data");
            e.printStackTrace();
        }
        return vacStock;
    }

    //function to display remaining stock and warning
    public static void remainingVaccination(int vacStock){
        if (vacStock>20){
            System.out.println("Remaining vaccination in stock: "+ vacStock);
        }else {
            System.out.println("OUT OF STOCK WARNING!!\n Stock has reached 20 or less ");
            System.out.println("Remaining vaccination in stock: " + vacStock);
        }
    }

    //function to add vaccinations to stock
    public static void addToStock(int vacStock){
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number of vaccination to be added to the stock: ");
        int newStock = input.nextInt();
        vacStock+=newStock;
        System.out.println("New vaccinations added to the stock : "+vacStock);
    }

     public static void exitProgram(){
         System.out.println("Program has been ended. \n Thank You!! ");
     }


}
