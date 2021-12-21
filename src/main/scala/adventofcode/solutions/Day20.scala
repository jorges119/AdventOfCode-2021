package adventofcode.solutions

import adventofcode.Support.*

@main def Day20 = Day(20) { (input, part) =>

  val i = input.split("\\n")

  val codeStr = i.head
  val image = i.drop(1).tail.zipWithIndex.map((r,y) => r.trim.zipWithIndex.map((c,x) => ((x,y) -> (if (c=='.') 0 else 1)))).flatten.toMap


  def enhance(image: Map[(Int, Int), Int], default: Int, c: Int) : Int = {
    if (c == 0)
      image.toArray.map(_._2).sum
    else
      val minX = image.minBy(_._1._1)._1._1
      val maxX = image.maxBy(_._1._1)._1._1
      val minY = image.minBy(_._1._2)._1._2
      val maxY = image.maxBy(_._1._2)._1._2

      val update = ((minX - 1) to (maxX + 1)).map((_, minY - 1) -> default).toMap ++ 
      ((minX - 1) to (maxX + 1)).map((_, maxY + 1) -> default).toMap ++
      ((minY) to (maxY)).map((minX - 1, _) -> default).toMap ++
      ((minY) to (maxY)).map((maxX + 1, _) -> default).toMap ++
      image

      val defaultO = 
        if (default == 0)
          if (codeStr.head == '#') 1 else 0
        else
          if (codeStr.last == '#') 1 else 0
      enhance (
        update.toArray.map((p,_) => (p, if (codeStr(
            Array(
            (p._1 - 1, p._2 - 1),
            (p._1    , p._2 - 1),
            (p._1 + 1, p._2 - 1),
            (p._1 - 1, p._2    ),
            (p._1    , p._2    ),
            (p._1 + 1, p._2    ),
            (p._1 - 1, p._2 + 1),
            (p._1    , p._2 + 1),
            (p._1 + 1, p._2 + 1),
            ).reverse.zipWithIndex.map((a,b) => if (update.getOrElse(a, default) == 1) (scala.math.pow(2,b)).toInt else 0).sum) == '#') 1 else 0)
          ).toMap,
          defaultO, c-1)
  }

  part(1) = enhance(image, 0, 2)
  part(2) = enhance(image, 0, 50)

}