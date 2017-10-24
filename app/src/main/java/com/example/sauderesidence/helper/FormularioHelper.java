package com.example.sauderesidence.helper;

import android.widget.EditText;

import com.example.sauderesidence.appteste.FormularioActivity;
import com.example.sauderesidence.appteste.R;

import com.example.sauderesidence.modelo.Participante;

/**
 * Created by sauderesidence on 23/10/17.
 */

public class FormularioHelper {
    private  EditText campoNome;
    private  EditText campoEmail;
    private  EditText campoTelefone;


    public FormularioHelper(FormularioActivity activity){

        campoNome = (EditText) activity.findViewById(R.id.edt_nome);
        campoEmail = (EditText) activity.findViewById(R.id.edt_email);
        campoTelefone = (EditText) activity.findViewById(R.id.edt_telefone);

    }

    public Participante getParticipanteDoFormulario(){
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String telefone = campoTelefone.getText().toString();

        Participante participante = new Participante();
        participante.setNome(nome);
        participante.setEmail(email);
        participante.setTelefone(telefone);

        return participante;
    }

}
