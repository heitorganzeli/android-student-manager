package br.com.caelum.cadastro;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
                Aluno student = (Aluno) listaAlunos.getItemAtPosition(position);
                Intent edit = new Intent(self, FormularioActivity.class);
                edit.putExtra("student", student);

                startActivity(edit);
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
        Permission.checkPermissions(this);

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

        MenuItem call = menu.add("Ligar");
        MenuItem sms = menu.add("Enviar SMS");
        MenuItem map = menu.add("Achar no Mapa");
        MenuItem site = menu.add("Navegar no Site");
        MenuItem delete = menu.add("Deletar");

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

        final Aluno selectedStudent = (Aluno) listaAlunos.getItemAtPosition(info.position);

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + selectedStudent.getPhone()));
        call.setIntent(callIntent);

        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("sms:" + selectedStudent.getPhone()));
        smsIntent.putExtra("sms_body", "voce foi matriculado");
        sms.setIntent(smsIntent);

        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(Uri.parse("geo:0,0?q=" + selectedStudent.getAddress()));
        map.setIntent(mapIntent);


        Intent siteIntent = new Intent(Intent.ACTION_VIEW);
        String urlString = selectedStudent.getSite();
        if (!urlString.startsWith("http://")) {
            urlString = "http://" + urlString;
        }
        siteIntent.setData(Uri.parse(urlString));
        site.setIntent(siteIntent);



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
