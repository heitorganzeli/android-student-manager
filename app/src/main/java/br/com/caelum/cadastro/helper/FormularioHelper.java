package br.com.caelum.cadastro.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Aluno;

public class FormularioHelper {


    private final EditText address;
    private final EditText name;
    private final TextInputLayout tilName;
    private final EditText email;
    private final EditText site;
    private final EditText phone;
    private final RatingBar rating;
    private final ImageView picture;


    private final FloatingActionButton pictureButton;
    private Aluno student;

    public FormularioHelper(FormularioActivity a) {
        this.address = a.findViewById(R.id.form_address);
        this.name = a.findViewById(R.id.form_name);
        this.tilName = a.findViewById(R.id.form_name_til);
        this.email = a.findViewById(R.id.form_email);
        this.site = a.findViewById(R.id.form_site);
        this.phone = a.findViewById(R.id.form_phone);
        this.rating = a.findViewById(R.id.form_rating);
        this.pictureButton = a.findViewById(R.id.form_upload_picture_button);
        this.picture = a.findViewById(R.id.form_picture);

        this.student = new Aluno();
    }

    public Aluno createAlunoFromForm() {

        student.setAddress(address.getText().toString().trim());
        student.setName(name.getText().toString().trim());
        student.setSite(site.getText().toString().trim());
        student.setEmail(email.getText().toString().trim());
        student.setPhone(phone.getText().toString().trim());
        student.setRating(rating.getRating());

        return student;
    }

    public void loadImage(String picturePath) {
        Bitmap bm = BitmapFactory.decodeFile(picturePath);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bm, bm.getWidth(), bm.getHeight(), true);

        picture.setImageBitmap(scaledBitmap);
    }

    public boolean verify() {
        String name = this.name.getText().toString();
        return !name.trim().isEmpty();
    }

    public void showError() {
        tilName.setError("Campo Obrigatorio");
        name.requestFocus();
    }

    public void show(Aluno student) {
        this.student = student;

        name.setText(student.getName());
        address.setText(student.getAddress());
        site.setText(student.getSite());
        email.setText(student.getEmail());
        phone.setText(student.getPhone());
        rating.setRating(student.getRating());
    }

    public FloatingActionButton getPictureButton() {
        return pictureButton;
    }

}
