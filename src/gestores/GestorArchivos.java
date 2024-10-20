package gestores;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GestorArchivos {

    /** Ruta del Directorio sobre el que vamos a trabajar */
    private static final String DIRECTORY_PATH = "E:\\2º DAM\\Acceso a Datos\\Gestion_de_Archivos\\src\\ejercicio";  

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File mainDirectory = new File(DIRECTORY_PATH);
        mainDirectory.mkdirs();
        int option;

        do {
            System.out.println("\n=== Menú de opciones ===");
            System.out.println("1. Listar archivos y carpetas");
            System.out.println("2. Ver permisos del archivo");
            System.out.println("3. Leer archivo");
            System.out.println("4. Escribir en archivo");
            System.out.println("5. Crear archivo");
            System.out.println("6. Borrar archivo");
            System.out.println("7. Crear directorio");
            System.out.println("8. Borrar directorio");
            System.out.println("9. Mostrar ruta absoluta");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    listarArchivosYCarpetas();
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del archivo: ");
                    String nombreArchivoPerm = scanner.nextLine();
                    verPermisosArchivo(new File(DIRECTORY_PATH, nombreArchivoPerm));
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del archivo a leer: ");
                    String nombreArchivoLeer = scanner.nextLine();
                    leerArchivo(new File(DIRECTORY_PATH, nombreArchivoLeer));
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del archivo a escribir: ");
                    String nombreArchivoEscribir = scanner.nextLine();
                    System.out.print("Ingrese el texto a escribir: ");
                    String texto = scanner.nextLine();
                    escribirEnArchivo(new File(DIRECTORY_PATH, nombreArchivoEscribir), texto);
                    break;
                case 5:
                    System.out.print("Ingrese el nombre del archivo a crear: ");
                    String nombreNuevoArchivo = scanner.nextLine();
                    crearArchivo(new File(DIRECTORY_PATH, nombreNuevoArchivo));
                    break;
                case 6:
                    System.out.print("Ingrese el nombre del archivo a borrar: ");
                    String nombreArchivoBorrar = scanner.nextLine();
                    borrarArchivo(new File(DIRECTORY_PATH, nombreArchivoBorrar));
                    break;
                case 7:
                    System.out.print("Ingrese el nombre del directorio a crear: ");
                    String nombreDirectorioCrear = scanner.nextLine();
                    crearDirectorio(new File(DIRECTORY_PATH, nombreDirectorioCrear));
                    break;
                case 8:
                    System.out.print("Ingrese el nombre del directorio a borrar: ");
                    String nombreDirectorioBorrar = scanner.nextLine();
                    borrarDirectorio(new File(DIRECTORY_PATH, nombreDirectorioBorrar));
                    break;
                case 9:
                    System.out.print("Ingrese el nombre del archivo: ");
                    String nombreArchivoRuta = scanner.nextLine();
                    mostrarRutaAbsoluta(new File(DIRECTORY_PATH, nombreArchivoRuta));
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (option != 0);

        scanner.close();
    }

    public static void listarArchivosYCarpetas() {
        File dir = new File(DIRECTORY_PATH);
        if (dir.exists() && dir.isDirectory()) {
            String[] archivos = dir.list();
            if (archivos != null) {
                System.out.println("Archivos y carpetas:");
                for (String archivo : archivos) {
                    System.out.println(archivo);
                }
            } else {
                System.out.println("La carpeta está vacía o no se puede leer.");
            }
        } else {
            System.out.println("La ruta no es un directorio válido.");
        }
    }

    public static void verPermisosArchivo(File archivo) {
        if (archivo.exists()) {
            System.out.println("Permisos del archivo:");
            System.out.println("Lectura: " + archivo.canRead());
            System.out.println("Escritura: " + archivo.canWrite());
            System.out.println("Ejecución: " + archivo.canExecute());
        } else {
            System.out.println("El archivo no existe.");
        }
    }

    public static void leerArchivo(File archivo) {
        if (archivo.exists()) {
            try (FileReader fr = new FileReader(archivo)) {
                int c;
                while ((c = fr.read()) != -1) {
                    System.out.print((char) c);
                }
                System.out.println();
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe.");
        }
    }

    public static void escribirEnArchivo(File archivo, String texto) {
        try (FileWriter fw = new FileWriter(archivo, true)) {  // 'true' para agregar al archivo
            fw.write(texto);
            System.out.println("Texto agregado al archivo.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void crearArchivo(File archivo) {
        try {
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    public static void borrarArchivo(File archivo) {
        if (archivo.delete()) {
            System.out.println("Archivo borrado: " + archivo.getName());
        } else {
            System.out.println("No se pudo borrar el archivo.");
        }
    }

    public static void crearDirectorio(File directorio) {
        if (directorio.mkdir()) {
            System.out.println("Directorio creado: " + directorio.getName());
        } else {
            System.out.println("No se pudo crear el directorio.");
        }
    }

    public static void borrarDirectorio(File directorio) {
        if (directorio.delete()) {
            System.out.println("Directorio borrado: " + directorio.getName());
        } else {
            System.out.println("No se pudo borrar el directorio. Asegúrate de que esté vacío.");
        }
    }

    public static void mostrarRutaAbsoluta(File archivo) {
        if (archivo.exists()) {
            System.out.println("Ruta absoluta: " + archivo.getAbsolutePath());
        } else {
            System.out.println("El archivo no existe.");
        }
    }
}
