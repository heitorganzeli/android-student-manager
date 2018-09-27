package br.com.caelum.cadastro.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Aluno;

public class StudentAdatpter extends BaseAdapter {
    private final List<Aluno> students;
    private final Activity activity;

    public StudentAdatpter(Activity activity, List<Aluno> students) {
        this.students = students;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return students.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();

        View line = layoutInflater.inflate(R.layout.student_item, viewGroup, false);

        TextView name = line.findViewById(R.id.student_item_name);
        TextView email = line.findViewById(R.id.student_item_email);
        ImageView img = line.findViewById(R.id.student_item_picture);

        Aluno student = students.get(i);

        name.setText(student.getName());
        email.setText(student.getEmail());


        Bitmap bitmap;
        if(student.getPicturePath() != null) {
            bitmap = BitmapFactory.decodeFile(student.getPicturePath());
        } else {
            bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
        }

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 100, bitmap.getHeight() * 100 / bitmap.getWidth(), true);
        img.setImageBitmap(scaledBitmap);

        return line;
    }
}
