/**
As a running example, we will have a series of functions that will parse a string 
into an integer, take the reciprocal, and then turn the reciprocal into a string.

Source: https://www.scala-exercises.org/cats/either 
**/

// exception-throwing style
object ExceptionStyle {
  def parse(s: String): Int =
    if (s.matches("-?[0-9]+")) s.toInt
    else throw new NumberFormatException(s"${s} is not a valid integer.")

  def reciprocal(i: Int): Double =
    if (i == 0) throw new IllegalArgumentException("Cannot take reciprocal of 0.")
    else 1.0 / i

  def stringify(d: Double): String = d.toString

}

// functional style
object EitherStyle {
    def parse(s: String): Either[NumberFormatException, Int] =
        if (s.matches("-?[0-9]+")) Either.right(s.toInt)
        else Either.left(new NumberFormatException(s"${s} is not a valid number"))

    def reciprocal(i: Int): Either[IllegalArgumentException, Double] =
        if (i == 0) Either.left(new IllegalArgumentException("Cannot take reciprocal of 0."))
        else Either.right(1.0 / i)

    def stringify(d: Double): String = d.toString

    def magic(s: String): Either[Exception, String] = 
        parse(s).map(reciprocal).map(stringify)
}
