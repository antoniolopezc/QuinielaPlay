name := "quinielaNueva"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean  withSources(),
  cache,
  "ws.securesocial" %% "securesocial" % "2.1.3" withSources(),
  "mysql" % "mysql-connector-java" % "5.1.21",
  "org.apache.poi" % "poi" % "3.10-FINAL",
  "org.apache.poi" % "poi-ooxml" % "3.10-FINAL"
)     

resolvers += Resolver.url("sbt-plugin-releases", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)

play.Project.playJavaSettings
