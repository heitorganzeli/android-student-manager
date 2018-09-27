package br.com.caelum.cadastro;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

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
                .addMigrations(migrationFrom1to2())
                .build();

        alunoDao = ((CadastroDatabase) database).getAlunoDao();

    }

    @NonNull
    private Migration migrationFrom1to2() {
        return new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {

                String sql = "alter table Aluno add column picturePath text";
                database.execSQL(sql);
            }
        };
    }

    public AlunoDao getAlunoDao() {
        return alunoDao;
    }
}
