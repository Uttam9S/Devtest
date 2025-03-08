pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Uttam9S/Devtest.git'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Docker Build & Push') {
            steps {
                sh 'docker build -t docker/youtube .'
            }
        }
    }
}
package com.example.youtubeclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Controller
public class YoutubeCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoutubeCloneApplication.class, args);
    }

    @GetMapping("/")
    public String home(Model model) {
        List<String> videos = Arrays.asList("Video 1", "Video 2", "Video 3");
        model.addAttribute("videos", videos);
        return "index";
    }
}
FROM openjdk:17
WORKDIR /app
COPY /var/lib/jenkins/workspace/git/target/youtubeclone.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

