package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] stringsSeparatedByOpeningBrace = signatureString.split("[(]");
        String[] stringsBeforeOpeningBrace = stringsSeparatedByOpeningBrace[0].split(" ");
        String accessModifier = "", returnType, methodName;
        if (stringsBeforeOpeningBrace.length == 3) {
            accessModifier = stringsBeforeOpeningBrace[0];
            returnType = stringsBeforeOpeningBrace[1];
            methodName = stringsBeforeOpeningBrace[2];
        } else {
            returnType = stringsBeforeOpeningBrace[0];
            methodName = stringsBeforeOpeningBrace[1];
        }
        String[] arguments = stringsSeparatedByOpeningBrace[1].replace(")", "").split(", ");
        List<MethodSignature.Argument> listOfArguments = new ArrayList<>();
        if (arguments.length != 1) {
            for (String argument : arguments) {
                String[] strings = argument.split(" ");
                listOfArguments.add(new MethodSignature.Argument(strings[0], strings[1]));
            }
        }
        MethodSignature methodSignature = new MethodSignature(methodName, listOfArguments);
        if (accessModifier.length() != 0) {
            methodSignature.setAccessModifier(accessModifier);
        }
        methodSignature.setReturnType(returnType);
        return methodSignature;
    }
}
