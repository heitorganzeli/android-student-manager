package br.com.caelum.cadastro;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by android7887 on 24/09/18.
 */

public class FormularioActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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
                Toast.makeText(FormularioActivity.this, "aluno salvo", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            case android.R.id.home:
                Toast.makeText(FormularioActivity.this, "descartado", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
