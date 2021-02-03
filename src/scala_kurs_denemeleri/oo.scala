package scala_kurs_denemeleri

object oo extends App {

  println("deneme")

  val author = new Writer("Charles","Dickens",1823)
  val author2 = new Writer("Charles","Dickens",1823)
  val novel = new Novel("Tanrıların Arabaları",1865,author)

  val newNoval = novel.copy(1867)
  println(novel.authorAge)
  println(newNoval.authorAge)
  println(novel.isWritenBy(author))
  println(novel.isWritenBy(author2))

  class Writer(firstName: String,lastName: String,val year: Int) {
    def fullname(): String = s"$firstName $lastName"
  }

  class Novel(name: String,releaseYear: Int,author: Writer) {
    def authorAge: Int = releaseYear - author.year
    def isWritenBy(author: Writer): Boolean = author == this.author
    def copy(newReleaseYear: Int): Novel = new Novel(name,newReleaseYear,author)
  }




}

