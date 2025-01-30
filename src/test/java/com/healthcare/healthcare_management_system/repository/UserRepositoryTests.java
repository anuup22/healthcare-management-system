package com.healthcare.healthcare_management_system.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.healthcare.healthcare_management_system.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
  @Autowired
  private UserRepository userRepository;

  @Test
  public void UserRepository_SaveAll_ReturnSavedUser() {
    // Arrange
    User user = User.builder()
        .firstName("Firstname")
        .lastName("Lastname")
        .email("hello@email.com")
        .password("22114433").build();

    // Act
    User savedUser = userRepository.save(user);

    // Assert
    Assertions.assertThat(savedUser).isNotNull();
    Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
  }
}
