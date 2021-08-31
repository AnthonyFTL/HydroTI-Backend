package com.upc.hydroti.cucumber.security.stepdefs;

import com.upc.hydroti.security.infra.entity.UserEntity;
import com.upc.hydroti.security.infra.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManagerSignInSteps {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Given("an user with email {string} and with password {string}")
    public void anUserWithEmailAndWithPassword(String email, String password) {
        String id = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(password);

        when(userRepository.findByEmailOrId(email))
                .thenReturn(Optional.of(new UserEntity(id, email, encodedPassword, "ROLE_IRRIGATION_MANAGER")));
    }

    @When("that the credentials are incorrect")
    public void thatTheCredentialsAreIncorrect() {
    }

    @Then("the user receives an error message.")
    public void theUserReceivesAnErrorMessage() {
    }
}
