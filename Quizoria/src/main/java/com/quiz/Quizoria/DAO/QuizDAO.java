package com.quiz.Quizoria.DAO;


import com.quiz.Quizoria.Entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizDAO extends JpaRepository<Quiz,Integer> {

    }
