lazy val `http4s-client-example` = project.in(file("."))
    .settings(commonSettings)
    .settings(
      name := "http4s-client-example"
    )

/***********************************************************************\
                      Boilerplate below these lines
\***********************************************************************/

val http4sV = "0.21.0"
val circeV = "0.11.2"


lazy val commonSettings = Seq(
  organization := "io.chrisdavenport",
  licenses += ("MIT", url("https://opensource.org/licenses/MIT")),

  scalaVersion := "2.12.8",

  addCompilerPlugin("org.typelevel" % "kind-projector" % "0.10.3" cross CrossVersion.binary),

  libraryDependencies ++= Seq(
    "org.http4s"                  %% "http4s-dsl"                 % http4sV,
    "org.http4s"                  %% "http4s-blaze-server"        % http4sV,
    "org.http4s"                  %% "http4s-blaze-client"        % http4sV,
    "org.http4s"                  %% "http4s-circe"               % http4sV,
    "io.circe"                    %% "circe-generic"              % circeV,

    "org.specs2"                  %% "specs2-scalacheck"          % "4.7.1"       % Test,
    "org.typelevel"               %% "discipline"                 % "0.11.1"         % Test,
    "com.github.alexarchambault"  %% "scalacheck-shapeless_1.13"  % "1.1.8"       % Test
  )
)
