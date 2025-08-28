package Archivos;

import java.io.File; //clase utilitaria de archivos
import java.io.IOException; //Excepción de archivos
import java.util.Date;
import java.text.SimpleDateFormat;

public class MyFile {

    private File file = null;

    public void setFile(String dir) {
        file = new File(dir);
    }

    public void getInfo() {
        if (file.exists()) {
            System.out.println("Sí existe.");
            System.out.println("Nombre: " + file.getName());
            System.out.println("Path: " + file.getPath());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Padre: " + file.getAbsoluteFile().getParentFile().getParent());
            System.out.println("Bytes: " + file.length());
            if (file.isFile()) {
                System.out.println("es un archivo.");
            } else if (file.isDirectory()) {
                System.out.println("es un folder.");
                System.out.println("Ultima modificación: " + new Date(file.lastModified()));
            }
        } else {
            System.out.println("no existe.");
        }
    }

    public void crearArchivo() throws IOException {
        if (file.createNewFile()) {
            System.out.println("Creado con éxito.");
        } else {
            System.out.println("No se pudo crear");
        }
    }

    public void crearFolder() throws IOException {
        if (file.mkdirs()) {
            System.out.println("Folder creado con éxito.");
        } else {
            System.out.println("No se pudo crear.");
        }
    }

    public boolean borrar() throws IOException {
        return borrarR(file);
    }

    public boolean borrarR(File f) throws IOException {
        if (f.isDirectory()) {
            for (File child : f.listFiles()) {
                borrarR(child);
            }
        }
        return f.delete();
    }

    private void tree(File dir, String tab) {
        if (dir.isDirectory()) {
            System.out.println(tab + dir.getName());
            for (File child : dir.listFiles()) {
                if (!child.isHidden()) {
                    tree(child, tab + "--");
                }
            }
        }
    }

    public void tree() {
        tree(file, "--");
    }

    private void mostrarDir(File file) {
        int cantArchivos = 0;
        int cantDir = 0;

        long lengthArchivos = 0;

        System.out.println("  Ultima Modificación     Tipo             Tamaño                  Nombre");
        
        for (File child : file.listFiles()) {
            if (!child.isHidden()) {
                String fechaModif = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(child.lastModified()));
                
                System.out.print("  " + fechaModif + "     ");
                System.out.print(((child.isDirectory()) ? "<DIR>" : "FILE ") + "            ");
                System.out.print(child.length() + " KB" + "                    ");
                System.out.print(child.getName() + "\n");

                if (child.isDirectory()) {
                    cantDir++;
                } else {
                    cantArchivos++;
                    lengthArchivos += child.length();
                }
            }
        }
        System.out.println("\n" + cantArchivos + " Archivo(s) " + lengthArchivos + " KB");
        System.out.println(cantDir + " Directorio(s) ");
    }

    public void mostrarDir() {
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("\nDirectorio de: " + file.getAbsolutePath() + "\n");
                mostrarDir(file);
            } else {
                System.out.println("\nNo es un directorio.");
            }
        } else {
            System.out.println("\nDirectorio no existe.");
        }
    }
}
