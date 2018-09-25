package br.com.caelum.cadastro.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.caelum.cadastro.model.Aluno;

@Dao
public interface AlunoDao {
    @Insert
    void insert(Aluno aluno);

    @Query("SELECT * FROM Aluno order by name")
    List<Aluno> getAll();
}
