package com.talento.tech.service.pedidos;

import com.talento.tech.service.excepciones.InvalidException;
import com.talento.tech.service.productos.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Lista {
    private final List<Producto> productos;
    private final Scanner scanner;
    public Lista() {
        this.productos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    public Optional<Producto> buscarPorNombre() {
        System.out.println("Ingrese el nombre del producto a buscar:");
        String nombre = this.scanner.nextLine();
        return this.productos.stream().filter(producto -> producto.getNombre().equals(nombre)).findAny();
    }
    public Optional<Producto> getById(int id) {
        return this.productos.stream().filter(producto -> producto.getId() == id).findAny();
    }

    public void buscarPorId() {
        System.out.println("Ingrese el id del producto a buscar:");
        int id = this.scanner.nextInt();
        this.scanner.nextLine();
        Optional<Producto> producto = this.getById(id);
        if (producto.isEmpty()) {
            System.out.println("No se encontró el producto");
        } else {
            System.out.println(producto.get());
        }
    }
    public void mostrarProductos() {
        System.out.println(this.productos);
    }
    public void actualizarStock() {
        System.out.println("Ingrese el id a editar:");
        int id = this.scanner.nextInt();
        this.scanner.nextLine();
        Optional<Producto> producto = this.getById(id);
        if (producto.isEmpty()) {
            System.out.println("No se encontró el producto");
        } else {
            int stock = this.scanner.nextInt();
            this.scanner.nextLine();
            try {
                producto.get().setStock(stock);
            } catch (InvalidException exception) {
                System.out.println(exception);
            }
        }
    }
    public void borrarProducto() {
        System.out.println("Ingrese el id a borrar:");
        int id = this.scanner.nextInt();
        this.scanner.nextLine();
        Optional<Producto> producto = this.getById(id);
        if (producto.isEmpty()) {
            System.out.println("No se encontró el producto");
        } else {
            String confirm = "";
            System.out.println("Confirma el borrado? Ingrese 's' o 'n'");
            while (!confirm.equals("s") && !confirm.equals("n")){
                confirm = this.scanner.nextLine();
                if (confirm.equals("s")) {
                    this.productos.remove(producto.get());
                    System.out.println("El producto "+producto.get().getNombre()+" fue borrado");
                } else if (confirm.equals("n")) {
                    System.out.println("Borrado cancelado");
                } else {
                    System.out.println("Por favor ingrese 's' o 'n'");
                }
            }
        }
    }
}
