package adventofcode.solutions

import adventofcode.Support.*

@main def Day08 = Day(8) { (input, part) =>

  val digits = List((0, 6), (1,2), (2,5), (3,5), (4,4), (5,5), (6,6), (7,3), (8,7), (9,6)) : List[(Int, Int)]

  val i = input.split("\\n").map{
    case s"$input | $output" => (input.trim.split("\\s"), output.trim.split("\\s"))
    case _ => throw new Exception("Non expected input")
  }

  part(1) = i.collect(_._2.filter(x=> digits.filter(d => d._2 == x.length).length == 1).length).sum

  def generateMap = (i: Array[String]) => {
    val set = i.groupBy(_.length)
    extension (input: Array[Char]) def getCharsByCount(count: Int): String = input.groupBy(identity).filter(_._2.length == count).map(_._1).mkString
    /*

      000000
      1    2
      1    2
      333333
      4    5
      4    5
      666666

      1: (2,5) -> 7: (0!) -> ... 
    */
    val d1 = set(2)(0)
    val d7 = set(3)(0)
    val d4 = set(4)(0)
    val s0 = d7.filterNot(d1.toSet)
    val s4 = set(5).map(_.filterNot((d1 + d4).toSet)).flatten.getCharsByCount(1)
    val s1 = set(5).map(_.filterNot((d1 + s4).toSet)).flatten.getCharsByCount(1)
    val s3 = d4.filterNot((d1 + s1).toSet)
    val s2 = set(6).map(_.filter(d1.toSet)).flatten.getCharsByCount(2)
    val s5 = set(6).map(_.filter(d1.toSet)).flatten.getCharsByCount(3)
    val s6 = set(5).map(_.filterNot((d7 + s1 + s3 + s4).toSet)).flatten.getCharsByCount(3)

    Map(
      (s0+s2+s5+s6+s4+s1).sortWith(_.compareTo(_) < 0) -> 0,
      (s2+s5).sortWith(_.compareTo(_) < 0) -> 1,
      (s0+s2+s3+s4+s6).sortWith(_.compareTo(_) < 0) -> 2,
      (s0+s2+s3+s5+s6).sortWith(_.compareTo(_) < 0) -> 3,
      (s1+s3+s2+s5).sortWith(_.compareTo(_) < 0) -> 4,
      (s0+s1+s3+s5+s6).sortWith(_.compareTo(_) < 0) -> 5,
      (s0+s1+s3+s5+s6+s4).sortWith(_.compareTo(_) < 0) -> 6,
      (s0+s2+s5).sortWith(_.compareTo(_) < 0) -> 7,
      (s0+s2+s3+s5+s6+s4+s1).sortWith(_.compareTo(_) < 0) -> 8,
      (s0+s2+s3+s5+s6+s1).sortWith(_.compareTo(_) < 0) -> 9,
    )
  }


  part(2) = i.map((x,y)=> {
    val mapping = generateMap(x)
    y.map(v => mapping(v.sortWith(_.compareTo(_) < 0).toString)).mkString.toInt
  }).sum
}