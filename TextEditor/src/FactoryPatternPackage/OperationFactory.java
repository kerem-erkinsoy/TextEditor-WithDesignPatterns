package FactoryPatternPackage;

public class OperationFactory {
    
    public FileOperations getOperations(String opType){
        if(opType==null){
            return null;
        }
        if(opType.equalsIgnoreCase("SAVE")){
            return new SaveFile();
        }
        else if(opType.equalsIgnoreCase("OPEN")){
            return new OpenFile();
        }
        else if(opType.equalsIgnoreCase("NEW")){
            return new NewFile();
        }
        return null;
    }
}
