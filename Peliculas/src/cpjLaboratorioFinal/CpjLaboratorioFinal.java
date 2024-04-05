package cpjLaboratorioFinal;

import java.util.Scanner;
import negocio.CatalogoPeliculas;
import negocio.CatalogoPeliculasImpl;

public class CpjLaboratorioFinal {

    public static int opcion;
    public static String nombreArchivo;
    static CatalogoPeliculas catalogoPeliculas = new CatalogoPeliculasImpl();

    public static void main(String[] args) {
        nombreArchivo = "Peliculas.txt";
        Scanner consola = new Scanner(System.in);
        opcion = -1;

        while (opcion != 0) {

            System.out.println("""
                           Elige una opcion: 
                           1. Iniciar catalogo preguntas
                           2. Agregar pelicula
                           3. Ver lista de peliculas
                           4. Buscar pelicula
                           0. Salir""");

            opcion = Integer.parseInt(consola.nextLine());

            switch (opcion) {
                case 0 -> {
                    System.out.println("¡Vuelva pronto!");
                    opcion = 0;
                }

                case 1 -> {
                    catalogoPeliculas.iniciarCatalogoPeliculas();
                }
                case 2 -> {
                    System.out.println("Introduce el nombre de una pelicula: ");
                    var nombrePelicula = consola.nextLine();
                    catalogoPeliculas.agregarPelicula(nombrePelicula);

                }
                case 3 -> {
                    catalogoPeliculas.listarPeliculas();
                }
                case 4 -> {
                    System.out.println("Introduce el nombre de la pelicula que buscas: ");
                    var pelicula = consola.nextLine();
                    catalogoPeliculas.buscarPelicula(pelicula);
                }
                default -> System.out.println("opcion  no reconocida");
            }

        }

    }

}
