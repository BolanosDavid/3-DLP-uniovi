package symboltable;

import ast.definitions.VarDefinition;

public class SymbolTableTest {
		
	public void testInsert() {
		SymbolTable st = new SymbolTable();
		VarDefinition definition = new VarDefinition(0, 0, "a", null);
		assert st.insert(definition);
		assert definition.getScope()==0;
		assert !st.insert(definition);
		st.set();
		VarDefinition definition2 = new VarDefinition(0, 0, "a", null);
		assert st.insert(definition2);
		assert definition2.getScope()==1;
		assert !st.insert(definition2);
		st.reset();
		assert !st.insert(definition);
	}
	
	public void testFind() {
		SymbolTable st = new SymbolTable();
		VarDefinition varDefinition = new VarDefinition(0, 0, "a", null);
		assert st.insert(varDefinition);
		assert st.find("a")!=null;
		assert st.find("b")==null;
		st.set();
		VarDefinition varDefinition2 = new VarDefinition(0, 0, "b", null);
		assert st.insert(varDefinition2);
		assert st.find("b")!=null;
		assert st.find("a")!=null;
		assert st.find("c")==null;
		st.reset();
		assert st.find("a")!=null;
		assert st.find("b")==null;

		assert st.find("c")==null;
		VarDefinition varDefinition3 = new VarDefinition(0, 0, "c", null);
		assert st.insert(varDefinition3);
		assert st.find("c")!=null;
		st.set();
		VarDefinition varDefinition4 = new VarDefinition(0, 0, "d", null);
		assert st.insert(varDefinition4);
		assert st.find("c")!=null;
		assert st.find("a")!=null;
		assert st.find("d")!=null;
		assert st.find("b")==null;
		st.reset();
		assert st.find("a")!=null;
		assert st.find("c")!=null;
		assert st.find("b")==null;
		assert st.find("d")==null;

	}

	public void testFindInCurrentScope() {
		SymbolTable st = new SymbolTable();
		VarDefinition varDefinition = new VarDefinition(0, 0, "a", null);
		assert st.insert(varDefinition);
		assert st.findInCurrentScope("a");
		assert !st.findInCurrentScope("b");
		st.set();
		VarDefinition varDefinition2 = new VarDefinition(0, 0, "b", null);
		assert st.insert(varDefinition2);
		assert st.findInCurrentScope("b");
		assert !st.findInCurrentScope("a");
		assert !st.findInCurrentScope("c");
		st.reset();
		assert st.findInCurrentScope("a");
		assert !st.findInCurrentScope("b");		
	}
	
	public static void main(String[] args) {
		SymbolTableTest test = new SymbolTableTest();
		test.testInsert();
		test.testFind();
		test.testFindInCurrentScope();
	}
	
}
