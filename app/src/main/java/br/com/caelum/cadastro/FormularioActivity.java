package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.caelum.cadastro.database.AlunoDao;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.model.Aluno;

/**
 * Created by android7887 on 24/09/18.
 */

public class FormularioActivity extends AppCompatActivity {
    private FormularioHelper helper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.helper = new FormularioHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.form_menu_save:
                Aluno aluno = helper.createAlunoFromForm();

                if (helper.verify()) {
                    save(aluno);
                    Toast.makeText(FormularioActivity.this, "aluno salvo: " + aluno.getName(), Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    helper.showError();
                }
                return true;
            case android.R.id.home:
                Toast.makeText(FormularioActivity.this, "descartado", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void save(Aluno aluno) {
        CadastroApplication app = (CadastroApplication) getApplication();
        AlunoDao alunoDao = app.getAlunoDao();

        alunoDao.insert(aluno);
    }
}
