package lshh.codedelta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
	"lshh.codedelta",
	"lshh.core.bean",
})
@SpringBootApplication
public class CodeDeltaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeDeltaApplication.class, args);
	}

}
