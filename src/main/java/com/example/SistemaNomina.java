package com.example;

import java.util.ArrayList;
import java.util.Scanner;

class Empleado {
    private String nombres;
    private String apellidos;
    private String documento;
    private int diasTrabajados;
    private double salarioBase;
    private boolean subsidioTransporte;

    private static final double VALOR_SUBSIDIO_TRANSPORTE = 162000;
    private static final double SALARIO_MINIMO = 1300000;
    private static final double PORCENTAJE_SALUD = 0.04;
    private static final double PORCENTAJE_PENSION = 0.04;

    public Empleado(String nombres, String apellidos, String documento, int diasTrabajados, double salarioBase, boolean subsidioTransporte) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.diasTrabajados = diasTrabajados;
        this.salarioBase = salarioBase;
        this.subsidioTransporte = subsidioTransporte;
    }

    public double calcularSalarioBruto() {
        return (salarioBase / 30) * diasTrabajados;
    }

    public double calcularSubsidioTransporte() {
        if (salarioBase <= 2 * SALARIO_MINIMO && subsidioTransporte) {
            return (VALOR_SUBSIDIO_TRANSPORTE / 30) * diasTrabajados;
        } else {
            return 0;
        }
    }

    public double calcularDeduccionSalud(double salarioBruto) {
        return salarioBruto * PORCENTAJE_SALUD;
    }

    public double calcularDeduccionPension(double salarioBruto) {
        return salarioBruto * PORCENTAJE_PENSION;
    }

    public void generarNomina() {
        double salarioBruto = calcularSalarioBruto();
        double valorSubsidioTransporte = calcularSubsidioTransporte();
        double totalDevengos = salarioBruto + valorSubsidioTransporte;

        double deduccionSalud = calcularDeduccionSalud(salarioBruto);
        double deduccionPension = calcularDeduccionPension(salarioBruto);
        double totalDeducciones = deduccionSalud + deduccionPension;

        double salarioNeto = totalDevengos - totalDeducciones;

        System.out.println("Empleado: " + nombres + " " + apellidos);
        System.out.println("Documento: " + documento);
        System.out.println("Días trabajados: " + diasTrabajados);
        System.out.println("Salario bruto: $" + String.format("%.2f", salarioBruto));
        System.out.println("Subsidio de transporte: $" + String.format("%.2f", valorSubsidioTransporte));
        System.out.println("Total devengos: $" + String.format("%.2f", totalDevengos));
        System.out.println("Deducción de salud: $" + String.format("%.2f", deduccionSalud));
        System.out.println("Deducción de pensión: $" + String.format("%.2f", deduccionPension));
        System.out.println("Total deducciones: $" + String.format("%.2f", totalDeducciones));
        System.out.println("Salario neto a pagar: $" + String.format("%.2f", salarioNeto));
        System.out.println("===================================");
    }
}

public class SistemaNomina {
    public static void main(String[] args) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String opcion;

        do {
            System.out.println("Ingrese los datos del empleado:");

            System.out.print("Nombres: ");
            String nombres = scanner.nextLine();

            System.out.print("Apellidos: ");
            String apellidos = scanner.nextLine();

            System.out.print("Documento: ");
            String documento = scanner.nextLine();

            System.out.print("Días trabajados: ");
            int diasTrabajados = Integer.parseInt(scanner.nextLine());

            System.out.print("Salario base mensual: ");
            double salarioBase = Double.parseDouble(scanner.nextLine());

            System.out.print("¿Aplica subsidio de transporte? (si/no): ");
            boolean subsidioTransporte = scanner.nextLine().equalsIgnoreCase("si");

            Empleado empleado = new Empleado(nombres, apellidos, documento, diasTrabajados, salarioBase, subsidioTransporte);
            empleados.add(empleado);

            System.out.print("¿Desea ingresar otro empleado? (si/no): ");
            opcion = scanner.nextLine();
        } while (opcion.equalsIgnoreCase("si"));

        System.out.println("\n=== Liquidación de Nómina ===");
        for (Empleado empleado : empleados) {
            empleado.generarNomina();
        }

        scanner.close();
    }
}
