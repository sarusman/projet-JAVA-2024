package com.javaprojet.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe principale de l'application Webapp.
 *
 * Cette classe est utilisée pour démarrer l'application Spring Boot. Elle est annotée avec
 * {@link SpringBootApplication} pour indiquer qu'il s'agit d'une application Spring Boot
 * et {@link EnableTransactionManagement} pour activer la gestion des transactions.
 *
 * La méthode {@link #main(String[])} lance l'application en utilisant SpringApplication.
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class WebappApplication {

	/**
	 * Méthode principale qui lance l'application Spring Boot.
	 *
	 * Cette méthode est le point d'entrée de l'application. Elle utilise {@link SpringApplication#run(Class, String[])}
	 * pour démarrer l'application.
	 *
	 * @param args Les arguments de ligne de commande, s'il y en a.
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}
}
