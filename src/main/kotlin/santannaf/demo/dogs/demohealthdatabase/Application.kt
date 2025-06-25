package santannaf.demo.dogs.demohealthdatabase

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.ListCrudRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@RestController
@ResponseBody
@RequestMapping(path = ["/texts"])
class TextController(
    private val repository: TestRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun saveText(@RequestBody request: TextRequest): ResponseEntity<*> {
        log.info("Receive request to save Text: $request")
        return ResponseEntity.ok(repository.save<Text>(Text(text = request.text)))
    }

    @GetMapping
    fun getTexts(): ResponseEntity<*> = ResponseEntity.ok(repository.findAll())
}

@Entity
data class Text(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int? = null,
    val text: String
)

interface TestRepository : ListCrudRepository<Text, Long>

data class TextRequest(val text: String)
