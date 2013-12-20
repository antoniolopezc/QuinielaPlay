// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "securesocial" at "http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases"

            
// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % System.getProperty("play.version"))

libraryDependencies += "com.google.code.gson" % "gson" % "2.2"

libraryDependencies += "com.micronautics" % "securesocial" % "2.1.0"                 