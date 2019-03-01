
abstract class Foo
object Test extends App {
  val foo = new Foo {
    val x = 3
  }
  println(foo.x)
}
