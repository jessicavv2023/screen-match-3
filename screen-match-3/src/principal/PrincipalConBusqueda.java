package principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
       /* HttpClient es una clase de Java introducida en Java 11 para realizar solicitudes HTTP.
        Este cliente se usa para enviar las solicitudes al servidor.*/
        HttpClient client = HttpClient.newHttpClient();
        /*Lo que queremos obtener Request*/
        HttpRequest request = HttpRequest.newBuilder()
              /*  Patron Builder es para contruir algo de puede tener muchas formas,
              * puede tener otras cosas aparte de uri */
                .uri(URI.create("http://www.omdbapi.com/?i=tt3896198&apikey=5bcc6528"))
                .build();
        HttpResponse<String> response = client
/*                Envia dentro de los parramentros request y BodyHandlers este ultimo ayuda a interpretar el mensaje que vamos a recibir */
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body()) ;


    }
}
