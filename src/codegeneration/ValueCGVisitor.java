package codegeneration;

public class ValueCGVisitor extends AbstractCGVisitor<Void, Void>{
    private AddressCGVisitor addressCGVisitor;
    public ValueCGVisitor(CodeGenerator codeGenerator) {
        super(codeGenerator);
    }

    public void setAddressCGVisitor(AddressCGVisitor addressCGVisitor) {
        this.addressCGVisitor = addressCGVisitor;
    }


}
