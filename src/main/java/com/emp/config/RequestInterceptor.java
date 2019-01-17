package com.emp.config;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.emp.rest.exception.customexception.ValidationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
@Component("requestInterceptor")
public class RequestInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

	private Map<String, Object> errorMap;

	private String errorMessage;

	private int errorStatusCode;

	private ObjectMapper objectMapper;

	@Resource
	private Environment environment;

	private RestTemplate restTemplate;

	private static final String PRODUCT_HEADER = "product";

	private static final String VOS_CENTRAL_RESOURCE_KEY = "vos.central.resource.url";
	private static final String PRODUCT_AUTHORIZE_URI = "/api/v1/contractmanagement/authorize";

	public RequestInterceptor() {
		this.objectMapper = new ObjectMapper();
		this.restTemplate = new RestTemplate();
		this.addErrorHandlerToRestTemplate();
	}



	private void constructErrorResponse() throws ValidationException, Exception {
		switch (errorStatusCode) {
		case 400:
			throw new ValidationException(errorMessage);
		case 401:
			throw new SecurityException(errorMessage);
		default:
			throw new Exception();
		}
	}

	private void addErrorHandlerToRestTemplate() {
		final StringWriter writer = new StringWriter();
		this.restTemplate.setErrorHandler(new ResponseErrorHandler() {
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
					errorStatusCode = response.getStatusCode().value();
					errorMessage = response.getStatusText();
					if (response.getBody() != null) {
						try {
							IOUtils.copy(response.getBody(), writer);
							String body = writer.toString();
							LOGGER.debug("Body - {}", body);
							errorMap = objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {
							});
							errorMessage = errorMap.get("msg") == null ? response.getStatusText()
									: errorMap.get("msg").toString();
						} catch (Exception ex) {
							LOGGER.error("", ex);
						}
					}
					return true;
				}
				return false;
			}

			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				return;
			}
		});
	}
}
