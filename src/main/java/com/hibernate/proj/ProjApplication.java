package com.hibernate.proj;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hibernate.proj.classes.Address;
import com.hibernate.proj.classes.Contacts;
import com.hibernate.proj.enums.URole;
import com.hibernate.proj.models.Cart;
import com.hibernate.proj.models.Order;
import com.hibernate.proj.models.Payment;
import com.hibernate.proj.models.Product;
import com.hibernate.proj.models.User;
import com.hibernate.proj.repositories.CartRepo;
import com.hibernate.proj.repositories.OrderRepo;
import com.hibernate.proj.repositories.PaymentRepo;
import com.hibernate.proj.repositories.ProductRepo;
import com.hibernate.proj.repositories.UserRepository;

@SpringBootApplication
public class ProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, ProductRepo productRepo, PaymentRepo paymentRepo,
										OrderRepo orderRepo, CartRepo cartRepo) {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			boolean continueRunning = true;

			while (continueRunning) {
				System.out.println("Choose an option: \n1. Add a new user\n2. List all users\n3. Get user by ID\n4. Get user by name\n5. Exit\n");
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
					case 1:
						addUser(scanner, userRepository, productRepo, paymentRepo, orderRepo, cartRepo);
						break;
					case 2:
						listAllUsers(userRepository);
						break;
					case 3:
						getUserById(scanner, userRepository);
						break;
					case 4:
						getUsersWithSameName(scanner, userRepository);
						break;
					case 5:
						continueRunning = false;
						break;
					default:
						System.out.println("Invalid choice. Please choose a valid option.");
				}
			}

			scanner.close();
		};
	}

	private void addUser(Scanner scanner, UserRepository userRepository, ProductRepo productRepo, PaymentRepo paymentRepo,
						 OrderRepo orderRepo, CartRepo cartRepo) {

		User user = new User();
		System.out.print("Enter first name: ");
		user.setFname(scanner.nextLine());

		System.out.print("Enter last name: ");
		user.setLname(scanner.nextLine());

		System.out.print("Enter email: ");
		String email = scanner.nextLine();

		System.out.print("Enter phone: ");
		String phone = scanner.nextLine();

		System.out.print("Enter role (e.g., SELLER, BUYER): ");
		URole role = URole.valueOf(scanner.nextLine().toUpperCase());
		user.setRole(role);

		Contacts contacts = new Contacts();
		contacts.setEmail(email);
		contacts.setPhone(phone);
		user.setContacts(contacts);

		Product product = new Product();
		product.setCreatedAt(LocalDateTime.now());
		product.setPname("Testing");
		product.setPrice(23.0);
		product.setUser(user);

		Product product2 = new Product();
		product2.setCreatedAt(LocalDateTime.now());
		product2.setPname("Testing2");
		product2.setPrice(27.23);
		product2.setUser(user);

		List<Product> products = new ArrayList<>();
		products.add(product2);
		products.add(product);

		Cart cart = new Cart();
		cart.setProducts(products);
		cart.setUser(user);

		Address address = new Address();
		address.setDistrict("Karongi");
		address.setSector("Rubengera");
		address.setStreet("test");

		Order order = new Order();
		order.setAddress(address);
		order.setCart(cart);
		order.setDelivered(false);
		order.setTotalPrice(37);

		Payment payment = new Payment();
		payment.setCart(cart);
		payment.setPaid_at(LocalDateTime.now());
		payment.setPayment_method("Stripe");
		payment.setPrice(34);
		payment.setTransaction_id("ARTY567HJG");

		userRepository.save(user);
		cartRepo.save(cart);
		productRepo.save(product);
		productRepo.save(product2);
		orderRepo.save(order);
		paymentRepo.save(payment);

		System.out.println("Successfully added.");
	}

	private void listAllUsers(UserRepository userRepository) {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			System.out.println("No users found.");
		} else {
			users.forEach(user -> System.out.println(user.toString()));
		}
	}

	private void getUserById(Scanner scanner, UserRepository userRepository) {
		System.out.print("Enter user ID: ");
		int userId = scanner.nextInt();
		scanner.nextLine();
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			System.out.println(userOptional.get().toString());
		} else {
			System.out.println("User not found.");
		}
	}

	private void getUsersWithSameName(Scanner scanner, UserRepository userRepository){
		System.out.println("Enter the name to get the users with that name: ");

		List<User> users = userRepository.findAllByLastName(scanner.next());
		users.forEach(user -> System.out.println(user.toString()));
	}

}
