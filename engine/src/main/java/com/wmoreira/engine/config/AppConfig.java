package com.wmoreira.engine.config;

import br.com.wmoreira.domains.mapper.OrderMapper;
import br.com.wmoreira.domains.mapper.ProductMapper;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.SimpleDateFormat;

/**
 * @author wellington.362@gmail.com
 */

@Configuration
@ComponentScan(basePackages = "com.wmoreira.engine")
public class AppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ProductMapper productMapper() {
        return new ProductMapper();
    }

    @Bean
    public OrderMapper orderMapper(ProductMapper productMapper, SimpleDateFormat sdf) {
        return new OrderMapper(productMapper, sdf);
    }

    @Bean
    public ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal() {
        return new ThreadLocal<SimpleDateFormat>(){
            @Override
            protected SimpleDateFormat initialValue()
            {
                return new SimpleDateFormat("yyyy-MM-dd");
            }
        };
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SimpleDateFormat sdf(ThreadLocal<SimpleDateFormat> threadLocal) {
        return threadLocal.get();
    }
}
