package com.example.entity;

public enum LogicalDeleteStatus {
    ACTIVE("有効"),
    DELETED("削除済");

    private final String logicalDeleteStatus;

    LogicalDeleteStatus(String logicalDeleteStatus) {
        this.logicalDeleteStatus = logicalDeleteStatus;
    }

    public String getLogicalDeleteStatus() {
        return logicalDeleteStatus;
    }
}
