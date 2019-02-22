package com.daffzzaqihaq.fofootballmvp.ui.detail;

import android.content.Context;
import android.os.Bundle;

import com.daffzzaqihaq.fofootballmvp.Utils.Constant;
import com.daffzzaqihaq.fofootballmvp.data.local.FootballDatabase;
import com.daffzzaqihaq.fofootballmvp.model.TeamsItem;
import com.daffzzaqihaq.fofootballmvp.ui.detail.DetailTeamContract;

public class DetailTeamPresenter implements DetailTeamContract.Presenter {

    private final DetailTeamContract.View view;
    private FootballDatabase footballDatabase;

    public DetailTeamPresenter(DetailTeamContract.View view){
        this.view = view;
    }

    @Override
    public void getDetailTeam(Bundle bundle) {
        if (bundle != null){
            TeamsItem teamsItem = (TeamsItem) bundle.getSerializable(Constant.KEY_DATA);
            view.showDetailTeam(teamsItem);
        }else {
            view.showFailureMessage("Data Kosong");
        }

    }

    @Override
    public void addToFavorite(Context context, TeamsItem teamsItem) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        footballDatabase.footballDAO().insertItem(teamsItem);
        view.showSuccessMessage("Tersimpan");

    }

    @Override
    public void removeFavorite(Context context, TeamsItem teamsItem) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        footballDatabase.footballDAO().delete(teamsItem);
        view.showSuccessMessage("Terhapus");

    }

    @Override
    public Boolean checkFavorite(Context context, TeamsItem teamsItem) {
        Boolean bFavorite = false;
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        return bFavorite = footballDatabase.footballDAO().selectedItem(teamsItem.getIdTeams()) != null;
    }
}
