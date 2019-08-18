/**
 * 
 */
package com.tbone.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Gaurav Kumar
 *
 */

@RestController
public class CurrencyConversionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {

		// Feign Problem (Rest API call too complex)
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);
		CurrencyConversionBean bean = responseEntity.getBody();
		bean.setQuantity(quantity);
		bean.setTotalCalculatedAmount(quantity.multiply(bean.getConversionMultiple()));
		logger.info("{}", bean);
		return bean;

	}

	@GetMapping("currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {
		// Map<String, String> uriVariables = new HashMap<String, String>();
		// uriVariables.put("from", from);
		// uriVariables.put("to", to);
		// ResponseEntity<CurrencyConversionBean> responseEntity = new
		// RestTemplate().getForEntity(
		// "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
		// CurrencyConversionBean.class,
		// uriVariables);
		CurrencyConversionBean bean = proxy.retrieveExchangeValue(from, to);
		bean.setQuantity(quantity);
		bean.setTotalCalculatedAmount(quantity.multiply(bean.getConversionMultiple()));
		logger.info("{}", bean);
		return bean;

	}
}
