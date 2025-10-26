package com.danilo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		Dotenv dotenv = null;
		try {
			Dotenv.configure()
					.systemProperties()
					.load();
		} catch (DotenvException e) {
			// Ignora se não encontrar o arquivo .env
			System.out.println("Arquivo .env nao encontrado, usando variaveis de ambiente padrao.");
		}
		SpringApplication.run(AuthApplication.class, args);
	}
}
