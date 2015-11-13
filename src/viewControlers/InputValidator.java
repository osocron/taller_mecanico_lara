package viewControlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by osocron on 10/11/15.
 */
public class InputValidator {

    public static boolean textIsLatinButNoPunctuation(String text){

        boolean isTextOnly;
        Pattern pattern = Pattern.compile("^[\\p{L} -]+$");
        Matcher matcher = pattern.matcher(text);
        isTextOnly = matcher.matches();
        return isTextOnly;
    }

    public static boolean textIsNumericOnly(String text){
        boolean isNumericOnly;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }

    public static boolean textIsDecimalOnly(String text, String precisionPostDecimal){
        boolean isNumericOnly;
        Pattern pattern = Pattern.compile("^\\d*\\.?\\d{0,"+precisionPostDecimal+"}$");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }

    public static boolean isRFC(String text){
        boolean isRFC;
        Pattern pattern = Pattern.compile("^[A-Za-z]{4}\\d{6}(?:[A-Za-z\\d]{3})?$");
        Matcher matcher = pattern.matcher(text);
        isRFC = matcher.matches();
        return isRFC;
    }

    public static boolean isPlaca(String text){
        boolean isPlaca;
        Pattern pattern = Pattern.compile("^[A-Za-z]{3}\\d{4}$");
        Matcher matcher = pattern.matcher(text);
        isPlaca = matcher.matches();
        return isPlaca;
    }

}
