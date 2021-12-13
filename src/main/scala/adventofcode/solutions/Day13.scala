package adventofcode.solutions

import adventofcode.Support.*

@main def Day13 = Day(13) { (input, part) =>

  val i = input.split("fold ")
  val dots = i.head.trim.split("\\n").map( _.trim match { case s"$a,$b" => (a.toInt, b.toInt)})
  val instructions = i.tail.map(_ match { case s"along $d=$v" => (d, v.trim.toInt)})

  def foldIt(i: Array[(String, Int)]) = {
    i.foldLeft(dots)((o,n) => o.map((x,y) => 
      if ((n._1 == "y" && y < n._2) || (n._1 == "x" && x < n._2)) 
        (x,y) 
      else if (n._1 == "y")
        (x, 2*n._2-y)
      else
        (2*n._2-x, y)
    ).distinct)
  }

  part(1) = foldIt(instructions.take(1)).length

  val code = foldIt(instructions)
  val (maxY, maxX) = (code.maxBy(_._1)._1 + 1, code.maxBy(_._2)._2 + 1)

  part(2) = System.lineSeparator + Array.fill(maxX)(Array.fill(maxY)(' ').zipWithIndex).zipWithIndex
  .map((v,p) => v.map((l,c) => if (code.find((x,y)=> x == c && y == p).isEmpty) ' ' else '#').mkString).mkString(System.lineSeparator) + System.lineSeparator

}