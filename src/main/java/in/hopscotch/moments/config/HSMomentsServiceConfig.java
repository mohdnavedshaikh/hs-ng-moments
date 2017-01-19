package in.hopscotch.moments.config;

import java.beans.PropertyVetoException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import in.hopscotch.moments.api.cookie.CookieContext;
import in.hopscotch.moments.api.interceptor.CookieInterceptor;
import in.hopscotch.moments.config.helper.RESTErrorControllerAdvice;
import in.hopscotch.moments.db.util.JDBCAccess;
import in.hopscotch.moments.db.util.JPAAccess;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySources({ @PropertySource("classpath:application.properties"), @PropertySource("classpath:site-jdbc.properties") })
@EnableWebMvc
public class HSMomentsServiceConfig extends WebMvcConfigurerAdapter {

    private static final String PACKAGE_IN_HOPSCOTCH_MOMENTS = "in.hopscotch.moments";

    @Inject
    Environment env;

    @Bean(destroyMethod = "close")
    public DataSource writeDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl(env.getRequiredProperty("jdbc.writedb.proxy.url"));
        dataSource.setUser(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        dataSource.setMaxIdleTime(120);
        dataSource.setMaxPoolSize(10);
        // dataSource.setUnreturnedConnectionTimeout(10);
        dataSource.setDebugUnreturnedConnectionStackTraces(true);
        dataSource.setConnectionCustomizerClassName("in.hopscotch.moments.config.helper.IsolationLevelReadCommittedConnectionCustomizer");
        return dataSource;
    }

    @Bean
    public JDBCAccess jdbcAccess() throws PropertyVetoException {
        JDBCAccess jdbcAccess = new JDBCAccess();
        jdbcAccess.setDataSource(writeDataSource());
        return jdbcAccess;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(writeDataSource());
        factoryBean.setPackagesToScan(PACKAGE_IN_HOPSCOTCH_MOMENTS);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(MySQL5Dialect.class.getName());
        vendorAdapter.setShowSql(false);
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setDatabase(Database.MYSQL);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        return factoryBean;
    }

    @Bean
    @Qualifier("jpaTransaction")
    public PlatformTransactionManager jpaTransactionManager() throws PropertyVetoException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(writeDataSource());
        return transactionManager;
    }

    @Bean
    RESTErrorControllerAdvice restErrorControllerAdvice() {
        return new RESTErrorControllerAdvice();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(new ISO8601DateFormat());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonConverter.setObjectMapper(mapper);
        return jsonConverter;
    }

    @Bean
    public JPAAccess jpaAccess() {
        return new JPAAccess();
    }

    @Bean
    public CookieInterceptor cookieInterceptor() {
        return new CookieInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cookieInterceptor());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    CookieContext cookieContext() {
        return new CookieContext();
    }

}
