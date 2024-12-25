package lshh.pollservice.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health-test")
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
