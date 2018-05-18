package com.it.soul.lab.sql.query;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.it.soul.lab.sql.query.models.Compare;

public class SQLUpdateQuery extends SQLSelectQuery{
	
	private StringBuffer paramBuffer;
	private StringBuffer whereBuffer;
	
	public SQLUpdateQuery() {
		this.pqlBuffer = new StringBuffer("UPDATE ");
		this.paramBuffer = new StringBuffer(" ");
		this.whereBuffer = new StringBuffer(" ");
	}
	
	@Override
	public String queryString() throws IllegalArgumentException {
		if(getTableName() == null || getTableName().trim().equals("")){
			throw new IllegalArgumentException("Parameter Table must not be Null OR Empty.");
		}
		if(isAllParamEmpty(getColumns())){
			throw new IllegalArgumentException("All Empty Parameters!!! You nuts (:D");
		}
		return pqlBuffer.toString() + paramBuffer.toString() + whereBuffer.toString();
	}
	
	@Override
	protected void prepareColumns(String[] columns) {
		if(getColumns() != null && getColumns().length > 0){
			int count = 0;
			for(String column : getColumns()){
				if(column.trim().equals("")){continue;}
				if(count++ != 0){paramBuffer.append(", ");}
				paramBuffer.append( column + " = " + MARKER);
			}
		}
	}
	
	@Override
	protected void prepareTableName(String name) {
		pqlBuffer.append(getTableName() + " SET");
	}
	
	@Override
	protected void prepareWhereParams(String[] whereParams) {
		prepareWhereParams(Compare.createListFrom(whereParams, ComparisonType.IsEqual));
	}
	
	@Override
	protected void prepareWhereParams(List<Compare> whereParams) {
		if(whereParams != null 
				&& whereParams.size() > 0
				&& !isAllParamEmpty(whereParams.toArray())){
			
			if(whereBuffer.length() > 0){
				whereBuffer.append("WHERE ");
				int count = 0;
				for(Compare param : whereParams){
					if(param.getProperty().trim().equals("")){continue;}
					if(count++ != 0){whereBuffer.append( " " + getLogic().name() + " ");}
					whereBuffer.append( param.getProperty() + " " + param.getType().toString() + " " + MARKER);
				}
			}
		}
	}
	
	public static String createUpdateQuery(String tableName, String[]setParams, Logic whereLogic, Map<String,ComparisonType> whereParams){
		
		//Checking Illegal Arguments
		try{
			if(tableName == null || tableName.trim().equals("")){
				throw new IllegalArgumentException("Parameter 'tableName' must not be Null OR Empty.");
			}
			if(isAllParamEmpty(setParams)){
				throw new IllegalArgumentException("All Empty Parameters!!! You nuts (:D");
			}
		}catch(IllegalArgumentException iex){
			throw iex;
		}
		
		StringBuffer pqlBuffer = new StringBuffer("UPDATE " + tableName + " SET ");
		if(setParams != null && setParams.length > 0){
			int count = 0;
			for(String str : setParams){
				if(str.trim().equals("")){continue;}
				if(count++ != 0){pqlBuffer.append(", ");}
				pqlBuffer.append( str + " = " + MARKER);
			}
		}
		
		if(whereParams != null 
				&& whereParams.size() > 0
				&& !isAllParamEmpty(whereParams.keySet().toArray())){
			
			if(pqlBuffer.length() > 0){
				pqlBuffer.append(" WHERE ");
				int count = 0;
				for(Entry<String,ComparisonType> param : whereParams.entrySet()){
					if(param.getKey().trim().equals("")){continue;}
					if(count++ != 0){pqlBuffer.append( " " + whereLogic.name() + " ");}
					pqlBuffer.append( param.getKey() + param.getValue().toString() + MARKER);
				}
			}
		}
		
		return pqlBuffer.toString();
	}
	
}