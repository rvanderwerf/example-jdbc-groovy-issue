package example.domain

import io.micronaut.core.annotation.Creator

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Question {

    @Id
    @GeneratedValue
    Long id
    int type
    private final String question

    @Creator
    Question(String question) {
        this.question = question
    }

    String getQuestion() {
        return question
    }
}