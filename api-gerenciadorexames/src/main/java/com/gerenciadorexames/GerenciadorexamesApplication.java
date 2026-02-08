package com.gerenciadorexames;

import com.gerenciadorexames.infra.entitys.Exam;
import com.gerenciadorexames.infra.entitys.User;
import com.gerenciadorexames.infra.entitys.enums.ExamStatus;
import com.gerenciadorexames.infra.entitys.enums.ExamType;
import com.gerenciadorexames.infra.repository.ExamRepository;
import com.gerenciadorexames.infra.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class GerenciadorexamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorexamesApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ExamRepository examRepository, UserRepository userRepository){
		return args -> {
			examRepository.deleteAll();
			userRepository.deleteAll();

			Exam e = new Exam();
			e.setName("Carlos Eduardo Silva");
			e.setCpf("123.456.789-00");
			e.setData(LocalDate.parse("2026-02-08"));
			e.setType(ExamType.SANGUE.getDescricao());
			e.setStatus(ExamStatus.EM_ANDAMENTO.getDescricao());
			examRepository.save(e);

			User u = new User();
			u.setName("Leandro Vilela");
			u.setEmail("leandro@gmail.com");
			u.setPassword("123456");
			userRepository.save(u);
		};
	}
}
