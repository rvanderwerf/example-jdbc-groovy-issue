package example

import example.domain.Question
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification
import spock.lang.Stepwise

import javax.inject.Inject

@MicronautTest
class QuestionControllerSpec extends Specification {

    @Inject
    @Client("/question")
    RxHttpClient client


    void "testSaveQuestion"()  {

        given:

        String questionText = "What is your favorite color?"
        Question question = new Question(questionText)

        HttpRequest request = HttpRequest.POST("/save", question)
        HttpResponse<Question> response = client.exchange(request,Question).blockingFirst()

        expect:
        assert response
        assert response.body()
        assert response.body().id
        assert response.body().question == questionText
        
        
    }

    void "testGetQuestion"() {
        given:
        Question question = client.retrieve(HttpRequest.GET("/1"),Question)
                .blockingFirst()

        expect:

        question
        question.id == 1



    }

    void "testCount"() {
       given:

       String count = client.retrieve(HttpRequest.GET("/count"))
               .blockingFirst()

       expect:
       count.toLong() == 1


   }


}