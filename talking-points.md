https://books.underscore.io/scala-with-cats/scala-with-cats.html

Go through some of the basic concepts of cats in preparation for the http4s workshop to better understand the usage of
cats in that library.

Will mainly focus on boxing and unboxing values and Either

If there is time, mention Monoids for adding/combining from section 2.7 summary in the book

# Cats
Name from Catebory Theory
Abstraction for functional programming in Scala

## Pars of cats
* Type Classes
* Implicits

# Type class
type class itself
instances for a particular type
interface methods exposed to the user

Represented by a trait

See code in repo on how to implement the type class
Similar implementation is already done in Cats and called Show

# Common imports
import cats._ imports all of Cats’ type classes in one go;

import cats.instances.all._ imports all of the type class instances for the standard library in one go;

import cats.syntax.all._ imports all of the syntax in one go;

import cats.implicits._ imports all of the standard type class instances and all of the syntax in one go.

The most common ones to use:
* import cats._
* import cats.implicits._

To implement Show for you custom type:
```
implicit val dateShow: Show[Date] = Show.show(date => s"${date.getTime}ms since the epoch.") 
```

# Functor
Is a type class that can be `map`ed over
Example: List, Option, Future

Functors are often used for effect management for a single effect

Working with nested types instead of _.map(_.map(_.map))
```
import cats.Functor
import cats.implicits._

val listOption = List(Some(1), None, Some(2))


Functor[List].compose[Option].map(listOption)(_ + 1)
val nested: Nested[List, Option, Int] = Nested(listOption)
nested.map(i => i)
```

another way if there is more nesting is to use the Nested class
```
import cats.data.Nested
import cats.implicits._

val listOption = List(Some(1), None, Some(2))
Nested(listOption).map(_ + 1)
```

# Applicative
Extends Functor with `ap` and `pure`
`pure` picks out the value from option and the Right or successfull from Futures

Applicative are used to work with multiple independent effects.

# Monad
Extends Applicative with `flatten`. Takes values from nested context `F[F[A]]` where F is the context and flattens it to
F[A]

Monad is a mechanism for sequencing computations

Informally Monad is anything with a constructor and a `flatMap` method

for comprehension in Scala always calls flatMap method of the object

# Either
Either is right biased, if there is a left it will break out of the for comprehension

The right bias was introduced in Scala 2.12. To have it before that import `cats.syntax.either`

```
val either1: Either[String, Int] = Right(10)
val either2: Either[String, Int] = Right(32)

for {
  a <- either1
  b <- either2
} yield a + b
```

```
import scala.cats.either._
val a = 3.asRight[String]
// a: Either[String, Int] = Right(3)

"error".asLeft[Int].recoverWith {
 case str: String => Right(-1)
}
// Either[String, Int] = Right(-1)
```

```
Either.fromTry(scala.util.Try("foo".toInt))
Either.fromOption(None, "failed")
// Either[Nothing, Int] = Left(failed)

-1.asRight[String].ensure("Must be non-negative")(_ > 0)
```

```
"hello".asRight[String].map(_ + "!")
// Either[String, String] = hello!
```
