package br.com.zup.autores

import br.com.zup.NovoAutorRequest
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastroAutorController(
    val autorRepository: AutorRepository,
    val enderecoClient: EnderecoClient
) {

    @Post
    // Anotação @Body apenas como dica visual, Micronaut entende que o parâmetro se trata de um request body
    fun cadastra(@Body @Valid request: NovoAutorRequest): Autor {

        val enderecoResponse = enderecoClient.consulta(request.cep)

        val autor = request.paraAutor(enderecoResponse.body()!!)
        println("Autor: ${autor.nome}")

        autorRepository.save(autor)
        return autor
    }
}