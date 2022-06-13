package com.test.pro.common.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.test.pro.common.util.GenericBuilder;
import com.test.pro.exception.AppException;
import com.test.pro.exception.BusinessException;
import com.test.pro.vo.ErrorInfo;
import com.test.pro.vo.ResponseVo;



/***
 * 异常处理
 * 
 * @author hai
 *
 */
//@ControllerAdvice
@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class GlobalExceptionHandler {
	/**
	 * 参数验证异常
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseVo handler(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
//        return Result.fail(objectError.getDefaultMessage());
		return setReponseVo(null, e.getMessage());
	}

	/**
	 * 业务异常
	 */
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(value = BusinessException.class)
	public ResponseVo handlerBusinessException(BusinessException e) {
		return setReponseVo(e.getCode(), e.getMessage());
	}

	/**
	 * 系统异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = AppException.class)
	public ResponseVo handlerSystemException(AppException e) {
		return setReponseVo(e.getCode(), e.getMessage());
	}

	/**
	 * 其它异常
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseVo handlerException(Exception e) {
		return setReponseVo(null, e.getMessage());
	}

	private ResponseVo setReponseVo(String code, String msg) {
		ErrorInfo errorInfo = GenericBuilder.of(ErrorInfo::new).with(ErrorInfo::setCode, code)
				.with(ErrorInfo::setMsg, msg).build();
		ResponseVo responseVo = GenericBuilder.of(ResponseVo::new).with(ResponseVo::setErrorInfo, errorInfo).build();
		return responseVo;
	}
}
