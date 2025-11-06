package com.campos.testcontainer.config;

import com.campos.testcontainer.entities.User;
import com.campos.testcontainer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
@Profile({"test"})  // Removido "mysql" - agora só roda em testes
public class DbSeedConfig implements CommandLineRunner {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() > 0) {
            System.out.println("Database already seeded. Skipping seed...");
            return;
        }

        System.out.println("Seeding database...");

        User user1 = new User(null, "Nikola", "Tesla", "Male", "nikola.tesla@gmail.com", LocalDate.parse("09/09/1983", dtf), "31998636521", "123456", "Street 1046 view", "NY");
        User user2 = new User(null, "Elon", "Musk", "Male", "elon.musk@gmail.com", LocalDate.parse("25/05/1981", dtf), "31996325874", "123456", "Street 45 Bart", "CA");

        userRepository.saveAll(Arrays.asList(user1, user2));

        System.out.println("Database seeded successfully!");
    }
}
