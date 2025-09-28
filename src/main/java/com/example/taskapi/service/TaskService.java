package com.example.taskapi.service;

import com.example.taskapi.model.Task;
import com.example.taskapi.model.TaskExecution;
import com.example.taskapi.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTaskById(String id) {
        return repository.findById(id);
    }

    public Task saveTask(Task task) {
        if (task.getCommand().toLowerCase().contains("rm") || 
            task.getCommand().toLowerCase().contains("shutdown")) {
            throw new IllegalArgumentException("Unsafe command detected!");
        }
        return repository.save(task);
    }

    public boolean deleteTask(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;  // success
        }
        return false; // not found
    }

    public List<Task> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Task executeTask(String id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        TaskExecution execution = new TaskExecution();
        execution.setStartTime(new Date());

        try {
            // Run the command safely
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", task.getCommand());pb.redirectErrorStream(true);
            Process process = pb.start();

            // Capture output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();

            execution.setEndTime(new Date());
            execution.setOutput(output.toString().trim() + "\nExit Code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            execution.setEndTime(new Date());
            execution.setOutput("Error while executing command: " + e.getMessage());
        }

        // Save execution result
        if (task.getTaskExecutions() == null) {
            task.setTaskExecutions(new ArrayList<>());
        }
        task.getTaskExecutions().add(execution);

        return repository.save(task);
    }
}
