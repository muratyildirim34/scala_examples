package scala_kurs_denemeleri

import scala.+:
import scala.collection.JavaConverters._
import scala.util.Random

object SeqListArrayVectorMapTupples extends App {

  // Sequence mutable
  val aSequence: Seq[Int] = 1 until 10 // Range type because until or to keywords
  val bSequence = Seq(1,2,5,4,3) // Companion object using apply method

  var numSeq = bSequence

  numSeq = numSeq :+ 42 // mutable

  println(aSequence)
  println(bSequence) // a sequence is a list
  //aSequence.foreach(println)

  //aSequence.foreach(x => println("Hello"))

  println(bSequence.reverse)
  println(bSequence.sorted)
  println(bSequence.sortWith((a,b) => a > b)) // order by desc
  println(bSequence.sorted(Ordering.Int.reverse))
  println(bSequence.sortBy(_.intValue()))
  println(bSequence.sortBy(_.intValue())(Ordering[Int].reverse))
  println(aSequence :+ 10) //became vector type - implicit conversion
  println(0 +: aSequence)
  println(aSequence ++ bSequence)

  //List immutable
  val myList = Nil // Nil => empty list
  val aList = List(1,2,3,4)
  println(12 :: aList)
  println(12 +: aList :+ 42)

  // head, tail and isEmpty => O(1) constant
  // length, reverse => O(n) linear

  var numList = List(1,2,3,4)

  val bList = List.fill(5)("apple") //curried function
  println(bList)

  println(bList.mkString("|"))

  //Array mutable
  val numArray = Array(1,2,3,4)
  val threeElements = Array.ofDim[Long](3)

  println("for git")

  threeElements.foreach(println)
  // Int, Long => 0    Double, Float => 0.0    Boolean => false     String, Person etc. => null

  numArray(2) = 5 // or numArray.update(2, 5) update method only for mutable collections
  numArray.update(2, 5)
  println(numArray.mkString(" "))
  println(numArray.sorted(Ordering.Int.reverse).mkString(" "))

  val wrappedArray: Seq[Int] = numArray // implicit conversion Array to Seq equals WrappedArray
  println(wrappedArray)


  //Vector immutable
  val numVector = Vector(1,2,3,4)
  println(numVector)

  //too faster than List read and write => O(log32(n)) 32 is branch factor

  //time comparison between List and Vector
//  val maxIter = 1000
//  val maxRandomLimit = 1000000
//
//  def getWriteTime(collection: Seq[Int]): Double = {
//    val random = new Random
//    val times = for {
//       iter <- 1 to maxIter
//    }
//    yield {
//      val currentTime = System.nanoTime()
//      collection.updated(random.nextInt(maxRandomLimit), 0)
//      System.nanoTime() - currentTime
//    }
//    times.sum * 1.0 / maxIter
//  }
//
//  val testList = (1 to maxIter).toList
//  val testVector = (1 to maxIter).toVector
//
//  println(getWriteTime(testList))
//  println(getWriteTime(testVector))

  //Set
  val numSet = Set(1,2,3,4,4)
  println(numSet) //Set(1, 2, 3, 4) set cannot contain duplicate elements



}
