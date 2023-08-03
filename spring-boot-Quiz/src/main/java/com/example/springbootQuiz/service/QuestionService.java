package com.example.springbootQuiz.service;

import com.example.springbootQuiz.dao.QuestionDao;
import com.example.springbootQuiz.modal.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestion(){
        return questionDao.findAll();
    }

    public List<Question> getQuestionByCategory(String category){
        return questionDao.findByCategory(category);
    }

    public String addQuestion(@RequestBody Question question){
        questionDao.save(question);
        return "success";
    }

    public String deleteQuestion(int id){
        questionDao.deleteById(id);
        return "deleted";
    }

    public Question updateQuestion(Question question,int id){
        questionDao.save(question);
        return question;
    }
}
