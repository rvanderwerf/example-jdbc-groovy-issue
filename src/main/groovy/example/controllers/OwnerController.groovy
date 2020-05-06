package example.controllers

import example.domain.Owner
import example.repositories.OwnerRepository
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@Controller("/owners")
class OwnerController {

    private final OwnerRepository ownerRepository

    OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository
    }

    @Get("/")
    List<Owner> all() {
        return ownerRepository.findAll()
    }

    @Get("/{name}")
    Optional<Owner> byName(@NotBlank String name) {
        return ownerRepository.findByName(name)
    }

    @Post("/save")
    Optional<Owner> save(@NotEmpty Owner owner) {
        return Optional.of(ownerRepository.save(owner))
    }
}