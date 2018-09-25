package br.com.caelum.cadastro.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import br.com.caelum.cadastro.model.Aluno;

@Dao
public interface AlunoDao {
    @Insert
    void insert(Aluno aluno);
}
