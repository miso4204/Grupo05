package com.marketour.aspectos;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;

import org.aspectj.lang.JoinPoint;

import com.marketour.services.*;
import com.marketour.facade.FacadeCompra.*;

public aspect AspectoMoneda {
	int idUsuario = -1;
	
	
	pointcut getIdUsuario():
		call(public * *.getValor(..)) ;

	BigDecimal around(): getIdUsuario(){

		BigDecimal original = proceed();
		System.out.println("ENTROOOOOOOOOOOOOOOOOOOOOO         " + original);

		BigDecimal n = new BigDecimal(123456789, MathContext.DECIMAL64);
		return n;
	}

	pointcut formaPago():
		call(public * *.getValor(..)) ;

	BigDecimal around(): formaPago(){

		BigDecimal original = proceed();
		System.out.println("ENTROOOOOOOOOOOOOOOOOOOOOO         " + original);

		BigDecimal n = new BigDecimal(123456789, MathContext.DECIMAL64);
		return n;
	}

}
