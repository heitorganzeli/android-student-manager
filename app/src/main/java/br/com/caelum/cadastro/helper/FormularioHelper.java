package br.com.caelum.cadastro.helper;

import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.model.Aluno;

public class FormularioHelper {


    private final EditText address;
    private final EditText name;
    private final EditText email;
    private final EditText site;
    private final EditText phone;
    private final RatingBar rating;

    public FormularioHelper(FormularioActivity a) {
        this.address = a.findViewById(R.id.form_address);
        this.name = a.findViewById(R.id.form_name);
        this.email = a.findViewById(R.id.form_email);
        this.site = a.findViewById(R.id.form_site);
        this.phone = a.findViewById(R.id.form_phone);
        this.rating = a.findViewById(R.id.form_rating);
    }

    public Aluno createAlunoFromForm() {
        Aluno aluno = new Aluno();

        aluno.setAddress(address.getText().toString());
        aluno.setName(name.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setRating(rating.getRating());
        aluno.setPhone(phone.getText().toString());

        return aluno;
    }
}
