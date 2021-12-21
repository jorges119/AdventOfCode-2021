package adventofcode.solutions

import adventofcode.Support.*

@main def Day21 = Day(21) { (input, part) =>

  val i = input.split("\\n").map(_.trim.match{ case s"Player $n starting position: $p" => p.trim.toInt}).toList

  val res = LazyList.continually((1 to 100)).flatten.grouped(3).scanLeft(Array[Array[Int]]())(_ :+ _.toArray).map(t =>
    t.foldLeft((0, i(0), 0, i(1), 0))((o,n) => 
      if (n.last % 2 != 0)
        val pos1 = ((o._2 + n.sum - 1)%10) + 1
        (o._1 + pos1, pos1, o._3, o._4, o._5 + 1)
      else
        val pos2 = ((o._4 + n.sum -1)%10) + 1
        (o._1, o._2, o._3 + pos2, pos2, o._5 + 1)
      )
  ).dropWhile((a,b,c,d,e) => a< 1000 && c < 1000).take(1).toList.head

  part(1) = res._5 * 3 * Array(res._1, res._3).min

  val li = Seq(1,1,1,2,2,2,3,3,3)
  val diceRolls = (0 to li.length-1).combinations(3).toList.map (v=>Seq(li(v(0)), li(v(1)), li(v(2)))).distinct.map(_.permutations).flatten
            .groupBy(_.sum).map((a,b) => (a, b.length)).toArray//.map(a => (a(0), a(1), a(2)))

  def rollDirac(start1: Int, points1: Int, start2: Int, points2: Int, count: Int, times: BigInt) : (BigInt, BigInt) = {
    if (points1 >= 21)
      (times, 0)
    else if (points2 >= 21)
      (0, times)
    else
      diceRolls.map((p,t) => 
        if(count%2 == 0)
          val pos1 = ((p + start1 - 1)%10) + 1
          rollDirac(pos1, points1 + pos1, start2, points2, count + 1, times * t)
        else
          val pos2 = ((p + start2 - 1)%10) + 1
          rollDirac(start1, points1, pos2, points2 + pos2, count + 1, times * t)
      ).reduce((o,n) => ((o._1 + n._1), (o._2 + n._2)))
  }

  val diracResult = rollDirac(i(0), 0, i(1), 0, 0, 1)
  part(2) = Array(diracResult._1, diracResult._2).max
}