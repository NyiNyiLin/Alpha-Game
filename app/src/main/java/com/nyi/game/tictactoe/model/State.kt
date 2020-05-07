package com.nyi.game.tictactoe.model

data class State(
  var lastPlayedPlayer : Player,
  var stateList : Array<Player>
)