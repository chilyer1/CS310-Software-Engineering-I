package edu.jsu.mcis;
import java.util.*;

public class ArgsParser{
    
	protected enum DataType{INT, FLOAT, BOOL, STRING};
	
    private List<String> argValues;
    private List<String> argNames;
	private List<DataType> argDataType;
    private String programName;
    private String programDescription;
    private String[] argDescriptions;
    public ArgsParser(){
        argValues = new ArrayList<String>();
        argNames = new ArrayList<String>();
		argDataType = new ArrayList<DataType>();
        programName = "";
        programDescription = "";
        
    }
    
    public void addArgDescriptions(String[] positionalArgs){
        argDescriptions = new String[argNames.size()];
        for(int i =0; i < positionalArgs.length;i++){
            argDescriptions[i] = positionalArgs[i];
        }
    }
    
    public void setProgramDescription(String d){
      programDescription = d; 
    }
    
    public String getProgramDescription(){
        return programDescription;
    }
    
    public void setProgramName(String s){
        programName = s;
    }
    
    public String getProgramName(){
        return programName;
    }


    public int getNumOfArguments(){
        return argValues.size();
    }
    
    public int getNumOfNameArgs(){
        return argNames.size();
    }
    
    public void addArg(String name){
        argNames.add(name);
		argDataType.add(DataType.STRING);
    }
	
	public void addArg(String name, Class<?> t){
		argNames.add(name);
		if(t == int.class){
			argDataType.add(DataType.INT);
		}
		else if(t == float.class){
			argDataType.add(DataType.FLOAT);
		}
		else if(t == boolean.class){
			argDataType.add(DataType.BOOL);
		}
		else{
			argDataType.add(DataType.STRING);
		}
		
	}
    
    private void checkForTooFewArgs(String[] cla, List<String> numNamedArgs, String prgmName)  {
    
        if(cla.length < argNames.size()){
            
            throw new TooFewArgsException(cla, numNamedArgs, prgmName);
            
        }
        
    }
    
	private void checkForTooManyArgs(String[] cla, List<String> numNamedArgs, String prgmName) {
        
		if(cla.length > argNames.size()){
		
			throw new TooManyArgsException(cla, numNamedArgs, prgmName);
		
		}
	
	}
    
    private void checkForHelp(String[] cla, List<String> numArgs, List<String> numNamedArgs, String prgmName, String prgmDescript, String[] argDescript){
       
        if(cla[0].equals("-h") || cla[0].equals("--help")){
            throw new HelpException(numNamedArgs, prgmName, prgmDescript, argDescriptions); 
        }
    }
	
	private void checkForInvalidArgument(List<String>nameOfArgs,String prgmName){
		
		
		
		
	}
    
    public void parse(String[] cla) {
        
           
        for(int i =0; i < cla.length;i++){
             argValues.add(cla[i]);
        } 
        checkForHelp(cla, argValues, argNames, programName, programDescription, argDescriptions);
        checkForTooFewArgs(cla, argNames, programName);
		checkForTooManyArgs(cla, argNames, programName);
        
        
    }
    
	
	//overload getArg? Defeats the purpose of having argDataType, though
    public Object getArg(String name){
        for(int i =0;i < getNumOfNameArgs();i++){
            if(name.equals(argNames.get(i))){
                if(argDataType.get(i) == DataType.INT){
					return Integer.parseInt(argValues.get(i));
				}
				
				else if(argDataType.get(i) == DataType.FLOAT){
					return Float.parseFloat(argValues.get(i));
				}
				
				else if(argDataType.get(i) == DataType.BOOL){
					return Boolean.parseBoolean(argValues.get(i));
				}
				
				else{
					return argValues.get(i);
				}
            }
        }
        return "";
       
        
    }
	
	public DataType getDataType(String name){
		for(int i = 0; i < getNumOfNameArgs();i++){
			if(name.equals(argNames.get(i))){
				return argDataType.get(i);
			}
		}
		return DataType.STRING;
	}
    
    
}