package edu.jsu.mcis;
import java.util.*;
public class TooFewArgsException extends RuntimeException {
    private String exceptionOutput= "";
    
    public TooFewArgsException(String preMsg, String prgmName, String[] cla, List<String> argNames){
        String args = "";
        String missingArgNames = "";
        
        if(cla.length == 0){
            
            for(int i = 0; i < argNames.size(); i++){
                args += argNames.get(i) + " ";
            }
            String argsSub = args.substring(0, args.length()- 1);
            missingArgNames = argsSub;
        }
        else{
            args = "";
            for(int i = cla.length; i < argNames.size() ;i++){
               args += (argNames.get(i) + " "); 
            }
            String argsSub = args.substring(0, args.length() - 1);
            missingArgNames = argsSub;    
            
            
        }
        exceptionOutput = preMsg + prgmName + ".java: error: the following arguments are required: "+missingArgNames;
        System.out.println(getExceptionOutput());
    }
    public String getExceptionOutput(){
		return exceptionOutput;
	}
    
}