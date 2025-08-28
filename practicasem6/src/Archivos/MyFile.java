package Archivos;

import java.io.File; //clase utilitaria de archivos
import java.io.IOException; //Excepción de archivos
import java.util.Date;

public class MyFile {

    private File file = null;

    public void setFile(String dir) {
        file = new File(dir);
    }

    public void getInfo() {
        if (file.exists()) {
            System.out.println("Sí existe.");
            System.out.println("\n Nombre: " + file.getName());
            System.out.println("\n Path: " + file.getPath());
            System.out.println("\n Absolute path: " + file.getAbsolutePath());
            System.out.println("\n Padre: " + file.getAbsoluteFile().getParentFile().getParent());
            System.out.println("\n Bytes: " + file.length());
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
}
