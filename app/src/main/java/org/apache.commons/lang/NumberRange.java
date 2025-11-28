package org.apache.commons.lang;

import java.util.Objects;
import org.apache.commons.lang.text.StrBuilder;

/* loaded from: classes3.dex */
public final class NumberRange {
    private final Number max;
    private final Number min;

    public NumberRange(Number number) {
        Objects.requireNonNull(number, "The number must not be null");
        this.min = number;
        this.max = number;
    }

    public NumberRange(Number number, Number number2) {
        Objects.requireNonNull(number, "The minimum value must not be null");
        Objects.requireNonNull(number2, "The maximum value must not be null");
        if (number2.doubleValue() < number.doubleValue()) {
            this.max = number;
            this.min = number;
        } else {
            this.min = number;
            this.max = number2;
        }
    }

    public Number getMinimum() {
        return this.min;
    }

    public Number getMaximum() {
        return this.max;
    }

    public boolean includesNumber(Number number) {
        return number != null && this.min.doubleValue() <= number.doubleValue() && this.max.doubleValue() >= number.doubleValue();
    }

    public boolean includesRange(NumberRange numberRange) {
        return numberRange != null && includesNumber(numberRange.min) && includesNumber(numberRange.max);
    }

    public boolean overlaps(NumberRange numberRange) {
        if (numberRange == null) {
            return false;
        }
        return numberRange.includesNumber(this.min) || numberRange.includesNumber(this.max) || includesRange(numberRange);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NumberRange)) {
            return false;
        }
        NumberRange numberRange = (NumberRange) obj;
        return this.min.equals(numberRange.min) && this.max.equals(numberRange.max);
    }

    public int hashCode() {
        return ((629 + this.min.hashCode()) * 37) + this.max.hashCode();
    }

    public String toString() {
        StrBuilder strBuilder = new StrBuilder();
        if (this.min.doubleValue() < 0.0d) {
            strBuilder.append('(').append(this.min).append(')');
        } else {
            strBuilder.append(this.min);
        }
        strBuilder.append('-');
        if (this.max.doubleValue() < 0.0d) {
            strBuilder.append('(').append(this.max).append(')');
        } else {
            strBuilder.append(this.max);
        }
        return strBuilder.toString();
    }
}