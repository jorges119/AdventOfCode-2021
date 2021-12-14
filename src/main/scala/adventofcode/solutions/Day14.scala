package adventofcode.solutions

import adventofcode.Support.*
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
// import scala.math.BigDecimal.RoundingMode

@main def Day14 = Day(14) { (input, part) =>

  val i = input.split("\\n")
  val initial = i(0)
  val templates = i.drop(2).map(_.trim.match{
    case s"$a -> $b" => (a.trim, Array(List(a.trim.head, b.trim.head).mkString, List(b.trim.head, a.trim.last).mkString))}).toMap

  def cycle(s: Array[(String, BigInt)], c: Int) : Array[(String,BigInt)] = {
    if (c == 0)
      s
    else
      cycle(s.map((a,b) => templates.getOrElse(a, Array("")).map(n => (n, b))).flatten.groupMapReduce(_._1)(_._2)(_ + _).toArray, c - 1)
  } 

  def getMaxMin(c: Int) = {
    val res = cycle(initial.sliding(2).map((_, BigInt(1))).toArray, c).map((a,b) => a.map((_,b))).flatten.groupMapReduce(_._1)(_._2)(_ + _).toArray
    (BigDecimal(res.maxBy(_._2)._2 - res.minBy(_._2)._2) / 2)//.setScale(0, RoundingMode.HALF_UP) 
  }
  part(1) = getMaxMin(10)
  part(2) = getMaxMin(40)
}