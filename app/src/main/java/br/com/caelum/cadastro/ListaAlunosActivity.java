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

import static android.R.id.list;
import static android.widget.AdapterView.*;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] alunos = {"Anderson", "Filipe", "Guilherme"};

        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);

        listaAlunos.setAdapter(adapter);


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
                String aluno = (String) adapter.getItemAtPosition(position);
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
}
