package com.example.beerserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BeerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerServerApplication.class, args);
	}
}


@RestController
class BeerController{

	@PostMapping(value = "/check", consumes = "application/json", produces = "application/json")
	public Response check (@RequestBody Person person){
		if( person.getAge()> 20){
			return new Response("OK");

		}else{
			return new Response("NOT_OK");
		}

	};
}


class Person {
	String name;
	int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

class Response{
	String status;

	public Response(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}