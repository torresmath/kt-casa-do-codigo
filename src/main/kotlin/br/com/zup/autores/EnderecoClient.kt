package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("http://viacep.com.br/ws")
interface EnderecoClient {

    @Get(
        value = "/{cep}/json",
        consumes = [MediaType.APPLICATION_XML],
        produces = [MediaType.APPLICATION_XML]
    )
    fun consulta(@PathVariable cep: String): HttpResponse<EnderecoResponse>
}
