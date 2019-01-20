package charles.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
  private static final Logger log = LoggerFactory.getLogger(App.class);
  
  public static void main(String[] args) {
    SpringApplication.run(App.class);
  }
  
  @Bean
  public CommandLineRunner demo(CustomerRepository repository) {
    return(args)->{
      //save customers data
      repository.save(new Customer("jack","bauer"));
      repository.save(new Customer("chloe","brain"));
      repository.save(new Customer("kim","bauer"));
      repository.save(new Customer("david","palmer"));
      repository.save(new Customer("michelle","dessler"));
      
      //fetch all customers
      log.info("customers found with findAll()");
      for(Customer c: repository.findAll()) {
        log.info(c.toString());
      }
      
      //fetch an individual customer by ID
      repository.findById(1L).ifPresent(customer->{
        log.info("find customer with findById(1L)");
        log.info(customer.toString());
      });
      
      //fetch customers by last name
      log.info("customer found with findByLastName('bauer')");
      repository.findByLastName("bauer").forEach(bauer->{
        log.info(bauer.toString());
      });
    };
  }
}
