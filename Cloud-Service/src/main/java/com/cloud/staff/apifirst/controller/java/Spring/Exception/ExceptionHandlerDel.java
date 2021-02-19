package com.cloud.staff.apifirst.controller.java.Spring.Exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 统一异常处理
 * @author zhaoHB
 *
 */
@ControllerAdvice
public class ExceptionHandlerDel {
	
	
	/**
	 * 参数校验异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public String exceptionHandlerDel(MethodArgumentNotValidException ex) {
		BindingResult bindingResult= ex.getBindingResult();
		StringBuilder errorMessage=new StringBuilder(bindingResult.getFieldErrors().size()*16);
		for(int i=0,j=bindingResult.getFieldErrors().size();i<j;i++) {
			if(i>0) {
				errorMessage.append(",");
			}
			FieldError fileError=bindingResult.getFieldErrors().get(i);
			errorMessage.append(fileError.getField()+":"+fileError.getDefaultMessage());
		}
		return errorMessage.toString();
	}
	
	/**
	 * 未知异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String exceptionHandlerDel(Exception e) {
		return e.getMessage();
	}
	
	

}
