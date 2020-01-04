package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		System.out.println("Enter rental data: ");

		System.out.print("Car model: ");
		String carModel = scan.nextLine();

		System.out.print("Pickup (dd/MM/yyyy hh:ss): ");
		Date start = sdf.parse(scan.nextLine());

		System.out.print("Return dd/MM/yyyy hh:ss): ");
		Date finish = sdf.parse(scan.nextLine());

		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

		System.out.print("Enter price per hour: ");
		double pricePerHour = scan.nextDouble();

		System.out.print("Enter price per day: ");
		double pricePerDay = scan.nextDouble();

		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());

		rentalService.ProcessInvoice(cr);

		System.out.println("Invoice: ");
		System.out.println("Basica payment: R$" + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax: R$" + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total payment: R$" + String.format("%.2f", cr.getInvoice().getTotalPayment()));

		scan.close();

	}
}
