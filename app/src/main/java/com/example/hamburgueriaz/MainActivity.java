package com.example.hamburgueriaz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView amountValueTextView;
    private Button addButton;
    private Button subtractButton;
    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountValueTextView = findViewById(R.id.amountValue);
        addButton = findViewById(R.id.addValue);
        subtractButton = findViewById(R.id.reduceValue);

        // Configurar os ouvintes de clique para os botões
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementQuantity();
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementQuantity();
            }
        });
    }

    // Função para incrementar a quantidade
    private void incrementQuantity() {
        if (quantity >= 0) {
            quantity++;
            updateQuantityTextView();
        }
    }

    // Função para decrementar a quantidade
    private void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
            updateQuantityTextView();
        }
    }

    // Atualiza o TextView da quantidade com o valor atual
    private void updateQuantityTextView() {
        amountValueTextView.setText(String.valueOf(quantity));
    }
}
