package com.daffzzaqihaq.fofootballmvp.ui.teams;

import com.daffzzaqihaq.fofootballmvp.model.TeamsItem;

import java.util.List;

public interface TeamsContract {

    interface View{
        void showProgress();
        void hideProgress();
        void showDataList(List<TeamsItem> teamsItemList);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getDataListTeam();
        void getSearchTeams(String searchText );

    }
}
