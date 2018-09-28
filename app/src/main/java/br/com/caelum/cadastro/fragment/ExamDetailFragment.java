package br.com.caelum.cadastro.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Exam;

public class ExamDetailFragment extends Fragment {

    private TextView subject;
    private TextView date;
    private TextView description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View detail = inflater.inflate(R.layout.fragment_exam_detail, container, false);

        fetchFields(detail);

        Exam exam;

        if (getArguments() != null) {
            exam = (Exam) getArguments().getSerializable("exam");
            show(exam);
        }

        return detail;
    }

    private void fetchFields(View detail) {
        subject = detail.findViewById(R.id.exam_detail_subject);
        date = detail.findViewById(R.id.exam_detail_date);
        description = detail.findViewById(R.id.exam_detail_description);
    }

    public void show(Exam exam) {
        subject.setText(exam.getSubject());
        date.setText(exam.getDate().toString());
        description.setText(exam.getDescription());
    }
}
