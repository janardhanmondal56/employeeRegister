package com.emp.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({ "com.emp" })
@EnableSwagger2
@Profile({ "development", "deployed" })
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private RequestInterceptor requestInterceptor;
	
	@Bean
	public Docket api() {
		List<Parameter> aParameters = new ArrayList<Parameter>();
		aParameters.add(new ParameterBuilder().name("product").modelRef(new ModelRef("string")).parameterType("header")
				.required(true).build());
		aParameters.add(
				new ParameterBuilder().name("uid").modelRef(new ModelRef("string")).parameterType("header").required(true).build());
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
				.build().globalOperationParameters(aParameters);
	}

	@Bean(name = "org.dozer.Mapper")
	public Mapper mapper() {
		List<String> mappingFiles = new ArrayList<>();
		mappingFiles.add("META-INF/dozerMappingFiles/contract_DozerMapping.xml");
		mappingFiles.add("META-INF/dozerMappingFiles/contract_document_DozerMapping.xml");
		mappingFiles.add("META-INF/dozerMappingFiles/contract_staging_DozerMapping.xml");
		return new DozerBeanMapper(mappingFiles);
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				if (container.getClass().isAssignableFrom(TomcatEmbeddedServletContainerFactory.class)) {
					TomcatEmbeddedServletContainerFactory tomcatContainer = (TomcatEmbeddedServletContainerFactory) container;
					tomcatContainer.addContextCustomizers(new ContextSecurityCustomizer());
				}
			}
		};
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor).addPathPatterns("/**");
	}
	
	private class ContextSecurityCustomizer implements TomcatContextCustomizer {
		@Override
		public void customize(Context context) {
			SecurityConstraint constraint = new SecurityConstraint();
			SecurityCollection securityCollection = new SecurityCollection();
			securityCollection.setName("restricted_methods");
			securityCollection.addPattern("/*");
			securityCollection.addMethod(HttpMethod.OPTIONS.toString());
			constraint.addCollection(securityCollection);
			constraint.setAuthConstraint(true);
			context.addConstraint(constraint);
		}
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}