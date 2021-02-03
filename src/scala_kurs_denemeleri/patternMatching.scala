package  scala_kurs_denemeleri

object patternMatching extends App {

  case class Person(name: String,age: Int)
  val bob = Person("Bob",20)

  val greatings = bob match {
    case Person(n,a) if a < 21 => s"Hi my name is $n and i am $a years old"
    case _ => "I dont know who i am!!"
  }

  println(greatings)



  trait Expression
  case class Number(n: Int) extends Expression
  case class Sum(e1: Expression,e2: Expression) extends Expression
  case class Prod(e1: Expression,e2: Expression) extends Expression

  def show(e: Expression): String = e match {
    case Number(n) => s"$n"
    case Sum(e1,e2) => show(e1) + " + " + show(e2)
    case Prod(e1,e2) => {
      def maybeParantesis(e: Expression): String = e match {
        case Prod(_,_) => show(e)
        case Number(_) => show(e)
        case Sum(_,_) => "(" + show(e) + ")"
      }
      maybeParantesis(e1) + " * " + maybeParantesis(e2)
    }
  }


  println(show(Prod(Sum(Number(1),Number(2)),Sum(Number(3),Number(4)))))
}
