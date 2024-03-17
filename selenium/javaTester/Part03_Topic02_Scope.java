package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Part03_Topic02_Scope {
    //ngòoài hàm
    //Các biến được khai báo ở bên ngoài hàm -> phạm vi là class
    //Biến Global (Toàn cục)
    WebDriver driver;


    String homepageUrl ; // Khai báo

    String fullname = "Cuong"; //khai báo và khởi tạo
    @BeforeClass
    public void beforeClass(){
        driver =new ChromeDriver();
}

    @Test
    public void TC_01() {
        //Biến được khai báo ở trong 1 hàm -> phạm vi cục bộ (local)   /// beforeClass là hàm TC_01 là hàm
        //Trong 1 hàm có 2 biến cùng tên (Global/local) thì nó sẽ lấy biến local
        //1 biến local nếu như gọi tới dùng nếu như chưa được khởi tạo sẽ bị lỗi

        String homepageUrl ="https://www.facebook.com/" ;
        driver.get(homepageUrl);

        //Dùng this để phân biệt biến cục bộ và loacal
        driver.get(this.homepageUrl);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}