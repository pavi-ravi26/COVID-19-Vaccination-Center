public class Patient extends Booth{
    String firstName;
    String surName;
    int age;
    String city;
    String NIC;
    String vacRequested;


    public Patient(String firstName,String surName,int age, String city,String NIC,String vacRequested,int boothNumber){
        super(boothNumber);
        this.firstName= firstName;
        this.surName=surName;
        this.age=age;
        this.city=city;
        this.NIC = NIC;
        this.vacRequested = vacRequested;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getVacRequested() {
        return vacRequested;
    }

    public void setVacRequested(String vacRequested) {
        this.vacRequested = vacRequested;
    }

    //inititalizing patientBooth
    public static void initialisePatientBooth(Patient arrPatient[]){
        for(int i=0;i<10;i++){
            arrPatient[i]=new Patient("e","e",0,"e","e","e",0);
        }
    }

}

