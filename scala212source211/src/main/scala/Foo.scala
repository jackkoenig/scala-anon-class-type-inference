
abstract class Foo
trait Bar {
  def foo: Foo
}
object Test extends App with Bar {
  val foo = new Foo {
    val x = 3
  }
  println(foo.x)
}
