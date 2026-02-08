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

      Exam e = Exam.builder()
        .name("Carlos Eduardo Silva")
        .cpf("123.456.789-00")
        .data(LocalDate.parse("2026-02-08"))
        .status(ExamStatus.EM_ANDAMENTO.getDescricao())
        .type(ExamType.SANGUE.getDescricao())
        .build();
      examRepository.save(e);

      User u = User.builder()
        .name("Leandro Vilela")
        .email("leandro@gmail.com")
        .password("123456")
        .build();
      userRepository.save(u);

		};
	}
}
