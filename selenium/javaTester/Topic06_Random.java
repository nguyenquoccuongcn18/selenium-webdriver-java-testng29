import java.util.Random;
public class Topic06_Random {
    // Tạo mới 1 đối tượng Random
    // sử dụng từ khóa new

   public static void main (String[] args){
       Random rd = new Random();
       int number = rd.nextInt();

       System.out.println("Số vừa được sinh ra là " + number);
       System.out.println("RandomCuongTest" + new Random().nextInt(9999) + "Automation");
       System.out.println("RandomCuongTest" + new Random().nextInt(9999) + "Automation");
       System.out.println("RandomCuongTest" + new Random().nextInt(9999)+ "Automation");
       System.out.println("RandomCuongTest" + new Random().nextInt(9999) + "Automation");

       int number1 = rd.nextInt(4);    // trả về 1 số nguyên nằm trong phạm vi [0...3)
       System.out.println("Số vừa được sinh ra là " + number1);

   }



}