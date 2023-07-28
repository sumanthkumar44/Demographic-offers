package Customer.Demographicoffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableMongoRepositories
public class DemographicOffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemographicOffersApplication.class, args);
	}
@Bean
  public RestTemplate restTemplate()
  {
	return new RestTemplate();
  }
}
