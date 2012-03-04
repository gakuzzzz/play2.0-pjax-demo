package models

case class User(name: String, followers: Int, followings: Int, repositories: Repository*)

object User {

  def findById(id: Int): Option[User] = Some(User("gakuzzzz", 100, 200,
    Repository("objective-monad"),
    Repository("cookiesession"),
    Repository("json")
  ))

}