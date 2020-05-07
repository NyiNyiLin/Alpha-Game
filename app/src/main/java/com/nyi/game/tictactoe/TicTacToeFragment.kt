package com.nyi.game.tictactoe

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.nyi.game.databinding.TicTacToeFragmentBinding
import com.nyi.game.databinding.ViewTicTacToeBinding
import com.nyi.game.tictactoe.PlayerState.COMPUTER_TURN
import com.nyi.game.tictactoe.PlayerState.DRAW
import com.nyi.game.tictactoe.PlayerState.O_WIN
import com.nyi.game.tictactoe.PlayerState.USER_TURN
import com.nyi.game.tictactoe.PlayerState.X_WIN
import com.nyi.game.tictactoe.TicTacToeView.TicTacToeClickListener
import com.nyi.game.tictactoe.model.Player
import com.nyi.game.tictactoe.model.Player.PLAYER_EMPTY
import com.nyi.game.tictactoe.model.State
import kotlinx.android.synthetic.main.tic_tac_toe_fragment.btnRetry

class TicTacToeFragment : Fragment() {

  companion object {
    fun newInstance() = TicTacToeFragment()
  }

  private lateinit var viewModel: TicTacToeViewModel

  private val binding by lazy {
    TicTacToeFragmentBinding.inflate(layoutInflater)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // inflater.inflate(R.layout.tic_tac_toe_fragment, container, false)
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(TicTacToeViewModel::class.java)

    binding.tictactoeView.setListener(listener = object : TicTacToeClickListener{
      override fun afterClickState(state: State) {
        viewModel.onPlayClick(state)
      }
    })

    binding.switchCompact.setOnCheckedChangeListener { buttonView, isChecked ->
      viewModel.onClickRetry(isChecked)
    }

    binding.btnRetry.setOnClickListener {
      viewModel.onClickRetry(binding.switchCompact.isChecked)
    }

    viewModel.currentState.observe(viewLifecycleOwner, Observer {
      binding.tictactoeView.setState(it.first, it.second)
    })

    viewModel.playingState.observe(viewLifecycleOwner, Observer {
      when(it){
        X_WIN -> {
          binding.tvTitle.text = "Computer Win!!"
          btnRetry.visibility = View.VISIBLE
        }
        O_WIN -> {
          binding.tvTitle.text = "You Win!!"
          btnRetry.visibility = View.VISIBLE
        }
        DRAW -> {
          binding.tvTitle.text = "Draw"
          btnRetry.visibility = View.VISIBLE
        }
        COMPUTER_TURN -> {
          binding.tvTitle.text = "Computer Turn"
          btnRetry.visibility = View.GONE
        }
        USER_TURN -> {
          binding.tvTitle.text = "Your Turn"
          btnRetry.visibility = View.GONE
        }
      }
    })

  }

}
