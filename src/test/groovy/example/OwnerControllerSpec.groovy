package example

import example.domain.Owner
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class OwnerControllerSpec extends Specification {
    @Inject
    @Client("/owners")
    RxHttpClient client

    void "test initial owners"() {

        when:"initial owners are retrieved"
        List<Owner> results = client.retrieve(HttpRequest.GET("/"), Argument.listOf(Owner.class))
                                    .blockingFirst()

        then:"there are 2"
        results.size() == 2
    }

    void "test create owner"() {
        when:
        Owner requestedOwner = new Owner("Birdman")
        requestedOwner.age = 10
        //requestedOwner.name = "Birdman"
        HttpRequest request = HttpRequest.POST("/save",requestedOwner)
        HttpResponse<Owner> owner = client.exchange(request,Owner).blockingFirst()

        then:
        owner.body().name == "Birdman"
    }
}
