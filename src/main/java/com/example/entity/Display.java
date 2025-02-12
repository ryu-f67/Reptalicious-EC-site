package com.example.entity;

public enum Display {
    DISPLAY("表示"),
    HIDDEN("非表示"),
    DELETED("削除");
    
    private final String displayStatus;

    Display(String displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String getdisplayStatus() {
        return displayStatus;
    }
}
