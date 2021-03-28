package com.example.parcial1_estivenreyes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btmGreen, btnYellow, btnRed, vista, confirmar;
    private EditText posXx;
    private EditText posYx;
    private EditText remember;
    private String posX,posY, record, txtRemember, relevance, recordMessage,relevanceTxt;

    private  TCP_Singleton tcp;

    ConstraintLayout nuevoR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tcp = new TCP_Singleton(this);
        tcp.requestConexion();

        btmGreen = findViewById(R.id.btnGreen);
        btnRed = findViewById(R.id.btnRed);
        btnYellow = findViewById(R.id.btnYellow);
        posXx = findViewById(R.id.posXx);
        posYx = findViewById(R.id.posYx);
        remember = findViewById(R.id.remember);
        vista = findViewById(R.id.vista);
        confirmar = findViewById(R.id.confirmar);

        btnRed.setOnClickListener(
                (v)-> {
                    relevanceTxt = "3";
                }
        );

        btnYellow.setOnClickListener(
                (v)-> {
                    relevanceTxt = "2";
                }
        );

        btmGreen.setOnClickListener(
                (v)-> {
                    relevanceTxt = "1";
                }
        );

        confirmar.setOnClickListener(
                (v) -> {
                    relevance = relevanceTxt;
                    posX = posXx.getText().toString();
                    posY = posYx.getText().toString();
                    record = remember.getText().toString();

                    txtRemember = posX +"," + posY +"," + record +"," + relevance;

                    if(!posX.equals("") && !posY.equals("") && !record.equals("") && !relevance.equals("")) {
                        tcp.sendMessage(txtRemember);

                    } else {
                        runOnUiThread(
                                () -> {
                                    Toast.makeText(this, "Rellene los campos por favor",
                                            Toast.LENGTH_SHORT).show();
                                }
                        );
                    }
                }
        );
    }
}