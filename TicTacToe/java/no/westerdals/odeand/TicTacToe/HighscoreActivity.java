package no.westerdals.odeand.TicTacToe;

// Created by Andreas Ødegaard on 26.03.2017.


import android.support.v4.app.Fragment;

public class HighscoreActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new HighscoreFragment();
    }
}
