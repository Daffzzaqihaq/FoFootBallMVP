package com.daffzzaqihaq.fofootballmvp.ui.favorite;

import android.content.Context;

import com.daffzzaqihaq.fofootballmvp.data.local.FootballDatabase;

import java.util.List;

public class FavoritePresenter implements FavoriteContract.Presenter {

    private final FavoriteContract.View view;
    private FootballDatabase footballDatabase;

    public FavoritePresenter(FavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListTeams(Context context) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        if (footballDatabase.footballDAO().selectFavorite() != null){
            view.showDataList(footballDatabase.footballDAO().selectFavorite());

        }else {
            view.showFailureMessage("Tidak ada data di Favorite");
        }
        view.hideRefresh();


    }
}
