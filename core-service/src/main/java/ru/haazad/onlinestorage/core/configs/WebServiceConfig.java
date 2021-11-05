package ru.haazad.onlinestorage.core.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soap/*");
    }

    @Bean(name="products")
    public DefaultWsdl11Definition productsWsdl11Definition(XsdSchema productsSchema){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("ProductsPort");
        definition.setLocationUri("/soap");
        definition.setTargetNamespace("http://www.haazad.ru/soap/products");
        definition.setSchema(productsSchema);
        return definition;

    }

    @Bean
    public XsdSchema productsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/products.xsd"));
    }
}
