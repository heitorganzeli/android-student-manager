package br.com.caelum.cadastro.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.caelum.cadastro.model.Aluno;

@Database(entities = {Aluno.class}, version = 2)
public abstract class CadastroDatabase extends RoomDatabase {
    public abstract AlunoDao getAlunoDao();
}
