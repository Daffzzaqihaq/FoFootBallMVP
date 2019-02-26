package com.daffzzaqihaq.fofootballmvp.ui.teams;

import android.util.Log;

import com.daffzzaqihaq.fofootballmvp.Utils.Constant;
import com.daffzzaqihaq.fofootballmvp.data.remote.APIClient;
import com.daffzzaqihaq.fofootballmvp.data.remote.ApiInterface;
import com.daffzzaqihaq.fofootballmvp.model.TeamResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsPresenter implements TeamsContract.Presenter {

    private final TeamsContract.View view;
    private ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    public TeamsPresenter(TeamsContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListTeam() {
        view.showProgress();

        Call<TeamResponse> call = apiInterface.getAllTeams(Constant.s, Constant.c);
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                view.hideProgress();

                if (response.body() != null){
                    view.showDataList(response.body().getTeams());
                }else {
                    view.showFailureMessage("Data Kosong");
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
                Log.i("Cek", t.getMessage());

            }
        });
    }

    @Override
    public void getSearchTeams(String searchText) {
        // Mencek apakah inputan user ada isinya?
        if (!searchText.isEmpty()){
            // Apabila ada lakukan request API
            view.showProgress();
            Call<TeamResponse> call = apiInterface.getSearchTeams(searchText);
            call.enqueue(new Callback<TeamResponse>() {
                @Override
                public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                    view.hideProgress();
                    if (response.body().getTeams() != null){
                        view.showDataList(response.body().getTeams());
                    }else {
                        view.showFailureMessage("Team is Empty");
                    }
                }

                @Override
                public void onFailure(Call<TeamResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailureMessage(t.getMessage());
                }
            });
        }else {
            // Apabila kosong maka lakukan pengambilan data Team tanpa search
            getDataListTeam();
        }
    }
}
