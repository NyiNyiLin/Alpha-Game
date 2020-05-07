package com.nyi.game.tictactoe

import com.nyi.game.tictactoe.model.Action
import com.nyi.game.tictactoe.model.Player
import com.nyi.game.tictactoe.model.Player.PLAYER_EMPTY
import com.nyi.game.tictactoe.model.Player.PLAYER_O_MINI
import com.nyi.game.tictactoe.model.Player.PLAYER_X_MAXI
import com.nyi.game.tictactoe.model.State

object TicTacToe {

  const val PLAYER_X_WIN = 3
  const val PLAYER_O_WIN = -3

  fun player(state: State) : Player {
      when(state.lastPlayedPlayer){
        PLAYER_O_MINI -> return PLAYER_X_MAXI
        PLAYER_X_MAXI -> return PLAYER_O_MINI
        else -> PLAYER_EMPTY
      }
    return PLAYER_EMPTY
  }

  fun action(state: State) : List<Action>{
    val availableAction = mutableListOf<Action>()
    state.stateList.forEachIndexed { index, player ->
      when(player){
        PLAYER_EMPTY -> {
          val newStateList = state.stateList.copyOf()
          var lastePlayPlayer = PLAYER_EMPTY

          when(state.lastPlayedPlayer){
            PLAYER_X_MAXI -> lastePlayPlayer = PLAYER_O_MINI
            PLAYER_O_MINI -> lastePlayPlayer= PLAYER_X_MAXI
          }
          newStateList[index] = lastePlayPlayer
          availableAction.add(Action(State(lastePlayPlayer, newStateList)))
        }
      }
    }
    return availableAction
  }

  fun result(state: State, action: Action) : State{
    return action.state
  }

  fun terminal(state: State) : Boolean{

    val firstHorizontal = state.stateList[0].code + state.stateList[1].code + state.stateList[2].code
    val secondHorizontal = state.stateList[3].code + state.stateList[4].code + state.stateList[5].code
    val thirdHorizontal = state.stateList[6].code + state.stateList[7].code + state.stateList[8].code

    val firstVertical = state.stateList[0].code + state.stateList[3].code + state.stateList[6].code
    val secondVertical = state.stateList[1].code + state.stateList[4].code + state.stateList[7].code
    val thirdVertical = state.stateList[2].code + state.stateList[5].code + state.stateList[8].code

    val leftDiagonal = state.stateList[0].code + state.stateList[4].code + state.stateList[8].code
    val rightDiagonal = state.stateList[2].code + state.stateList[4].code + state.stateList[6].code

    if(firstHorizontal == PLAYER_X_WIN || firstHorizontal == PLAYER_O_WIN) return true
    else if(secondHorizontal == PLAYER_X_WIN || secondHorizontal == PLAYER_O_WIN) return true
    else if(thirdHorizontal == PLAYER_X_WIN || thirdHorizontal == PLAYER_O_WIN) return true
    else if(firstVertical == PLAYER_X_WIN || firstVertical == PLAYER_O_WIN) return true
    else if(secondVertical == PLAYER_X_WIN || secondVertical == PLAYER_O_WIN) return true
    else if(thirdVertical == PLAYER_X_WIN || thirdVertical == PLAYER_O_WIN) return true
    else if(leftDiagonal == PLAYER_X_WIN || leftDiagonal == PLAYER_O_WIN) return true
    else if(rightDiagonal == PLAYER_X_WIN || rightDiagonal == PLAYER_O_WIN) return true
    else {
      state.stateList.forEach {
        when(it) {
          PLAYER_EMPTY -> return false
        }
      }
      return true
    }
  }

  fun utility(state: State) : Int{
    val firstHorizontal = state.stateList[0].code + state.stateList[1].code + state.stateList[2].code
    val secondHorizontal = state.stateList[3].code + state.stateList[4].code + state.stateList[5].code
    val thirdHorizontal = state.stateList[6].code + state.stateList[7].code + state.stateList[8].code

    val firstVertical = state.stateList[0].code + state.stateList[3].code + state.stateList[6].code
    val secondVertical = state.stateList[1].code + state.stateList[4].code + state.stateList[7].code
    val thirdVertical = state.stateList[2].code + state.stateList[5].code + state.stateList[8].code

    val leftDiagonal = state.stateList[0].code + state.stateList[4].code + state.stateList[8].code
    val rightDiagonal = state.stateList[2].code + state.stateList[4].code + state.stateList[6].code

    if(firstHorizontal == PLAYER_X_WIN || firstHorizontal == PLAYER_O_WIN) return firstHorizontal
    else if(secondHorizontal == PLAYER_X_WIN || secondHorizontal == PLAYER_O_WIN) return secondHorizontal
    else if(thirdHorizontal == PLAYER_X_WIN || thirdHorizontal == PLAYER_O_WIN) return thirdHorizontal
    else if(firstVertical == PLAYER_X_WIN || firstVertical == PLAYER_O_WIN) return firstVertical
    else if(secondVertical == PLAYER_X_WIN || secondVertical == PLAYER_O_WIN) return secondVertical
    else if(thirdVertical == PLAYER_X_WIN || thirdVertical == PLAYER_O_WIN) return thirdVertical
    else if(leftDiagonal == PLAYER_X_WIN || leftDiagonal == PLAYER_O_WIN) return leftDiagonal
    else if(rightDiagonal == PLAYER_X_WIN || rightDiagonal == PLAYER_O_WIN) return rightDiagonal
    else return 0
  }

  fun maxValue(state: State) : Int{
    if(terminal(state)) return utility(
      state
    )
    val availableAction = action(state)
    var max = -19
    availableAction.forEach {
      val value = miniValue(
        result(
          it.state,
          it
        )
      )
      if(value >= max) max = value
    }
    return max
  }

  fun miniValue(state: State) : Int{
    if(terminal(state)) return utility(
      state
    )
    val availableAction = action(state)
    var min = 19
    availableAction.forEach {
      val value = maxValue(
        result(
          it.state,
          it
        )
      )
      if(value <= min) min = value
    }
    return min
  }

  fun calculateForX(state: State) : State{
    val availableAction = action(state)
    var max = -19
    var returnState = availableAction[0].state

    availableAction.forEach {
      val value = miniValue(
        result(
          it.state,
          it
        )
      )
      if(value >= max) {
        max = value
        returnState = it.state
      }
    }
    return returnState
  }
}