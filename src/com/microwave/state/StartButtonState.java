package com.microwave.state;

public enum StartButtonState {
    START("start"),
    PAUSE("pause"),
    RESUME("resume");

    private String value;

    StartButtonState(String value){
        this.value = value;
    }

    public static StartButtonState getByStringName(String text) {
        switch(text){
            case "start":
                return START;
            case "pause":
                return PAUSE;
            case "resume":
                return RESUME;
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
