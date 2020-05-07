package com.nyi.game.tictactoe

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nyi.game.tictactoe.PlayerState.DRAW
import com.nyi.game.tictactoe.PlayerState.O_WIN
import com.nyi.game.tictactoe.PlayerState.USER_TURN
import com.nyi.game.tictactoe.PlayerState.X_WIN
import com.nyi.game.tictactoe.model.Player.PLAYER_EMPTY
import com.nyi.game.tictactoe.model.Player.PLAYER_O_MINI
import com.nyi.game.tictactoe.model.Player.PLAYER_X_MAXI
import com.nyi.game.tictactoe.model.State

class TicTacToeViewModel : ViewModel() {

  val currentState = MutableLiveData<Pair<State, Boolean>>()
  val playingState = MutableLiveData<PlayerState>()

  private val emptyComputerStartState = State(
    lastPlayedPlayer = PLAYER_O_MINI,
    stateList = arrayOf(
      PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY,
      PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY,
      PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY
    )
  )

  private val emptyUserStartState = State(
    lastPlayedPlayer = PLAYER_EMPTY,
    stateList = arrayOf(
      PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY,
      PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY,
      PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY
    )
  )

  init {
    currentState.postValue(Pair(emptyUserStartState, true))
    playingState.postValue(USER_TURN)
  }

  fun makeComputerStartFirst(){
    val computerClickState = TicTacToe.calculateForX(emptyComputerStartState)
    //log(computerClickState)
    var clickable = true
    if(isFinish(computerClickState)) clickable = false
    currentState.postValue(Pair(computerClickState, clickable))
  }

  fun onPlayClick(playerClickState : State){
    //log(playerClickState)
    currentState.postValue(Pair(playerClickState, false))

    if(isFinish(playerClickState)) return

    val computerClickState = TicTacToe.calculateForX(playerClickState)
    //log(computerClickState)
    var clickable = true
    if(isFinish(computerClickState)) clickable = false
    currentState.postValue(Pair(computerClickState, clickable))
  }

  fun onClickRetry(isComputerFirst : Boolean){
    if(isComputerFirst){
      playingState.postValue(USER_TURN)
      currentState.postValue(Pair(emptyComputerStartState, true))
      makeComputerStartFirst()
    }else{
      playingState.postValue(USER_TURN)
      currentState.postValue(Pair(emptyUserStartState, true))
    }

  }

  private fun isFinish(state: State) : Boolean{
    if(TicTacToe.terminal(state)){
      val result = TicTacToe.utility(state)

      if(result == TicTacToe.PLAYER_X_WIN) playingState.postValue(X_WIN)
      else if(result == TicTacToe.PLAYER_O_WIN) playingState.postValue(O_WIN)
      else playingState.postValue(DRAW)
      return true
    }
    playingState.postValue(USER_TURN)
    return false
  }

  private fun log(state: State){
    var finalCodeList = "["
    state.stateList.forEach {
      finalCodeList += "${it.code}  , "
    }
    finalCodeList += "]"
    Log.d("Tic Tac Toe", finalCodeList)
  }
}
