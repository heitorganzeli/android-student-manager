package br.com.caelum.cadastro;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
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

        FloatingActionButton addButton = findViewById(R.id.list_add);

        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent form = new Intent(self, FormularioActivity.class);
                startActivity(form);

            }
        });


        registerForContextMenu(listaAlunos);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAlunos();
    }

    private void loadAlunos() {
        AlunoDao dao = getAlunoDao();

        List<Aluno> alunos = dao.getAll();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);

        listaAlunos.setAdapter(adapter);
    }

    private AlunoDao getAlunoDao() {
        CadastroApplication app = (CadastroApplication) getApplication();
        return app.getAlunoDao();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add("Ligar");
        menu.add("Enviar SMS");
        menu.add("Achar no Mapa");
        menu.add("Navegar no Site");
        MenuItem delete = menu.add("Deletar");

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

        final Aluno selectedStudent = (Aluno) listaAlunos.getItemAtPosition(info.position);

        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Alert")
                        .setMessage("Deseja mesmo deletar?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlunoDao dao = getAlunoDao();
                                dao.delete(selectedStudent);
                                loadAlunos();
                            }
                        })
                        .setNegativeButton("Nao", null).show();

                return false;
            }
        });


    }
}
