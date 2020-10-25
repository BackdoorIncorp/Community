package net.backdoorinc.community.enums;

public enum MessagesEnum {
    NO_PERMISSION("darfst das nicht!"),
    NOT_ONLINE("Spieler ist nicht online");

    private String Message;

    MessagesEnum(String Message) {
        this.Message = Message;
    }

    public String getMessage() {
        return this.Message;
    }
}
