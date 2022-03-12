
# Type Classes
- allow extending libraries with new functionality
    - without using traditional inheritance
    - without altering original lib source code

- 3 important components of type class pattern
    1. type class itself
    2. instances for particular type
    3. interface methods that we expose to users

Type Class
```scala
// in Cats type class is represented by a trait with at least 1 type param

// "serialize to JSON" behavior encoded in this trait
trait JsonWriter[A] {
    def write(value: A): Json
}
```

Type Class Instances
```scala
// In Scala we define type class instances by creating concrete implementations of type class
// and tagging them with implicit keyword

object JsonWriterInstances {
    implicit val personWriter: JsonWriter[Person] =
        new JsonWriter[Person] {
            def write(value: Person): Json = 
                JsObject(Map(
                    "name" -> JsString(value.name),
                    "email" -> JsString(value.email)
                ))
        }
}
```

Type Class Interfaces
```scala
// Type class interface is any functionality we expose to users
// Interfaces are generic methods that accept instances of type class as implicit params

// 2 common ways of specifying interfaces
===
// 1. Interface Objects
object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}
// to use it
import JsonWriterInstances._
Json.toJson(Person("Dave", "dave@example.com"))
// compiler searches for and inserts type class instances of relevant types
Json.toJson(Person("Dave", "dave@example.com"))(personWriter)
```

# 1.2 Working with Implicits

Implicit Scope

```scala
object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}

Json.toJson("String")  // searches for instance of type JsonWriter[String]
```

4 ways to package type class instances: by placing them in
1. an object
2. a trait
3. companion object of type class
4. companion object of parameter type

# References
- Book - Scala with Cats
