package example.repositories

import example.domain.Owner
import example.domain.Question
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.H2)
interface QuestionRepository extends CrudRepository<Question, Long> {

    @Override
    List<Question> findAll()

    Optional<Question> findByQuestion(String question)
}