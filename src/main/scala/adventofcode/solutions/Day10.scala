package adventofcode.solutions

import adventofcode.Support.*

@main def Day10 = Day(10) { (input, part) =>

  def remClosed(i: String, returnInc: Boolean): String = {
    val newStr = i.replace("<>", "").replace("[]", "").replace("{}", "").replace("()", "")
    if (newStr.length != i.length)
      remClosed(newStr, returnInc)
    else
      val res = newStr.intersect(">]})")
      if (res.length > 0)
        List(res(0)).mkString
      else
        if (returnInc) newStr else ""
  }

  val points = Map( "" -> 0, ")" -> 3 , "]" -> 57, "}" -> 1197, ">" -> 25137)
  val pointsC = Map('(' -> 1 , '[' -> 2, '{' -> 3, '<' -> 4)
  val x = input.split("\\n")

  part(1) = x.map(l=> points(remClosed(l, false))).sum

  // val scores = x.map(l => remClosed(l, true)).filter(_.intersect("([{<").length > 0).map(_.reverse.trim.filterNot(c => pointsC.toArray.map(_._1).contains(c))).toList
  val scores = x.map(l => remClosed(l, true)).filter(_.intersect("([{<").length > 0).map(_.reverse.trim.foldLeft(BigInt(0))((o,n) => o * 5 + pointsC(n))).toList.sorted
  part(2) = scores((scores.length.toFloat/2.0).ceil.toInt-1)

}