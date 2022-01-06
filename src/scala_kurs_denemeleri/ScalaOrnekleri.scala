package scala_kurs_denemeleri

import java.util.concurrent.atomic.DoubleAccumulator

import scala.annotation.tailrec
import scala.collection.immutable.Range
import scala.collection.mutable.Map

object ScalaOrnekleri extends App{

  val aCondition = true

  val aDeger = if(aCondition) 3 else 5

  println(aDeger)

  val aWeirdVal = aDeger-2


  val x = {
    val a = 5; val b=4
    if(a>b) "a b'den büyüktür" else "saçmalama ayten"
  }

  println(x)

  def isPrimeAccumulator(n: Int): Boolean = {
    @tailrec
    def isPrimeAccumulatorHelper(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeAccumulatorHelper(t - 1 , n % t != 0 && isStillPrime)

    isPrimeAccumulatorHelper(n / 2 ,true)
  }


  println(isPrimeAccumulator(203))
  println(isPrimeAccumulator(4555))

  def fibonacciNew(n: Int): Int = {
    def fibonacciTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fibonacciTailrec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fibonacciTailrec(2 , 1 , 1)
  }

  println(fibonacciNew(10))


  def deneme (a: Int): Int= a / 2

  def smallFactorial(a: Int): BigInt = {
    if (a <= 1) 1 else {
      println(s"factorial $a için factorial " + (a-1))
      var result = a * smallFactorial(a-1)
      println(a)
      result
    }
  }
  println(smallFactorial(10))

  def factorial(n: Int): BigInt = {
    def factorialHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1 ) accumulator
      else {
        println(accumulator + "için" + (x-1))
        var value = factorialHelper( x - 1 , x * accumulator)
        println(accumulator)
        value
      }

    factorialHelper(n,1)
  }

  println(factorial(10))


  println(deneme(5))

  def recursive (aString: String,n: Int): String = {
    if (n==1) aString else aString + recursive(aString,n-1)
  }

  println(recursive("Hello",5))

  def fibonacci (n: Int): Int = {
    if (n == 0) 0 else if (n <= 1) 1 else fibonacci(n-2) + fibonacci(n-1)
  }

  println(fibonacci(6))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1 ) true
      else n % t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(n / 2)
  }

  println(isPrime(2))
  println(isPrime(3))
  println(isPrime(4))
  println(isPrime(6))
  println(isPrime(7))
  println(isPrime(9))
  println(isPrime(10))
  println(isPrime(241))




  def concat: (String,String) => String = new Function2[String,String,String] {
    override def apply(a: String,b: String): String = a + b
  }

  println(concat("Hello","World"))

  val topla: (Int,Int) => Int = new Function2[Int,Int,Int] {
    override def apply(a: Int,b: Int): Int = a + b
  }

  val cikar: (Int,Int) => Int = new Function2[Int,Int,Int] {
    override def apply(a: Int,b: Int): Int = if (a < b) - 1 else a - b
  }

  val superTopla: Function1[Int, Function1[Int,Int]] = new Function1[Int, Function1[Int,Int]] {
    override def apply(x: Int): Function1[Int,Int] = new Function1[Int,Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  println(superTopla(3)(4)) //CURRIED function

  val niceAdder: (Int,Int) => Int = _+_

  val superTopla2 = (x: Int) => (y: Int) => x + y

  println(superTopla2(12)(15))

  val fibonacci2: Int => Int = (n: Int) =>
    if (n == 0) 0 else if (n == 1) 1 else fibonacci2(n-2) + fibonacci2(n-1)


  println(fibonacci2(8))

  def nTimes(f: Int => Int, n: Int,x: Int): Int =
    if (n <= 0) x else nTimes(f, n-1, f(x))

  val plusOne: Int => Int = _ + 1

  val plusOneNew = (x: Int) => x + 1

  println(nTimes(plusOne,10,0))

  def nTimesBetter(f: Int => Int,n: Int): Int => Int =
    if (n <= 0) x => x else x => nTimesBetter(f, n-1)(f(x))

  val plus = nTimesBetter(plusOneNew,10)

  println(plus(1))

  def forEach: List[String] => Unit =_.foreach(println)

  val a = List("ali","Veli","Deli")

  forEach(a)

  def compose[A,B,T](f: A => B,g: T => A): T => B = x => f(g(x))
  def andThen[A,B,C](f: A => B,g: B => C): A => C = x => g(f(x))

  val toplama: Int => Int = _ + 2
  val carp: Int => Int = _ * 3

  val sonuc1 = compose(toplama,carp)
  val sonuc2 = andThen(toplama,carp)

  println(sonuc1(6))
  println(sonuc2(6))


  val list = List(1,2,3)

  println(list.head)
  println(list.tail)

  println(list.map(_ + 2))

  println(list.map(_ + 2))
  println(list.filter(_ % 2 == 0))

  val func = (x: Int) => List(x,x+1)

  println(list.flatMap(func))


  println(list.flatMap(func).zip(List(1,2,3,4,5,6)))

  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')

  println(chars.flatMap(x => numbers.map( y => { if(y % 2 == 0) y + " - " + x})))

  println(chars.flatMap(x => numbers.map( y => { if(y % 2 == 0) y + " - " + x})).filter(_ != ()))

  val combination = for {
    n <- numbers if n % 2 == 0
    c <- chars
  } yield s"$n - $c"

  println(combination)

  import scala.collection.mutable.ListBuffer

  var fileList = new ListBuffer[Int]

  fileList = ListBuffer(1,2,2,2,3,3,4,5)

  var fileList2 =  new ListBuffer[Int]

  fileList.foreach(x => {
    if (!fileList2.contains(x)) {
      fileList2 += x
    }
  })
  println(fileList)
  println(fileList2)

  def factorial_test(x: Int): BigInt = (2 to x).reduce(_ * _)

  println(s"burada ${factorial_test(17)}")

  println(fileList)
  println(fileList.foldLeft(3)(_+_))
  println(fileList.foldLeft(0)(_*_))

}
