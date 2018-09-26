package br.com.caelum.cadastro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import br.com.caelum.cadastro.database.AlunoDao;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.model.Aluno;

/**
 * Created by android7887 on 24/09/18.
 */

public class FormularioActivity extends AppCompatActivity {
    private static final int PICTURE_REQUEST = 1;
    private FormularioHelper helper;
    private String picturePath;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.helper = new FormularioHelper(this);

        if(getIntent().hasExtra("student")) {
            Aluno student = (Aluno) getIntent().getSerializableExtra("student");
            helper.show(student);
        }

        helper.getPictureButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                picturePath = getExternalFilesDir("pictures") + "/" + System.currentTimeMillis() + ".jpg";
                camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(picturePath)));

                startActivityForResult(camera, PICTURE_REQUEST);
            }
        });
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
                if (helper.verify()) {
                    Aluno aluno = helper.createAlunoFromForm();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICTURE_REQUEST) {
            if(resultCode == RESULT_OK) {
                helper.loadImage(picturePath);
            }
        }
    }

    private void save(Aluno aluno) {
        CadastroApplication app = (CadastroApplication) getApplication();
        AlunoDao alunoDao = app.getAlunoDao();

        if (aluno.getId() == null) {
            alunoDao.insert(aluno);
        } else {
            alunoDao.update(aluno);
        }

    }
}
