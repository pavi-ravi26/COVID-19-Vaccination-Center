
import java.util.Scanner;


public class VaccinationCenter {
    public static void main(String[] args) throws Exception {
        int tokenNo=1;
        int totalVacInStock = 150;
        Scanner input= new Scanner(System.in);


        //initializing booths
        Booth booth0 = new Booth(0,0,"AstraZeneca");
        Booth booth1 = new Booth(1,0,"AstraZeneca");
        Booth booth2 = new Booth(2,0,"Sinopharm");
        Booth booth3 = new Booth(3,0,"Sinopharm");
        Booth booth4 = new Booth(4,0,"Pfizer");
        Booth booth5 = new Booth(5,0,"Pfizer");
        Booth[] arrBooth={booth0,booth1,booth2,booth3,booth4,booth5};

        System.out.println("All booths are initialised");

        //initializing patientBooth
        Patient[] arrPatientInBooth = new Patient[10];
        Patient.initialisePatientBooth(arrPatientInBooth);



        boolean result = true;
        while (result) {
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

            switch (choice) {
                case "100":case "VVB":
                    Booth.viewAllBooth(arrBooth);
                    break;
                case "101": case "VEB":
                    Booth.viewEmptyBooth(arrBooth);
                    break;
                case "102":case "APB":
                    totalVacInStock=(Booth.addPatient(arrBooth,arrPatientInBooth,totalVacInStock));
                    break;
                case "103":case "RPB":
                    Booth.removePatient(arrBooth,arrPatientInBooth,totalVacInStock);
                    break;
                case "104":case "VPS":

                    //sortPatient(arrPatient);
                    break;
                case "105":case "SPD":
                    Booth.storeProgram(arrBooth,arrPatientInBooth,totalVacInStock);
                    break;
                case "106":case "LPD":
                    Booth.loadProgram(arrBooth,arrPatientInBooth);
                    break;
                case "107":case "VRV":
                    Booth.remainingVaccination(totalVacInStock);
                    break;
                case "108":case "AVS":
                    Booth.addToStock(totalVacInStock);
                    break;
                case "999":case "EXT":
                    Booth.exitProgram();
                    result = false;
            }
        }



    }
}
