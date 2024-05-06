package com.cloud.study.seata_multipart_source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SeataMultipartSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeataMultipartSourceApplication.class, args);
	}

}
