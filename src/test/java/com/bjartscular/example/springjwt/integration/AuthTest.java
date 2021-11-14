package com.bjartscular.example.springjwt.integration;

import com.bjartscular.example.springjwt.payload.request.LoginRequest;
import com.bjartscular.example.springjwt.payload.response.JwtResponse;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.orm.hibernate5.SessionFactoryUtils.getDataSource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

//    TestEntityManager
//


    @Test
    public void login1() throws Exception {
//        var response = this.restTemplate.postForEntity("http://localhost:" + port + "/api/auth/signin", new LoginRequest(), JwtResponse.class);
//        System.out.println(response.);
//        assertThat(response.getStatusCode()).is(HttpStatus.BAD_REQUEST);
//        LoginRequest request = new LoginRequest("user1", "password");
        assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "/api/auth/signin", new LoginRequest(), JwtResponse.class).getStatusCode().value()).isEqualTo(400);

//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
//                String.class)).contains("Hello, World");
    }

    @Test
    public void loginSuccess() {
        LoginRequest request = new LoginRequest("user1", "password");
        assertThat(this.restTemplate.postForEntity("http://localhost:" + port + "/api/auth/signin", request, JwtResponse.class).getStatusCode().value()).isEqualTo(200);

    }
    @Test
    public void login2(){
//        try (InputStream is = getClass().getClassLoader().getResourceAsStream("dbunit/expected-user.xml")) {
//            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
//            ITable expectedTable = expectedDataSet.getTable("CLIENTS");
//            Connection conn = getDataSource().getConnection();
//
//        }

//        IDataSet expectedDataSet = getDataSet();
//        ITable expectedTable = expectedDataSet.getTable("CLIENTS");
//        IDataSet databaseDataSet = getConnection().createDataSet();
//        ITable actualTable = databaseDataSet.getTable("CLIENTS");
//        assertEquals(expectedTable, actualTable);
    }

//    @Test
//    public void givenDataSetEmptySchema_whenDataSetCreated_thenTablesAreEqual() throws Exception {
//        IDataSet expectedDataSet = getDataSet();
//        ITable expectedTable = expectedDataSet.getTable("CLIENTS");
//        IDataSet databaseDataSet = getConnection().createDataSet();
//        ITable actualTable = databaseDataSet.getTable("CLIENTS");
//        assertEquals(expectedTable, actualTable);
//    }
}
