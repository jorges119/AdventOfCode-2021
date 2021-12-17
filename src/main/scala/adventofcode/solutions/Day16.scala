package adventofcode.solutions

import adventofcode.Support.*

@main def Day16 = Day(16) { (input, part) =>

  val table = Map(
    '0' ->  "0000",
    '1' ->  "0001",
    '2' ->  "0010",
    '3' ->  "0011",
    '4' ->  "0100",
    '5' ->  "0101",
    '6' ->  "0110",
    '7' ->  "0111",
    '8' ->  "1000",
    '9' ->  "1001",
    'A' ->  "1010",
    'B' ->  "1011",
    'C' ->  "1100",
    'D' ->  "1101",
    'E' ->  "1110",
    'F' ->  "1111"
  )

  val i = input.trim.map(table(_)).mkString

  def getContents(s:String, c: Int): Array[(Double, Int, Int)] = {
    if (s.length < 6 || c == 0)
      Array[(Double,Int, Int)]()
    else
      val version = Integer.parseInt(s.take(3), 2)
      val typeId =  Integer.parseInt(s.drop(3).take(3), 2)

      if (typeId == 4) 
        val length = (s.concat("000").drop(6).grouped(5).takeWhile(_.head != '0').length + 1) * 5
        val num = s.drop(6).take(length).zipWithIndex.filter(_._2 % 5 != 0).reverse.map(_._1).zipWithIndex.filter(_._1 == '1').map(x => scala.math.pow(2,x._2)).sum
          Array((num, length + 6, version)) ++ getContents(s.drop(6 + length), c - 1)
      else
        val (values, skip) = 
          if (s.drop(6).head == '0') 
            val count = Integer.parseInt(s.drop(7).take(15),2)
            (getContents(s.drop(22).take(count), -1), 22)

          else 
            val numPack = Integer.parseInt(s.drop(7).take(11).mkString, 2)
            (getContents(s.drop(18), numPack), 18)

        val numRes:Double = typeId match {
          case 0 => values.map(_._1).sum
          case 1 => values.map(_._1).product
          case 2 => values.map(_._1).min
          case 3 => values.map(_._1).max
          case 5 => if (values(0)._1 > values(1)._1) 1 else 0
          case 6 => if (values(0)._1 < values(1)._1) 1 else 0
          case 7 => if (values(0)._1 == values(1)._1) 1 else 0
        }
        Array((numRes, values.map(_._2).sum + skip, version + values.map(_._3).sum)) ++ getContents(s.drop(values.map(_._2).sum + skip), c - 1)
  }

  part(1) = getContents(i, -1).head._3
  part(2) = BigDecimal(getContents(i, -1).head._1).setScale(0, BigDecimal.RoundingMode.HALF_UP).toBigInt
}