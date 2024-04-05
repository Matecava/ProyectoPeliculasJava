package datos;

import domain.Pelicula;
import excepciones.AccesoDatosEx;
import excepciones.EscrituraDatosEx;
import excepciones.LecturaDatosEx;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class AccesoDatoImpl implements AccesoDatos {

    @Override
    public Boolean existe(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreArchivo) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while (linea != null) {
                Pelicula pelicula = new Pelicula(linea);
                peliculas.add(pelicula);
                linea = entrada.readLine();
            }
            entrada.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccesoDatoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LecturaDatosEx("Excepcion al listar pelicula al catalogo");
        } catch (IOException ex) {
            Logger.getLogger(AccesoDatoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LecturaDatosEx("Excepcion al buscar pelicula al catalogo");
        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreArchivo, Boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreArchivo);
        try {
            var escritura = new PrintWriter(new FileWriter(archivo, anexar));
            escritura.println(pelicula.toString());
            escritura.close();
            System.out.println("Se ha escrito la pelicula en el catalogo");
        } catch (IOException ex) {
            Logger.getLogger(AccesoDatoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new EscrituraDatosEx("Excepcion al escribir peliculas");
        }
    }

    @Override
    public String buscar(String nombreArchivo, String nombre) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        String resultado = null;

        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String buscador = null;
            buscador = entrada.readLine();
            while (buscador != null) {
                if (nombre != null && nombre.equalsIgnoreCase(buscador)) {
                    resultado = "La pelicula " + nombre + " se encuentra en el catalogo";
                    break;
                }
                buscador = entrada.readLine();

            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccesoDatoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LecturaDatosEx("Excepcion al buscar pelicula al catalogo");
        } catch (IOException ex) {
            Logger.getLogger(AccesoDatoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LecturaDatosEx("Excepcion al buscar pelicula al catalogo");
        }

        return resultado;
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        var archivo = new File(nombreArchivo);
        try {
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
        } catch (IOException ex) {
            Logger.getLogger(AccesoDatoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new AccesoDatosEx("Excepcion al crear archivo");
        }
    }

    @Override
    public void borrar(String nombreArchivo) {
        var archivo = new File(nombreArchivo);
        if(archivo.exists())
            archivo.delete();
        
        System.out.println("El archivo ha sido borrado con exito");
    }

}
