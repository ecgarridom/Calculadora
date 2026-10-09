package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView textPantalla;
    String text = "0";
    double num1 = 0;
    double num2 = 0;
    String operador = "";
    boolean nuevoNumero = false;
    double memoria = 0;

    // Botones especiales
    Button btnMMin, btnMMax, btnMr, btnMc, btnC, btnDecimal;

    // Botones operaciones
    Button btnIgual, btnMult, btnDiv, btnResta, btnSuma;

    // Botones numeros
    Button btn0, btn1, btn2, btn3, btn4;
    Button btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

// Pantalla
        textPantalla = findViewById(R.id.txtPantalla);

        // Botones especiales
        btnMMin = findViewById(R.id.btnMMin);
        btnMMax = findViewById(R.id.btnMMax);
        btnMr = findViewById(R.id.btnMr);
        btnMc = findViewById(R.id.btnMc);
        btnC = findViewById(R.id.btnC);
        btnDecimal = findViewById(R.id.btnDecimal);

        // Botones operaciones
        btnIgual = findViewById(R.id.btnIgual);
        btnMult = findViewById(R.id.btnMult);
        btnDiv = findViewById(R.id.btnDiv);
        btnResta = findViewById(R.id.btnResta);
        btnSuma = findViewById(R.id.btnSuma);

        // Botones numeros
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        // Eventos de los botones numericos
        btn0.setOnClickListener(v -> insertarNum("0"));
        btn1.setOnClickListener(v -> insertarNum("1"));
        btn2.setOnClickListener(v -> insertarNum("2"));
        btn3.setOnClickListener(v -> insertarNum("3"));
        btn4.setOnClickListener(v -> insertarNum("4"));
        btn5.setOnClickListener(v -> insertarNum("5"));
        btn6.setOnClickListener(v -> insertarNum("6"));
        btn7.setOnClickListener(v -> insertarNum("7"));
        btn8.setOnClickListener(v -> insertarNum("8"));
        btn9.setOnClickListener(v -> insertarNum("9"));

        //Evento borrar
        btnC.setOnClickListener(v -> {
            num1 = 0;
            num2 = 0;
            operador = "";
            text = "0";
            textPantalla.setText(text);
            nuevoNumero = false;
        });

        //Evento decimal
        btnDecimal.setOnClickListener(v -> {
            if (!text.contains(".")) {
                if (nuevoNumero) {
                    text = "0.";
                    nuevoNumero = false;
                } else {
                    text = text + ".";
                }

                textPantalla.setText(text);
            }
        });

        btnSuma.setOnClickListener(v -> guardarOperador("+"));
        btnResta.setOnClickListener(v -> guardarOperador("-"));
        btnMult.setOnClickListener(v -> guardarOperador("*"));
        btnDiv.setOnClickListener(v -> guardarOperador("/"));

        btnIgual.setOnClickListener(v -> calcular());

        btnMc.setOnClickListener(v-> {memoria = 0;});
        btnMMax.setOnClickListener(v->{memoria += Double.parseDouble(text);});
        btnMMin.setOnClickListener(v->{memoria -= Double.parseDouble(text);});

        btnMr.setOnClickListener(v->{
            if(memoria %1 == 0){
                text = String.valueOf((int)memoria);
            }else{
                text = String.valueOf(memoria);
            }
            textPantalla.setText(text);
            nuevoNumero = true;
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            //v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    protected void insertarNum(String numero) {
        if (nuevoNumero) {
            text = numero;
            nuevoNumero = false;
        } else if (text.equals("0")) {
            text = numero;
        } else {
            text = text + numero;
        }
        textPantalla.setText(text);
    }

    protected void guardarOperador(String signo){
        if (!operador.equals("")) {
            calcular();
        } else {
            num1 = Double.parseDouble(text);
        }
        operador = signo;
        text = "0";
        nuevoNumero = false;
    }

    protected void calcular() {
        if (operador.equals("")) {
            return;
        }
        num2 = Double.parseDouble(text);

        if (operador.equals("+")) {
            num1 = num1 + num2;
        } else if (operador.equals("-")) {
            num1 = num1 - num2;
        } else if (operador.equals("*")) {
            num1 = num1 * num2;
        } else if (operador.equals("/")) {
            if (num2 == 0) {
                textPantalla.setText("Error");
                text = "0";
                operador = "";
                nuevoNumero = true;
                return;
            }
            num1 = num1 / num2;
        }
        if(num1 % 1 == 0){
            text = String.valueOf((int)num1);
        }else {
            text = String.valueOf(num1);
        }
        textPantalla.setText(text);
        operador = "";
        nuevoNumero = true;
    }

}