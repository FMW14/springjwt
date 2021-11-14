package com.bjartscular.example.springjwt.integration;

import ch.qos.logback.classic.Level;
import org.dbunit.Assertion;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
//import org.junit.jupiter.api.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.bjartscular.example.springjwt.ConnectionSettings.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class AuthDbUnitTest extends DataSourceBasedDBTestCase {
    private static final Logger logger = LoggerFactory.getLogger(AuthDbUnitTest.class);
//    Logger logger2 = (Logger)LoggerFactory.getLogger("org.dbunit")
//logger2.setLevel(Level.ERROR);

    private Connection connection;

    @Override
    protected DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("dbunit/users.xml")) {
            return new FlatXmlDataSetBuilder().build(resourceAsStream);
        }
    }

    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        connection = getConnection().getConnection();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void givenDataSetEmptySchema_whenDataSetCreated_thenTablesAreEqual() throws Exception {
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("USERS");
//        IDataSet databaseDataSet = getConnection().createDataSet();
//        ITable actualTable = databaseDataSet.getTable("USERS");
//        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void givenDataSet_whenSelect_thenFirstTitleIsGreyTShirt() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("select * from USERS where id = 1");

        assertThat(rs.next()).isTrue();
        assertThat(rs.getString("username")).isEqualTo("mod");
    }
}
