package com.quiz.Quizoria.Services;

import com.quiz.Quizoria.DAO.QuestionDAO;
import com.quiz.Quizoria.Entities.Question;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category)
    {
        return new ResponseEntity<>(questionDAO.findQuestionByCategory(category),HttpStatus.OK);
    }

    public Optional<Question> getQuestionById(Integer id)
    {
        return questionDAO.findById(id);
    }

    public ResponseEntity<String> addQuestion(Question question)
    {
        try {
            questionDAO.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>("Couldn't add the question",HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<String> deleteQuestion(Integer id)
    {
        Optional<Question> q = questionDAO.findById(id);
        if(!q.equals(Optional.empty())) {
            try {
                questionDAO.deleteById(id);
                return new ResponseEntity<>("Successfully Deleted the question!", HttpStatus.ACCEPTED);
            }catch(Exception e)
            {
                return new ResponseEntity<>("Couldn't delete the question",HttpStatus.EXPECTATION_FAILED);
            }
        }
        else
        {
            return new ResponseEntity<>("Sorry!\nWe couldn't find the intended question",HttpStatus.NOT_FOUND);
        }
    }
}
