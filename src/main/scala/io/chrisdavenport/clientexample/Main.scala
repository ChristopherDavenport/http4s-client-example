package io.chrisdavenport.clientexample
import cats.effect._
import cats.implicits._

object Main extends IOApp {

  override def run(args: List[String]) =
    ClientExampleServer.serve[IO].compile.drain.as(ExitCode.Success)

}