package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaSqlServerApplication implements CommandLineRunner {
//	private IClientService clientService;

	public static void main(String[] args) {

		SpringApplication.run(SpringDataJpaSqlServerApplication.class, args);
	}

//	@GetMapping
//	public ResponseEntity<List<Client>> getClient(){
//		return new ResponseEntity<>(clientService.getClient(), HttpStatus.OK);
//	}

	@Override
	public void run(String... args) throws Exception {
//		String sql = "SELECT * FROM CLIENT";
//		List<Client> clientList	= jdbcTemplate.query(sql,
//				BeanPropertyRowMapper.newInstance(Client.class));

//		clientList.forEach(System.out::println);


//		List<Client> clientList = clientService.getClient();
//		clientList.forEach(System.out::println);
	}
}
