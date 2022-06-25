## 2.2.3

* Lanzamiento inicial
* Detecccion de gafas y/o sombreros, ojos cerrados, etc.
* ConfiguracionUI desde el servidior
* Salidas 17 y 15

## 2.2.4

* Se elimina la pantalla de tratamiento de datos
* Se agrega el envio de errores a coralogix desde el SDk

## 2.2.5

* Se agrega la nueva funcionalidad del Liveness.
* Este nueva version no cuenta la funcionalidad de conteo de intentos de para captura de rostro.
* Se debera eliminar la dependencia de la libreria "libScanovateImagingHybridLiveness_2_2_6" del gradle.

## 2.2.6

* Se hace correcion de timeout en espera de respuesta en los servicios

## 2.2.7

* Se configura respuesta del getconfig para descargara la url nueva de liveness.
* No se envia el KeyProcessLiveness para evitar errores de validacion dentro de la plataforma.

## 2.2.8

* Se configura el getConfig el archivo de recursos desde el servidor para comfandi.
* Se envia en KeyProcessLiveness, que se valida en la plataforma.

  



