package com.example.w2s;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.w2s.API_Repo.APIClient;
import com.example.w2s.Adapters.CheckListAdapter;
import com.example.w2s.Adapters.CloseListAdapter;
import com.example.w2s.Interfaces.ApiInterface;
import com.example.w2s.Interfaces.RecyclerClickFunctions;
import com.example.w2s.Models.Info;
import com.example.w2s.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerClickFunctions {

    ApiInterface apiInterface;
    CheckListAdapter checkListAdapter;
    CloseListAdapter closeListAdapter;
    ActivityMainBinding activityMainBinding;
    List<Info> chkBoxList;
    List<Info> closeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        chkBoxList=new ArrayList<>();
        closeList=new ArrayList<>();

        apiInterface= APIClient.getClient().create(ApiInterface.class);
        getDatas();

        closeListAdapter=new CloseListAdapter(closeList,MainActivity.this,MainActivity.this);
        activityMainBinding.hlist.setAdapter(closeListAdapter);

    }

    public void getDatas(){

        try {
            Call<List<Info>> call=apiInterface.getDatas();

            call.enqueue(new Callback<List<Info>>() {
                @Override
                public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {
                    Log.d("Check",response.body().toString());
                    chkBoxList=response.body();
                    checkListAdapter=new CheckListAdapter(chkBoxList,MainActivity.this,MainActivity.this);
                    activityMainBinding.vlist.setAdapter(checkListAdapter);

                }

                @Override
                public void onFailure(Call<List<Info>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Server Issue, Try After Later", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onCheckboxClick(int position) {

            Toast.makeText(this, "clicked on "+position, Toast.LENGTH_SHORT).show();
            Info frominfo=chkBoxList.get(position);
            Info toinfo=new Info(frominfo.getUserId(),frominfo.getId(),frominfo.getTitle(),frominfo.getBody(),position);
            closeList.add(toinfo);
            closeListAdapter.notifyDataSetChanged();
            chkBoxList.remove(position);
            checkListAdapter.notifyDataSetChanged();


    }

    @Override
    public void onCloseClick(int position) {

        Info frominfo=closeList.get(position);
        chkBoxList.add(frominfo.getPosition(),frominfo);
        checkListAdapter.notifyDataSetChanged();
        closeList.remove(position);
        closeListAdapter.notifyDataSetChanged();
    }


}