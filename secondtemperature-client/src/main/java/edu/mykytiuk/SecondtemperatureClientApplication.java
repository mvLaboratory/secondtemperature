package edu.mykytiuk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class SecondtemperatureClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondtemperatureClientApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

@RestController
@RequestMapping("/temperature")
class TemperatureGatewayRestController {
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(method = RequestMethod.GET, value = "/values")
	public List<Reading> getReadings() {
//		ParameterizedTypeReference<Resources<Reading>> ptr = new ParameterizedTypeReference<Resources<Reading>>() {
//		};
//		ResponseEntity<Resources<Reading>> entity = this.restTemplate.exchange("http://secondtemperature-server/temperature", HttpMethod.GET, null, ptr);
//
//		return entity.getBody().getContent();
		ParameterizedTypeReference<List<Reading>> ptr = new ParameterizedTypeReference<List<Reading>>() {};
		ResponseEntity<List<Reading>> entity = this.restTemplate.exchange("http://secondtemperature-server/temperature", HttpMethod.GET, null, ptr);
		return entity.getBody();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/string")
	public String getReadingString() {
		ParameterizedTypeReference<List<Reading>> ptr = new ParameterizedTypeReference<List<Reading>>() {};
		ResponseEntity<List<Reading>> entity = this.restTemplate.exchange("http://secondtemperature-server/temperature", HttpMethod.GET, null, ptr);
		return entity.getBody().toString();
	}

	@RequestMapping(value = "/ui", method = RequestMethod.GET)
	public String showReadings() {
		return "index";
	}
}

@Controller
class TemperatureController {
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/ui", method = RequestMethod.GET)
	public String showReadings(Model model) {
		model.addAttribute("readings", getUIReadingsList());
		return "index2";
	}

	@RequestMapping(value = "/jsp", method = RequestMethod.GET)
	public String showReadingsJSP(Model model) {
		model.addAttribute("readings", getUIReadingString());
		return "temperature";
	}

	public String getUIReadingString() {
		ParameterizedTypeReference<List<Reading>> ptr = new ParameterizedTypeReference<List<Reading>>() {};
		ResponseEntity<List<Reading>> entity = this.restTemplate.exchange("http://secondtemperature-server/temperature", HttpMethod.GET, null, ptr);
		List<Reading> readingsList = entity.getBody();
		return readingsList.toString();
	}

	public Reading[] getUIReadings() {
		ParameterizedTypeReference<List<Reading>> ptr = new ParameterizedTypeReference<List<Reading>>() {};
		ResponseEntity<List<Reading>> entity = this.restTemplate.exchange("http://secondtemperature-server/temperature", HttpMethod.GET, null, ptr);
		List<Reading> readingsList = entity.getBody();

		Reading[] readingArray = new Reading[readingsList.size()];
		int i = 0;
		for (Reading reading : readingsList) {
			readingArray[i] = reading;
			i++;
		}

		return readingArray;
	}

	public List<Reading> getUIReadingsList() {
		ParameterizedTypeReference<List<Reading>> ptr = new ParameterizedTypeReference<List<Reading>>() {};
		ResponseEntity<List<Reading>> entity = this.restTemplate.exchange("http://secondtemperature-server/temperature", HttpMethod.GET, null, ptr);
		List<Reading> readingsList = entity.getBody();
		Collections.sort(readingsList);
		return readingsList;
	}
}