package edu.hw11.task3;

import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import org.objectweb.asm.Opcodes;

public class Task3 {
    private Task3() {
    }

    public static Class<?> createCustomClassWithImplementedMethod(String className, String methodName) {
        return new ByteBuddy()
            .subclass(Object.class)
            .name(className)
            .defineMethod(methodName, long.class, Modifier.PUBLIC)
            .withParameters(int.class)
            .intercept(new Implementation.Simple(getImplementation()))
            .make()
            .load(Task3.class.getClassLoader())
            .getLoaded();
    }

    private static ByteCodeAppender getImplementation() {
        return (methodVisitor, context, methodDescription) -> {
            methodVisitor.visitCode();

            methodVisitor.visitInsn(Opcodes.LCONST_0);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 2);

            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 4);

            methodVisitor.visitInsn(Opcodes.ICONST_0);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 6);

            Label l3 = new Label();
            Label l4 = new Label();
            methodVisitor.visitLabel(l3);
            methodVisitor.visitFrame(
                Opcodes.F_APPEND,
                3,
                new Object[] {Opcodes.LONG, Opcodes.LONG, Opcodes.INTEGER},
                0,
                null
            );
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 6);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, l4);

            methodVisitor.visitVarInsn(Opcodes.LLOAD, 4);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 7);

            methodVisitor.visitVarInsn(Opcodes.LLOAD, 2);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, 4);
            methodVisitor.visitInsn(Opcodes.LADD);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 4);

            methodVisitor.visitVarInsn(Opcodes.LLOAD, 7);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 2);

            methodVisitor.visitIincInsn(6, 1);
            methodVisitor.visitJumpInsn(Opcodes.GOTO, l3);

            methodVisitor.visitLabel(l4);
            methodVisitor.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, 2);
            methodVisitor.visitInsn(Opcodes.LRETURN);
            methodVisitor.visitEnd();

            return new ByteCodeAppender.Size(4, 9);
        };
    }
}
