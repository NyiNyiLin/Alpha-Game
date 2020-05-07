package com.nyi.game.tictactoe

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.nyi.game.R
import com.nyi.game.databinding.ViewTicTacToeBinding
import com.nyi.game.tictactoe.model.Player
import com.nyi.game.tictactoe.model.Player.PLAYER_EMPTY
import com.nyi.game.tictactoe.model.Player.PLAYER_O_MINI
import com.nyi.game.tictactoe.model.Player.PLAYER_X_MAXI
import com.nyi.game.tictactoe.model.State

class TicTacToeView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  private val binding by lazy {
    ViewTicTacToeBinding.inflate(
      context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
      this,
      true
    )
  }

  private var currentState : State? = null
  private var allowClick : Boolean = false
  private var listener : TicTacToeClickListener? = null

  private val itemViewList = listOf<ImageView>(
    binding.ivFirst, binding.ivSecond, binding.ivThird,
    binding.ivFourth, binding.ivFifth, binding.ivSixth,
    binding.ivSeventh, binding.ivEighth, binding.ivNinth
  )
  init {
    binding.ivFirst.setOnClickListener {
      onClickTicTacToeItem(0)
    }

    binding.ivSecond.setOnClickListener {
      onClickTicTacToeItem(1)
    }

    binding.ivThird.setOnClickListener {
      onClickTicTacToeItem(2)
    }

    binding.ivFourth.setOnClickListener {
      onClickTicTacToeItem(3)
    }

    binding.ivFifth.setOnClickListener {
      onClickTicTacToeItem(4)
    }

    binding.ivSixth.setOnClickListener {
      onClickTicTacToeItem(5)
    }

    binding.ivSeventh.setOnClickListener {
      onClickTicTacToeItem(6)
    }

    binding.ivEighth.setOnClickListener {
      onClickTicTacToeItem(7)
    }

    binding.ivNinth.setOnClickListener {
      onClickTicTacToeItem(8)
    }
  }

  fun setState(state : State, allowClick : Boolean){
    this.currentState = state
    this.allowClick = allowClick

    state.stateList.forEachIndexed { index, player ->
      when(player){
        PLAYER_X_MAXI -> itemViewList[index].setImageResource(R.drawable.ic_tic_tac_toe_x_24dp)
        PLAYER_O_MINI -> itemViewList[index].setImageResource(R.drawable.ic_tic_tac_toe_o)
        PLAYER_EMPTY -> itemViewList[index].setImageDrawable(null)
      }
    }
  }

  fun setListener(listener: TicTacToeClickListener){
    this.listener = listener
  }

  private fun onClickTicTacToeItem(position : Int){
    if(currentState == null) return

    val clickPlayer = currentState!!.stateList[position]

    when(clickPlayer){
      PLAYER_EMPTY -> {
        if(allowClick){
          var currentPlayer = currentState!!.lastPlayedPlayer
          when(currentState!!.lastPlayedPlayer){
            PLAYER_X_MAXI -> currentPlayer = PLAYER_O_MINI
            PLAYER_O_MINI -> currentPlayer = PLAYER_X_MAXI
            PLAYER_EMPTY -> currentPlayer = PLAYER_O_MINI
          }

          val newStatList = currentState!!.stateList.copyOf()
          newStatList[position] = currentPlayer

          if(listener != null){
            listener!!.afterClickState(
              State(
                lastPlayedPlayer = currentPlayer,
                stateList = newStatList
              )
            )
          }
        }
      }
    }
  }

  interface TicTacToeClickListener{
    fun afterClickState(state: State)
  }
}