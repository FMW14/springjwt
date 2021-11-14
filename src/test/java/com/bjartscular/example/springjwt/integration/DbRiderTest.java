package com.bjartscular.example.springjwt.integration;

import com.bjartscular.example.springjwt.model.User;
import com.bjartscular.example.springjwt.repos.RoleRepo;
import com.bjartscular.example.springjwt.repos.UserRepo;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.connection.RiderDataSource;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider()
@SpringBootTest
@ActiveProfiles(profiles = "localtest")
@DBUnit(cacheConnection = false,
        leakHunter = true,
        expectedDbType = RiderDataSource.DBType.H2,
//        caseInsensitiveStrategy = Orthography.LOWERCASE,
//        caseSensitiveTableNames = true,
//        schema = "dbunit/schema1.sql"
        schema = "PUBLIC"
)
public class DbRiderTest {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepo;

    @BeforeEach
    @DataSet("users.yml")
    public void setUpUsers() {
    }

    @Test
    @ExpectedDataSet("expectedUsers.yml")
    public void addUser() {
        User user = new User("mod1", "mod1@example.com", "12345");
        userRepository.save(user);
        assertThat(userRepository.count()).isEqualTo(2);
    }
}
