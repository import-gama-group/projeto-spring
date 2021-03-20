package com.example.app.utils;



public  class Formatador {

public static String formatadorCpf(String cpf) throws Exception {
		
		if(!cpf.matches("\\d{11}"))
			throw new Exception();
		String retorno = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4"); 
		System.out.println(retorno);
		return retorno; 
	}

public static String formatadorPhone(String phone) throws Exception {
	
	if(!phone.matches("\\d{11}"))
		throw new Exception();
	String retorno = phone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1)$2-$3"); 
	System.out.println(retorno);
	return retorno;
}
	
}
