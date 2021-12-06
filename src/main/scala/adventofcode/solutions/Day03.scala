package adventofcode.solutions

import adventofcode.Support.*

@main def Day03 = Day(3) { (input, part) =>

  val i = input.split("\\n")

  val data = i.map(_.trim.toArray.map(_ - 48)).toArray
  val threshold = (data.length.toFloat / 2).ceil
  val gamma = Integer.parseInt(data.transpose.collect(x=> if (x.sum > threshold) '1' else '0' ).mkString, 2)
  part(1) = (Integer.parseInt(List.fill(i(0).length)('1').mkString, 2) - gamma) * gamma

  def findVal(l: Array[String], c: Boolean, idx: Int) : Array[String] = {
    if (l.length == 1)
      l
    else
      val threshold = (l.length.toFloat / 2).ceil.toInt
      val ord = l.sortWith(_(idx)<_(idx))
      val sep = ord.indexWhere(_(idx) == '1')
      val ones = ord.drop(sep)
      val zeros = ord.take(sep)
      if((c && ones.length >= threshold) || (!c && ones.length < threshold)){
        findVal(ones, c, idx + 1)
      } else {
        findVal(zeros, c, idx + 1)
      }
  }
  part(2) = Integer.parseInt(findVal(i, true, 0)(0).mkString,2) * Integer.parseInt(findVal(i, false, 0)(0).mkString,2)

}