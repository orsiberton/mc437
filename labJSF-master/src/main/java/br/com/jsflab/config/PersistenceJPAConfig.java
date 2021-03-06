package br.com.jsflab.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {

    private static final String DATA_SOURCE_PASSWORD = "root";

    private static final String DATA_SOURCE_USERNAME = "postgres";

    private static final String DATA_SOURCE_URL = "jdbc:postgresql://127.0.0.1:5432/jsflab";

    private static final String DATA_SOURCE_DRIVER_CLASS_NAME = "org.postgresql.Driver";

    private static final String[] PACKAGES_TO_SCAN = { "br.com.jsflab.entidades" };

    private static final String[][] PROPERTIES = {
        { "hibernate.hbm2ddl.auto", "update" },
        { "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" } };

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(PACKAGES_TO_SCAN);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DATA_SOURCE_DRIVER_CLASS_NAME);
        dataSource.setUrl(DATA_SOURCE_URL);
        dataSource.setUsername(DATA_SOURCE_USERNAME);
        dataSource.setPassword(DATA_SOURCE_PASSWORD);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties() {

        Properties properties = new Properties();
        for (String[] property : PROPERTIES) {
            properties.setProperty(property[0], property[1]);
        }

        return properties;
    }
}
