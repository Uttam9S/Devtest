import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to My Simple Java Web App!";
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }
}

