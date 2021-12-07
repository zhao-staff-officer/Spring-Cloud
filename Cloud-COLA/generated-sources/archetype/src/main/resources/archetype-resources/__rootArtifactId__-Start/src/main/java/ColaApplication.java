#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"${package}","com.alibaba.cola"})
public class ColaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColaApplication.class, args);
    }

}
