# Ovarro Software Engineer Technical Test

## Brief

Create an application to model a game of [Connect Four](https://en.wikipedia.org/wiki/Connect_Four). Feel free to use a framework if you wish. The application must fulfill the following core requirements:
* Allow the player(s) to configure the size of the grid and the winning row length (i.e. A player could need 4, 5, or 3 tokens, etc. in a row to win the game)
* Initialise a new game grid.
* Add a token to the game grid. A token can be either Red or Yellow. A token is added to a specified grid column.
* Show the game grid state/progress in the console or log to a file.
* Show the next player to play, Red or Yellow.
* Show the winner.

As much as possible, please keep it simple. If you need any more information, please don't hesitate to ask. We would rather you ask obvious questions and get it right rather than not ask and get it wrong. (Asking good questions is seen as a positive!)

## Advanced (Optional)

In addition to the core requirements above implement a GUI that can be interacted with of the game grid/state, allowing players to see the game progress and place their tokens on the grid.

## Review Criteria

At a high level, we will be looking for:
* Readability: clear naming conventions, clear documentation where necessary
* Good understanding of the programming language and its features
* Automated tests
* Simplicity
* Clear instructions on how to build, test & run the application using the build framework of your choice
* Logging
* Error handling
* Clearly described regular commits visible through VCS

## Languages

Feel free to use any language of your choice.

## Timescale

Please time-box this exercise at about 3 hours. If you've spent more than that and you're still not finished, just submit what you have - we're more interested in what you have done, not what you haven't, nor do we want this to take up a huge amount of your time. It is more important to model a subset of functionality to a higher level of quality than to model all the functionality but to a lower level of quality.

## The Deliverable

In the root of your application create a README.md including:

  1. A cover note explaining the technology choices you have made.
  2. Any instructions required to run your solution and tests in a Linux/Mac environment.

Email a link to your repo (make sure it's public!), or as an attachment the git bundled repository showing your commit history with all your commits on the master branch:

        git bundle create <anything>.bundle --all
