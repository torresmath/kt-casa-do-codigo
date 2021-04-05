package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import java.util.*
import javax.transaction.Transactional

@Controller("/autores")
class BuscaAutorController(val autorRepository: AutorRepository) {

    @Get
    @Transactional
    fun lista(@QueryValue(defaultValue = "") email: String) : HttpResponse<Any> {
        if (email.isBlank()) {
            val autores = autorRepository.findAll()

            val response = autores.map { autor -> DetalhesDoAutorResponse(autor) }

            return HttpResponse.ok(response)
        }

        val possivelAutor: Optional<Autor> = autorRepository.findByEmail(email)

        if (!possivelAutor.isPresent) {
            return HttpResponse.notFound()
        }

        return HttpResponse.ok(DetalhesDoAutorResponse(possivelAutor.get()))
    }
}