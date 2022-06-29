# Lanzador de ejemplo para integración de la libreria scanovate_colombia

El lanzador es un ejemplo de implementación de las librerias necesarias para iniciar el proceso de validación.

## Importación
El sdk requere las siguientes librerias necesarias para realizar el correcto proceso de validación de identidad
 
    //LibreriasNativas 
    implementation(name: 'hybridComponent_1_0_1', ext: 'aar')
    implementation(name: 'libScanovateImagingHybridLiveness_2_2_2', ext: 'aar')
    implementation(name: 'scanovate_colombia_2_2_11', ext: 'aar')
    implementation(name: 'ScanovateManualCapture_1_0_7', ext: 'aar')
	
	
	//LibreriasComplementarias
    implementation 'com.android.support:multidex:1.0.3'
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    implementation 'com.android.support:support-v4:28.0.0'
    implementation 'org.jetbrains:annotations:15.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    implementation 'com.coralogix.sdk:coralogix-sdk:2.0.6'
    implementation 'org.greenrobot:eventbus:3.2.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.14.0'
    implementation 'com.squareup.retrofit2:converter-scalars:2.3.0'
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
    implementation 'com.google.android.gms:play-services-vision:17.0.2'
	

### Version minima del SDK Android

Cambiar la versión minima del SDK Android a 21 (o mas alta) en el archivo `android/app/build.gradle`

### Ejemplo

Este es un pequeño ejemplo de como invocar el metodo que lanzara la librería. 

    
    ScanovateSdk.start(this,
                "1",
                1,
                "adodemo",
                "db92efc69991",
                "https://adocolombia-qa.ado-tech.com/adodemo/api/",
                "",
                false,
                "",
                "",
                new ScanovateHandlerSDK() {
                    @Override
                    public void onSuccess(CloseResponse response, int code, String uuidDevice) {
                        //EjecuciónSatisfactoria


                    }

                    @Override
                    public void onFailure(CloseResponse closeResponse) {
                        //EjecuciónErronea

                    }
                });
    

## Documentacion

para mas detalle sobre la documentación se pueden dirigir al link, donde se encuentran especificados las campos de iniciación del SDK y sus posibles salidas. https://adocolumbia.ado-tech.com/documentation/sdkandroidios.html