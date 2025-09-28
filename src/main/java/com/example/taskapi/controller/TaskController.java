package com.example.taskapi.controller;

import com.example.taskapi.model.Task;
import com.example.taskapi.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // GET /tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // GET /tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /tasks
    @PutMapping
    public ResponseEntity<Task> createOrUpdateTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.saveTask(task));
    }

    // DELETE /tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id) {
    boolean deleted = taskService.deleteTask(id);
    if (deleted) {
        return ResponseEntity.ok("Task with id " + id + " deleted successfully.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Task with id " + id + " not found.");
    }
}

    // GET /tasks/search?name=xxx
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasksByName(@RequestParam String name) {
        List<Task> tasks = taskService.findByName(name);
        return tasks.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(tasks);
    }

    // PUT /tasks/{id}/execute
    @PutMapping("/{id}/execute")
    public ResponseEntity<Task> executeTask(@PathVariable String id) {
        return ResponseEntity.ok(taskService.executeTask(id));
    }
}
