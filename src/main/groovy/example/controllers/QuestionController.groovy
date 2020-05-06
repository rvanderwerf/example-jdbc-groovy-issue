package example.controllers

import example.domain.Question
import example.repositories.QuestionRepository

import groovy.transform.CompileStatic


import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.reactivex.Single

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Controller("/question")
class QuestionController {

    final QuestionRepository questionRepository

    QuestionController(QuestionRepository questionRepository) {
       this.questionRepository = questionRepository
    }


    @Post(uri="/save")
    Optional<Question> save(@NotEmpty Question question) {
            return Optional.of(questionRepository.save(question))
    }


    @Get("/{id}")
    HttpResponse<Question> get(Long id){
        Optional<Question> question = questionRepository.findById(id)
        if (question.isPresent()) {
           return HttpResponse.ok((Question)question.get())
        } else {
           return HttpResponse.notFound()
        }

    }

   /* @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Get("/list")
    HttpResponse<Slice<Question>> list(Pageable pageAble) {
        Slice<Question> list = abstractQuestionRepository.list(pageAble)
        if (list) {
            return HttpResponse.ok(list)
        } else {
            return HttpResponse.serverError()
        }
    }*/

/*
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Put
    Single<Question> update(Question question) {
        Single.just(questionRepository.update(question))
    }
*/

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Delete("/")
    HttpResponse delete(Question question){
        questionRepository.deleteById(question.id)
        HttpResponse.ok()

    }


    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Get("/count")
    HttpResponse<Long> count(){
        HttpResponse.ok(questionRepository.count())

    }

    @Get(value = "/test", produces = MediaType.TEXT_PLAIN)
    String getTest() {
        return "some string"
    }


}