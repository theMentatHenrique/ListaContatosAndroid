package com.example.manipulandopastas.activity.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;


import androidx.annotation.NonNull;

import com.example.manipulandopastas.DAO.AlunoDAO;
import com.example.manipulandopastas.activity.adapter.ListaAlunoAdapter;
import com.example.manipulandopastas.model.Aluno;

public class ListaAlunosView {
    private final ListaAlunoAdapter adapter;
    private final Context context;
    private final AlunoDAO dao;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter=new ListaAlunoAdapter(this.context);
         this.dao = new AlunoDAO();
    }

    public void confirmaExclusao(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("removendo usuario").setMessage("Deseja remover o aluno ?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AdapterView.AdapterContextMenuInfo menuInfo=
                                (AdapterView.AdapterContextMenuInfo)
                                        item.getMenuInfo();
                        Aluno alunoEscolhido=adapter.getItem(menuInfo.position);
                        remove(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não",null).show();
    }
    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void remove(Aluno selecionado) {
        dao.remove(selecionado);
        adapter.remove(selecionado);
    }
    public void configuraAdapter(ListView listaAlunos) {
        listaAlunos.setAdapter(adapter);
    }


}
