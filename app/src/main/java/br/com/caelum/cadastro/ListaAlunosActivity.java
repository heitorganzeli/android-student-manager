package br.com.caelum.cadastro;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.database.AlunoDao;
import br.com.caelum.cadastro.model.Aluno;

import static android.R.id.list;
import static android.widget.AdapterView.*;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);


         listaAlunos = (ListView) findViewById(R.id.lista_alunos);



        final Context self = this;

        listaAlunos.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View item, int position, long id) {
                Toast.makeText(self, "position " + position, Toast.LENGTH_SHORT).show();
            }
        });

        listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                Aluno aluno = (Aluno) adapter.getItemAtPosition(position);
                Toast.makeText(self, "aluno: " + aluno, Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.list_add);

        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent form = new Intent(self, FormularioActivity.class);
                startActivity(form);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAlunos();
    }

    private void loadAlunos() {
        CadastroApplication app = (CadastroApplication) getApplication();
        AlunoDao dao = app.getAlunoDao();

        List<Aluno> alunos = dao.getAll();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);

        listaAlunos.setAdapter(adapter);

    }
}
