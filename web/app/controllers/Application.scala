package controllers

import akka.actor._
import play.api._
import play.api.mvc._
import javax.inject._
import akka.pattern.ask
import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import akka.util.Timeout
import akka.contrib.pattern.DistributedPubSubExtension
import akka.contrib.pattern.DistributedPubSubMediator
import com.typesafe.config.ConfigFactory
import net.francesbagual.sandbox.microservice.akka.PersonService

trait AsyncConfiguration {
  val timeout: Timeout = 10 seconds
}

@Singleton
class Application @Inject()(actorSystem:ActorSystem) extends Controller with AsyncConfiguration {

  val threadPool = play.api.libs.concurrent.Execution.Implicits.defaultContext
  val mediator = DistributedPubSubExtension(actorSystem).mediator

  def index = Action {
    Ok("Helllo from Play 4.1")
  }

  def hello = Action.async {
    (mediator ? DistributedPubSubMediator.Send("/user/hello", PersonService.SayHello("World"), localAffinity = false))(timeout).mapTo[String].map { message => Ok(message) }
  }
}