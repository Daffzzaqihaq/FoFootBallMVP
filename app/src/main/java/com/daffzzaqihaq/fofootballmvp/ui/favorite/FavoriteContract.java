package com.daffzzaqihaq.fofootballmvp.ui.favorite;

import android.content.Context;

import com.daffzzaqihaq.fofootballmvp.model.TeamsItem;

import java.util.List;

public interface FavoriteContract {

    interface View{
        void showDataList(List<TeamsItem> teamsItemList);
        void showFailureMessage(String msg);
        void hideRefresh();
    }

    interface Presenter{
        void getDataListTeams(Context context);
        void searchTeams(Context context, String searchText);
    }
}
