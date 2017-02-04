package com.sergey.myeditor.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.sergey.myeditor.MyApplication;
import com.sergey.myeditor.data.model.Field;
import com.sergey.myeditor.ui.adapter.MyRecycleViewAdapter;
import com.sergey.myeditor.R;
import com.sergey.myeditor.ui.presenter.MainViewFragmentPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by sergey on 27.01.17.
 */

public class MainViewFragment extends Fragment implements MainViewFragmentPresenter.MainViewFragmentUI {

    public static final String TAG = MainViewFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    public static final int REQUEST = 199;
    private MyRecycleViewAdapter myRecycleViewAdapter;

    @Inject
    protected MainViewFragmentPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.content_main, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.board);
        progressBar = (ProgressBar) root.findViewById(R.id.progress);
        return root;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setRecycleChildrenOnDetach(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.findViewHolderForLayoutPosition(0);
        myRecycleViewAdapter = new MyRecycleViewAdapter(getActivity(), recyclerView, MainViewFragment.this::startItem);
        recyclerView.setAdapter(myRecycleViewAdapter);
        progressBar.setVisibility(View.VISIBLE);

    }


    private void startItem(Field field) {
        Intent intent = new Intent(getActivity(), EditActivity.class);
        intent.putExtra("Id", field.getId());
        intent.putExtra("title", field.getTitle());
        intent.putExtra("check", field.isCompleted());
        intent.putExtra("userId", field.getUserId());
        startActivityForResult(intent, REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST){

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attacheUI(this);
        presenter.fillData();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachUI();
    }

    @Override
    public void fillUI(List<Field> fields) {
        progressBar.setVisibility(View.GONE);
        myRecycleViewAdapter.setFields(fields);
        myRecycleViewAdapter.notifyDataSetChanged();
    }
}
