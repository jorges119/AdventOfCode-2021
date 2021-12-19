package adventofcode.solutions

import adventofcode.Support.*

@main def Day18 = Day(18) { (input, part) =>

	val i = input.split("\\n").map(_.trim)

	def iterate(s: String): String = {
		val explodePtrRev = raw"\](\d+),(\d+)\[(.*)".r
		val splitPtr = raw"(.*)(\d\d+)(.+?)".r
		val explodePoint = try {
			s.scanLeft(""){_+_}.dropWhile(x => 
			((x.filter(_=='[').length - x.filter(_==']').length) < 4) || 
			(x.reverse match {
				case explodePtrRev(r, l, start) => false
				case _ => true
			})
			).head
		} catch {
			case _ => ""
		}

		val exp = raw"(.+?)(\d+)(.*)".r

		val testStr = if (!explodePoint.isEmpty) explodePoint else s

		testStr.reverse match {
			case explodePtrRev(r, l, start) => {
				val newStart = start match { case exp(e, v, s) => s"${s.reverse}${v.reverse.toInt + l.reverse.toInt}${e.reverse}" case _ => start.reverse}
				val newEnd = s.drop(testStr.length) match { case exp(s, v, e) => s"$s${v.toInt + r.reverse.toInt}$e" case _ => s.drop(testStr.length)}
				val update = s"${newStart}0${newEnd}"
				iterate(update)
			}

			case splitPtr(end, number, start) => {
				val numL = ((number.reverse.toFloat /2).floor).toInt
				val numR = ((number.reverse.toFloat /2).ceil).toInt
				iterate(s"${start.reverse}[$numL,$numR]${end.reverse}")
			}


			case _ => s
		}
	}

	def calculate(s:String): Int = {
		val pairsPtr = raw"(.+?)\[(\d+),(\d+)\](.*)".r
		val donePtr = raw"x(\d+)x".r
		s match {
			case donePtr(v) => v.toInt 
			case pairsPtr(s, l, r, e) => calculate(s"$s${(l.toInt * 3) + (r.toInt * 2)}$e")
			case _ => throw new Error(s)
		}
	}

	part(1) = calculate("x" + i.drop(1).foldLeft(i(0))((o,n) => { iterate(s"[${o},${n}]") }) + "x")

	part(2) = i.combinations(2).map(l => List(s"x[${l(0)},${l(1)}]x", s"x[${l(1)},${l(0)}]x")).flatten.map(s => calculate(iterate(s))).max
}