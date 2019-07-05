package io.chrisdavenport.clientexample

import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.dsl._
import org.http4s._
import org.http4s.implicits._
import cats.effect._
import cats.implicits._
import fs2._
import scala.concurrent.ExecutionContext.global

object ClientExampleServer {
    def serve[F[_]](implicit Effect: ConcurrentEffect[F], T: Timer[F]) : Stream[F, ExitCode] = 
        for {
            _ <- Stream.eval(Sync[F].delay(println("Starting Client")))
            client <- Stream.resource(BlazeClientBuilder[F](global).resource)
            jokes = Jokes.impl(client)
            service = getDadJoke[F](jokes)
            exitCode <- BlazeServerBuilder[F].bindHttp(8080, "0.0.0.0")
                .withHttpApp(service.orNotFound)
                .serve
        } yield exitCode


    def getDadJoke[F[_]: Effect](j: Jokes[F]): HttpRoutes[F] = {
        val dsl = new Http4sDsl[F]{}
        import dsl._

        HttpRoutes.of[F]{
            case GET -> Root / "joke" => 
                j.get.flatMap(Ok(_))
        }

    }

}