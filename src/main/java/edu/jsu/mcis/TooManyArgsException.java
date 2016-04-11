package edu.jsu.mcis;
import java.util.*;

/**<p>
 * This class allows the user to ...
 *<p>
 *@author Avery Whitecotton
 *@author Adam Butler
 *@author Colby Morris
 *@author Grady Houlditch
 *@author Colby Hilyer
 *@author Cody McGee
 */

public class TooManyArgsException extends RuntimeException{
	private String exceptionOutput= "";
	
	/**
	 *Class constructor. 
	 *
	 *@param preMessage Pre-message used to contruct the exceptionOutput
	 *@param cla Command Line Arguments list
	 *@param arguments Program arguments list
	 *@param prgmName Program name
 	 *@param optArgs Optional arguments list
	 *@param unknownAN unknown argument name list
	 *@param unknownAV unknown argument value list
	 */

	public TooManyArgsException(String preMessage,String[] cla, List<Argument> arguments, String prgmName, List<OptionalArgument> optArgs, List<String> unknownAN, List<String> unknownAV){

		String extraArgs = "";
		if(optArgs.size() == 0){
            for(int i = arguments.size(); i < cla.length; i++){
                extraArgs = extraArgs + " " + cla[i];
	        }
        }
		
        else{
            List<String> temp = new ArrayList<String>();
            for(int i =0; i < arguments.size();i++){
                temp.add(arguments.get(i).getValue());
            } 
            List<String> oAN = new ArrayList<String>();
            List<String> oAV = new ArrayList<String>();
            for(int i = 0; i < optArgs.size();i++){
                oAN.add(optArgs.get(i).getName());
                oAV.add(optArgs.get(i).getValue());
            }
            for(int i = 0; i < cla.length;i++){
                if(!oAN.contains(cla[i]) && !oAV.contains(cla[i]) && !temp.contains(cla[i]) && !unknownAN.contains(cla[i]) && !unknownAV.contains(cla[i])){
                    extraArgs = extraArgs + " " + cla[i];
                    
                }
               
            }
        }
        
        


       
         
		exceptionOutput = preMessage + "\n" + prgmName+".java: error: unrecognized arguments:"+extraArgs;
        

    }
	
	 /**
	 *This method adds a ... to the program.
	 *
	 *@return exceptionOutput Exception output message
	 */
	public String getExceptionOutput(){
		return exceptionOutput;
	}
}