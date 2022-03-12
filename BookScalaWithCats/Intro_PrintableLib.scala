
trait Printable[A] {
    def format(value: A): String
}

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

final case class Cat(name: String, age: Int, color:String)

// create an implementation of Printable for Cat that returns
// content in the following format
implicit val printableCat: Printable[Cat] =
    new Printable[Cat] {
        def format(cat: Cat): String =
            s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
    }

object Main {
    def main(args: Array[String]): Unit = {
        println("Starting program...")
    }
}