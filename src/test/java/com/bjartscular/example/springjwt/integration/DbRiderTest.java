package com.bjartscular.example.springjwt.integration;

//import com.github.database.rider.spring.api.DBRider;
import com.bjartscular.example.springjwt.model.User;
import com.bjartscular.example.springjwt.repos.RoleRepo;
import com.bjartscular.example.springjwt.repos.UserRepo;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.connection.RiderDataSource;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DBRider
@DBRider()
@SpringBootTest
@ActiveProfiles(profiles = "localtest")
//@DBUnit(cacheConnection = false, leakHunter = true, driver = "org.h2.Driver.class.getName()", url = "jdbc:h2:mem:sample;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:dbunit/schema.sql'",user = "sa", password = "sa")
//@DBUnit(cacheConnection = false, leakHunter = true, expectedDbType = RiderDataSource.DBType.H2, driver = "org.h2.Driver.class", url = "jdbc:h2:mem:users",user = "sa", password = "sa", schema = "dbunit/schema1.sql")
@DBUnit(cacheConnection = false, leakHunter = true, expectedDbType = RiderDataSource.DBType.H2)
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
    public void shouldListUsers() {
        assertThat(userRepository).isNotNull();
        assertThat(userRepository.count()).isEqualTo(1);
//        assertThat(userRepository.findByEmail("springboot@gmail.com")).isEqualTo(new User(3));
//        assertThat(userRepository.findByUsername("mod")).isEqualTo(new User(3));
    }
}
