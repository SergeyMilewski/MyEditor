package com.sergey.myeditor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sergey.myeditor.R;

/**
 * Created by smileuski on 03.02.17.
 */

public class EditActivity extends AppCompatActivity {

    EditText editText;
    Button save;
    Toolbar toolbar;
    AppCompatCheckBox checkBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        editText = (EditText) findViewById(R.id.title);
        save = (Button) findViewById(R.id.button);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        checkBox = (AppCompatCheckBox) findViewById(R.id.checkbox);
        Intent intent = getIntent();
        editText.setText(intent.getStringExtra("title"));
        checkBox.setChecked(intent.getBooleanExtra("check", false));
        setUpActionBar(toolbar, getString(R.string.user, intent.getIntExtra("userId", 0)));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent result = new Intent();
                result.putExtra("title", editText.getText().toString());
                result.putExtra("check", checkBox.isChecked());
                setResult(MainViewFragment.REQUEST, result);
                finish();
            }
        });
    }

    private void setUpActionBar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }


}
