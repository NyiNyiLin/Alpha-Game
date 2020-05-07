package com.nyi.game.tictactoe

enum class PlayerState (code : Int){
  PLAYING(1),
  X_WIN(2),
  O_WIN(3),
  DRAW(6),
  COMPUTER_TURN(4),
  USER_TURN(5)
}