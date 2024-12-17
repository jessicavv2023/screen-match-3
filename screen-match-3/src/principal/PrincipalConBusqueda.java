package principal;

import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la pelicula que desea ver ");
    var busqueda = scanner.nextLine();

        String direccion = "http://www.omdbapi.com/?t=" + busqueda +  "&apikey=5bcc6528";
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
        System.out.println(json) ;

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        // fromJson de es de un archivo json a clases y el toJson es de clases a un archivo JSON
      TituloOmdb  miTituloOmbd= gson.fromJson(json, TituloOmdb.class);
        System.out.println(miTituloOmbd);
        Titulo miTitulo= new Titulo(miTituloOmbd);matrix
        System.out.println(miTitulo);
    }
}
