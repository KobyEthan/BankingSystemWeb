/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author kobym
 */
//The DA class will have the try block that instantiates this class and then throws the exception
public class RecordNotFoundException extends Exception {
    
    public RecordNotFoundException(String message) {
        super("Record Not Found");
    }
}
