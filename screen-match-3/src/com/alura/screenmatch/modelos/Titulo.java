package com.alura.screenmatch.modelos;

import com.alura.screenmatch.excepcion.ErrorEnConversionDeDuracionExpection;
import com.google.gson.annotations.SerializedName;

public class Titulo  implements Comparable <Titulo> {
    @SerializedName("Title")
    private String nombre;
    @SerializedName("Year")
    private int fechaDeLanzamiento;
    private boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDeEvaluaciones;
    private int duracionEnMinutos;

   /* los constrructores generalmente van de bajo de los atributos */

    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Titulo(TituloOmdb miTituloOmbd) {
        this.nombre= miTituloOmbd.title();
        this.fechaDeLanzamiento = Integer.valueOf(miTituloOmbd.year());
        if (miTituloOmbd.runtime().contains("N/A")){
            // si mi titulo contiene N/A significa que si ocurre una excpetion
            throw  new ErrorEnConversionDeDuracionExpection("No pude convertir la duracion"
                    + "porque contiene un N/A");
        }
        try {
            // Extrae solo los números del runtime, ignorando "min" u otras palabras
            String runtimeStr = miTituloOmbd.runtime().split(" ")[0]; // Toma solo la parte numérica
            this.duracionEnMinutos = Integer.valueOf(runtimeStr);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ErrorEnConversionDeDuracionExpection("No pude convertir la duración porque el formato es inválido: " + miTituloOmbd.runtime());
        }

            // se usa el .replace para remplazar el tiempo por si solo tiene dos digitos el espacio que dada lo remplaza por nada para uqe lo pueda convertir
       // this.duracionEnMinutos = Integer.valueOf(miTituloOmbd.runtime().substring(0,3).replace("", ""));
    }

    public String getNombre() {
        return nombre;
    }

    public int getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public boolean isIncluidoEnElPlan() {
        return incluidoEnElPlan;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public int getTotalDeEvaluaciones() {
        return totalDeEvaluaciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaDeLanzamiento(int fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public void setIncluidoEnElPlan(boolean incluidoEnElPlan) {
        this.incluidoEnElPlan = incluidoEnElPlan;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public void muestraFichaTecnica(){
        System.out.println("Nombre de la película: " + nombre);
        System.out.println("Año de lanzamiento: " + fechaDeLanzamiento);
    }

    public void evalua(double nota){
        sumaDeLasEvaluaciones += nota;
        totalDeEvaluaciones++;
    }

    public double calculaMediaEvaluaciones(){
        return sumaDeLasEvaluaciones / totalDeEvaluaciones;
    }


    @Override
    public int compareTo(Titulo otroTitulo) {
        return this.getNombre().compareTo(otroTitulo.getNombre());
    }

    @Override
    public String toString (){
        return "****** nombre" + nombre  +
                "fecha de lanzamiento" + fechaDeLanzamiento +
                "tiempo " + duracionEnMinutos + "*****";
    }
}
