package adventofcode.solutions

import adventofcode.Support.*

@main def Day09 = Day(9) { (input, part) =>

  val i = input.split("\\n").map(_.trim.map(c => List(c).mkString.toInt).toArray)

  def findMin = (i: Array[Int], r: Int) =>{
    i.sliding(3).zipWithIndex.toList.map((v, idx) => if (v(0) > v(1) && v(1) < v(2)) ((v(1), (r, idx)), true) else ((v(1), (r, idx)), false)).toArray
  }
  val points = i.zipWithIndex.map((v, idx) => findMin(9 +: v :+ 9, idx)).flatten.zip(i.transpose.map(v => findMin(9 +: v :+ 9, 0)).transpose.flatten).filter((c,r) => c._2 & r._2 == true)

  part(1) = points.map((a, _) => a._1._1 + 1).sum

  val colSize = i.length
  val rowSize = i(0).length

  def getAdj(ps: Array[(Int, Int)]): Array[(Int,Int)] = {
    val search = ps.map((a,b) => Array(
      (a + 1, b),
      (a - 1, b),
      (a, b + 1),
      (a, b - 1),
      ).filter((y,x)=> x >= 0 && x < rowSize && y >= 0 && y < colSize).filter((y,x) => i(y)(x) != 9)).flatten.distinct
      
      
    if (search.filterNot(ps.toSet).length > 0)
      getAdj((search ++ ps).distinct)
    else
      ps
  }

  part(2) = (for p <- points.map((c, r) => c._1._2) yield { getAdj(Array(p)).length }).sorted.reverse.take(3).product
}