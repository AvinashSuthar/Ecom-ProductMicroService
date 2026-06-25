package com.ecom.product.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
  private Boolean status;
  private String message;
  private T data;
}
