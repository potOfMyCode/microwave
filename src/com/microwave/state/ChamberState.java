package com.microwave.state;

public enum ChamberState {
    DOOR_CLOSED_NO_PROCESS("Door closed. No Process exist."),
    DOOR_OPENED_NO_PROCESS("Door opened. Pick a dish"),
    DOOR_CLOSED_COOKING("Door closed. Cooking..."),
    DOOR_CLOSED_PAUSE("Door closed. Cooking on pause"),
    DOOR_OPEN_PAUSE("Door opened. Cooking on pause")
    ;

    private String value;

    ChamberState(String value) {
        this.value = value;
    }

    public static ChamberState getByString(String text) {
        switch (text){
            case "Door closed. No Process exist.":
                return DOOR_CLOSED_NO_PROCESS;
            case "Door opened. Pick a dish":
                return DOOR_OPENED_NO_PROCESS;
            case "Door closed. Cooking...":
                return DOOR_CLOSED_COOKING;
            case "Door closed. Cooking on pause":
                return DOOR_CLOSED_PAUSE;
            case "Door opened. Cooking on pause":
                return DOOR_OPEN_PAUSE;
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
