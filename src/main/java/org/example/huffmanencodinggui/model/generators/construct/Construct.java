package org.example.huffmanencodinggui.model.generators.construct;

import org.example.huffmanencodinggui.model.generators.TreeGenerator;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Construct extends TreeGenerator<ConstructDisplay> {
    protected String targetString;
    protected ArrayList<String> fragments;

    @Override
    public String getExampleArgs() {
        return "Hello World!,Wor,He,Hell,llo, W,or,ld,!";
    }

    @Override
    public String getErrorMessage() {
        return "targetString,comma,the,list,of,text,fragments,composing,target,String";
    }

    @Override
    public void parseArgs(String args) {
        String[] argList = args.split(",");
        if (argList.length < 2) throw new IllegalArgumentException();
        this.targetString = argList[0];
        this.fragments = new ArrayList<>();
        fragments.addAll(Arrays.asList(argList).subList(1, argList.length));
    }
}
