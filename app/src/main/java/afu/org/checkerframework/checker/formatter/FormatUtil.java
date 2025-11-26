package afu.org.checkerframework.checker.formatter;

import afu.org.checkerframework.checker.formatter.qual.ConversionCategory;
import afu.org.checkerframework.checker.formatter.qual.ReturnsFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.IllegalFormatException;
import java.util.MissingFormatArgumentException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public class FormatUtil {
    private static final String formatSpecifier = "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])";
    private static Pattern fsPattern = Pattern.compile(formatSpecifier);

    private static class Conversion {
        private final ConversionCategory cath;
        private final int index;

        public Conversion(char c, int i) {
            this.index = i;
            this.cath = ConversionCategory.fromConversionChar(c);
        }

        int index() {
            return this.index;
        }

        ConversionCategory category() {
            return this.cath;
        }
    }

    @ReturnsFormat
    public static String asFormat(String str, ConversionCategory... conversionCategoryArr) throws IllegalFormatException {
        ConversionCategory[] parameterCategories = formatParameterCategories(str);
        if (parameterCategories.length != conversionCategoryArr.length) {
            throw new ExcessiveOrMissingFormatArgumentException(conversionCategoryArr.length, parameterCategories.length);
        }
        for (int i = 0; i < conversionCategoryArr.length; i++) {
            if (conversionCategoryArr[i] != parameterCategories[i]) {
                throw new IllegalFormatConversionCategoryException(conversionCategoryArr[i], parameterCategories[i]);
            }
        }
        return str;
    }

    public static void tryFormatSatisfiability(String str) throws IllegalFormatException {
        String.format(str, (Object[]) null);
    }

    public static ConversionCategory[] formatParameterCategories(String str) throws IllegalFormatException {
        tryFormatSatisfiability(str);
        Conversion[] conversionArr = parse(str);
        HashMap map = new HashMap();
        int iMax = -1;
        int i = -1;
        int i2 = -1;
        for (Conversion conversion : conversionArr) {
            int iIndex = conversion.index();
            if (iIndex != -1) {
                if (iIndex != 0) {
                    i2 = iIndex - 1;
                } else {
                    i++;
                    i2 = i;
                }
            }
            iMax = Math.max(iMax, i2);
            map.put(Integer.valueOf(i2), ConversionCategory.intersect(map.containsKey(Integer.valueOf(i2)) ? (ConversionCategory) map.get(Integer.valueOf(i2)) : ConversionCategory.UNUSED, conversion.category()));
        }
        ConversionCategory[] conversionCategoryArr = new ConversionCategory[iMax + 1];
        for (int i3 = 0; i3 <= iMax; i3++) {
            conversionCategoryArr[i3] = map.containsKey(Integer.valueOf(i3)) ? (ConversionCategory) map.get(Integer.valueOf(i3)) : ConversionCategory.UNUSED;
        }
        return conversionCategoryArr;
    }

    private static int indexFromFormat(Matcher matcher) {
        String strGroup = matcher.group(1);
        if (strGroup != null) {
            return Integer.parseInt(strGroup.substring(0, strGroup.length() - 1));
        }
        return (matcher.group(2) == null || !matcher.group(2).contains(String.valueOf(Typography.less))) ? 0 : -1;
    }

    private static char conversionCharFromFormat(Matcher matcher) {
        String strGroup = matcher.group(5);
        if (strGroup == null) {
            return matcher.group(6).charAt(0);
        }
        return strGroup.charAt(0);
    }

    private static Conversion[] parse(String str) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = fsPattern.matcher(str);
        while (matcher.find()) {
            char cConversionCharFromFormat = conversionCharFromFormat(matcher);
            if (cConversionCharFromFormat != '%' && cConversionCharFromFormat != 'n') {
                arrayList.add(new Conversion(cConversionCharFromFormat, indexFromFormat(matcher)));
            }
        }
        return (Conversion[]) arrayList.toArray(new Conversion[arrayList.size()]);
    }

    public static class ExcessiveOrMissingFormatArgumentException extends MissingFormatArgumentException {
        private static final long serialVersionUID = 17000126;
        private final int expected;
        private final int found;

        public ExcessiveOrMissingFormatArgumentException(int i, int i2) {
            super("-");
            this.expected = i;
            this.found = i2;
        }

        public int getExpected() {
            return this.expected;
        }

        public int getFound() {
            return this.found;
        }

        @Override // java.util.MissingFormatArgumentException, java.lang.Throwable
        public String getMessage() {
            return String.format("Expected %d arguments but found %d.", Integer.valueOf(this.expected), Integer.valueOf(this.found));
        }
    }

    public static class IllegalFormatConversionCategoryException extends IllegalFormatConversionException {
        private static final long serialVersionUID = 17000126;
        private final ConversionCategory expected;
        private final ConversionCategory found;

        public IllegalFormatConversionCategoryException(ConversionCategory conversionCategory, ConversionCategory conversionCategory2) {
            super(conversionCategory.chars.length() == 0 ? '-' : conversionCategory.chars.charAt(0), conversionCategory2.types == null ? Object.class : conversionCategory2.types[0]);
            this.expected = conversionCategory;
            this.found = conversionCategory2;
        }

        public ConversionCategory getExpected() {
            return this.expected;
        }

        public ConversionCategory getFound() {
            return this.found;
        }

        @Override // java.util.IllegalFormatConversionException, java.lang.Throwable
        public String getMessage() {
            return String.format("Expected category %s but found %s.", this.expected, this.found);
        }
    }
}
