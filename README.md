# cats-workshop

## Exercise 1
Take the list below and add 1 to all alements which have a value without using nested mapping.
```
val listOption = List(Some(1), None, Some(2))
```
*Hint* Functor compose or Nested are good ways to solve it

## Exercise 2
Make a `getUsers` function that accepts a list of ids that randomly returns an `Option` of User which sometimes have
values. Return all the usernames of the returned users.

## Exercise 3
Make a `validateUser` function that can return an LoginResult which is an Either of LoginError or User.
Each validation is in it's own step and if any step fails it short circuits and does not run the rest of the
validations.


