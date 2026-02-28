package com.gerenciadorexames;

import com.gerenciadorexames.infra.entities.*;
import com.gerenciadorexames.infra.entities.enums.ExamStatus;
import com.gerenciadorexames.infra.entities.enums.Role;
import com.gerenciadorexames.infra.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class GerenciadorexamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorexamesApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(
    UserRepository userRepository,
    PatientRepository patientRepository,
    ExamRepository examRepository,
    ReceptionRepository receptionRepository,
    ResultRepository resultRepository,
    PasswordEncoder passwordEncoder
  ){
		return args -> {
      userRepository.deleteAll();
      patientRepository.deleteAll();
			examRepository.deleteAll();
      receptionRepository.deleteAll();
			userRepository.deleteAll();

      System.out.println("Populando o banco de dados");

      User admin = User.builder()
        .name("Leandro Vilela")
        .email("leandro@gmail.com")
        .password(passwordEncoder.encode("123456"))
        .role(Role.ADMIN)
        .build();
      userRepository.save(admin);

      User nurse = User.builder()
        .name("Sarah Almeida")
        .email("sarah@clinica.com")
        .password(passwordEncoder.encode("123456789"))
        .role(Role.ENFERMEIRO)
        .build();
      userRepository.save(nurse);

      Exam glicemia = Exam.builder()
        .name("Glicemia de Jejum")
        .measureUnity("mg/dL")
        .minReference(70.0)
        .maxReference(99.0)
        .build();
      examRepository.save(glicemia);

      Exam colesterol = Exam.builder()
        .name("Colesterol Total")
        .measureUnity("mg/dL")
        .minReference(0.0)
        .maxReference(190.0)
        .build();
      examRepository.save(colesterol);

      Patient patient = Patient.builder()
        .name("Carlos Eduardo Alves")
        .cpf("123.456.789-01")
        .birthDate(LocalDate.of(1995, 5, 20))
        .sex("Masculino")
        .phone("(35) 91234-4567")
        .email("carlos@gmail.com")
        .build();
      patientRepository.save(patient);

      Reception reception = Reception.builder()
        .patient(patient)
        .collectionDate(LocalDate.now())
        .nameRequestDoctor("Dr. Raimundo Silva")
        .status(ExamStatus.CONCLUIDO)
        .build();
      receptionRepository.save(reception);

      Double glicemiaObtida = 115.0;
      ExamResult resultGlicemia = ExamResult.builder()
        .reception(reception)
        .exam(glicemia)
        .obtainedValue(glicemiaObtida)
        .flagChanged(glicemiaObtida > glicemia.getMaxReference() || glicemiaObtida < glicemia.getMinReference())
        .build();
      resultRepository.save(resultGlicemia);

      Double colesterolObtido = 180.0;
      ExamResult resultColesterol = ExamResult.builder()
        .reception(reception)
        .exam(colesterol)
        .obtainedValue(colesterolObtido)
        .flagChanged(colesterolObtido > colesterol.getMaxReference() || colesterolObtido < colesterol.getMinReference())
        .build();
      resultRepository.save(resultColesterol);

      System.out.println("Dados iniciais carregados com sucesso!");
    };
	}
}
