package no.westerdals.odeand.TicTacToe;

// Created by Andreas Ødegaard on 17.03.2017.


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class PlayerFragment extends Fragment {

    private Player mPlayer1, mPlayer2;
    private Button mStartGame, mHighscoreBtn;
    private ToggleButton mSinglePlayer;
    private EditText mPlayer1Name, mPlayer2Name;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_player, container, false);

        mStartGame = (Button) view.findViewById(R.id.start_button);
        mPlayer1Name = (EditText) view.findViewById(R.id.txtPlayerOne);
        mPlayer2Name = (EditText) view.findViewById(R.id.txtPlayerTwo);
        mPlayer1 = new Player("Player 1", 0, new ArrayList<Integer>());
        mPlayer2 = new Player("Player 2", 0, new ArrayList<Integer>());
        mSinglePlayer = (ToggleButton) view.findViewById(R.id.toggleButton);

        mSinglePlayer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPlayer2Name.setVisibility(View.GONE);
                } else {
                    mPlayer2Name.setVisibility(View.VISIBLE);

                }
            }
        });

        mStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mPlayer1Name.getText().toString().equals("") &&
                        (!mPlayer2Name.getText().toString().equals("") || mSinglePlayer.isChecked())) {
                    mPlayer1.setName(mPlayer1Name.getText().toString());
                } else {
                    Toast.makeText(getContext(), "You need to set name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mSinglePlayer.isChecked()) {
                    mPlayer2.setName("Android");
                    mPlayer1.setSinglePlayer(true);
                    mPlayer2.setSinglePlayer(true);
                } else {
                    mPlayer2.setName(mPlayer2Name.getText().toString());
                }

                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("player1", mPlayer1);
                intent.putExtra("player2", mPlayer2);
                startActivity(intent);

            }
        });

        mHighscoreBtn = (Button) view.findViewById(R.id.highscoreButton);
        mHighscoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HighscoreActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
