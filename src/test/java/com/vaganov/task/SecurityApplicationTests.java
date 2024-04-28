package com.vaganov.task;

import com.vaganov.task.config.JwtService;
import com.vaganov.task.tasks.Task;
import com.vaganov.task.tasks.TaskController;
import com.vaganov.task.tasks.TaskDto;
import com.vaganov.task.tasks.TaskService;
import com.vaganov.task.user.Role;
import com.vaganov.task.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SecurityApplicationTests {

	@Mock
	private TaskService taskService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MockMvc mvc;

	@InjectMocks
	private TaskController taskController;

	private List<Task> tasks;

	private TaskDto taskDto;

	@Test
	void generateUserToken(){
		User user = new User(1, "ales", "Alex", "11@mail.ru", "11", null, null,null, null, Role.USER, null);
		String token = jwtService.generateToken(user);
		Assertions.assertNotNull(token);
		boolean isExpired = jwtService.isTokenValid(token, user);
		Assertions.assertTrue(isExpired);
	}

	@Test
	public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/tasks/tasks")).andExpect(status().isForbidden());
	}

	@BeforeEach
	void setUp() {
		tasks = Arrays.asList(new Task(1L, "Task 1", "Description 1", LocalDate.now(), true),
				new Task(2L, "Task 2", "Description 2", LocalDate.now(), false));

	}

	@Test
	void getTasks_ShouldReturnAllTasks() {
		when(taskService.findAllTasks()).thenReturn(tasks);
		List<Task> response = taskController.getAllTasks();
		assertEquals(2, response.size());
	}

	@Test
	void getTask_ShouldReturnTaskById() {
		when(taskService.findTaskById(anyLong())).thenReturn(tasks.get(0));
		Task response = taskController.getTaskById(1L);
		assertEquals(tasks.get(0), response);
	}

	// Тест для метода createTask
	@Test
	void createTask_ShouldSaveNewTask() {
		taskDto= TaskDto.builder().build();
		taskDto.setTitle("Task 1");
		taskDto.setDescription("Description 1");
		taskDto.setDueDate(LocalDate.now());
		taskDto.setCompleted(true);
		Task newTask = new Task(1L, "Task 1", "Description 1", LocalDate.now(), true);
		when(taskService.saveTask(any(TaskDto.class))).thenReturn(newTask);
		assertEquals(taskDto.getDescription(), taskController.newTask(taskDto).getDescription());

	}

	// Тест для метода updateTask
	@Test
	void updateTask_ShouldUpdateExistingTask() {
		Task existingTask = tasks.get(0);
		taskDto= TaskDto.builder().build();
		taskDto.setTitle("Task 1");
		taskDto.setDescription("Description 1");
		taskDto.setDueDate(LocalDate.now());
		taskDto.setCompleted(true);
		when(taskService.updateTask(any(TaskDto.class), eq(existingTask.getId()))).thenReturn(existingTask);
		assertEquals(taskDto.getDescription(), taskController.updateTask(taskDto,tasks.get(0).getId()).getDescription());
	}

	// Тест для метода deleteTask
	@Test
	void deleteTask_ShouldDeleteTaskById() {
		Long id = anyLong();
		when(taskService.deleteTask(id)).thenReturn("Id " + id +" deleted successfully!");
		String result = taskController.deleteTask(1L);
		assertEquals("Id " + 0 +" deleted successfully!", result);
	}


}
