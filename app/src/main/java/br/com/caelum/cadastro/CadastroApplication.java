package br.com.caelum.cadastro;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import br.com.caelum.cadastro.database.AlunoDao;
import br.com.caelum.cadastro.database.CadastroDatabase;

public class CadastroApplication extends Application {

    private AlunoDao alunoDao;

    @Override
    public void onCreate() {
        super.onCreate();

        RoomDatabase database = Room.databaseBuilder(this,
                CadastroDatabase.class,
                "Cadastro")
                .allowMainThreadQueries()
                .build();

        alunoDao = ((CadastroDatabase) database).getAlunoDao();

    }

    public AlunoDao getAlunoDao() {
        return alunoDao;
    }
}
