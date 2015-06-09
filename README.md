# typesafe-mircroservices

Sandbox to play with a frontend that is using Play Framework and a backend that uses Akka

In a terminal run 

	$ cd web && sbt run

In another terminal run

	$ cd person-srv && sbt run 4444

Then, you can go first to [http://localhost:9000](http://localhost:9000) so Play application compiles and start and then [http://localhost:9000/hello](http://localhost:9000/hello). The second request will go from Play to Person-SRV. It is using the DistributedPubSubExtension akka extension to discover where the hello actor is running.


| Characteristic | Technology |
|----------------|:-----------:|
| Tooling | To be added |
| Configuration | To be added |
| Discovery | Akka cluster and DistributedPubSubExtension akka extension |
| Routing | To be added |
| Observability | Work In Progress (Kamon) |
| Datastore | None so far |
| Deploy automation | None so far |
| Container | Scala with JVM container |
