import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Booth{
    int patientCount;
    int boothNumber;
    String boothType;
    LinkedList <Patient> waitingList =new LinkedList<>(); //each booth has waiting queue such that the patient requested tat booth
    //referred from GeeksforGeeks

    public Booth(int boothNumber, int patientCount,String boothType){
        this.boothNumber=boothNumber;
        this.patientCount=patientCount;
        this.boothType=boothType;
    }
    public Booth(int boothNumber){
        this.boothNumber=boothNumber;
    }

    public String getBoothType() {
        return boothType;
    }

    public void setBoothType(String boothType) {
        this.boothType = boothType;
    }

    public int getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }

    public int getBoothNumber() {
        return boothNumber;
    }

    public void setBoothNumber(int boothNumber) {
        this.boothNumber = boothNumber;
    }

    public LinkedList<Patient> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(LinkedList<Patient> waitingList) {
        this.waitingList = waitingList;
    }

    //function to view all vaccination booth
    public static void viewAllBooth(Booth arrRef[]) {
        for (Booth b:arrRef) {
            if (b.patientCount == 0) {
                System.out.println("booth " + b.boothNumber + " is empty");
            } else {
                System.out.println("Booth " + b.boothNumber + " is occupied by " + b.patientCount + " patients.");
            }
        }
    }

    //function to view empty booth
    public static void viewEmptyBooth(Booth arrRef[]){
        System.out.println("Empty booths are: ");
        for (Booth b:arrRef) {
            if (b.patientCount==0) {
                System.out.println("booth " + b.boothNumber );
            }
        }
    }


    //function to add patient to booth
    public static int addPatient(Booth arrRef[],Patient arrPatient[],int vacStock){
        Scanner input =new Scanner(System.in);
        System.out.println("Enter the booth to be add(AstraZeneca,Sinopharm,Pfizer) : ");
        String boothName = input.nextLine();

        for(Booth b:arrRef){
            if(boothName.equals(b.getBoothType())) {
                if (vacStock > 0) {
                    int boothNo = b.getBoothNumber();
                    int i = b.getPatientCount();
                    System.out.println("Enter Patient's First name : ");
                    String firstName = input.nextLine();
                    arrPatient[i].setFirstName(firstName);
                    System.out.println("Enter Patient's Surname : ");
                    String surName = input.nextLine();
                    arrPatient[i].setSurName(surName);
                    System.out.println("Enter Patient's age : ");
                    int age = Integer.parseInt(input.nextLine());
                    arrPatient[i].setAge(age);
                    System.out.println("Enter Patient's city: ");
                    String city = input.nextLine();
                    arrPatient[i].setCity(city);
                    System.out.println("Enter Patient's NIC : ");
                    String NIC = input.nextLine();
                    arrPatient[i].setNIC(NIC);
                    arrPatient[i].setVacRequested(boothName);
                    arrPatient[i].setBoothNumber(boothNo);
                    System.out.println("Patient added to booth " + arrPatient[i].getBoothNumber());
                    b.setPatientCount(i + 1);
                    break;
                } else {

                    int boothNo = b.getBoothNumber();
                    System.out.println("Enter Patient's First name : ");
                    String firstName = input.nextLine();

                    System.out.println("Enter Patient's Surname : ");
                    String surName = input.nextLine();

                    System.out.println("Enter Patient's age : ");
                    int age = Integer.parseInt(input.nextLine());

                    System.out.println("Enter Patient's city: ");
                    String city = input.nextLine();

                    System.out.println("Enter Patient's NIC : ");
                    String NIC = input.nextLine();
                    System.out.println("Sorry the Center is full!! You've been added to waiting list");
                    b.waitingList.addLast(new Patient(firstName, surName, age, city, NIC, boothName, boothNo));
                }
            }else{
                continue;
            }
        }
        return vacStock-1;
    }

    //function to remove patient from booth
    public static void removePatient(Booth arrRef[],Patient arrPatient[],int vacStock){
        Scanner input=new Scanner(System.in);
        System.out.print("Enter the name of patient to be removed: ");
        String rPatientName= input.nextLine();

        //seeking for Patient name and remove it
        for (int i=0;i<arrPatient.length;i++){
            if (rPatientName.equals(arrPatient[i].getFirstName())){
                if (vacStock<150){
                    arrPatient[i]=new Patient("e","e",0,"e","e","e",0);
                }else {
                    arrPatient[i] = arrRef[i].waitingList.getFirst();
                    arrRef[i].waitingList.removeFirst();
                    System.out.println("Patient in waiting list added to booth "+i);
                }
                System.out.println("Patient "+rPatientName+" is removed from booth ");
                arrRef[i].setPatientCount(arrRef[i].getPatientCount()-1);
            }
        }
    }

    //function to write data to VaccinationData file
    public static void storeProgram(Booth arrRef[],Patient arrPatient[],int vacStock) {
        try {
            FileWriter data = new FileWriter("VaccinationDataClass.txt");
            data.write("stocklevel"+" "+vacStock+"\n");
            for (Booth a:arrRef){
                int boothNumber= a.getBoothNumber();
                int patientCount=a.getPatientCount();
                data.write(boothNumber+" "+patientCount+"\n");
            }
            data.write("--------\n");
            for(Patient b:arrPatient){
                int boothNumber= b.getBoothNumber();
                String firstName =b.getFirstName();
                String surName=b.getSurName();
                int age=b.getAge();
                String city=b.getCity();
                String NIC=b.getNIC();
                String vacRequested=b.getVacRequested();
                data.write( boothNumber+"  "+firstName+" "+surName+" "+age+" "+city+" "+NIC+" "+vacRequested+"\n");
            }
            data.write("--------\n");
            int i=0;
            for(Booth ele : arrRef) {
                data.write(ele.getWaitingList()+ "\n");
                i=i+1;

            }
            System.out.println("program data updated");
            data.write("--------");
            data.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    //function to load data from VaccinationData file
    public static void loadProgram(Booth arrRef[],Patient arrPatient[]){
        try{
            File data= new File("VaccinationDataClass.txt");
            Scanner loadData = new Scanner(data);
            while(loadData.hasNextLine()){
                for (Booth b:arrRef){
                    if (loadData.next().equals("--------")){
                        break;
                    }else {
                        //int boothNumber = Integer.parseInt(loadData.next());
                        System.out.print("2"+loadData.next());
                        System.out.print(loadData.next());
                        //int patientCount = Integer.parseInt(loadData.next());
                        System.out.print("3"+loadData.next());
                        //b.setBoothNumber(boothNumber);
                        //b.setPatientCount(patientCount);
                    }
                }
                for (Patient a:arrPatient){
                    if (loadData.next().equals("--------")){
                        break;
                    }else {
                        int boothNumber = Integer.parseInt(loadData.next());
                        String patientName = loadData.next();
                        System.out.print("boothNumber\n");
                        a.setBoothNumber(boothNumber);
                        a.setFirstName(patientName);
                    }
                }
            }
            loadData.close();
        }catch (FileNotFoundException e) {
            System.out.print("Couldn't load data");
            e.printStackTrace();
        }
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
    public static int addToStock(int vacStock){
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number of vaccination to be added to the stock: ");
        int newStock = input.nextInt();
        vacStock+=newStock;
        System.out.println("New vaccinations added to the stock");
        return vacStock;
    }

    public static void exitProgram(){
        System.out.println("Program has been ended. \n Thank You!! ");
    }




}
