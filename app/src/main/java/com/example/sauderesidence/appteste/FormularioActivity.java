package com.example.sauderesidence.appteste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sauderesidence.dao.ParticipanteDao;
import com.example.sauderesidence.helper.FormularioHelper;
import com.example.sauderesidence.modelo.Participante;

public class FormularioActivity extends AppCompatActivity {
    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Button botaoInserir = (Button) findViewById(R.id.bt_inserir);
        helper = new FormularioHelper(this);

        botaoInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Participante participanteDoFormulario = helper.getParticipanteDoFormulario();
                ParticipanteDao dao = new ParticipanteDao(FormularioActivity.this);

                dao.inserir(participanteDoFormulario);

                dao.close();

                finish();
            }
        });
    }
}
