package com.shift4.mapper;


public enum CronInputParam {
    MINUTES(0, 14, "minute"),
    HOURS(1, 16,"hour"),
    DAYS(2, 8,"day of month"),
    MONTH(3, 15,"month"),
    DAY_OF_WEEK(4, 9,"day of week"),
    COMMAND(5, 13,"command");

    public final CronIndexLabel indexLabel;

    CronInputParam(int index, int whitespaceBetweenColumn, String message) {
        this.indexLabel = new CronIndexLabel.TimeLabelBuilder()
                .setIndexes(index)
                .setWhitespaceBetweenColumn(whitespaceBetweenColumn)
                .setLabel(message).build();
    }

    public String getLabel() {
        return this.indexLabel.getLabel();
    }

    public int getIndex() {
        return this.indexLabel.getIndex();
    }

    public int getWhitespaceBetweenColumn() {
        return this.indexLabel.getWhitespaceBetweenColumn();
    }

    public static CronInputParam valueOfIndex(int index) {
        for (CronInputParam frame : values()) {
            if (frame.indexLabel.getIndex() == index) {
                return frame;
            }
        }
        return null;
    }
}