package net.francesbagual.sandbox.microservice.akka

import akka.actor.Props
import akka.actor.Actor
import akka.routing.RoundRobinPool
import akka.actor.ActorSystem
import akka.contrib.pattern.DistributedPubSubExtension
import akka.contrib.pattern.DistributedPubSubMediator.Put

object HelloActor {
  def props = Props[HelloActor]
}

class HelloActor extends Actor {
  import HelloActor._
  val mediator = DistributedPubSubExtension(context.system).mediator
  mediator ! Put(self)

  def receive = {
    case PersonService.SayHello(name: String) =>
      sender() ! "Hello, " + name
  }
}