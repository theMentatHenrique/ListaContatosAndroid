package com.example.manipulandopastas.DAO;

import androidx.annotation.Nullable;

import com.example.manipulandopastas.model.Aluno;
import java.util.List;
import java.util.ArrayList;

public class AlunoDAO {
    private static int incrementId=1;
    private final static List<Aluno> alunos=new ArrayList<>();
    public void salvar(Aluno aluno) {
        aluno.setId(incrementId);
        alunos.add(aluno);
        incrementaId();
    }

    private void incrementaId() {
        incrementId++;
    }

    public static void editaAluno(Aluno aluno){
        Aluno encontrado = encontraAluno(aluno);
        if(encontrado!=null){
            int posicao=alunos.indexOf(encontrado);
            alunos.set(posicao,aluno);
        }
    }

    @Nullable
    private static Aluno encontraAluno(Aluno aluno) {
        for (Aluno a:alunos) {
            if(a.getId()== aluno.getId()){return a;}

        }
        return null;
    }

    public List<Aluno> todos(){
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno encontrado=encontraAluno(aluno);
        if(aluno!=null){alunos.remove(encontrado);}

    }
}
