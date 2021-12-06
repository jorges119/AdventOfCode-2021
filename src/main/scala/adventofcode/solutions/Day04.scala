package adventofcode.solutions

import adventofcode.Support.*

@main def Day04 = Day(4) { (input, part) =>


  val i = input.split("\\n\\n")
  val draw = i(0).split(",").map(_.trim.toInt)
  val boards = i.tail.map(_.split("\\n").map(_.trim.split("\\s+").map(_.trim.toInt).toList).toList)

  def getMatches = (b: List[List[Int]]) => {
    (b ::: b.transpose).flatten.sliding(5,5).map(_.map(y=>(draw.indexOf(y), y)).maxBy(_._1)).minBy(_._1)
  }

  val ((num, last), board) = boards.zipWithIndex.map((x) => (getMatches(x._1), x._2)).minBy(_._1)
  part(1) = boards(board).flatten.filterNot(draw.take(num + 1).toSet).sum * last

  val ((num0, last0), board0) = boards.zipWithIndex.map((x) => (getMatches(x._1), x._2)).maxBy(_._1)
  part(2) = boards(board0).flatten.filterNot(draw.take(num0 + 1).toSet).sum * last0

}