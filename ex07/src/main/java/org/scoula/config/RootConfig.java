package org.scoula.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 🌱 Root Application Context 설정 클래스
 * - Spring Framework의 최상위(Root) 애플리케이션 컨텍스트를 설정하는 클래스
 * - 웹 계층과 무관한 비즈니스 로직, 서비스, 데이터 액세스 계층의 Bean들을 관리
 */
@Configuration
@PropertySource({"classpath:/application.properties"})
@MapperScan(basePackages = {"org.scoula.mapper"})
public class RootConfig {

    @Value("${jdbc.driver}")
    String driver;
    @Value("${jdbc.url}")
    String url;
    @Value("${jdbc.username}")
    String username;
    @Value("${jdbc.password}")
    String password;
//
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();

        // MyBatis 설정 파일 위치 지정
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));

        // 데이터베이스 연결 설정
        sqlSessionFactory.setDataSource(dataSource);

        return sqlSessionFactory.getObject();
    }

    /**
     * 트랜잭션 매니저 설정
     * - 데이터베이스 트랜잭션을 스프링이 관리하도록 설정
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());
        return manager;
    }
}
