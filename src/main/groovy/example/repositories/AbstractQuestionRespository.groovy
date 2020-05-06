package example.repositories

import example.domain.Question
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.H2)
interface AbstractQuestionRespository extends CrudRepository<Question, Long> {

    @Override
    List<Question> findAll()


}
