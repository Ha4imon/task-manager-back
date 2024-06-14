package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
  private final TaskRepo taskRepo;
  private final ObjectMapper objectMapper;

  @PostMapping("/api/add")
  public void addTask(@RequestBody Task task) {
    log.info("New row:" + taskRepo.save(task));
  }

  @GetMapping("/api/all")
  public List<Task> getAll() throws JsonProcessingException {
    return taskRepo.findAll();
  }

  @GetMapping("/api/find")
  public Task getTask(@RequestParam int id) {
    return taskRepo.findById(id).orElseThrow();
  }

  @DeleteMapping("/api/delete")
  public void deleteTask(@RequestParam int id) {
    taskRepo.deleteById(id);
  }

  @PutMapping("/api/change")
  public String changeTask(@RequestBody Task task) {
    if (!taskRepo.existsById(task.getId())) {
      return "Not exist";
    }

    return taskRepo.save(task).toString();
  }
}
