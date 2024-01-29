package com.project.magic8ball.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Magic8BallController {

    private final List<String> responses = new ArrayList<>();

    @GetMapping("/magic8ball")
    public String getMagic8BallPage(Model model) {
        return "magic8ball";
    }

   /* @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestParam String question) {
        String response = generateResponse();
        responses.add(response);
        return ResponseEntity.ok(response);
    }*/
    
    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestParam String question) {
        if (isValidQuestion(question)) {
            String response = generateResponse();
            responses.add(response);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body("Invalid question");
        }
    }

    private boolean isValidQuestion(String question) {
        // Vérifier si la question n'est pas vide et comporte au moins un mot
        if (question != null && !question.trim().isEmpty()) {
            // Vérifier si la question se termine par un point d'interrogation
            return question.trim().endsWith("?");
        }
        return false;
    }


    private String generateResponse() {
        // Liste des réponses possibles
        List<String> possibleResponses = Arrays.asList(
            "Yes",
            "No",
            "Maybe",
            "Ask again later",
            "Outlook not so good",
            "It is certain",
            "Without a doubt",
            "Most likely",
            "Cannot predict now",
            "Concentrate and ask again",
            "My reply is no",
            "Don't count on it",
            "Very doubtful",
            "Signs point to yes",
            "Better not tell you now",
            "Reply hazy, try again",
            "Ask again tomorrow",
            "Outlook is good",
            "It is decidedly so",
            "Yes definitely"
        );

        // Créer un générateur aléatoire
        Random random = new Random();

        // Sélectionner une réponse aléatoire de la liste
        int randomIndex = random.nextInt(possibleResponses.size());
        String response = possibleResponses.get(randomIndex);

        return response;
    }

}
