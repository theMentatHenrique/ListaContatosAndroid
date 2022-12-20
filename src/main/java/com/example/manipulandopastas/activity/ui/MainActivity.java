package com.example.manipulandopastas.activity.ui;

import static com.example.manipulandopastas.activity.ui.ConstantesActivities.CHAVE_ALUNO;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.manipulandopastas.R;
import com.example.manipulandopastas.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static final String TITULO_APP_BAR="Contatos";

    private final ListaAlunosView listaAlunosView=new ListaAlunosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_APP_BAR);
        setContentView(R.layout.activity_main);
        Log.i("passei por aqui","aquiiiiiiiiiiiiiis");
        configuraFABNovoAluno();
        configuraLista();


    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu,menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull  MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==R.id.acitivyti_main_remover){
            listaAlunosView.confirmaExclusao(item);

        }
        return super.onContextItemSelected(item);
    }



    private void configuraFABNovoAluno() {
        FloatingActionButton addAluno=findViewById(R.id.fab_aluno);

        addAluno.setOnClickListener((View)->{
            abreformularioModoInsereAluno();
        });
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_main_lista_alunos);
        listaAlunosView.configuraAdapter(listaAlunos);
        configuraListenerDeCliquePorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void abreformularioModoInsereAluno() {
        startActivity(new Intent(
                this,
                Formulario_alunos.class
        ));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();
    }




    private void configuraListenerDeCliquePorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Aluno selecionado= (Aluno) adapterView.getItemAtPosition(pos);
                abreformulariomodoEditaAluno(selecionado);
            }
        });
    }

    private void abreformulariomodoEditaAluno(Aluno selecionado) {
        Intent vaiPraFormulario=new Intent(MainActivity.this,Formulario_alunos.class);
        vaiPraFormulario.putExtra(CHAVE_ALUNO, selecionado);
        startActivity(vaiPraFormulario);
    }


}
