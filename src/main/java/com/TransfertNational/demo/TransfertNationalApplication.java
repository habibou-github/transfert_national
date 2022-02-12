package com.TransfertNational.demo;

import com.TransfertNational.demo.Entities.Agence;
import com.TransfertNational.demo.Entities.Client;
import com.TransfertNational.demo.Repositorys.AgenceRepository;
import com.TransfertNational.demo.Repositorys.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication

public class TransfertNationalApplication implements CommandLineRunner {
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	AgenceRepository agenceRepository;

	public static void main(String[] args) {
		SpringApplication.run(TransfertNationalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Client clientEntity = new Client();
		clientEntity.setClientId("345TfzR3regr654Greg");
		clientEntity.setTitre("M");
		clientEntity.setFullName("hamza habibou");
		clientEntity.setGSM("0606060606");

		clientRepository.save(clientEntity);

		Client clientEntity2 = new Client();
		clientEntity2.setClientId("alahoakbar12345");
		clientEntity2.setTitre("Mme");
		clientEntity2.setFullName("souad taliban");
		clientEntity2.setGSM("0610203040");

		clientRepository.save(clientEntity2);

		Agence agenceEntity = new Agence();
		agenceEntity.setAdresseAgence("Kaouki");
		agenceEntity.setActive(true);
		agenceEntity.setVilleAgence("SAFI");
		agenceEntity.setNomAgence("MASSIRA_AGENCE");
		agenceEntity.setTelephoneAgence("0661968774");

		agenceRepository.save(agenceEntity);

		Agence agenceEntity2 = new Agence();
		agenceEntity2.setAdresseAgence("ITALY");
		agenceEntity2.setActive(true);
		agenceEntity2.setVilleAgence("CASA");
		agenceEntity2.setNomAgence("LLLLLLLLLL");
		agenceEntity2.setTelephoneAgence("0000000000");

		agenceRepository.save(agenceEntity2);

	}
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
