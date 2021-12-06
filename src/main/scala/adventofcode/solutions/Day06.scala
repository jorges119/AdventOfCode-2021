package adventofcode.solutions

import adventofcode.Support.*

@main def Day06 = Day(6) { (input, part) =>

  val i = input.split(",").map(_.trim.toInt).groupBy(identity).map((d,a) => ((d - 8), BigInt(a.length))).toArray

  def calc(i: (BigInt, Array[(Int,BigInt)]), p: Int): (BigInt, Array[(Int,BigInt)]) = {
    val res = i._2.map((d, c) => ((d + 9) +: ((d + 16) to p by 7)).filter(_<=p).map((_,c))).flatten.groupBy(_._1).map((x,y) => (x, y.map(l => l._2).sum)).toArray
    if (res.length == 0)
      (i._1 + i._2.map(_._2).sum, res)
    else
      calc((i._1 + i._2.map(_._2).sum, res), p)
  }

  part(1) = calc((BigInt(0), i), 80)._1
  part(2) = calc((BigInt(0), i), 256)._1

}