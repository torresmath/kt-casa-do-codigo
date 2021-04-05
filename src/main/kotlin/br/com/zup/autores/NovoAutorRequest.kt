package br.com.zup

import br.com.zup.autores.Autor
import br.com.zup.autores.Endereco
import br.com.zup.autores.EnderecoResponse
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

// Durante a compilação o Micronaut lê essa anotação e cria um Bean de Introspecção,
// tornando os atributos da classe acessíveis para o Validation.
// Para surtir efeito, é necessário especificar o target das anotações de validation (no caso field)
@Introspected
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String
) {
    fun paraAutor(enderecoResponse: EnderecoResponse) : Autor {

        val endereco = Endereco(enderecoResponse, numero, cep)
        return Autor(nome, email, descricao, endereco)
    }
}