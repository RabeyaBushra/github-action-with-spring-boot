package com.crud.app.rest;

import com.crud.app.rest.Controllers.ApiControllers;
import com.crud.app.rest.Models.User;
import com.crud.app.rest.Repo.UserRepo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@WebMvcTest(ApiControllers.class)
class RestApiApplicationTests {
	@Autowired
	private ApiControllers apiControllers;

	@MockBean
	UserRepo userRepo;


	User USER1 = new User(1,"Tahiark@bogum.com","Tahia","Tonni","DR",21);
	User USER2 = new User(2, "Park", "Bo Gum", "Developer", 28);
	User USER3 = new User(3,"son@yejin.com", "Son", "YeJin", "Developer", 40);



	@org.junit.Test
	public void getUserTest() {

		List<User> records = new ArrayList<>(Arrays.asList(USER1, USER2, USER3));
		when(userRepo.findAll()).thenReturn(records);
		assertEquals(3,apiControllers.getUsers().size());
	}


	@Test
	public void getSingleUser() {

		when(userRepo.findById(USER1.getId())).thenReturn(Optional.of(USER1));
		Long ID = USER1.getId();
		System.out.println(ID);
		assertEquals(200,apiControllers.getSingleUser(ID).getStatusCodeValue());

	}


	@Test
	public void getSingleUserNotFound() {
		User USER5 = new User();
		when(userRepo.existsByEmail(String.valueOf(USER2.getEmail()))).thenReturn(Stream.of(USER5).isParallel());
		assertEquals("No User found for this id",apiControllers.getSingleUser(10).getBody());
	}

	@Test
	public void CreateUser() {

		when(userRepo.existsByEmail(String.valueOf(USER2.getEmail()))).thenReturn(Stream.of(USER2).isParallel());
		assertEquals("save the new info",apiControllers.saveUser(USER2).getBody());
	}

	@Test
	public void updateUserNotExist() {
		User USER9= new User();
		User UpadteUser9 = new User();
		when(userRepo.existsByEmail(String.valueOf(USER9.getEmail()))).thenReturn(Stream.of(USER9).isParallel());
		when(userRepo.save(UpadteUser9)).thenReturn(UpadteUser9);
		Long ID = USER9.getId();
		System.out.println(ID);
		assertEquals("No User found for this id",apiControllers.updateUser(ID,UpadteUser9).getBody());
	}




	@Test
	public void deleteUserTest()
	{   when(userRepo.existsByEmail(String.valueOf(USER2.getEmail()))).thenReturn(Stream.of(USER2).isParallel());
		userRepo.delete(USER2);
		verify(userRepo,times(1)).delete(USER2);
	}


}
