package com.mabel_tech.scanovate_colombia_sdk_demo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import org.jetbrains.annotations.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import mabel_tech.com.scanovate_demo_sdk.ScanovateHandlerSDK;
import mabel_tech.com.scanovate_demo_sdk.ScanovateSdk;
import mabel_tech.com.scanovate_demo_sdk.model.CloseResponse;
import mabel_tech.com.scanovate_demo_sdk.network.ApiHelper;
import mabel_tech.com.scanovate_demo_sdk.network.RetrofitClient;


public class MainActivity extends AppCompatActivity {

    private ProgressDialog progress;

    Button btn_enrolar;
    Button btn_verificar;
    TextView tv;
    Context contect;
    EditText numberId;
    String numberIdentification;
    Boolean verification;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        progress = new ProgressDialog(this);
        progress.setTitle("Procesando estado");
        progress.setMessage("Por favor espere un momento...");
        progress.setIndeterminate(true);
        progress.setCanceledOnTouchOutside(false);
        contect = this;
        btn_enrolar = findViewById(R.id.btn_enroll);
        btn_verificar = findViewById(R.id.btn_verification);
        numberId = findViewById(R.id.numberId);
        verification = false;
        initView();
    }

    public void initView() {

        btn_enrolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture();
            }
        });

        btn_verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_verificar.getText().equals("Enviar")) {
                    numberId.setVisibility(View.VISIBLE);
                    btn_enrolar.setVisibility(View.INVISIBLE);
                    btn_verificar.setText("Enviar");
                } else {
                    if (numberId.getText().length() != 0) {
                        numberIdentification = numberId.getText().toString();
                        verification = true;
                        numberId.setVisibility(View.INVISIBLE);
                        btn_verificar.setVisibility(View.INVISIBLE);
                        capture();

                    }

                }
            }
        });
    }

    public void capture() {
        ScanovateSdk.start(this,
                "1",
                1,
                "adodemo",
                "db92efc69991",
                "https://adocolombia-qa.ado-tech.com/adodemo/api/",
                numberIdentification,
                verification,
                "",
                "",
                new ScanovateHandlerSDK() {
                    @Override
                    public void onSuccess(CloseResponse response, int code, String uuidDevice) {
                        String calificacion = response.getExtras().getStateName();
                        btn_verificar.setVisibility(View.INVISIBLE);
                        btn_enrolar.setVisibility(View.INVISIBLE);
                        evaluateTransaction(response.getTransactionId());
                        progress.show();
                        //tv.setText("Resultado de la transacción: " + calificacion);


                    }

                    @Override
                    public void onFailure(CloseResponse closeResponse) {
                        String calificacion = closeResponse.getExtras().getStateName() + " " + closeResponse.getExtras().getAdditionalProp1();
                        btn_verificar.setVisibility(View.INVISIBLE);
                        btn_enrolar.setVisibility(View.INVISIBLE);
                        tv.setText("Resultado de la transacción: " + calificacion);

                    }
                });
    }


    private void retray() {
        btn_verificar.setVisibility(View.VISIBLE);
        btn_enrolar.setVisibility(View.VISIBLE);

    }

    private void evaluateTransaction(String transactionId) {

        RetrofitClient retrofitClient = new RetrofitClient();
        retrofitClient.validateTransaction("adodemo", transactionId, new ApiHelper.ValidateTransactionHandler() {
            @Override
            public void onSuccess(String stateName) {
                progress.dismiss();
                tv.setText("Resultado de Transacción: " + stateName);
                retray();
            }

            @Override
            public void onConnectionFailed() {
                String algo = "";
            }

            @Override
            public void onFailure(int i, String s) {
                //evaluateTransaction(transactionId, contect);
            }
        }, contect);
    }
}
