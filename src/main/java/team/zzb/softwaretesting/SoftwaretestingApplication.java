package team.zzb.softwaretesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "team.zzb.softwaretesting")
@ServletComponentScan
public class SoftwaretestingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SoftwaretestingApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SoftwaretestingApplication.class);
    }

}
