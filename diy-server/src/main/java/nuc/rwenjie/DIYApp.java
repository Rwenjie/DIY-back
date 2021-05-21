package nuc.rwenjie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Rwenjie
 * @ClassName DIYApplication
 * @Description TODO 启动类
 * @Date 2021/3/18 13:12
 **/

@SpringBootApplication
@MapperScan("nuc.rwenjie.modules.sys.mapper")
public class DIYApp {
    public static void main(String[] args) {

        SpringApplication.run(DIYApp.class, args);
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        System.out.println("______________________________________________________________________________________________________________________________启动时间: " + ft.format(dNow));
    }
}
