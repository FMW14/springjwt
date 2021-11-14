package com.bjartscular.example.springjwt.integration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.sql.DataSource;
import java.io.InputStream;

@DatabaseSetup("/users.xml")
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class DbUnitTest {
    protected DataSourceDatabaseTester dataSourceDatabaseTester;

    @Autowired
    protected DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSourceDatabaseTester = new DataSourceDatabaseTester(dataSource);
        IDataSet dataSet = new FlatXmlDataSet(getClass().getResource("/global-data.xml"));
        dataSourceDatabaseTester.setDataSet(dataSet);
        dataSourceDatabaseTester.onSetup();

        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("dbunit/users.xml")) {
//            return new FlatXmlDataSetBuilder().build(resourceAsStream);
            dataSet= new FlatXmlDataSetBuilder().build(resourceAsStream);
        }
    }

}
