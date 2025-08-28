package Archivos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestMyFile {

    static MyFile mf = new MyFile();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option = 0;

        do {
            System.out.println("\nMenu");
            System.out.println("\n1- Set el archivo/folder");
            System.out.println("\n2- Ver el archivo/folder");
            System.out.println("\n3- Crear archivo");
            System.out.println("\n4- Crear folder");
            System.out.println("\n5- Eliminar folder/archivo");
            System.out.println("\n6- Tree");
            System.out.println("\n7- Salir");
            System.out.println("Elija una opción: ");
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        set();
                        break;
                    case 2:
                        mf.getInfo();
                        break;
                    case 3:
                        mf.crearArchivo();
                        break;
                    case 4:
                        mf.crearFolder();
                        break;
                    case 5:
                        if (mf.borrar()) {
                            System.out.println("se ha borrado.");
                        } else {
                            System.out.println("no se logró borrar");
                        }
                    case 6:
                        mf.tree();
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Favor ingrese una opción correcta");

            } catch (NullPointerException e) {
                System.out.println("Debe seleccionar la opción número uno mínimo una vez");
            } catch (IOException e) {
                System.out.println("Error en disco.");
            }
        } while (option != 7);
    }

    private static void set() {
        System.out.println("Direcciónn: ");
        mf.setFile(scanner.next());
    }
}
