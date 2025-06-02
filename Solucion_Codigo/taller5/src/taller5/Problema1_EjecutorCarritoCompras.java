
public class Problema1_EjecutorCarritoCompras {

    public static void main(String[] args) {
        // Productos disponibles en tienda

        Producto[] inventario = new Producto[3];
        inventario[0] = new Producto("Laptop", 1200, 5);
        inventario[1] = new Producto("Mouse", 25, 10);
        inventario[2] = new Producto("USB", 10, 20);

        CarritoDeCompras carrito = new CarritoDeCompras(inventario, 0);

        carrito.agregarProducto("Laptop", 1);
        carrito.agregarProducto("USB", 3);

        carrito.mostrarDetalleCompra();

        carrito.realizarPago(1300);
    }

}

class Producto {

    public String nombre;
    public double precio;
    public int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return String.format("PRODUCTO\n"
                + "Nombre: %s\n"
                + "Precio: %.2f\n"
                + "Cantidad: %.2f\n",
                nombre, precio, cantidad);
    }
}

class CarritoDeCompras {

    public Producto[] productos;
    public Producto[] comprar;
    public double efectivo;
    public double montoFactuta;
    public double descuento;

    public CarritoDeCompras() {
    }

    public CarritoDeCompras(Producto[] productos, double efectivo) {
        this.productos = productos;
        this.efectivo = efectivo;
        this.comprar = new Producto[10];
    }

    public Producto[] getProductos() {
        return productos;
    }

    public double getEfectivo() {
        return efectivo;
    }

    public double getMontoFactuta() {
        return montoFactuta;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

    public void setEfectivo(double efectivo) {
        this.efectivo = efectivo;
    }

    public void setMontoFactuta(double montoFactuta) {
        this.montoFactuta = montoFactuta;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public void calcularDescuento() {
        if (this.montoFactuta > 1000) {
            this.descuento = this.montoFactuta * 0.1;
        }
    }

    public void agregarProducto(String nombre, int cantidad) {
        for (int i = 0; i < productos.length; i++) {
            if (nombre.equals(productos[i].getNombre()) && cantidad <= productos[i].getCantidad()) {
                for (int j = 0; j < comprar.length; j++) {
                    if (comprar[j] == null) {
                        comprar[j] = new Producto(nombre, productos[i].getPrecio(), cantidad);
                        break;
                    }
                }
                break;
            }
        }
    }

    public double calcularMontoFactura() {
        montoFactuta = 0;
        for (int i = 0; i < comprar.length; i++) {
            if (comprar[i] != null) {
                montoFactuta += comprar[i].getPrecio() * comprar[i].getCantidad();
            }
        }
        if (montoFactuta > 1000) {
            descuento = montoFactuta * 0.1;  // 10% descuento
        } else {
            descuento = 0;
        }
        return montoFactuta - descuento;
    }

    // Realizar pago y mostrar mensajes
    public void realizarPago(double montoPagado) {
        double totalAPagar = calcularMontoFactura();
        if (montoPagado >= totalAPagar) {
            System.out.println("¡Gracias por su compra!");
            System.out.println("Total sin descuento: $" + montoFactuta);
            System.out.println("Descuento aplicado: $" + descuento);
            System.out.println("Total a pagar: $" + totalAPagar);
            System.out.println("Cambio: $" + (montoPagado - totalAPagar));
        } else {
            System.out.println("Pago insuficiente. Faltan $" + (totalAPagar - montoPagado));
        }
    }

    // Mostrar detalle de la compra
    public void mostrarDetalleCompra() {
        System.out.println("Detalle de productos en el carrito:");
        for (int i = 0; i < comprar.length; i++) {
            if (comprar[i] != null) {
                System.out.println(comprar[i].toString());
            }
        }
    }

    @Override
    public String toString() {
        String detalleProductos = "";

        for (Producto producto : comprar) {
            if (producto != null) {
                detalleProductos += producto.toString() + "\n";
            }
        }

        return "CARRITO:\n"
                + detalleProductos
                + "Efectivo: " + efectivo + "\n"
                + "Monto compra: " + montoFactuta + "\n"
                + "Descuento: " + descuento + "\n"
                + "Total a pagar: " + (montoFactuta - descuento) + "\n";
    }
}
