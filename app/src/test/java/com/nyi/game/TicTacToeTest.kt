package com.nyi.game

import com.nyi.game.tictactoe.TicTacToe
import com.nyi.game.tictactoe.model.Player.PLAYER_EMPTY
import com.nyi.game.tictactoe.model.Player.PLAYER_O_MINI
import com.nyi.game.tictactoe.model.Player.PLAYER_X_MAXI
import com.nyi.game.tictactoe.model.State
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TicTacToeTest {

  @Test
  fun tic_tac_toe_test() {
    val oneLeftState = State(
      PLAYER_O_MINI,
      arrayOf(
        PLAYER_X_MAXI, PLAYER_X_MAXI, PLAYER_O_MINI,
        PLAYER_O_MINI, PLAYER_O_MINI, PLAYER_X_MAXI,
        PLAYER_X_MAXI, PLAYER_EMPTY, PLAYER_X_MAXI
      )
    )

    val twoLeftState = State(
      PLAYER_O_MINI,
      arrayOf(
        PLAYER_EMPTY, PLAYER_X_MAXI, PLAYER_O_MINI,
        PLAYER_O_MINI, PLAYER_O_MINI, PLAYER_X_MAXI,
        PLAYER_X_MAXI, PLAYER_EMPTY, PLAYER_X_MAXI
      )
    )

    val threeLeftState = State(
      PLAYER_O_MINI,
      arrayOf(
        PLAYER_EMPTY, PLAYER_X_MAXI, PLAYER_O_MINI,
        PLAYER_O_MINI, PLAYER_O_MINI, PLAYER_EMPTY,
        PLAYER_X_MAXI, PLAYER_EMPTY, PLAYER_O_MINI
      )
    )

    val emptyState = State(
      PLAYER_O_MINI,
      arrayOf(
        PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY,
        PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY,
        PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY
      )
    )

    val firstState = State(
      PLAYER_O_MINI,
      arrayOf(
        PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY,
        PLAYER_EMPTY, PLAYER_O_MINI, PLAYER_EMPTY,
        PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_X_MAXI
      )
    )
    val secondState = State(
      PLAYER_O_MINI,
      arrayOf(
        PLAYER_EMPTY, PLAYER_EMPTY, PLAYER_EMPTY,
        PLAYER_EMPTY, PLAYER_O_MINI, PLAYER_EMPTY,
        PLAYER_O_MINI, PLAYER_X_MAXI, PLAYER_X_MAXI
      )
    )

    val finalState = TicTacToe.calculateForX(secondState)

    //Log.d("Tic Tac Toe", "${finalState.lastPlayedPlayer} + ${finalState.stateList}")

    var finalCodeList = ""
    finalState.stateList.forEach {
      finalCodeList += "${it.code}  , "
    }
    assertEquals(finalCodeList, 2 + 3)
  }

  @Test
  fun test_action() {
    val oneLeftState = State(
      PLAYER_O_MINI,
      arrayOf(
        PLAYER_EMPTY, PLAYER_X_MAXI, PLAYER_O_MINI,
        PLAYER_O_MINI, PLAYER_O_MINI, PLAYER_EMPTY,
        PLAYER_X_MAXI, PLAYER_EMPTY, PLAYER_X_MAXI
      )
    )
    val actionList = TicTacToe.action(oneLeftState)
    var finalCodeList = ""
    actionList.forEach {
      finalCodeList += "["
      it.state.stateList.forEach {
        finalCodeList += "${it.code}  , "
      }
      finalCodeList += "]"
    }
    assertEquals(finalCodeList, 2 + 3)

  }
}
