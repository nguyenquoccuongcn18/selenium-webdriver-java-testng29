package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Data_Type {
    //Kieeu dữ lieu

    //I - Kiểu dữ liệu nguyên thủy (Primiti type)
        // 8 loại
        // số nguyên: byte-short-int-long
            //ko có phần thập phân:nvien trong 1 cty/1 học sinh trong 1 lớp

        // số thực: float-double
            //có phần thập phân:điểm số / hệ số lương .... 19.45
        // kí tự : char
            //
        //Logic:boolean



    //II - Kiểu  dữ liệu tham chiếu (Reference type)

        //Class =new
    FirefoxDriver firefoxDriver= new FirefoxDriver();


        //Interface
    WebDriver Driver ;

        //Object
    Object name = "huy";
        //String
   String namee ="huy";

         //Array
    int [] studentname = {10,115};
    //String [] studfen ={11,26};



        //Collection: List/Set/Queue
    List <String> studeen = new LinkedList<>();
    List <String> studen1 = new ArrayList<>();


    ///III
    //Khai báo 1 biến để lưu trữ 1 loại dữ liệu nào đó
    //Access Modifier:Phạm vi truyy cập (Public/Private/defaul/protected....)
    //Kiểu dữ liệu
    //Tên biến
    //Giá trị biến - Gán vào với phép =
    //Nếu như không gắn mực định (0,null)

    public int studenumber=200;

    byte number=30;
    short numerber1=3000;
    int getStudenumber=1155555555;
    long student=500005150;
    float studern=9.66F;
    private boolean studentt=true;
    public char c='d';





}
