lazy val `http4s-client-example` = project.in(file("."))
    .settings(commonSettings)
    .settings(
      name := "http4s-client-example"
    )

/***********************************************************************\
                      Boilerplate below these lines
\***********************************************************************/




lazy val commonSettings = Seq(
  organization := "io.chrisdavenport",
  licenses += ("MIT", url("https://opensource.org/licenses/MIT")),

  scalaVersion := "2.12.4",

  addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.9" cross CrossVersion.binary),

  libraryDependencies ++= Seq(
   "org.http4s"                  %% "http4s-dsl"                 % "0.18.21",
   "org.http4s"                  %% "http4s-blaze-server"        % "0.18.21",
   "org.http4s"                  %% "http4s-blaze-client"        % "0.18.21",

    "org.specs2"                  %% "specs2-scalacheck"          % "4.4.0"       % Test,
    "org.typelevel"               %% "discipline"                 % "0.10.0"         % Test,
    "com.github.alexarchambault"  %% "scalacheck-shapeless_1.13"  % "1.1.8"       % Test
  )
)
