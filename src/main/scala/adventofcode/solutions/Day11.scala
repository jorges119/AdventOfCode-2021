package adventofcode.solutions

import adventofcode.Support.*

@main def Day11 = Day(11) { (input, part) =>


  val i = input.split("\\n").zipWithIndex.map((s,r) => s.trim.zipWithIndex.map((c, col) => (List(c).mkString.toInt, (r,col))).toArray).flatten

  def getAdj(a:Int, b:Int) = {
    List(
      (a + 1, b),
      (a - 1, b),
      (a    , b + 1),
      (a    , b - 1),
      (a + 1, b +1),
      (a + 1, b -1),
      (a - 1, b -1),
      (a - 1, b +1)
    )
  }

  def updateCycle(i: (Array[(Int, (Int, Int), Boolean)], Int)): (Array[(Int, (Int,Int), Boolean)], Int) = {
    val flashing = i._1.filter((v,_,_) => v > 9).map((_,p,_) => getAdj(p._1,p._2))
    if (flashing.length > 0)
      updateCycle((i._1.map((v,p,s) => if (v > 9) (0, p, true)  else (v,p,s))
        .map((v,p,s) => (if (!s) v + flashing.flatten.count(_ == p) else v, p, s))) , i._2 + flashing.length)
    else
      i
  }

  def cycle(c: Int, f: Int, i: Array[(Int, (Int, Int))]) : Int = {
    if (c < 1)
      f
    else
      val (newState, count) = updateCycle((i.map((a,b) => (a + 1, b, false)), f))
      cycle(c - 1, count, newState.map((a,b,_) => (a,b)))
  }

  part(1) = cycle(100, 0, i)

  def cycleTillSync(c: Int, f: Int, i: Array[(Int, (Int, Int))]) : Int = {
    val (newState, count) = updateCycle((i.map((a,b) => (a + 1, b, false)), f))
    if ((count - f) == i.length)
      c + 1
    else
      cycleTillSync(c + 1, count, newState.map((a,b,_) => (a,b)))
  }

  part(2) = cycleTillSync(0, 0, i)
}