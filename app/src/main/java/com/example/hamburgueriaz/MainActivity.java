package com.example.hamburgueriaz;


import android.content.Intent;
import android.os.Bundle;
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
    private double totalOrderValue = 0.00;

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
        orderButton.setOnClickListener(view -> sendOrder());
        addValue.setOnClickListener(view -> addQuantity());
        reduceValue.setOnClickListener(view -> reduceQuantity());
    }

    // Função para somar a quantidade
    private void addQuantity() {
        if (quantity >= 0) {
            quantity++;
            updateQuantity(quantity);
        }
    }

    // Função para subtrair a quantidade
    private void reduceQuantity() {
        if (quantity > 0) {
            quantity--;
            updateQuantity(quantity);
        }
    }

    // Função para atualizar a quantidade na view
    private void updateQuantity(int quantity) {
        amountValueTextView.setText(String.valueOf(quantity));
    }

    // Função para calcular o valor total do pedido
    private void calculeTotalOrder() {
        totalOrderValue = (quantity * 20.00) +
                (baconCheckBox.isChecked() ? 2.00 : 0.00) +
                (cheeseCheckBox.isChecked() ? 2.00 : 0.00) +
                (onionRingCheckBox.isChecked() ? 3.00 : 0.00);

    }

    // Função para enviar o pedido
    private void sendOrder() {
        calculeTotalOrder();

        TextInputEditText userName = findViewById(R.id.inputText);
        StringBuilder resumeOrder = new StringBuilder();
        resumeOrder.append("Nome: ").append(userName.getText()).append("\n");
        resumeOrder.append("Quantidade: ").append(quantity).append("\n");

        if (baconCheckBox.isChecked()) {
            resumeOrder.append("Adicional: Bacon\n");
        }
        if (cheeseCheckBox.isChecked()) {
            resumeOrder.append("Adicional: Queijo\n");
        }
        if (onionRingCheckBox.isChecked()) {
            resumeOrder.append("Adicional: Onion Rings\n");
        }

        resumeOrder.append("Valor Total: R$ ").append(totalOrderValue);

        totalOrderValueTextView.setText(resumeOrder.toString());

        composeEmail("hamburgueriaZ@email.com.br", "Pedido de " + userName.getText(), resumeOrder.toString());
    }

    private void composeEmail(String email, String subject, String message) {

        final Intent result = new Intent(android.content.Intent.ACTION_SEND);
        result.setType("plain/text");
        result.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { email });
        result.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        result.putExtra(android.content.Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(result, "Send mail..."));
    }
}
