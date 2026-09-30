package JavaTag01;

/**
 * Stores the progress of the current room puzzle.
 */
public class GameState {
    private boolean clockInspected;
    private boolean paintingInspected;
    private boolean doorInspected;
    private boolean cabinetInspected;
    private boolean cabinetUnlocked;
    private int attempts;

    public void inspectClock() {
        clockInspected = true;
    }

    public void inspectPainting() {
        paintingInspected = true;
    }

    public void inspectDoor() {
        doorInspected = true;
    }

    public void inspectCabinet() {
        cabinetInspected = true;
    }

    public boolean isClockInspected() {
        return clockInspected;
    }

    public boolean isPaintingInspected() {
        return paintingInspected;
    }

    public boolean isDoorInspected() {
        return doorInspected;
    }

    public boolean isCabinetInspected() {
        return cabinetInspected;
    }

    public boolean isCabinetUnlocked() {
        return cabinetUnlocked;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getClueCount() {
        int count = 0;

        if (clockInspected) {
            count++;
        }

        if (paintingInspected) {
            count++;
        }

        if (doorInspected) {
            count++;
        }

        if (cabinetInspected) {
            count++;
        }

        return count;
    }

    public boolean isPuzzleReady() {
        return clockInspected
            && paintingInspected
            && doorInspected
            && cabinetInspected;
    }

    public void recordFailedAttempt() {
        attempts++;
    }

    public void unlockCabinet() {
        cabinetUnlocked = true;
    }
}
