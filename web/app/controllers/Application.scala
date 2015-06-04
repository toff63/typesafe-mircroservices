package controllers

import akka.actor._
import play.api._
import play.api.mvc._
import javax.inject._
import akka.pattern.ask
import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import akka.util.Timeout

trait AsyncConfiguration {
  val timeout:Timeout = 5 seconds
}

@Singleton
class Application @Inject() (system: ActorSystem) extends Controller with AsyncConfiguration {
  val helloActor = system.actorOf(HelloActor.props, "hello-actor")
  val threadPool = play.api.libs.concurrent.Execution.Implicits.defaultContext

  def index = Action {
    Ok("Helllo from Play 4.1")
  }
  
  def hello = Action.async {
    helloActor.ask(HelloActor.SayHello("World"))(timeout).mapTo[String].map {message => Ok(message)}
  }

}

object HelloActor {
  def props = Props[HelloActor]

  case class SayHello(name: String)
}

class HelloActor extends Actor {
  import HelloActor._

  def receive = {
    case SayHello(name: String) =>
      sender() ! "Hello, " + name
  }
}