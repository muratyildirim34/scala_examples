package scala_kurs_denemeleri

object ooquiz extends App {
  val writer = new Writer("Charles", "Dickens", 1812)
  val writer2 = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Gods' Vehicles", 1884, writer)

  println(novel.authorAge)
  println(novel.isWrittenBy(writer))
  println(novel.isWrittenBy(writer2))

}

class Writer(firstname: String, surname: String, val year: Int) {
  def fullName = firstname + " " + surname
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge: Int = year - author.year
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

