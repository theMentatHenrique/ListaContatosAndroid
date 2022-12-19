package com.example.manipulandopastas.activity.ui;

import static com.example.manipulandopastas.activity.ui.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.manipulandopastas.DAO.AlunoDAO;
import com.example.manipulandopastas.R;
import com.example.manipulandopastas.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final AlunoDAO aluno = new AlunoDAO();
    private final String TITULO_APP_BAR="Agenda";
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_APP_BAR);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 20; i++) {
            aluno.salvar(new Aluno("joao","1234","henri@gmail"));
            aluno.salvar(new Aluno("maria","1234","henri@gmail"));

        }

        btnAdicionar();
        configuraActivity();

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu,menu);
    }

    private void btnAdicionar() {
        FloatingActionButton addAluno=findViewById(R.id.fab_aluno);

        addAluno.setOnClickListener((View)->{
            abreformularioInsercao();
        });
    }

    private void abreformularioInsercao() {
        startActivity(new Intent(
                this,
                Formulario_alunos.class
        ));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(aluno.todos());
    }

    private void configuraActivity() {
        ListView listaAlunos = findViewById(R.id.activity_main_lista_alunos);
        //final List<Aluno> lista=aluno.todos();
        configuraadapter(listaAlunos);
        configuraClickDeListenerPorItem(listaAlunos);registerForContextMenu(listaAlunos);


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if(itemId==R.id.acitivyti_main_remover){
            AdapterView.AdapterContextMenuInfo menuInfo=(AdapterView.AdapterContextMenuInfo)
                    item.getMenuInfo();
            Aluno alunoEscolhido=adapter.getItem(menuInfo.position);
            remove(alunoEscolhido);
        }
        return super.onContextItemSelected(item);

    }
    /*
    private void configuraListernerLongClick(ListView listaAlunos) {
        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Log.i("chguei aqui",String.valueOf(pos));
                Aluno selecionado= (Aluno) adapterView.getItemAtPosition(pos);
                remove(selecionado);
                return false;
            }
        });
    }

     */


    private void remove(Aluno selecionado) {
        aluno.remove(selecionado);
        adapter.remove(selecionado);
    }

    private void configuraClickDeListenerPorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Aluno selecionado= (Aluno) adapterView.getItemAtPosition(pos);
                abreformularioedicao(selecionado);
            }
        });
    }

    private void abreformularioedicao(Aluno selecionado) {
        Intent vaiPraFormulario=new Intent(MainActivity.this,Formulario_alunos.class);
        vaiPraFormulario.putExtra(CHAVE_ALUNO, selecionado);
        startActivity(vaiPraFormulario);
    }

    private void configuraadapter(ListView listaAlunos) {
        listaAlunos.setAdapter(
                adapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1)
        );
        listaAlunos.setAdapter(adapter);
    }
}
