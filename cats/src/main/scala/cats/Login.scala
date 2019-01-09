package cats

case class User(username: String, password: String)

object Login {
  type LoginResult = Either[LoginError, User]

  sealed trait LoginError extends Product with Serializable

  final case class UserNotFound(username: String)
    extends LoginError

  final case class PasswordIncorrect(username: String)
    extends LoginError

  case object UnexpectedError extends LoginError
}
