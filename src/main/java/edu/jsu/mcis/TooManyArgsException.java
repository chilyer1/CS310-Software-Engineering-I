package edu.jsu.mcis;
import java.util.*;

public class TooManyArgsException extends RuntimeException{
	
	
	public TooManyArgsException(String[] cla, List<String> argNames, String prgmName){
		String extraArgs = "";
		String args = "";
		for(int i = argNames.size(); i < cla.length; i++){
			extraArgs = extraArgs + " " + cla[i];
		}
        for(int i =0; i < argNames.size();i++){
            args += (argNames.get(i) + " ");
        }
        String argSub = args.substring(0, args.length() - 1); 
        System.out.println("usage: java "+ prgmName+" "+argSub + "\n" + prgmName + ".java: error: unrecognized arguments:"+extraArgs);
        
        
    }
}