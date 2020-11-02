package geekspring.market;

import geekspring.market.utils.RabbitProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableFeignClients
public class MarketApplication implements CommandLineRunner {

	private RabbitProvider rabbitProvider;

	@Autowired
	public void setRabbitProvider(RabbitProvider rabbitProvider) {
		this.rabbitProvider = rabbitProvider;
	}

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		rabbitProvider.openConnect();
		rabbitProvider.receiverMsg();
	}

}
