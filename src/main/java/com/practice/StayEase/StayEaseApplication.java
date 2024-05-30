package com.practice.StayEase;

import com.practice.StayEase.entity.Hotel;
import com.practice.StayEase.entity.Role;
import com.practice.StayEase.entity.User;
import com.practice.StayEase.repository.HotelRepository;
import com.practice.StayEase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class StayEaseApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HotelRepository hotelRepository;

	public static void main(String[] args) {
		SpringApplication.run(StayEaseApplication.class, args);
	}

	public void run(String... args){
		User adminAccount = userRepository.findByRole(Role.ADMIN);

		if(adminAccount == null){
			User user = new User();
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setEmail("admin@gmail.com");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}

		Hotel hotel = new Hotel();
		hotel.setHotelName("Leela palace");
		hotel.setDescription("Good place to stay");
		hotel.setLocation("Delhi");
		hotel.setNumberOfAvailableRooms(1000L);

		hotelRepository.save(hotel);

		Hotel hotel1 = new Hotel();
		hotel1.setHotelName("Radison Blu");
		hotel1.setDescription("Comfy place to stay");
		hotel1.setLocation("Delhi");
		hotel1.setNumberOfAvailableRooms(1000L);

		hotelRepository.save(hotel1);

	}

}
