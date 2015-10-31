name := "finch-101"
version := "0.0.0"
scalaVersion := "2.11.7"

resolvers += Resolver.sonatypeRepo("snapshots")

resolvers += "TM" at "http://maven.twttr.com"

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % "0.9.0-SNAPSHOT" changing(),
  "com.github.finagle" %% "finch-circe" % "0.9.0-SNAPSHOT" changing(),
  "io.circe" %% "circe-generic" % "0.2.0-SNAPSHOT" changing(),
  "com.twitter" %% "twitter-server" % "1.12.0",
  "com.twitter" %% "finagle-stats" % "6.27.0"
)

initialCommands in console :=
  """
    |import io.finch._
    |import io.finch.request._
    |import io.finch.circe._
    |import io.circe.generic.auto._
    |import com.twitter.finagle.Httpx
    |import com.twitter.finagle.Service
    |import com.twitter.finagle.httpx.{Request, Response}
    |import com.twitter.util.{Future, Await}
  """.stripMargin
