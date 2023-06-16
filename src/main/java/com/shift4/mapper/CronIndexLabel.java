package com.shift4.mapper;

import static com.shift4.validator.CommonValidator.isNull;
import static com.shift4.validator.CommonValidator.validateInputParam;

public class CronIndexLabel {
    private final String label;
    private final int index;
    private final int whitespaceBetweenColumn;

    private CronIndexLabel(TimeLabelBuilder builder){
        this.index = builder.index;
        this.label = builder.label;
        this.whitespaceBetweenColumn = builder.whitespaceBetweenColumn;
    }

    public String getLabel() {
        return label;
    }

    public int getIndex() {
        return index;
    }

    public int getWhitespaceBetweenColumn() {
        return whitespaceBetweenColumn;
    }

    public static class TimeLabelBuilder{
        private String label;
        private int index;
        private int whitespaceBetweenColumn;

        public TimeLabelBuilder(){
        }

        public TimeLabelBuilder setLabel(String label) {
            validateInputParam(isNull, label,  "Label on CronIndexLabel object cannot be null");
            this.label = label;
            return this;
        }

        public TimeLabelBuilder setIndexes(int index) {
            this.index = index;
            return this;
        }

        public TimeLabelBuilder setWhitespaceBetweenColumn(int whitespaceBetweenColumn) {
            this.whitespaceBetweenColumn = whitespaceBetweenColumn;
            return this;
        }

        public CronIndexLabel build(){
            return new CronIndexLabel(this);
        }

    }

}
