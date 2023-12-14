package com.example.pizza;

import com.example.pizza.models.MealCategory;
import com.example.pizza.models.MealModel;
import com.example.pizza.models.PizzeriaModel;
import com.example.pizza.repositories.MealRepository;
import com.example.pizza.repositories.PizzeriaRepository;
import com.example.pizza.services.MealService;
import com.example.pizza.services.PizzeriaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class PizzaApplicationTests {
	@Mock
	private MealRepository mealRepository;

	@Mock
	private PizzeriaRepository pizzeriaRepository;

	@InjectMocks
	private MealService mealService;

	@InjectMocks
	private PizzeriaService pizzeriaService;

	@Test
	public void testGetAllMeals() {
		Set<PizzeriaModel> pizzerias = new HashSet<PizzeriaModel>();
		MealModel testMeal1 = new MealModel(1L, "TEST Pizza", "TEST description ABCDEFG", 12, MealCategory.Pizza, pizzerias);
		MealModel testMeal2 = new MealModel(2L, "TEST Salad", "TEST description ABCDEFG", 12, MealCategory.Salad, pizzerias);
		when(mealRepository.findAll()).thenReturn(Arrays.asList(testMeal1, testMeal2));

		Set<MealModel> result = mealService.getAllMeals();

		verify(mealRepository).findAll();

		Set<MealModel> expected = new HashSet<>(Arrays.asList(testMeal1, testMeal2));
		assertEquals(expected, result);
	}

	@Test
	public void testGetMealById() {
		Set<PizzeriaModel> pizzerias = new HashSet<PizzeriaModel>();
		MealModel testMeal = new MealModel(3L, "TEST Dessert", "TEST description ABCDEFG", 12, MealCategory.Dessert, pizzerias);

		when(mealRepository.findById(1L)).thenReturn(Optional.of(testMeal));

		Optional<MealModel> result = mealService.getMealById(1L);

		verify(mealRepository).findById(1L);

		assertEquals(Optional.of(testMeal), result);
	}

	@Test
	public void testRemovePizzeriaFromMeal() {
		Set<PizzeriaModel> pizzerias = new HashSet<PizzeriaModel>();
		Set<MealModel> meals = new HashSet<MealModel>();
		MealModel meal = new MealModel(4L, "TEST Pasta1", "TEST description ABCDEFG", 12, MealCategory.Pasta, pizzerias);
		PizzeriaModel pizzeria = new PizzeriaModel(1L, "Test Pizzeria", "3805678345", "Hauptstr. 1 12345", "TEST description", meals);

		when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));
		when(pizzeriaRepository.findById(1L)).thenReturn(Optional.of(pizzeria));

		mealService.removePizzeriaFromMeal(1L, 1L);

		verify(mealRepository).findById(1L);
		verify(pizzeriaRepository).findById(1L);

		verify(mealRepository).save(meal);
		verify(pizzeriaRepository).save(pizzeria);
	}

	@Test
	public void testSaveMeal() {
		Set<PizzeriaModel> pizzerias = new HashSet<PizzeriaModel>();
		MealModel meal = new MealModel(8L, "TEST Dessert2", "TEST description ABCDEFG", 12, MealCategory.Dessert, pizzerias);

		when(mealRepository.save(meal)).thenReturn(meal);

		MealModel result = mealService.saveMeal(meal);

		verify(mealRepository).save(meal);

		assertEquals(meal, result);
	}

	@Test
	public void testRemovePizzeriaFromMealSuccess() {
		Set<PizzeriaModel> pizzerias = new HashSet<PizzeriaModel>();
		Set<MealModel> meals = new HashSet<MealModel>();
		MealModel meal = new MealModel(8L, "TEST Dessert2", "TEST description ABCDEFG", 12, MealCategory.Dessert, pizzerias);
		PizzeriaModel pizzeria = new PizzeriaModel(1L, "Test Pizzeria", "3805678345", "Hauptstr. 1 12345", "TEST description", meals);

		when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));
		when(pizzeriaRepository.findById(1L)).thenReturn(Optional.of(pizzeria));

		mealService.removePizzeriaFromMeal(1L, 1L);

		verify(mealRepository).findById(1L);
		verify(pizzeriaRepository).findById(1L);

		verify(mealRepository).save(meal);
		verify(pizzeriaRepository).save(pizzeria);
	}

	@Test
	public void testRemovePizzeriaFromMealMealNotFound() {
		when(mealRepository.findById(1L)).thenReturn(Optional.empty());

		mealService.removePizzeriaFromMeal(1L, 1L);

		verify(mealRepository).findById(1L);
	}

	@Test
	public void testRemovePizzeriaFromMealPizzeriaNotFound() {
		when(mealRepository.findById(1L)).thenReturn(Optional.of(new MealModel()));
		when(pizzeriaRepository.findById(1L)).thenReturn(Optional.empty());

		mealService.removePizzeriaFromMeal(1L, 1L);

		verify(mealRepository).findById(1L);
		verify(pizzeriaRepository).findById(1L);
	}

	@Test
	public void testGetAllPizzeriasWithName() {
		when(pizzeriaRepository.findByName("Test Pizzeria")).thenReturn(Collections.singletonList(new PizzeriaModel()));

		pizzeriaService.getAllPizzerias("Test Pizzeria");

		verify(pizzeriaRepository).findByName("Test Pizzeria");

		verifyNoMoreInteractions(pizzeriaRepository);
	}

	@Test
	public void testGetAllPizzeriasWithoutName() {
		when(pizzeriaRepository.findAll()).thenReturn(Collections.singletonList(new PizzeriaModel()));

		pizzeriaService.getAllPizzerias(null);
		verify(pizzeriaRepository).findAll();
		verifyNoMoreInteractions(pizzeriaRepository);
	}

	@Test
	public void testGetPizzeriaById() {
		when(pizzeriaRepository.findById(1L)).thenReturn(Optional.of(new PizzeriaModel()));
		pizzeriaService.getPizzeriaById(1L);
		verify(pizzeriaRepository).findById(1L);
	}

	@Test
	public void testSavePizzeriaSuccess() {
		Set<MealModel> meals = new HashSet<MealModel>();
		PizzeriaModel pizzeria = new PizzeriaModel(1L, "Test Pizzeria", "3805678345", "Hauptstr. 1 12345", "TEST description", meals);
		pizzeriaService.savePizzeria(pizzeria);
		verify(pizzeriaRepository).save(pizzeria);
	}

	@Test
	public void testSavePizzeriaNull() {
		assertThrows(IllegalArgumentException.class, () -> pizzeriaService.savePizzeria(null));

		verifyNoMoreInteractions(pizzeriaRepository);
	}
}