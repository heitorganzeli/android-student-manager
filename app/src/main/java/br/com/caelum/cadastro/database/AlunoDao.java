package br.com.caelum.cadastro.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.caelum.cadastro.model.Aluno;

@Dao
public interface AlunoDao {
    @Insert
    void insert(Aluno aluno);

    @Query("SELECT * FROM Aluno order by name")
    List<Aluno> getAll();

    @Delete
    void delete(Aluno aluno);

    @Update
    void update(Aluno aluno);

    @Query("select count(*) from Aluno where phone = :phone")
    Integer existsPhone(String phone);
}
