package com.example;

import java.util.List;

public class FunctionOutput {
  private final List<Approver> approvers;

  public FunctionOutput(List<Approver> approvers) {
    this.approvers = approvers;
  }

  public List<Approver> approvers() {
    return approvers;
  }
}
