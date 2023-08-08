package com.example.springbootQuiz.controller;


import com.example.springbootQuiz.modal.Question;
import com.example.springbootQuiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/getQuestion")
    public List<Question> getQuestion(){
        return questionService.getAllQuestion();
    }

    @GetMapping("/category/{category}")
    public List<Question> getQuestionByCategory(@PathVariable String category){
       return questionService.getQuestionByCategory(category);
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable("id")int id){
        return questionService.deleteQuestion(id);
    }

    @PutMapping("/update/{id}")
    public Question updateQuestion(@RequestBody Question question,@PathVariable("id") int id){
        questionService.updateQuestion(question,id);
        return question;
    }


}
