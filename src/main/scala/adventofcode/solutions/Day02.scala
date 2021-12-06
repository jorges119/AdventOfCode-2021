package adventofcode.solutions

import adventofcode.Support.*

@main def Day02 = Day(2) { (input, part) =>

  val i = input.split("\\n").map(_.trim.split(" ") match {case Array(x,y) => (x,y.toInt)}).toList

  part(1) = i.foldLeft((0,0))((o, n) => {
      n match {
        case ("forward", v : Int) => (o._1 + v, o._2)
        case ("down", v : Int) => (o._1 , o._2 + v)
        case ("up", v : Int) => (o._1 , o._2 - v)
      }
    }) match { case (x: Int, y: Int) => x*y}

   part(2) = i.foldLeft((0,0,0))((o, n) => {
      n match {
        case ("forward", v : Int) => (o._1 + v, o._2 + (o._3 * v), o._3)
        case ("down", v : Int) => (o._1 , o._2, o._3 + v)
        case ("up", v : Int) => (o._1 , o._2, o._3 - v)
      }
    }) match { case (x: Int, y: Int, a: Int) => x*y}

}