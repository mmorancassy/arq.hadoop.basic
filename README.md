# MapReduce básico basado en el ejemplo de adictosaltrabajo.com

## Para ejecutar el ejemplo puede hacer de dos maneras

* Previamente es necesario copiar el fichero de carga a un directorio dentro de HDFS: 

> hadoop fs -mkdir /input
> hadoop fs -put fichero_de_carga.csv /input

* Empaquetando el proyecto con Gradle en un jar y mediante la siguiente secuencia de comandos:

> hadoop jar arq.hadoop.basic.jar es.hadoop.basic.manager.AirQualityManager /input/fichero_de_carga.csv /output

* Empaquetando el proyecto con Gradle y accediento al paquete de la distribución es necesario ejecutar el script:

> build/distributions/arq.hadoop.basic.tar --> bin/arq.hadoop.basic
