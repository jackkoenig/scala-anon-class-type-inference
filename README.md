# scala-anon-class-type-inference

Examples of Scala type inference working or failing for a couple of examples across versions:

## Example 1
Implementing trait field with anonymous subclass:
```scala
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
```
* Works in Scala 2.12 with scalac option `-Xsource:2.11`
    * SBT project `scala212source211`
    * https://scastie.scala-lang.org/tSN4oC8LQeambwf9SuyWew
* Fails in Scala 2.12
    * SBT project `scala212failing`
    * https://scastie.scala-lang.org/jSUquLBxQLevd1aSRLXn4Q
    
### Example Output:
```
$ sbt
...
sbt:scala212source211> run
[info] Running Test
3
[success] Total time: 1 s, completed Mar 1, 2019 11:42:21 AM

sbt:scala212source211> project scala212failing
[info] Set current project to scala212failing (in build file:/Users/jack/work/scala-anon-class-type-inference/)

sbt:scala212failing> run
[info] Compiling 1 Scala source to /Users/jack/work/scala-anon-class-type-inference/scala212failing/target/scala-2.12/classes ...
[error] /Users/jack/work/scala-anon-class-type-inference/scala212failing/src/main/scala/Foo.scala:10:15: value x is not a member of Foo
[error]   println(foo.x)
[error]               ^
[error] one error found
[error] (Compile / compileIncremental) Compilation failed
[error] Total time: 2 s, completed Mar 1, 2019 11:42:32 AM
```

## Example 2
Merely having a field that is an anonymous subclass
```scala
abstract class Foo
object Test extends App {
  val foo = new Foo {
    val x = 3
  }
  println(foo.x)
}
```
* Works in Scala 2.12
    * SBT project `scala212working`
    * https://scastie.scala-lang.org/1aPySn1KS424jVKpJKPGEg
* Fails in Dotty
    * SBT project `dottyfailing`
    * https://scastie.scala-lang.org/yNq1m554SQGvvz0k4gOQ4A

### Example Output:
```
$ sbt
...
sbt:scala212failing> project scala212working
[info] Set current project to scala212working (in build file:/Users/jack/work/scala-anon-class-type-inference/)

sbt:scala212working> run
[info] Running Test 
3

sbt:scala212working> project dottyfailing
[info] Set current project to dottyfailing (in build file:/Users/jack/work/scala-anon-class-type-inference/)

sbt:dottyfailing> run
[info] Compiling 1 Scala source to /Users/jack/work/scala-anon-class-type-inference/dottyfailing/target/scala-0.12/classes ...
[error] -- [E008] Member Not Found Error: /Users/jack/work/scala-anon-class-type-inference/dottyfailing/src/main/scala/Foo.scala:7:14 
[error] 7 |  println(foo.x)
[error]   |          ^^^^^
[error]   |          value x is not a member of Foo
[error] one error found
[error] (Compile / compileIncremental) Compilation failed
[error] Total time: 3 s, completed Mar 1, 2019 11:44:37 AM
```
