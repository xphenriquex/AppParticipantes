package com.example.sauderesidence.appteste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import com.example.sauderesidence.dao.ParticipanteDao;
import com.example.sauderesidence.modelo.Participante;

public class ListaParticipantes extends AppCompatActivity {
    private ListView listaParticipantes;
    private ArrayAdapter<Participante> adapter;
    private Participante participante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_participantes);


        //pegando o id da listview
        this.listaParticipantes = (ListView) findViewById(R.id.lista_participantes);


        registerForContextMenu(listaParticipantes);

        //sobrescrevendo o onItemClick da inner class
        this.listaParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 participante = (Participante) adapter.getItem(i);

                Toast.makeText(ListaParticipantes.this, "Participante :" + participante.getNome(), Toast.LENGTH_SHORT).show();
            }
        });


        //sobrescrevendo o setOnItemLong da inner class
        this.listaParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                participante = (Participante) adapter.getItem(i);

                return false;
            }
        });



        Button botaoAdiciona = (Button) findViewById(R.id.bt_flutuantuante_lista_participante);

        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListaParticipantes.this, FormularioActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inflate, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.novo:
                Intent intent = new Intent(ListaParticipantes.this, FormularioActivity.class);
                startActivity(intent);
                break;

            case R.id.preferencias:
                Toast.makeText(this, "está em constr ução", Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        carregarListaParticipantes();

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("Ver mapa");
        menu.add("Tirar foro");

        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ParticipanteDao dao = new ParticipanteDao(ListaParticipantes.this);
                dao.deletar(participante);
                dao.close();
                carregarListaParticipantes();

                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void carregarListaParticipantes(){
        ParticipanteDao dao = new ParticipanteDao(this);

        List<Participante> participantes = dao.getLista();

        adapter = new ArrayAdapter<Participante>(this, android.R.layout.simple_list_item_1, participantes);

        //setando o adpter
        this.listaParticipantes.setAdapter(adapter);
    }
}
