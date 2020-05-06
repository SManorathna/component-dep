package com.wso2telco.workflow.model;


import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "prodapimgtdb", schema = "app_operation_history")
public class AppOperationHistoryDTO {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name ="ID")
  private int id;

  @Column(name ="APPLICATION_ID")
  private int applicationId;

  @Column(name ="SUBSCRIPTION_ID")
  private int subscriptionId;

  @Column(name ="OPERATION")
  private String operation;

  @Column(name ="DESCRIPTION")
  private String description;

  @Column(name ="PERFORMED_BY")
  private String performedBy;

  @Column(name ="PERFORMED_AT")
  private LocalDateTime performedAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(int applicationId) {
    this.applicationId = applicationId;
  }

  public int getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(int subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPerformedBy() {
    return performedBy;
  }

  public void setPerformedBy(String performedBy) {
    this.performedBy = performedBy;
  }

  public LocalDateTime getPerformedAt() {
    return performedAt;
  }

  public void setPerformedAt(LocalDateTime performedAt) {
    this.performedAt = performedAt;
  }
}
