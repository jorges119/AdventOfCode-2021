package adventofcode.solutions

import adventofcode.Support.*

@main def Day12 = Day(12) { (input, part) =>

  val i = input.split("\\n").map(_.trim.match{ case s"$a-$b" => Array((a,b),(b,a))}).flatten.groupBy(_._1).map((i,v) => (i, v.map(_._2).distinct))

  def followPath(p: Array[Array[String]], reVisit: Boolean) : Array[Array[String]] = {
    val a = p.filter(_.last != "end").map((path) => i(path.last)
    .filter(x=> (x != "start") && (x == x.toUpperCase || (reVisit && path.filter(c => c == c.toLowerCase).groupBy(identity).filter(_._2.length > 1)
    .toArray.length == 0) || !path.contains(x))).map(x => path :+ x)).flatten

    if (a.length > 0)
      followPath((a ++ p.filter(_.last == "end")).distinct, reVisit)
    else
      p.filter(_.last == "end").distinct
  }

  part(1) = followPath(Array(Array("start")), false).length
  // part 2 has not been optimized for performance, a boolean flag in the path would reduce the query for duplicates on each state change, but is Sunday
  part(2) = followPath(Array(Array("start")), true).length

}