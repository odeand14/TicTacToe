package no.westerdals.odeand.TicTacToe;

// Created by Andreas Ødegaard on 26.03.2017.


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HighscoreFragment extends Fragment {

    private PlayersDataSource dataSource;
    private ListView listView;
    private ArrayAdapter<Player> playerArrayAdapter;
    private List<Player> allPlayers;
    private Button mBackBtn, mNewGameBtn, mDeleteScorelistBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_highscore, container, false);
        super.onCreate(savedInstanceState);


        allPlayers = new ArrayList<>();

        mBackBtn = (Button) view.findViewById(R.id.backToGameButton);
        mNewGameBtn = (Button) view.findViewById(R.id.newGameButton);
        mDeleteScorelistBtn = (Button) view.findViewById(R.id.deleteAllButton);

        dataSource = new PlayersDataSource(getContext());
        dataSource.open();
        allPlayers = new ArrayList<>();

        try {
            Bundle bundle = getActivity().getIntent().getExtras();
            Player playerOne = (Player) bundle.getSerializable("player1");
            Player playerTwo = (Player) bundle.getSerializable("player2");
            dataSource.createPlayer(playerOne);
            dataSource.createPlayer(playerTwo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        allPlayers = dataSource.getTopTwentyPlayers();

        dataSource.close();

        playerArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.simple_list_item, allPlayers);

        listView = (ListView) view.findViewById(R.id.score_listView);
        listView.setAdapter(playerArrayAdapter);



        mNewGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                startActivity(intent);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mDeleteScorelistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSource.close();
                getContext().deleteDatabase(SQLiteHelper.DATABASE_NAME);
                allPlayers.clear();
                playerArrayAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

}
