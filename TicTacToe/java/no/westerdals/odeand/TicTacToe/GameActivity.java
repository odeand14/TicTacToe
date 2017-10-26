package no.westerdals.odeand.TicTacToe;

// Created by Andreas Ødegaard on 17.03.2017.


import android.support.v4.app.Fragment;

public class GameActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new GameFragment();
    }
}
