package com.clj.fastble.exception;

public class NotFoundDeviceException extends BleException {
    public NotFoundDeviceException() {
        super(103, "Not Found Device Exception Occurred!");
    }
}
