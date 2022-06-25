# Lanzador de ejemplo para integración de la libreria scanovate_colombia

El lanzador es un ejemplo de implementación de las librerias necesarias para iniciar el proceso de validación.

## Instalación

Primero, añadir las librerías "scanovate_colombia_2_2_8", "ScanovateManualCapture_1_0_7"
en las dependencias del proyecto. 

    `dependencies{

    implementation'com.coralogix.sdk:coralogix-sdk:2.0.6'
    implementation(name: 'scanovate_colombia_2_2_8', ext: 'aar')
    implementation(name: 'ScanovateManualCapture_1_0_7', ext: 'aar')
    }`
  
Asi mismo se podrán importar las siguientes librerías.

`import mabel_tech.com.scanovate_demo.ScanovateHandler;
 import mabel_tech.com.scanovate_demo.ScanovateSdk;
 import mabel_tech.com.scanovate_demo.model.CloseResponse;
 import mabel_tech.com.scanovate_demo.network.ApiHelper;
 import mabel_tech.com.scanovate_demo.network.RetrofitClient;`

La librería responde el resultado de la transacción en un objeto llamado CloseResponse  

### Version minima del SDK Android

Cambiar la versión minima del SDK Android a 21 (o mas alta) en el archivo `android/app/build.gradle`

### Ejemplo

Este es un pequeño ejemplo de como invocar el metodo que lanzara la librería. 

    
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
                "Comfandiqa",
                "db92efc69991",
                "https://adocolombia-qa.ado-tech.com/ComfandiQA/api/",
                numberIdentification,
                verification,
                "",
                "",
                new ScanovateHandler() {
                    @Override
                    public void onSuccess(CloseResponse response, int code, String uuidDevice) {
                        progress.show();
                        String calificacion = response.getExtras().getStateName();
                        btn_verificar.setVisibility(View.INVISIBLE);
                        btn_enrolar.setVisibility(View.INVISIBLE);
                        tv.setText("Resultado de la transacción: " + calificacion);

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
    

## Documentacion

para mas detalle sobre la documentación se pueden dirigir al link, donde se encuentran especificados las campos de iniciación del SDK y sus posibles salidas. https://adocolumbia.ado-tech.com/documentation/sdkandroidios.html