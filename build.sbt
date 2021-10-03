name := """Warden"""
organization := "be.renaud11232"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.6"

libraryDependencies += guice

libraryDependencies ++= Seq(
  evolutions,
  jdbc,
  javaJdbc,
  "org.xerial" % "sqlite-jdbc" % "3.36.0.3",
  javaJpa,
  "org.hibernate" % "hibernate-core" % "5.5.7.Final",
  "com.github.gwenn" % "sqlite-dialect" % "0.1.2",
  "org.springframework.security" % "spring-security-crypto" % "5.5.2"
)

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"