package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MethodParser {

    public static MethodSignature parseFunction(String signatureString) {

        // Getting method arguments
        int indexOfArgsBeginning = signatureString.indexOf("(");
        int indexOfArgsEnding = signatureString.indexOf(")");
        boolean hasArgs = (indexOfArgsBeginning + 1 != indexOfArgsEnding);
        List<MethodSignature.Argument> argsList = new ArrayList<>();

        if (hasArgs) {

            String argumentsString = signatureString.substring(indexOfArgsBeginning + 1, indexOfArgsEnding);
            String[] splittedArgumentsString = argumentsString.split(", ");
            int argsCount = splittedArgumentsString.length;
            String type;
            String name;
            for (int i = 0; i < argsCount; i++) {
                type = splittedArgumentsString[i].split(" ")[0];
                name = splittedArgumentsString[i].split(" ")[1];
                MethodSignature.Argument arg = new MethodSignature.Argument(type, name);
                argsList.add(i, arg);
            }
        }

        // Getting method name, type and access modifier
        String mainString = signatureString.substring(0, indexOfArgsBeginning);
        String[] splittedMainString = mainString.split(" ");
        int lengthOfSplittedMainString = splittedMainString.length;

        String methodName = splittedMainString[lengthOfSplittedMainString-1];
        String returnType = splittedMainString[lengthOfSplittedMainString-2];
        String accessModifier = "";
        if (lengthOfSplittedMainString == 3) {
            accessModifier = splittedMainString[0];
        }


        // Constructing
        MethodSignature signatureOutput;
        if (hasArgs) {
            signatureOutput = new MethodSignature(methodName, argsList);
        } else {
            signatureOutput = new MethodSignature(methodName);
        }
        signatureOutput.setReturnType(returnType);

        if (!Objects.equals(accessModifier, "")) {
            signatureOutput.setAccessModifier(accessModifier);
        }
        return signatureOutput;
    }
}