package codegeneration;

import ast.types.CharType;
import ast.types.IntType;
import ast.types.NumberType;
import ast.Type;

import java.io.IOException;
import java.io.PrintWriter;

public class CodeGenerator {
    private PrintWriter out;
    private int labels = -1;

    public CodeGenerator(String outputFilename, String sourceFilename){
        try {
            out = new PrintWriter(outputFilename);
            commentSource(sourceFilename);
        } catch (IOException e){
            System.out.println("Error opening the file " + sourceFilename + ".");
            System.exit(-1);
        }
    }


    //Functions

    public void enter(int localBytes) {
        out.println("\tenter\t" + localBytes);
        out.flush();
    }

    public void call(String name){
        out.println("\tcall\t" + name);
        out.flush();
    }

    public void halt(){
        out.println("halt");
        out.flush();
    }

    public void ret(int returnBytes, int localBytes, int paramsBytes) {
        out.println("\tret\t" + returnBytes + ", " + localBytes + ", " + paramsBytes);
        out.flush();
    }

    public void pop(Type returnType) {
        out.println("\tpop" + returnType.suffix());
        out.flush();
    }

    // Input and Output

    public void out(Type type){
        out.println("\tout" + type.suffix());
        out.flush();
    }

    public void input(Type type){
        out.println("\tin" + type.suffix());
        out.flush();
    }

    // Load and Store

    public void load(Type type) {
        out.println("\tload" + type.suffix());
        out.flush();
    }

    public void store(Type type){
        out.println("\tstore" + type.suffix());
        out.flush();
    }


    // Push instructions

    public void push(double real_constant){
        out.println("\tpushf\t" + real_constant);
        out.flush();
    }

    public void push(int int_constant){
        out.println("\tpushi\t" + int_constant);
        out.flush();
    }

    public void push(char char_constant){
        out.println("\tpushb\t" + (int) char_constant);
        out.flush();
    }

    public void pushBP() {
        out.println("\tpush\tbp");
        out.flush();
    }

    public void pusha(int offset) {
        out.println("\tpusha\t" + offset);
        out.flush();
    }


    // Logical operations + UnaryNot

    public void logical(String operator) {
        switch (operator) {
            case "&&" -> and();
            case "||" -> or();
        }
        out.flush();
    }

    public void and() {
        out.println("\tand");
        out.flush();
    }

    public void or() {
        out.println("\tor");
        out.flush();
    }

    public void not() {
        out.println("\tnot");
        out.flush();
    }

    // Comparison operations

    public void comparison(String operator, Type type) {
        switch (operator) {
            case ">" -> gt(type);
            case "<" -> lt(type);
            case ">=" -> ge(type);
            case "<=" -> le(type);
            case "==" -> eq(type);
            case "!=" -> ne(type);
        }
        out.flush();
    }

    public void gt(Type type) {
        out.println("\tgt" + type.suffix());
        out.flush();
    }

    public void lt(Type type) {
        out.println("\tlt" + type.suffix());
        out.flush();
    }

    public void ge(Type type) {
        out.println("\tge" + type.suffix());
        out.flush();
    }

    public void le(Type type) {
        out.println("\tle" + type.suffix());
        out.flush();
    }

    public void eq(Type type) {
        out.println("\teq" + type.suffix());
        out.flush();
    }

    public void ne(Type type) {
        out.println("\tne" + type.suffix());
        out.flush();
    }

    //Arithmetic operations
    public void arithmetic(String operator, Type type) {
        switch (operator) {
            case "+" -> add(type);
            case "-" -> sub(type);
            case "*" -> mul(type);
            case "/" -> div(type);
            case "%" -> mod(type);
        }
        out.flush();
    }

    public void add(Type type) {
        out.println("\tadd" + type.suffix());
        out.flush();
    }

    public void sub(Type type) {
        out.println("\tsub" + type.suffix());
        out.flush();
    }

    public void mul(Type type) {
        out.println("\tmul" + type.suffix());
        out.flush();
    }

    public void div(Type type) {
        out.println("\tdiv" + type.suffix());
        out.flush();
    }

    public void mod(Type type) {
        out.println("\tmod" + type.suffix());
        out.flush();
    }


    // Conversion

    public void convertTo(Type from, Type to){
        if(from.suffix() ==  to.suffix())  return;

        if (from.equals(IntType.getInstance())) {
            if (to.equals(CharType.getInstance())) {
                out.println("\ti2b");
            } else if (to.equals(NumberType.getInstance())) {
                out.println("\ti2f");
            }
        } else if (from.equals(NumberType.getInstance())) {
            if (to.equals(IntType.getInstance())) {
                out.println("\tf2i");
            } else if (to.equals(CharType.getInstance())) {
                out.println("\tf2i");
                out.println("\ti2b");
            }
        } else if (from.equals(CharType.getInstance())) {
            if (to.equals(IntType.getInstance())) {
                out.println("\tb2i");
            } else if (to.equals(NumberType.getInstance())) {
                out.println("\tb2i");
                out.println("\ti2f");
            }
        }
        out.flush();
    }


    // Comments

    public void commentLine(int line) {
        out.println("\n#line\t" + line);
        out.flush();
    }

    public void printFunction(String name) {
        out.println("\n " + name + ":");
        out.flush();
    }

    public void comment(String message){
        out.println("\t' * " + message);
        out.flush();
    }

    public void commentSource(String source) {
        out.println("\n#source\t" + "\"" + source + "\"\n");
        out.flush();
    }

    public void callMain(){
        out.println("\n' Invocation to the main function");
        out.println("call main");
        out.flush();
    }

    // Labels

    public int getLabel(){
        labels++;
        return labels;
    }

    public void label(int number){
        out.println(" label" + number+":");
        out.flush();
    }

    // Jumps

    public void jz(int label) {
        out.println("\tjz\t" + "label" +label);
        out.flush();
    }

    public void jmp(int label) {
        out.println("\tjmp\t" + "label" +label);
        out.flush();
    }

    public void jnz(int label) {
        out.println("\tjnz\t" + "label" +label);
        out.flush();
    }


}