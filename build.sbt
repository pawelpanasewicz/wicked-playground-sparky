
name := """wicked-playground-sparky"""

scalaVersion := "2.11.8"

testOptions in Test += Tests.Argument("-oD", "-F", "10")   //org.scalatest.tools.Runner - description of options

//as advised in https://github.com/holdenk/spark-testing-base#minimum-memory-requirements-and-ooms
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")

parallelExecution in Test := false

import Dependencies._
libraryDependencies ++= Seq(
  //spark is so big in terms of dependency - it's better
  //not to put here anything in order to avoid binary compatibility issues
  //
  spark,
  sparkSql,
  mllib,
  mllibLocal,
  dataBricksCsv,
  scalaTest % Test,
  scalaCheck % Test,
  discipline % Test,
  sparkHive % Test,   //otherwise if not included there are some dependency errors
  sparkTestingBase % Test
)