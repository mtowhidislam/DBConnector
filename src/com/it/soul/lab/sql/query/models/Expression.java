package com.it.soul.lab.sql.query.models;

import java.util.ArrayList;
import java.util.List;

import com.it.soul.lab.sql.query.SQLQuery.Operator;
import com.it.soul.lab.sql.query.SQLQuery.DataType;

public class Expression implements LogicExpression{
	public Expression(String property, Operator type){
		this.property = property;
		this.type = type;
		this.valueProperty = new Property(property);
	}
	public String getProperty() {
		return property;
	}
	public Operator getType() {
		return type;
	}
	public Expression setPropertyValue(Object value, DataType type){
		this.valueProperty.setValue(value);
		this.valueProperty.setType(type);
		return this;
	}
	public Property getValueProperty() {
		return valueProperty;
	}
	public Expression setQuientifier(char quientifier){
		this.quientifier = quientifier;
		return this;
	}
	public Expression setMarker(String marker){
		this.expressMarker = marker;
		return this;
	}

	protected static final char MARKER = '?';
	private String property;
	private Operator type;
	private Property valueProperty;
	private char quientifier = ' '; //Default is empty space
	private String expressMarker = String.valueOf(MARKER);
	
	public static List<Expression> createListFrom(String[] names, Operator type){
		List<Expression> resutls = new ArrayList<Expression>();
		for (String name : names) {
			resutls.add(new Expression(name, type));
		}
		return resutls;
	}
	
	public static Properties convertToProperties(List<Expression> coms){
		Properties props = new Properties();
		for (Expression compare : coms) {
			props.add(compare.getValueProperty());
		}
		return props;
	}
	public String toString(){
		if (Character.isWhitespace(quientifier) == false) {return  quientifier+ "." + getProperty() + " " + type.toString() + " " + getPropertyValue(valueProperty);}
		else {return getProperty() + " " + type.toString() + " " + getPropertyValue(valueProperty);}
	}
	private String getPropertyValue(Property val){
		if(val.getValue() != null && val.getType() != null){
			if(val.getType() == DataType.BOOL 
					|| val.getType() == DataType.INT
					|| val.getType() == DataType.DOUBLE
					|| val.getType() == DataType.FLOAT) {
				return val.getValue().toString();
			}else{
				return "'"+val.getValue().toString()+"'";
			}
		}else{
			return  String.valueOf(MARKER);
		}
	}
	@Override
	public String express() {
		if (Character.isWhitespace(quientifier) == false) {return quientifier+ "." + getProperty() + " " + type.toString() + " " + expressMarker;}
		else {return getProperty() + " " + type.toString() + " " + MARKER;}
	}
	@Override
	public Expression[] resolveCompares() {
		return new Expression[] {this};
	}
}