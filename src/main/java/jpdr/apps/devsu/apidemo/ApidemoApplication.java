package jpdr.apps.devsu.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("jpdr.apps.devsu.apidemo.repositories")
public class ApidemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApidemoApplication.class, args);
	}

}
