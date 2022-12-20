package com.example.manipulandopastas;

import android.app.Application;

import com.example.manipulandopastas.DAO.AlunoDAO;
import com.example.manipulandopastas.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosTestes();

    }

    private void criaAlunosTestes() {
        AlunoDAO dao=new AlunoDAO();
        dao.salvar(new Aluno("joao","1234","je@htmali"));
        dao.salvar(new Aluno("richard","1234","je@htmali"));
        dao.salvar(new Aluno("maria","1234","je@htmali"));
    }
}
