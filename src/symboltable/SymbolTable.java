package symboltable;

import java.util.*;
import ast.Definition;

public class SymbolTable {
	// Visitar variable de la función, visitar parámetros de function type

	/**
	 * 0 -> global
	 * 1 -> local
	 */
	private int scope=0;
	private final List<Map<String,Definition>> table;
	public SymbolTable()  {
		table = new ArrayList<>();
		table.add(new HashMap<>());
	}

	/**
	 * Entramos en la definición de función actual
	 */
	public void set() {
		scope++;
		table.add(new HashMap<>());
	}

	/**
	 * Salimos de la definición de función actual
	 */
	public void reset() {
		table.remove(scope);
		scope--;
	}

	/**
	 * Insertamos la definición
	 * @param definition: Definición a insertar
	 * @return true si se inserto correctamente, false si ya existe
	 */
	public boolean insert(Definition definition) {
		if(table.get(scope).containsKey(definition.getName())){
			return false;
		}
		definition.setScope(scope);
		table.get(scope).put(definition.getName(), definition);
		return true;
	}

	/**
	 * Busca una definción en todos los scopes disponibles (0 o 1)
	 * @param id: nombre de la variable
	 * @return Definition de la variable si la encuentra, null en caso contrario
	 */
	public Definition find(String id) {
		for(int scope = table.size()-1 ; scope >= 0; scope--) {
			if (table.get(scope).containsKey(id)) {
				return table.get(scope).get(id);
			}
		}
		return null;
	}
	//package-protected for testing pourposes

	/**
	 * Métod usado para los tests. Sirve para buscar una variable en el Scope actual, sin tener en cuenta todos
	 * @param id: Nombre de la variable
	 * @return true si la variable existe en el Scope actual, false en caso contrario
	 */
	boolean findInCurrentScope(String id) {
		Map<String,Definition> currentScope = table.get(scope);
		return currentScope.containsKey(id);
	}
}
