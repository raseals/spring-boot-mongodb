package com.raseals.springbootmongodb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@Slf4j
public class SpringBootMongodbApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootMongodbApplication.class, args);
	}
}
