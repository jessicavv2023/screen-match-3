package com.alura.screenmatch.excepcion;

public class ErrorEnConversionDeDuracionExpection extends RuntimeException{// extiende ahi porque
    // si extiende de throwable, significa que es una exception de tipo checked nos obliga que la clase que implemnta la exception en este caso Titulo
    // tenga que implementarlo y por esto es mejor RuntimeException que no obliga que la clase Titulo tenga que imolementarlo
    private String mensaje;
     public ErrorEnConversionDeDuracionExpection(String mensaje) {
    this.mensaje = mensaje;
     }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}
