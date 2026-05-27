package com.shift_generator.shift_generator.bootstrap;

import com.shift_generator.shift_generator.model.*;
import com.shift_generator.shift_generator.repository.*;
import com.shift_generator.shift_generator.repository.user.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
@SuppressWarnings("null")
public class DatabaseSeeder implements CommandLineRunner {
    
    private final UserRepository userRepository;
    @Value("${app.db.seed-on-start:false}")
    private boolean shouldSeed;

    DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (shouldSeed) {
            System.out.println("*************************************************");
            System.out.println("[SHIFT GENERATOR-SEED]: Started seeding database.");
            System.out.println("*************************************************");
            rebuildDatabaseSchema();
        }
        else if (userRepository.count() > 0) {
            System.out.println("************************************************************");
            System.out.println("[SHIFT GENERATOR-BOOTSTRAP]: Found existing data. Skipping seed.");
            System.out.println("************************************************************");
            return;
        }

        executeSeeding();
    }

    private void rebuildDatabaseSchema() {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            
            String dbName = conn.getCatalog();

            stmt.execute("DROP DATABASE IF EXISTS " + dbName);
            stmt.execute("CREATE DATABASE " + dbName);
            stmt.execute("USE " + dbName);

            System.out.println("***************************************************************");
            System.out.println("Creating initial DataBase ");
            System.out.println("***************************************************************");

            // Utenti
            stmt.execute("CREATE TABLE users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(50) NOT NULL UNIQUE, " +
                "email VARCHAR(100) NOT NULLL UNIQUE, " +
                "password VARCHAR(255) NOT NULL, " +
                "role VARCHAR(20) NOT NULL, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP) ENGINE=InnoDB");

            System.out.println("*******************************************************************************");
            System.out.println("[SHIFT GENERATOR-BOOTSTRAP]: Rebuilt schema, seeding completed.");
            System.out.println("*******************************************************************************");
        } catch (Exception e) {
            throw new RuntimeException("Critical DDL error: " + e.getMessage(), e);
        }

        @Transactional
        public void executeSeeding() {
            
        }

    }
}
