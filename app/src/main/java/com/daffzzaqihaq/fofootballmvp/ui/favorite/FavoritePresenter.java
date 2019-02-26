package com.daffzzaqihaq.fofootballmvp.ui.favorite;

import android.content.Context;

import com.daffzzaqihaq.fofootballmvp.data.local.FootballDatabase;
import com.daffzzaqihaq.fofootballmvp.model.TeamsItem;

import java.util.ArrayList;
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

    @Override
    public void searchTeams(Context context, String searchText) {
        if (!searchText.isEmpty()){
            footballDatabase = FootballDatabase.getFootballDatabase(context);
            // Memasukan semua data favorite ke dalam Variable List
            List<TeamsItem> teamsItemList = footballDatabase.footballDAO().selectFavorite();
            // Membuat penampung untuk menampung data yg sudah kita seleksi berdasarkan inputan user
            List<TeamsItem> mTeamItemList = new ArrayList<>();

            if (teamsItemList != null){
                // Melakukan perulangan untuk mengecek data yang ada di dalam table favorite
                for (TeamsItem data: teamsItemList){
                    // Pengecekan team berdasarkan dengan permintaan User
                    String namaTeam = data.getStrTeam().toLowerCase();
                    if (namaTeam.contains(searchText.toLowerCase())){
                        // Memasukkan Team yang sama dengan inputan user ke dalam wadah ke 2
                        mTeamItemList.add(data);
                    }
                    // Mengirimkan wadah ke dua ke view
                    view.showDataList(mTeamItemList);
                }
            }
        }else {
            // Apabila inputan user kosong ambil dat tanpa keyword
            getDataListTeams(context);
        }

    }
}
