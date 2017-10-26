package no.westerdals.odeand.TicTacToe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameFragment extends Fragment {
    private Random mRand;
    private ImageAdapter mAdapter;
    private GridView mGridView;
    private TextView mTxtPlayer1, mTxtPlayer2;
    private Button mResetButton, mHighscoreButton, mNewGameButton;
    private Player playerOne, playerTwo;
    private Vibrator vibrator;
    private boolean finished;
    private int turn;

    public int getRand() {
        return mRand.nextInt(9);
    }

    public void makeMove(Player player, int position) {
        if (player.equals(playerOne)) {
            mAdapter.mThumbsIds[position] = R.drawable.o2;
        } else {
            mAdapter.mThumbsIds[position] = R.drawable.x;
        }
        player.getPlayerMoves().add(position);
        mAdapter.notifyDataSetChanged();
        vibrator.vibrate(25);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game, container, false);
        super.onCreate(savedInstanceState);

        vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

        Bundle bundle = getActivity().getIntent().getExtras();
        playerOne = (Player) bundle.getSerializable("player1");
        playerTwo = (Player) bundle.getSerializable("player2");
        
        mRand = new Random();
        mAdapter = new ImageAdapter(getContext());
        finished = false;
        turn = 0;


        mTxtPlayer1 = (TextView) view.findViewById(R.id.name_player_one);
        mTxtPlayer2 = (TextView) view.findViewById(R.id.name_player_two);
        mGridView = (GridView) view.findViewById(R.id.gridview);

        mTxtPlayer1.setText(playerOne.getName() + " : " + playerOne.getScore());
        mTxtPlayer2.setText(playerTwo.getName() + " : " + playerTwo.getScore());

        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (finished || (playerOne.getPlayerMoves().contains(position) || playerTwo.getPlayerMoves().contains(position))) return;

                if (playerOne.isSinglePlayer()) {
                    makeMove(playerOne, position);
                    if (!WinCondition.hasWon(playerOne) && (playerOne.getPlayerMoves().size() + playerTwo.getPlayerMoves().size()) < 8) androidMakeMove();
                } else {
                    if (turn % 2 == 0) {
                        makeMove(playerOne, position);
                    } else {
                        makeMove(playerTwo, position);
                    }
                }

                if (WinCondition.hasWon(playerOne)) {
                    vibrator.vibrate(500);
                    Toast.makeText(getContext(), playerOne.getName() + " has won!", Toast.LENGTH_SHORT).show();
                    playerOne.incrementScore();
                    finished = true;
                    mTxtPlayer1.setText(playerOne.getName() + " : " + playerOne.getScore());
                    return;
                }


                if (WinCondition.hasWon(playerTwo)) {
                    vibrator.vibrate(500);
                    Toast.makeText(getContext(), playerTwo.getName() + " has won!", Toast.LENGTH_SHORT).show();
                    playerTwo.incrementScore();
                    mTxtPlayer2.setText(playerTwo.getName() + " : " + playerTwo.getScore());
                    finished = true;
                    return;
                }

                if ((playerOne.getPlayerMoves().size() + playerTwo.getPlayerMoves().size()) > 8) {
                    Toast.makeText(getContext(), "Uavgjort!", Toast.LENGTH_SHORT).show();
                    finished = true;
                }

                turn++;
            }
        });

        mResetButton = (Button) view.findViewById(R.id.resetButton);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < mAdapter.mThumbsIds.length; i++) {
                    mAdapter.mThumbsIds[i] = R.drawable.frame;
                }
                mAdapter.notifyDataSetChanged();
                playerOne.getPlayerMoves().clear();
                playerTwo.getPlayerMoves().clear();
                finished = false;
                turn = 0;
            }
        });

        mHighscoreButton = (Button) view.findViewById(R.id.highscoreButton);
        mHighscoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), HighscoreActivity.class);
                intent.putExtra("player1", playerOne);
                intent.putExtra("player2", playerTwo);
                getActivity().finish();
                startActivity(intent);
            }
        });

        mNewGameButton = (Button) view.findViewById(R.id.newGameButton);
        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });

        return view;
    }

    private void androidMakeMove() {
        int randomPosition = getRand();

        while ((playerOne.getPlayerMoves().contains(randomPosition) || playerTwo.getPlayerMoves().contains(randomPosition))
                &&  turn < 9) {
            randomPosition = getRand();
        }

        if (playerOne.getPlayerMoves().size() < 5 && playerTwo.getPlayerMoves().size() != 4 ) makeMove(playerTwo, randomPosition);
    }
}
