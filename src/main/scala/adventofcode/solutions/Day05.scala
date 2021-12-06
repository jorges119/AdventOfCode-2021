package adventofcode.solutions

import adventofcode.Support.*

@main def Day05 = Day(5) { (input, part) =>

  val i = input.split("\\n").map(l => l.trim.split(" -> ").map(v => v.trim.split(',').map(_.trim.toInt)).flatten) 

  def RangeFromInts = (a: Int, b: Int) => {
    val array = Array(a, b).sorted
    (array(0) to array(1)).toList
  }

  def DiagRange = (i: Array[Int])  => {
    val xAr = Array(i(0), i(2)).sorted
    val yAr = Array(i(1), i(3)).sorted
    if(i(2) - i(0) == i(3) - i(1))
      (xAr(0) to xAr(1)).zip(yAr(0) to yAr(1)).toList
    else 
      (xAr(0) to xAr(1)).zip((yAr(0) to yAr(1)).reverse).toList
  }

  part(1) = i.filter(x => x(0) == x(2) || x(1) == x(3)).map(x => {
      for {
        i1 <- RangeFromInts(x(0), x(2))
        i2 <- RangeFromInts(x(1), x(3))
      } yield (i1, i2)
    }).flatten.groupBy(identity).count(_._2.size > 1)


  // // confirm first that all diagonals are indeed diagonals
  // i5.filter(x => x(0) != x(2) && x(1) != x(3)).map(x => ((x(0) - x(2)).abs, (x(1) - x(3)).abs)).count((a,b)=> a != b)

  part(2) = ( i.filter(x => x(0) == x(2) || x(1) == x(3)).map(x => {
      for {
        i1 <- RangeFromInts(x(0), x(2))
        i2 <- RangeFromInts(x(1), x(3))
      } yield (i1, i2)
    }).flatten.toList ::: i.filter(x => x(0) != x(2) && x(1) != x(3)).map(DiagRange).flatten.toList
  ).groupBy(identity).count(_._2.size > 1)
}