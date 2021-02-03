import scala.annotation.tailrec

package object scala_kurs_denemeleri


object network extends App {


  def add(network: Map[String,Set[String]],person: String): Map[String,Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String,Set[String]],a: String,b: String): Map[String,Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    // Veri tipi Set değil List olunca => friendsA :+ b oluyor
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String,Set[String]],a: String,b: String): Map[String,Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    // Set olmasının sebebi - ile setten string silebiliyor
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String,Set[String]],person: String): Map[String,Set[String]] = {
    def removeAux(friends: Set[String],networkAcc: Map[String,Set[String]]): Map[String,Set[String]] =
      if(friends.isEmpty) networkAcc
      else removeAux(friends.tail,unfriend(networkAcc,friends.head,person))

    val unfriended = removeAux(network(person), network)
    // Type Map - String => Mapden bir key value silmek demek
    unfriended - person
  }

  // get the friends of a person
  def nfriends(network: Map[String,Set[String]],person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  // get who has maximum friend in network
  def maxFriend(network: Map[String,Set[String]]): String =
    network.maxBy(_._2.size)._1
    //network.maxBy(x => x._2.size)._1

  def countPeopleWithNoFriend(network: Map[String,Set[String]]): Int =
    //network.filter(x => x._2.size == 0).size or
    network.count(x => x._2.isEmpty)

  def socialConn(network: Map[String,Set[String]], a: String,b: String): Boolean = {
    @tailrec
    def bfs(target: String,consideredPeople: Set[String],discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target,consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }


  // istenen formatta boş bir map yarat!!
  val empty: Map[String, Set[String]] = Map()
  val network = add(add(add(empty,"Murat"),"Mahmut"),"Ayşe")

  val muratMahmut = friend(network,"Murat","Mahmut")
  val muratAyse = friend(muratMahmut,"Murat","Ayşe")
  val son = remove(muratAyse,"Mahmut")
  println(son)



}
