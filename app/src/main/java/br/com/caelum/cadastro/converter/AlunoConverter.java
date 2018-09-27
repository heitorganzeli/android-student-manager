package br.com.caelum.cadastro.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.caelum.cadastro.model.Aluno;

public class AlunoConverter {
    public String toJSON(List<Aluno> alunos) {
        JSONStringer jsonStringer = new JSONStringer();
        try {
            jsonStringer.object().key("list").array().object().key("aluno").array();
            for (Aluno aluno : alunos) {
                jsonStringer.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getName())
                        .key("telefone").value(aluno.getPhone())
                        .key("endereco").value(aluno.getAddress())
                        .key("site").value(aluno.getSite())
                        .key("nota").value(aluno.getRating())
                        .key("caminhoFoto").value(aluno.getPicturePath())
                        .endObject();
            }
            jsonStringer.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonStringer.toString();
    }
}