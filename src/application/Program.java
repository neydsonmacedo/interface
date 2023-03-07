package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import services.ContractService;
import services.PaypalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Entre os dados do contrato:");
		System.out.print("Numero: ");
		int numero = sc.nextInt();
		sc.nextLine();
		System.out.println("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.nextLine(),fmt);
		System.out.print("Valor do contrato: ");
		double totalValue = sc.nextDouble();
		
		Contract contract = new Contract(numero,date,totalValue);
		System.out.print("Entre com o numero de parcelas:");
		int n = sc.nextInt();
		ContractService contractService = new ContractService(new PaypalService());
		contractService.processContract(contract, n);
		System.out.println("Parcelas:");
		System.out.println(contract);
		
		
		sc.close();
		

	}

}
