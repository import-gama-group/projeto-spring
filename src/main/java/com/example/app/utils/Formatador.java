package com.example.app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatador {

	public static String formatadorCpf(String cpf) throws Exception {

		if (!cpf.matches("\\d{11}"))
			throw new Exception();
		String retorno = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
		System.out.println(retorno);
		return retorno;
	}

	public static String formatadorPhone(String phone) throws Exception {

		if (!phone.matches("\\d{11}"))
			throw new Exception();
		String retorno = phone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1)$2-$3");
		System.out.println(retorno);
		return retorno;
	}

	public static String formatarData(Date data) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return dateFormat.format(data);
	}
	
	public static Date stringParaDate(String data) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
	    Date dataFormatada = (Date)formatter.parse(data); 
		return dataFormatada;
	}

}
