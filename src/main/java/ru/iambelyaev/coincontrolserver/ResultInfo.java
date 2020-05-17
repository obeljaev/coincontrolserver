package ru.iambelyaev.coincontrolserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.HashMap;

public enum ResultInfo {
    OK(0),
    NameIsNull(1),
    PasswordIsNull(2),
    IdIsNotNull(3),
    AlreadyExist(4),
    NotFoundId(5),
    IdIsNull(6);

    private int value;
    private static Map map = new HashMap<>();

    private ResultInfo(int value) {
        this.value = value;
    }

    static {
        for (ResultInfo resultInfo : ResultInfo.values()) {
            map.put(resultInfo.value, resultInfo);
        }
    }

    public static ResultInfo valueOf(int errorType) {
        return (ResultInfo) map.get(errorType);
    }

    public int getValue() {
        return value;
    }
}