package com.rest_api.ShopverseBackend.todo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class TodoDTO {
  private UUID categoryId;
  private String title;
  private boolean isCompleted;
}
