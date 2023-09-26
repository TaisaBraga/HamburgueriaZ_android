package com.example.hamburgueriaz;

import static android.content.Intent.ACTION_SENDTO;
import static android.content.Intent.EXTRA_EMAIL;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextView amountValueTextView;
    private CheckBox baconCheckBox;
    private CheckBox cheeseCheckBox;
    private CheckBox onionRingCheckBox;
    private TextView totalOrderValueTextView;
    private Button orderButton, addValue, reduceValue;

    private int quantity = 0;
    private double totalOrderValue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar as views
        amountValueTextView = findViewById(R.id.amountValue);
        baconCheckBox = findViewById(R.id.baconCheckBox);
        cheeseCheckBox = findViewById(R.id.cheeseCheckbox);
        onionRingCheckBox = findViewById(R.id.onionRingCheckBox);
        totalOrderValueTextView = findViewById(R.id.totalOrderValue);
        orderButton = findViewById(R.id.orderButton);
        addValue = findViewById(R.id.addValue);
        reduceValue = findViewById(R.id.reduceValue);

        setupListeners();
    }

    private void setupListeners() {
        orderButton.setOnClickListener(view -> enviarPedido());
        addValue.setOnClickListener(view -> somar());
        reduceValue.setOnClickListener(view -> subtrair());
    }

    // Função para somar a quantidade
    public void somar() {
        if (quantity >= 0) {
            quantity++;
            atualizarQuantidade(quantity);
        }
    }

    // Função para subtrair a quantidade
    public void subtrair() {
        if (quantity > 0) {
            quantity--;
            atualizarQuantidade(quantity);
        }
    }

    // Função para atualizar a quantidade na view
    private void atualizarQuantidade(int quantity) {
        amountValueTextView.setText(String.valueOf(quantity));
    }

    // Função para calcular o valor total do pedido
    private void calcularTotal() {
        totalOrderValue = (quantity * 20.00) +
                (baconCheckBox.isChecked() ? 2.00 : 0.00) +
                (cheeseCheckBox.isChecked() ? 2.00 : 0.00) +
                (onionRingCheckBox.isChecked() ? 3.00 : 0.00);

    }

    // Função para enviar o pedido
    public void enviarPedido() {
        calcularTotal();

        TextInputEditText userName = findViewById(R.id.inputText);
        StringBuilder pedidoResumo = new StringBuilder();
        pedidoResumo.append("Nome: ").append(userName.getText()).append("\n");
        pedidoResumo.append("Quantidade: ").append(quantity).append("\n");

        if (baconCheckBox.isChecked()) {
            pedidoResumo.append("Adicional: Bacon\n");
        }
        if (cheeseCheckBox.isChecked()) {
            pedidoResumo.append("Adicional: Queijo\n");
        }
        if (onionRingCheckBox.isChecked()) {
            pedidoResumo.append("Adicional: Onion Rings\n");
        }

        pedidoResumo.append("Valor Total: R$ ").append(totalOrderValue);

        totalOrderValueTextView.setText(pedidoResumo.toString());

        composeEmail("loja@email.com.br", "Pedido de " + userName.getText(), pedidoResumo.toString());
    }

    public void composeEmail(String email, String subject, String message) {

        final Intent result = new Intent(android.content.Intent.ACTION_SEND);
        result.setType("plain/text");
        result.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { email });
        result.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        result.putExtra(android.content.Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(result, "Send mail..."));
    }
}
