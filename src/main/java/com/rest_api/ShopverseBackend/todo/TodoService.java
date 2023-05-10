package com.rest_api.ShopverseBackend.todo;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  @Autowired
  private TodoRepository todoRepository;

  public List<Todo> findAll() {
    return todoRepository.findAll();
  }

  public Todo findById(UUID id) {
    return todoRepository.findById(id).orElse(null);
  }

  public void deleteById(UUID id) {
    todoRepository.deleteById(id);
  }

  public Todo createOne(Todo todo) {
    return todoRepository.save(todo);
  }
}
