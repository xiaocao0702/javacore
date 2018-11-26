package com.demo.javacore.annotation.annotations.database;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static com.sun.mirror.util.DeclarationVisitors.NO_OP;
import static com.sun.mirror.util.DeclarationVisitors.getDeclarationScanner;

/**
 * Created by cao on 2018/11/7.
 */
public class TableCreationProcessorFactory implements AnnotationProcessorFactory {
    
    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }

    
    public Collection<String> supportedAnnotationTypes() {
        return Arrays.asList("annotations.database.DBTable","annotations.database.Constraints","annotations.database.SQLString","annotations.database.SQLInteger");
    }

    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
        return new TableCreationProcessor(env);
    }

    private class TableCreationProcessor implements AnnotationProcessor {
        private final AnnotationProcessorEnvironment env;
        private String sql = "";

        public TableCreationProcessor(AnnotationProcessorEnvironment env) {
            this.env = env;
        }

        public void process() {
            for (TypeDeclaration typeDeclaration : env.getSpecifiedTypeDeclarations()) {
                typeDeclaration.accept(getDeclarationScanner(new TableCreationVisitor(), NO_OP));
                sql = sql.substring(0, sql.length() - 1) + ");";
                System.out.println("creation SQL is :\n" + sql);
                sql = "";
            }

        }

        private class TableCreationVisitor extends SimpleDeclarationVisitor {
            public void visitClassDeclaration(ClassDeclaration classDeclaration) {
                DBTable dbTable = classDeclaration.getAnnotation(DBTable.class);
                if(dbTable != null) {
                    sql += "CREATE TABLE ";
                    sql += dbTable.name().length() < 1 ? classDeclaration.getSimpleName() : dbTable.name();
                    sql += "(";
                }
            }

            public void visitFieldDeclaration(FieldDeclaration fieldDeclaration) {
                String columnName = "";
                if(fieldDeclaration.getAnnotation(SQLInteger.class) != null) {
                    SQLInteger sInt = fieldDeclaration.getAnnotation(SQLInteger.class);
                    if(sInt.name().length() < 1)
                        columnName = fieldDeclaration.getSimpleName();
                    else
                        columnName = sInt.name();
                    sql += "\n " + columnName + " INT" + getConstraints(sInt.constraints()) + ", ";
                }
                if(fieldDeclaration.getAnnotation(SQLString.class) != null) {
                    SQLString sStr = fieldDeclaration.getAnnotation(SQLString.class);
                    if(sStr.name().length() < 1)
                        columnName = fieldDeclaration.getSimpleName();
                    else
                        columnName = sStr.name();
                    sql += "\n  " + columnName + "VARCHAR(" + sStr.value() + ") " + getConstraints(sStr.constraints()) + ", ";
                }

            }

            private String getConstraints(Constraints constraints) {
                String cons = "";
                if(constraints.primaryKey())
                    cons = " PRIMARY KEY";
                if(!constraints.allowNull())
                    cons = " NOT NULL";
                if(constraints.unique())
                    cons = " UNIQUE";
                return cons;
            }
        }
    }
}
