package org.example.huffmanencodinggui.model.generators.construct;

import java.util.ArrayList;

public class ConstructNaive extends Construct{
    @Override
    public void doWork() {
        canConstruct(this.targetString, "");
    }

    public ConstructDisplay canConstruct(String target, String fragmentUsed) {
        caller.callFunction();

        ConstructDisplay display = new ConstructDisplay();
        display.setFragmentUsed(fragmentUsed);

        if (!target.startsWith(fragmentUsed)) {
            display.setCurrentTarget(target);
            return caller.returnValue(display);
        }
        String newTarget = target.substring(fragmentUsed.length());
        display.setCurrentTarget(newTarget);

        if (newTarget.isEmpty()) {
            display.setFound(true);
        } else {
            ArrayList<ConstructDisplay> childCalls = new ArrayList<>();
            for (String fragment:fragments) {
                childCalls.add(canConstruct(newTarget, fragment));
            }
            display.setFound(childCalls.stream().anyMatch(ConstructDisplay::isFound));
        }
        return caller.returnValue(display);
    }

    @Override
    public String getKey() {
        return "Can Construct Naive";
    }
}
