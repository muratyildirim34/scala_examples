package scala_kurs_denemeleri

import scala.util.{Random, Try}

object  Connections extends App {

  val hostname = "localhost"
  val port = "8090"
  def randomHtml(s: String) = println(s)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection Interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object httpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String,port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConn(host: String,port: String): Try[Connection] = Try(getConnection(host,port))
  }


  val possibleConn = httpService.getSafeConn(hostname,port)
  val possibleHtml = possibleConn.flatMap(connection => connection.getSafe("/home"))
  possibleHtml.foreach(randomHtml)

  //short hand
  httpService.getSafeConn(hostname,port)
    .flatMap(conn => conn.getSafe("/home"))
    .foreach(randomHtml)

  // for statement
  for {
    connection <- httpService.getSafeConn(hostname,port)
    html       <- connection.getSafe("/home")
  }
    randomHtml(html)

}


