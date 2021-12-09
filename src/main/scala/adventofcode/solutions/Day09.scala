package adventofcode.solutions

import adventofcode.Support.*

@main def Day09 = Day(9) { (input, part) =>

  val i = input.split("\\n").map(_.trim.map(c => List(c).mkString.toInt).toArray)

  def findMin = (i: Array[Int]) =>{
    i.sliding(3).toList.map(v => if (v(0) > v(1) && v(1) < v(2)) (v(1), true) else (v(1), false)).toArray
  }
  part(1) = i.map(v => findMin(9 +: v :+ 9)).flatten.zip(i.transpose.map(v => findMin(9 +: v :+ 9)).transpose.flatten).filter((c,r) => c._2 & r._2 == true).map(_._1._1 + 1).sum
  part(2) = 0 // Not completed yet
}