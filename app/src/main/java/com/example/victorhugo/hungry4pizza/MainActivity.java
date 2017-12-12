package com.example.victorhugo.hungry4pizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.victorhugo.hungry4pizza.model.Pedido;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtUsuario)
    TextView txtUsuario;

    @BindView(R.id.cbBacon)
    CheckBox cbBacon;

    @BindView(R.id.cbAtum)
    CheckBox cbAtum;

    @BindView(R.id.cbChampignon)
    CheckBox cbChampignon;

    @BindView(R.id.cbChocolate)
    CheckBox cbChocolate;

    @BindView(R.id.btFecharPedido)
    Button btFecharPedido;

    @BindView(R.id.rgTamanho)
    RadioGroup rgTamanho;

    @BindView(R.id.spPagamentos)
    Spinner spPagamentos;

    Pedido pedido = new Pedido();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        INICIALIZA O COMPONENTE BUTTER KNIFE
        ButterKnife.bind(this);

        if (getIntent() != null) {
            txtUsuario.setText("Olá, " + getIntent().getStringExtra("USUARIO"));
        }

        setListenerCheckbox(cbAtum);
        setListenerCheckbox(cbBacon);
        setListenerCheckbox(cbChampignon);
        setListenerCheckbox(cbChocolate);

    }

    private void setListenerCheckbox(final CheckBox checkbox) {
//        LISTENER PARA O CHECKBOX
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                CASO O CHECKBOX ESTEJA CHECKED
                if (isChecked) {
//                    ADICIONA O SABOR NO PEDIDO
                    pedido.addSabor(checkbox.getText().toString());
                } else {
//                    REMOVE O SABOR NO PEDIDO
                    pedido.removerSabor(checkbox.getText().toString());
                }
            }
        });
    }

    @OnClick(R.id.btFecharPedido)
    public void fecharPedido() {
        pedido.setCliente(getIntent().getStringExtra("USUARIO"));
        pedido.setTamanho(getTamanhoSelecionado());
        pedido.setFormaPgto(spPagamentos.getSelectedItem().toString());

        Intent i = new Intent(this, ConfirmarPedidoActivity.class);
        i.putExtra("PEDIDO", pedido);
        startActivity(i);
    }

//    RETORNA O TAMANHO SELECIONADO
    public String getTamanhoSelecionado() {
        return ((RadioButton) findViewById(rgTamanho.getCheckedRadioButtonId())).getText().toString();
    }
}
