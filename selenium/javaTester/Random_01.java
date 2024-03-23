package javaTester;

public class Random_01 {
    //Java Builtin (Cung cấp sẵn lấy ra sử dụng)

    //JAva Libararies (do cá nhân tổ chứuc tự viết)

    public static void main (String[] args){
        Random_01 rand = new Random_01();
        System.out.println(rand.NextDouble(0));
        System.out.println(rand.nextInt(9999));

        System.out.println("RandomCuongTest" + new Random_01().nextInt(9999) + "Automation");
        System.out.println("RandomCuongTest" + new Random_01().nextInt(9999) + "Automation");
        System.out.println("RandomCuongTest" + new Random_01().nextInt(9999) + "Automation");
        System.out.println("RandomCuongTest" + new Random_01().NextDouble(9999) + "Automation");

    }

    public int nextInt(int i) {
        return 0;
    }


    public int NextInt(int i) {
        return 0;
    }

    private boolean NextDouble(int i) {
        return false;
    }

}
