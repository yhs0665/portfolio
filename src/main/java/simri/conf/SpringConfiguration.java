package simri.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class SpringConfiguration {
	@Autowired 
	private ApplicationContext applicationContext; //자동으로 스프링컨테이너의 값을 가지고온다
  
  @Bean
	public BasicDataSource dataSource() {
		BasicDataSource basicdataSource = new BasicDataSource();
		basicdataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicdataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		basicdataSource.setUsername("c##java");
		basicdataSource.setPassword("bit");
		basicdataSource.setMaxTotal(20);
		basicdataSource.setMaxIdle(3);

		return basicdataSource;
	}
  
  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("simri/mybatis-config.xml"));
    sqlSessionFactoryBean.setDataSource(dataSource());
    sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:*/dao/*Mapper.xml"));
    return sqlSessionFactoryBean.getObject(); //SqlSessionFactory 리턴~
  }
  
  @Bean
  public SqlSessionTemplate sqlSession() throws Exception {
	  return new SqlSessionTemplate(sqlSessionFactory());
  }
  
  
  @Bean
  public DataSourceTransactionManager transactionManager() {
	  DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
	  dataSourceTransactionManager.setDataSource(dataSource());
	  return dataSourceTransactionManager;
  }

  
}