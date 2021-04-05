package br.com.zup.autores

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

// Classes entity por padrão já são Introspected
@Entity
class Autor(
    val nome: String,
    val email: String,
    val descricao: String
) {
    @Id
    @GeneratedValue
    var id : Long? = null

    val criadoEm: LocalDateTime = LocalDateTime.now()
}
