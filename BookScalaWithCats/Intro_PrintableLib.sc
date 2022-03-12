
// 1. Define a type class Printable[A] containing a single method format.
// format should accept a value of type A and return a String
trait Printable[A] {
    def format(value: A): String
}
// correct

// 2. Create an object PrintableInstances containing instances of
// Printable for String and Int.
object PrintableInstances {
    implicit val printableString: Printable[String] =
        new Printable[String] {
            def format(value: String): String =
                value
        }

    implicit val printableInt: Printable[Int] =
        new Printable[Int] {
            def format(value: Int): String =
                value.toString
        }
}

object Printable {
    def format[A](value: A)(implicit printable: Printable[A]): String =
        printable.format(value)

    def print[A](value: A)(implicit printable: Printable[A]): Unit =
        println(format(value))
}
// correct

final case class Cat(name: String, age: Int, color:String)

// create an implementation of Printable for Cat that returns
// content in the following format
implicit val printableCat: Printable[Cat] =
    new Printable[Cat] {
        def format(cat: Cat): String =
            s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
    }
// better another implementation used what we build
//import PrintableInstances._
//implicit val printableCat: Printable[Cat] = new Printable[Cat] {
//    override def format(cat: Cat): String = {
//        val name = Printable.format(cat.name)
//        val age = Printable.format(cat.age)
//        val color = Printable.format(cat.color)
//        s"${name} is a ${age} year-old ${color} cat."
//    }
//}

// ==========
val cat = Cat("my-cat", 10, "black")
Printable.print(cat)
// correct