package javaTester;

public class Topic08_Parammaster {
    static String fullnameglobal = "Cuongne";
    public static void main(String[] args) {
    //Đối số
        setFullname ("automation");
        System.out.println(getFullname());
    }
    public static void setFullname(String fullname){//tham số
        fullnameglobal = fullname;

    }
    public static String getFullname(){
        return fullnameglobal;

    }
}
