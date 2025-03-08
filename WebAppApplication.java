package com.example.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
@Controller
public class WebAppApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebAppApplication.class);
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Java WebApp Deployed via Jenkins & Docker!");
        return "index";  // This will be dynamically generated
    }

    @Bean
    public CommandLineRunner createHtmlFile() {
        return args -> {
            File templatesDir = new File("src/main/resources/templates");
            if (!templatesDir.exists()) {
                templatesDir.mkdirs();
            }
            
            File htmlFile = new File(templatesDir, "index.html");
            if (!htmlFile.exists()) {
                try (FileWriter writer = new FileWriter(htmlFile)) {
                    writer.write("""
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Java WebApp</title>
                        </head>
                        <body>
                            <h1>${message}</h1>
                        </body>
                        </html>
                    """);
                    System.out.println("index.html generated successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}

