package principal;

import com.alura.screenmatch.calculos.CalculadoraDeTiempo;
import com.alura.screenmatch.calculos.FiltroRecomendacion;
import com.alura.screenmatch.modelos.Episodio;
import com.alura.screenmatch.modelos.Pelicula;
import com.alura.screenmatch.modelos.Serie;
import com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;

public class PrincipalConListas {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        /*        miPelicula.setNombre("Encanto");*/
        miPelicula.setDuracionEnMinutos(180);
        System.out.println("Duración de la película: " + miPelicula.getDuracionEnMinutos());
  miPelicula.evalua(7);
        miPelicula.muestraFichaTecnica();
        miPelicula.evalua(8);
        miPelicula.evalua(5);
        miPelicula.evalua(10);
        System.out.println("Total de evaluaciones: " + miPelicula.getTotalDeEvaluaciones());
        System.out.println(miPelicula.calculaMediaEvaluaciones());


        Serie lost = new Serie("Lost", 2000);
        lost.muestraFichaTecnica();
        lost.evalua(8);
        lost.setTemporadas(10);
        lost.setEpisodiosPorTemporada(10);
        lost.setMinutosPorEpisodio(50);
        System.out.println("Duracion de la série: " + lost.getDuracionEnMinutos());

        Pelicula otraPelicula = new Pelicula("Avatar", 2023);
        otraPelicula.setDuracionEnMinutos(200);
otraPelicula.evalua(7);
        CalculadoraDeTiempo calculadora = new CalculadoraDeTiempo();
        calculadora.incluido(miPelicula);
        calculadora.incluido(otraPelicula);
        calculadora.incluido(lost);
        System.out.println(calculadora.getTiempoTotal());

        FiltroRecomendacion filtro = new FiltroRecomendacion();
        filtro.filtra(miPelicula);

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setSerie(lost);
        episodio.setTotalVisualizaciones(300);
        filtro.filtra(episodio);

        var peliculaDeBruno = new Pelicula("El señor de los anillos",2001);
        peliculaDeBruno.setDuracionEnMinutos(180);
peliculaDeBruno.evalua(5);

        Pelicula peliculaLoveintheBigCity = new Pelicula("Love in the Big City", 2024);
        peliculaLoveintheBigCity.setDuracionEnMinutos(118);
peliculaLoveintheBigCity.evalua(10);
        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(peliculaDeBruno);
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(peliculaLoveintheBigCity);
        lista.add(lost);
/*        del alrdo derecho de los puntos es lo que queremos iteraar y del aldo izquierdo va el como queremos que se llame cada coas que se tiene  que iterar*/
         for(Titulo items:lista){
             System.out.println(items.getNombre());
             if (items instanceof Pelicula pelicula && pelicula.getClasificacion() > 2 ){
                /* Pelicula pelicula = (Pelicula) items;*/
                 System.out.println(pelicula.getClasificacion());
             }


         }

        System.out.println("Tamaño de la lista: " + lista.size());
        System.out.println("La primera pelicula es: " + lista.get(0).getNombre());

        System.out.println(lista);
    }
}
