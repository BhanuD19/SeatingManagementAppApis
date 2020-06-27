package com.project.seatingplan;

import com.project.seatingplan.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfiguration.class)

public class SeatingplanApplication {

  public static void main(String[] args) {
    SpringApplication.run(SeatingplanApplication.class, args);
  }

}
