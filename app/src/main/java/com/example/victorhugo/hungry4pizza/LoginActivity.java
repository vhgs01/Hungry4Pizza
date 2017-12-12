package com.example.victorhugo.hungry4pizza;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    //    FAZ O BIND SOZINHO, USNADO A LIB BUTTER KNIFE (FINDBYID)
    @BindView(R.id.userName)
    TextInputLayout userName;

    //    FAZ O BIND SOZINHO, USNADO A LIB BUTTER KNIFE (FINDBYID)
    @BindView(R.id.userPassword)
    TextInputLayout userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        INICIALIZA O COMPONENTE BUTTER KNIFE
        ButterKnife.bind(this);

//        ADICIONA UM WATCHER PARA O EDIT TEXT
        userName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                CHAMA UMA FUNÇÃO
                validaUsuario();
            }
        });

//        ADICIONA UM WATCHER PARA O EDIT TEXT
        userPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                CHAMA UMA FUNÇÃO
                validaSenha();
            }
        });
    }

//    FAZ A ACÃO DE CLICK DO BOTÃO CONECTAR
    @OnClick(R.id.btConectar)
    public void conectar() {
//        CASO SEJA VALIDADO OS CAMPOS ELE PASSA
        if (validaUsuario() && validaSenha()) {
//            INICIALIZA UMA INTENT PARA A MAIN ACTIVITY
            Intent main = new Intent(this, MainActivity.class);
//            ATRIBUI UM VALOR A INTENT
            main.putExtra("USUARIO", userName.getEditText().getText().toString());
//            CHAMA A INTENT MAIN
            startActivity(main);
        }
    }

//    FUNÇÃO QUE VALIDA A SENHA
    private boolean validaSenha() {
//        VERIFICA SE A SENHA NÃO ESTÁ EM BRANCO
        if (userPassword.getEditText().getText().toString().isEmpty()) {
//            CASO A SENHA ESTEJA EM BRANCO, SETA UM TEXTO DE ERRO
            userPassword.setError(getString(R.string.txtErrorPassword));
            return false;
        } else {
//            CASO A SENHA NÃO ESTEJA EM BRANCO, LIMPA O ERRO
            userPassword.setError(null);
            return true;
        }
    }

//    FUNÇÃO QUE VALIDA O USUÁRIO
    private boolean validaUsuario() {
//        VERIFICA SE O USUÁRIO NÃO ESTÁ EM BRANCO
        if (userName.getEditText().getText().toString().isEmpty()) {
//            CASO O USUÁRIO ESTEJA EM BRANCO, SETA UM TEXTO DE ERRO
            userName.setError(getString(R.string.txtErrorLogin));
            return false;
        } else {
//            CASO O USUÁRIO NÃO ESTEJA EM BRANCO, LIMPA O ERRO
            userName.setError(null);
            return true;
        }
    }
}