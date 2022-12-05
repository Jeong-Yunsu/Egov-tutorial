package app;

import app.common.config.EgovWebApplicationInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@ServletComponentScan
@SpringBootApplication
@Import({EgovWebApplicationInitializer.class})
public class EgovBootApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        System.out.println("##### EgovBootApplication Start #####");

        SpringApplication springApplication = new SpringApplication(EgovBootApplication.class);
        //springApplication.setBannerMode(Banner.Mode.OFF);
        //springApplication.setLogStartupInfo(false);
        springApplication.run(args);

        System.out.println("##### EgovBootApplication End #####");
    }
}