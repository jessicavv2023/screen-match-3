package principal;

import com.alura.screenmatch.excepcion.ErrorEnConversionDeDuracionExpection;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        // List es la inferza y arrayList la clase concreta
        List<Titulo> titulos = new ArrayList<>(); // aarray de las peliculas
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
        while (true){

            System.out.println("Ingrese la pelicula que desea ver ");
            var busqueda = scanner.nextLine();
            if (busqueda.equalsIgnoreCase("salir")){
                break;
            }


            String encodedText = URLEncoder.encode(busqueda, "UTF-8");

            String direccion = "http://www.omdbapi.com/?t=" + encodedText +  "&apikey=5bcc6528";
            try {
       /* HttpClient es una clase de Java introducida en Java 11 para realizar solicitudes HTTP.
        Este cliente se usa para enviar las solicitudes al servidor.*/
                HttpClient client = HttpClient.newHttpClient();
                /*Lo que queremos obtener Request*/
                HttpRequest request = HttpRequest.newBuilder()
                        /*  Patron Builder es para contruir algo de puede tener muchas formas,
                         * puede tener otras cosas aparte de uri */
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
/*                Envia dentro de los parramentros request y BodyHandlers este ultimo ayuda a interpretar el mensaje que vamos a recibir */
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println("Se recibio el JSON" + json) ;

//            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
                // fromJson de es de un archivo json a clases y el toJson es de clases a un archivo JSON
                TituloOmdb  miTituloOmbd= gson.fromJson(json, TituloOmdb.class);
                if (miTituloOmbd == null) {
                    System.out.println("Error: No se pudo convertir el JSON a un objeto TituloOmdb.");
                    continue;
                }

                System.out.println(miTituloOmbd);
// se crea un archivo .txt
                System.out.println("a qui essta mi miTituloOmbd" + miTituloOmbd);
                try {
                    Titulo miTitulo = new Titulo(miTituloOmbd);
                    System.out.println("Titulo ya convertido: averrrrrr " + miTitulo);titulos.add(miTitulo);
                } catch (Exception e) {
                    System.out.println("Error al crear el objeto Titulo: " + e.getMessage());
                }


//            FileWriter escritura = new FileWriter("pelicula.txt");
//            escritura.write(miTituloOmbd.toString());
//            escritura.close(); // se cierra la comunicacion para terminar de escribir el archivo

            } catch (NumberFormatException e ){
                System.out.println("Ocurrior un error ");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e ){
                System.out.println("Error en la URI, verifique la direccion");
            } catch (ErrorEnConversionDeDuracionExpection e ){
                System.out.println(e.getMessage());
            }

        }
        System.out.println( "*** Lista de titulos ***" + titulos  );

        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(  gson.toJson(titulos));
        escritura.close();
        System.out.println("JSON recibido: " + escritura);
        System.out.println("Finalizó la ejecución del programa!");
    }
}
