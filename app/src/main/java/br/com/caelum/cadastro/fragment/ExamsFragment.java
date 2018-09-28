package br.com.caelum.cadastro.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.activity.ExamsActivity;
import br.com.caelum.cadastro.model.Exam;

public class ExamsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exams_list, container, false);

        Log.e("inflate", "inflating fragment");

        ListView examsList = view.findViewById(R.id.fragment_exams_list);

        ArrayAdapter<Exam> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listExams());

        examsList.setAdapter(adapter);

        examsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long l) {
                Exam exam = (Exam) adapter.getItemAtPosition(position);
                ExamsActivity activity = (ExamsActivity) getActivity();
                activity.selectExam(exam);
            }
        });

        return view;
    }

    private List<Exam> listExams() {
        List<Exam> exams = new ArrayList<>();

        exams.add(new Exam(new Date(), "Android"));
        exams.add(new Exam(new Date(), "IOs"));

        return exams;

    }
}
