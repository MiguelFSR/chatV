data class Message(var user:String,
                   var message:String,
                   var time:Long)

`MessageController.kt` is a REST controller that defines a POST endpoint to publish the received message object to a Pusher channel (`chat`):

import com.pusher.rest.Pusher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/message")
class MessageController {
    private val pusher = Pusher("1063949", "f195b91b36920b26a4f4", "1ab95c597c2a955f432f")

    init {
        pusher.setCluster("us2")
    }

    @PostMapping
    fun postMessage(@RequestBody message: Message) : ResponseEntity<Unit> {
        pusher.trigger("chat", "new_message", message)
        return ResponseEntity.ok().build()
    }
}