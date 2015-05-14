package com.marketour.aspectos;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

import org.aspectj.lang.JoinPoint;

import com.marketour.services.*;
import com.marketour.facade.FacadeCompra.*;
import com.marketour.persistence.Repository;
public aspect AspectoMoneda {
	int idUsuario = -1;
	com.marketour.persistence.Repository<com.marketour.domain.Usuario> repository = new com.marketour.persistence.Repository<com.marketour.domain.Usuario>(com.marketour.domain.Usuario.class);
	com.marketour.persistence.Repository<com.marketour.domain.Moneda> repositorymoneda = new com.marketour.persistence.Repository<com.marketour.domain.Moneda>(com.marketour.domain.Moneda.class);
	
	pointcut getIdUsuario():
		call(public * *.authenticate(..)) ;
	 after() returning(Object	r): getIdUsuario(){

		 HashMap<String, String> map=(HashMap<String, String>) r;
		idUsuario=   Integer.parseInt( map.get("id"));	
		
	}

	pointcut conversionMonedaDomainMostrar():
		call(public * com.marketour.domain.*.getValor(..)) ;

	BigDecimal around(): conversionMonedaDomainMostrar(){		 
		BigDecimal original = proceed();
		if (idUsuario>0){			
			com.marketour.domain.Usuario usuario =repository.FindByIdAspecto(idUsuario);	
			if (usuario.getMoneda()!=null){
				com.marketour.domain.Moneda moneda =repositorymoneda.FindByIdAspecto(usuario.getMoneda().getId());
				original=original.multiply(moneda.getConversion());
				System.out.println("MONEDAAAAA:" + moneda.getDescripcion());	
			} 
			
		}else{
			System.out.println("LOGEARSE PARA TIPO DE MONEDA         ");
		}
		

		
		return original;
	}
	
	pointcut conversionMonedaBusinessCompra():
		call(public * com.marketour.business.*.getValor(..)) ;

	BigDecimal around(): conversionMonedaBusinessCompra(){		
		BigDecimal original = proceed();
		if (idUsuario>0){			
			com.marketour.domain.Usuario usuario =repository.FindByIdAspecto(idUsuario);	
			if (usuario.getMoneda()!=null){
				com.marketour.domain.Moneda moneda =repositorymoneda.FindByIdAspecto(usuario.getMoneda().getId());
				original=original.divide(moneda.getConversion(), 2, RoundingMode.HALF_UP);
				System.out.println("MONEDAAAAA:" + moneda.getDescripcion());	
			}
			
		}else{
			System.out.println("LOGEARSE PARA TIPO DE MONEDA         ");
		}
		

		
		return original;
	}
	
	
	

}
