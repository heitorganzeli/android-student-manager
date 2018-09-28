package br.com.caelum.cadastro.activity;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.fragment.ExamDetailFragment;
import br.com.caelum.cadastro.fragment.ExamsFragment;

public class ExamsActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exams_activity);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Log.e("inflate", "transaction");

        if(isLand()) {
            transaction.replace(R.id.exams_activity_frame_left, new ExamsFragment());
            transaction.replace(R.id.exams_activity_frame_right, new ExamDetailFragment());
        }else {
            transaction.replace(R.id.exams_activity_frame, new ExamsFragment());
        }

        transaction.commit();

    }

    private boolean isLand() {
        return getResources().getBoolean(R.bool.isLand);
    }
}
