package org.primeTimeHotel.Domain_Model_Objects;

public enum ReservationStatus {
    SCHEDULED(0),
    CANCELED(1),
    CHECKED_IN(2),
    CHECKED_OUT(3);

    private final int code;

    ReservationStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ReservationStatus fromCode(int code) {
        for (ReservationStatus status : ReservationStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
